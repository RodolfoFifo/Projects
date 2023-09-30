package pucmm.edu.controladores;
import static pucmm.edu.Main.*;

import io.javalin.http.Handler;
import io.javalin.http.UploadedFile;
import org.jasypt.util.text.BasicTextEncryptor;
import pucmm.edu.encapsulaciones.Foto;
import pucmm.edu.encapsulaciones.Usuario;
import pucmm.edu.util.*;

import java.util.Base64;
import java.util.Map;

public class UsuarioControlador {
    public static Handler serveCrearPage = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        Usuario user = usuarioServices.find(RequestUtil.getParamId(ctx));
        model.put("usuarioFailed", ctx.sessionAttribute("usuarioFailed"));
        model.put("usuarioSucceeded", ctx.sessionAttribute("usuarioSucceeded"));
        model.put("mensaje", ctx.sessionAttribute("mensaje"));
        ctx.sessionAttribute("usuarioFailed", null);
        ctx.sessionAttribute("usuarioSucceeded", null);
        ctx.sessionAttribute("mensaje",null);
        if(user != null){
            model.put("user", user);
            ctx.render(Path.Template.CREAR_EDITAR_USUARIO, model);
        } else{
            ctx.render(Path.Template.CREAR_EDITAR_USUARIO, model);
        }
    };

    public static Handler fetchAllUsuarios = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        model.put("users", usuarioServices.findAll());
        model.put("usuarioFailed", ctx.sessionAttribute("usuarioFailed"));
        model.put("usuarioSucceeded", ctx.sessionAttribute("usuarioSucceeded"));
        model.put("mensaje", ctx.sessionAttribute("mensaje"));
        ctx.sessionAttribute("usuarioFailed", null);
        ctx.sessionAttribute("usuarioSucceeded", null);
        ctx.sessionAttribute("mensaje",null);
        ctx.render(Path.Template.FETCH_USUARIO, model);
    };

    public static Handler handleEliminar = ctx -> {
        String username = RequestUtil.getSessionCurrentUser(ctx);
        Usuario user = usuarioServices.find(RequestUtil.getParamId(ctx));
        if(username != null){
            if (user == null) {
                ctx.sessionAttribute("usuarioFailed", true);
                ctx.sessionAttribute("usuarioSucceeded", false);
                ctx.sessionAttribute("mensaje", "Este usuario no existe");
            } else if(username.equalsIgnoreCase(user.getUsuario())){
                ctx.sessionAttribute("usuarioFailed", true);
                ctx.sessionAttribute("usuarioSucceeded", false);
                ctx.sessionAttribute("mensaje", "No puede eliminar su usuario");
                ctx.redirect(Path.Web.FETCH_USUARIOS);
            }else{
                Foto foto = user.getFoto();
                if(usuarioServices.eliminarById(RequestUtil.getParamId(ctx))){
                    if(foto != null){
                        fotoServices.eliminar(foto);
                    }
                    ctx.sessionAttribute("usuarioFailed", false);
                    ctx.sessionAttribute("usuarioSucceeded", true);
                    ctx.sessionAttribute("mensaje", "Usuario eliminado exitosamente");
                }else{
                    ctx.sessionAttribute("usuarioFailed", true);
                    ctx.sessionAttribute("usuarioSucceeded", false);
                    ctx.sessionAttribute("mensaje", "No se pudo eliminar el usuario debido a un error inesperado");
                }
            }
        }
        ctx.redirect(Path.Web.FETCH_USUARIOS);
    };

    public static Handler handleUsuarioPost = ctx -> {
        Usuario user = usuarioServices.find(RequestUtil.getParamId(ctx));
//      EDITAR USUARIO
        if(user != null){
            String nombre_usuario = user.getUsuario();
            user.setNombre(RequestUtil.getQueryNombre(ctx));
            user.setClave(RequestUtil.getQueryPassword(ctx));
            user.setUsuario(RequestUtil.getQueryUsername(ctx));
            if(RequestUtil.getQueryRol(ctx).equalsIgnoreCase("administrador")){
                user.setRol(RolesApp.ROLE_ADMIN);
            }else if(RequestUtil.getQueryRol(ctx).equalsIgnoreCase("autor")){
                user.setRol(RolesApp.ROLE_USUARIO);
            }
            UploadedFile pic = RequestUtil.getUploadedFile(ctx);
            if(pic != null && !pic.filename().equalsIgnoreCase("")){
                try {
                    byte[] bytes = pic.content().readAllBytes();
                    String encodedString = Base64.getEncoder().encodeToString(bytes);
                    Foto foto = new Foto(pic.filename(), pic.contentType(), encodedString);
                    fotoServices.crear(foto);
                    user.setFoto(foto);
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            if(usuarioServices.getUsuarioByUsername(user.getUsuario())== null || user.getUsuario().equalsIgnoreCase(nombre_usuario)){
                if(usuarioServices.editar(user) != null){
                    if(nombre_usuario.equalsIgnoreCase(RequestUtil.getSessionCurrentUser(ctx))){
                        ctx.sessionAttribute("currentUser", user.getUsuario());
                        if(ctx.cookie("user") != null && ctx.cookie("pwd") != null){
                            BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
                            textEncryptor.setPasswordCharArray("some-random-data".toCharArray());
                            ctx.cookie("user", textEncryptor.encrypt(user.getUsuario()), 604800);
                            ctx.cookie("pwd", textEncryptor.encrypt(user.getClave()), 604800);
                        }
                    }
                    ctx.sessionAttribute("usuarioSucceeded", true);
                    ctx.sessionAttribute("usuarioFailed", false);
                    ctx.sessionAttribute("mensaje", "Usuario editado exitosamente");
                    ctx.redirect(Path.Web.CREAR_EDITAR_USUARIO+"?id="+RequestUtil.getParamId(ctx));
                }else{
                    ctx.sessionAttribute("usuarioFailed", true);
                    ctx.sessionAttribute("usuarioSucceeded", false);
                    ctx.sessionAttribute("mensaje", "No se pudo editar el usuario debido a un error inesperado");
                    ctx.redirect(Path.Web.CREAR_EDITAR_USUARIO+"?id="+RequestUtil.getParamId(ctx));
                }
            }else{
                ctx.sessionAttribute("usuarioFailed", true);
                ctx.sessionAttribute("usuarioSucceeded", false);
                ctx.sessionAttribute("mensaje", "No se pudo editar el usuario porque el nombre de usuario ya existe");
                ctx.redirect(Path.Web.CREAR_EDITAR_USUARIO+"?id="+RequestUtil.getParamId(ctx));
            }
        }
//      CREAR USUARIO
        else{
            String nombre = RequestUtil.getQueryNombre(ctx);
            String username = RequestUtil.getQueryUsername(ctx).toLowerCase();
            String password = RequestUtil.getQueryPassword(ctx);
            RolesApp rol = null;
            if(RequestUtil.getQueryRol(ctx).equalsIgnoreCase("administrador")){
                rol = RolesApp.ROLE_ADMIN;
            }else if(RequestUtil.getQueryRol(ctx).equalsIgnoreCase("autor")){
                rol = RolesApp.ROLE_USUARIO;
            }
            if(usuarioServices.getUsuarioByUsername(username)== null){
                UploadedFile pic = RequestUtil.getUploadedFile(ctx);
                user = new Usuario(nombre, username, password, rol);
                if(pic != null && !pic.filename().equalsIgnoreCase("")){
                    try {
                        byte[] bytes = pic.content().readAllBytes();
                        String encodedString = Base64.getEncoder().encodeToString(bytes);
                        Foto foto = new Foto(pic.filename(), pic.contentType(), encodedString);
                        fotoServices.crear(foto);
                        user.setFoto(foto);
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
                user = usuarioServices.crear(user);
                if(user != null){
                    ctx.sessionAttribute("usuarioSucceeded", true);
                    ctx.sessionAttribute("usuarioFailed", false);
                    ctx.sessionAttribute("mensaje", "Usuario creado exitosamente");
                    ctx.redirect(Path.Web.FETCH_USUARIOS);
                } else{
                    ctx.sessionAttribute("usuarioFailed", true);
                    ctx.sessionAttribute("usuarioSucceeded", false);
                    ctx.sessionAttribute("mensaje", "No se pudo crear el usuario debido a un error inesperado");
                    ctx.redirect(Path.Web.CREAR_EDITAR_USUARIO);
                }
            }else{
                ctx.sessionAttribute("usuarioFailed", true);
                ctx.sessionAttribute("usuarioSucceeded", false);
                ctx.sessionAttribute("mensaje", "No se pudo crear el usuario porque el nombre de usuario ya existe");
                ctx.redirect(Path.Web.CREAR_EDITAR_USUARIO);
            }
        }
    };
}