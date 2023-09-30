package pucmm.edu;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.javalin.http.staticfiles.Location;
import io.javalin.Javalin;
import io.javalin.rendering.JavalinRenderer;
import io.javalin.rendering.template.JavalinVelocity;
import javalinjwt.JWTProvider;
import io.javalin.openapi.JsonSchemaLoader;
import io.javalin.openapi.JsonSchemaResource;
import io.javalin.openapi.plugin.OpenApiPlugin;
import io.javalin.openapi.plugin.OpenApiPluginConfiguration;
import io.javalin.openapi.plugin.redoc.ReDocConfiguration;
import io.javalin.openapi.plugin.redoc.ReDocPlugin;
import io.javalin.openapi.plugin.swagger.SwaggerConfiguration;
import io.javalin.openapi.plugin.swagger.SwaggerPlugin;
import pucmm.edu.controladores.FormularioControlador;
import pucmm.edu.controladores.IndexControlador;
import pucmm.edu.controladores.LoginControlador;
import pucmm.edu.controladores.UsuarioControlador;
import pucmm.edu.encapsulaciones.Formulario;
import pucmm.edu.encapsulaciones.Foto;
import pucmm.edu.encapsulaciones.Usuario;
import pucmm.edu.grpc.FormularioServicesGrpc;
import pucmm.edu.rest.ProviderExample;
import pucmm.edu.rest.RestControlador;
import pucmm.edu.servicios.FormularioServices;
import pucmm.edu.servicios.FotoServices;
import pucmm.edu.servicios.UsuarioServices;
import pucmm.edu.util.RolesApp;
import pucmm.edu.util.ViewUtil;
import pucmm.edu.util.Path;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Main {
    public static UsuarioServices usuarioServices;
    public static FotoServices fotoServices;
    public static FormularioServices formularioServices;
    public static String usuarioActual;
    public static JWTProvider<Usuario> provider;

    public static void main(String[] args) throws IOException, InterruptedException {
        provider = ProviderExample.createHMAC512();

        JavalinRenderer.register(new JavalinVelocity(), ".vm");
        usuarioServices = UsuarioServices.getInstancia();
        fotoServices = FotoServices.getInstancia();
        formularioServices = FormularioServices.getInstancia();
        if(usuarioServices.findAll().isEmpty()){
            usuarioServices.crear(new Usuario("admin", "admin", "admin", RolesApp.ROLE_ADMIN));
            usuarioServices.crear(new Usuario("user", "user", "user", RolesApp.ROLE_USUARIO));
        }
        //Creando la instancia del servidor y configurando.
        Javalin app = Javalin.create(config ->{
            //configurando los documentos estaticos.
            config.staticFiles.add(staticFileConfig -> {
                staticFileConfig.hostedPath = "/";
                staticFileConfig.directory = "/public";
                staticFileConfig.location = Location.CLASSPATH;
                staticFileConfig.precompress=false;
                staticFileConfig.aliasCheck=null;
            });

            //Habilitando el CORS. Ver: https://javalin.io/plugins/cors#getting-started para más opciones.
            config.plugins.enableCors(corsContainer -> {
                corsContainer.add(corsPluginConfig -> {
                    corsPluginConfig.anyHost();
                });
            });

            //habilitando el plugins de las rutas definidas.
            config.plugins.enableRouteOverview("/rutas");

            //Configurando el servicio SOAP en nuestro proyecto.
            //config.jetty.server(() -> new SoapControlador().agregarWebServicesSoap());

            //
            config.plugins.register(new OpenApiPlugin(
                    new OpenApiPluginConfiguration()
                            .withDocumentationPath("/openapi")
                            .withDefinitionConfiguration((version, definition) -> definition
                                            .withOpenApiInfo((openApiInfo) -> {
                                                openApiInfo.setTitle("Awesome App");
                                                openApiInfo.setVersion("1.0.0");
                                            })
                                            .withServer((openApiServer) -> {
                                                openApiServer.setUrl(("http://localhost:{port}{basePath}/" + version + "/"));
                                                openApiServer.setDescription("Server description goes here");
                                                openApiServer.addVariable("port", "8080", new String[] { "7070", "8080" }, "Port of the server");
                                                openApiServer.addVariable("basePath", "", new String[] { "", "v1" }, "Base path of the server");
                                            })
                                           /* // Based on official example: https://swagger.io/docs/specification/authentication/oauth2/
                                            .withSecurity(new SecurityConfiguration()
                                                    .withSecurityScheme("BasicAuth", new BasicAuth())
                                            )
                                            .withDefinitionProcessor(content -> { // you can add whatever you want to this document using your favourite json api
                                                content.set("test", new TextNode("Value"));
                                                return content.toPrettyString();
                                            }))*/
                            )
            ));

            SwaggerConfiguration swaggerConfiguration = new SwaggerConfiguration();
            //swaggerConfiguration.setDocumentationPath(deprecatedDocsPath);
            config.plugins.register(new SwaggerPlugin(swaggerConfiguration));

            ReDocConfiguration reDocConfiguration = new ReDocConfiguration();
            //reDocConfiguration.setDocumentationPath(deprecatedDocsPath);
            config.plugins.register(new ReDocPlugin(reDocConfiguration));

            for (JsonSchemaResource generatedJsonSchema : new JsonSchemaLoader().loadGeneratedSchemes()) {
                System.out.println(generatedJsonSchema.getName());
                System.out.println(generatedJsonSchema.getContentAsString());
            }


        });

        //
        app.start(getHerokuAssignedPort());

        app.cfg.accessManager((handler, ctx, permittedRoles) -> {
            //para obtener el usuario estaré utilizando el contexto de sesion.
            final String username = ctx.sessionAttribute("currentUser");

            if ((permittedRoles.isEmpty() && username == null) || ctx.path().startsWith("/crear") || ctx.path().startsWith("/tabla") || ctx.path().startsWith("/offline")) {
                handler.handle(ctx);
                return;
            }
            //validando si existe el usuario.
            if (username == null) {
                ctx.redirect(Path.Web.LOGIN);
                return;
            }
            //buscando el permiso del usuario.
            RolesApp rol = null;
            try {
                rol = usuarioServices.getUsuarioByUsername(username).getRol();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            if (permittedRoles.contains(rol) || permittedRoles.contains(RolesApp.ROLE_USUARIO)) {
                handler.handle(ctx);
                return;
            } else if (ctx.path().startsWith("/login") && ctx.sessionAttribute("currentUser") != null) {
                ctx.redirect(Path.Web.INDEX);
            }else{
                ctx.status(401);
            }
        });

        app.routes(() -> {
            path("/", () -> {
                get("/offline", ctx -> {
                    Map<String, Object> modelo = ViewUtil.baseModel(ctx);
                    ctx.render("/public/page-error-internet.vm", modelo);
                });
                get("/tabla", ctx -> {
                    Map<String, Object> modelo = ViewUtil.baseModel(ctx);
                    ctx.render("/public/tablaFormularioOffline.vm", modelo);
                });
                get("/crear", ctx -> {
                    Map<String, Object> modelo = ViewUtil.baseModel(ctx);
                    ctx.render("/public/formularioOffline.vm", modelo);
                });
//          INDEX
                get(Path.Web.INDEX, IndexControlador.serveIndexPage, RolesApp.ROLE_USUARIO);
//          GET CRUD USUARIOS
                get(Path.Web.FETCH_USUARIOS, UsuarioControlador.fetchAllUsuarios, RolesApp.ROLE_ADMIN);
//          GET CREAR-EDITAR USUARIO
                get(Path.Web.CREAR_EDITAR_USUARIO, UsuarioControlador.serveCrearPage, RolesApp.ROLE_ADMIN);
//          POST CREAR-EDITAR USER
                post(Path.Web.CREAR_EDITAR_USUARIO, UsuarioControlador.handleUsuarioPost, RolesApp.ROLE_ADMIN);
//          GET ELIMINAR USER
                get(Path.Web.ELIMINAR_USUARIO, UsuarioControlador.handleEliminar, RolesApp.ROLE_ADMIN);
//              LOGIN
                get(Path.Web.LOGIN, LoginControlador.serveLoginPage);
                post(Path.Web.LOGIN, LoginControlador.handleLoginPost);
                post(Path.Web.LOGOUT, LoginControlador.handleLogoutPost, RolesApp.ROLE_USUARIO);
//          GET VER FORMULARIO
                get(Path.Web.VER, FormularioControlador.serveVerPage, RolesApp.ROLE_USUARIO);
//          GET FORMULARIOS
                get(Path.Web.FETCH_FORMULARIOS, FormularioControlador.fetchAllFormularios, RolesApp.ROLE_USUARIO);
            });

            path("/rest", () -> {
//          LOGIN / JWT GENERATE
                post("/login", RestControlador.loginPost);
//          FORM X USER
                get("/form", RestControlador.formsXuser);
//          CREAR FORM
                post("/form", RestControlador.crearForm);
            });
        });
        app.error(401, ViewUtil.error401);
        app.error(404, ViewUtil.error404);
        app.error(403, ViewUtil.error403);
        app.error(400, ViewUtil.error400);
        app.error(503, ViewUtil.error503);
        app.error(500, ViewUtil.error500);

        app.wsBefore("/mensajeServidor", wsHandler -> {
            System.out.println("Filtro para WS antes de la llamada ws");
            //ejecutar cualquier evento antes...
        });

        /**
         * Definición del WS en Javalin en contexto
         */
        app.ws("/mensajeServidor", ws -> {

            ws.onConnect(ctx -> {
                System.out.println("Conexión Iniciada - "+ctx.getSessionId());
            });

            ws.onMessage(ctx -> {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(ctx.message());
                Foto foto = fotoServices.crear(new Foto(jsonNode.get("nombre").toString().replaceAll("\"", ""), jsonNode.get("mimetype").toString().replaceAll("\"", ""), jsonNode.get("base64").toString().replaceAll("\"", "")));

                Formulario form = formularioServices.crear(new Formulario(jsonNode.get("nombre").toString().replaceAll("\"", ""), jsonNode.get("sector").toString().replaceAll("\"", ""), jsonNode.get("nivel").toString().replaceAll("\"", ""), usuarioServices.getUsuarioByUsername(usuarioActual), jsonNode.get("latitude").toString(), jsonNode.get("longitude").toString(), jsonNode.get("accuracy").toString(), foto));

                if(form != null){
                    ctx.session.getRemote().sendString(jsonNode.get("id").toString());
                } else{
                    ctx.session.getRemote().sendString("0");
                }
            });

            ws.onBinaryMessage(ctx -> {
                System.out.println("Mensaje: "+ctx.data().length);;
            });

            ws.onClose(ctx -> {
                System.out.println("Conexión Cerrada - "+ctx.getSessionId());
            });

            ws.onError(ctx -> {
                System.out.println("Ocurrió un error en el WS");
            });
        }, RolesApp.ROLE_USUARIO);

        System.out.println("Servidor gRPC Implementado en Java - JConf Dominicana 2021");

        //Puerto del servidor.
        int port = 50051;

        //Inicializando el servidor
        Server server = ServerBuilder.forPort(port)
                .addService(new FormularioServicesGrpc())// indicando el servicio registrado.
                .build()
                .start();
        System.out.println("Servidor gRPC iniciando y escuchando en " + port);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("Cerrando servidor por la JVM ");
            if (server != null) {
                server.shutdown();
            }
            System.err.println("Servidor abajo!...");
        }));
        server.awaitTermination();
    }

    /**
     * Metodo para indicar el puerto en Heroku
     * @return
     */
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 7000; //Retorna el puerto por defecto en caso de no estar en Heroku.
    }

    /*private static OpenApiOptions getOpenApiOptions() {
        Info applicationInfo = new Info()
                .version("1.0")
                .description("My Application");
        return new OpenApiOptions(applicationInfo).path("/openapi").swagger(new SwaggerOptions("/openapi-ui"));
    }*/

}