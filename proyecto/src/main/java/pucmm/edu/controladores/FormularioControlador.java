package pucmm.edu.controladores;

import io.javalin.http.Handler;
import pucmm.edu.encapsulaciones.Formulario;
import pucmm.edu.util.Path;
import pucmm.edu.util.RequestUtil;
import pucmm.edu.util.RolesApp;
import pucmm.edu.util.ViewUtil;
import java.util.Map;

import static pucmm.edu.Main.*;
import static pucmm.edu.Main.usuarioServices;

public class FormularioControlador {
    public static Handler serveVerPage = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        Formulario form = formularioServices.find(RequestUtil.getParamId(ctx));
        if(form != null){
            if(form.getUser().getUsuario().equalsIgnoreCase(RequestUtil.getSessionCurrentUser(ctx)) || usuarioServices.getUsuarioByUsername(RequestUtil.getSessionCurrentUser(ctx)).getRol().equals(RolesApp.ROLE_ADMIN)){
                model.put("form", form);
                ctx.render(Path.Template.VER, model);
            }else{
                ctx.sessionAttribute("formularioFailed", true);
                ctx.sessionAttribute("mensaje","No puede ver este formulario porque no fue usted quien lo envio al servidor y usted no es adminsitrador.");
                ctx.redirect(Path.Web.FETCH_FORMULARIOS);
            }
        } else{
            ctx.sessionAttribute("formularioFailed", true);
            ctx.sessionAttribute("mensaje","Formulario no encontrado.");
            ctx.redirect(Path.Web.FETCH_FORMULARIOS);
        }
    };

    public static Handler fetchAllFormularios = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        if(usuarioServices.getUsuarioByUsername(RequestUtil.getSessionCurrentUser(ctx)).getRol().equals(RolesApp.ROLE_ADMIN)){
            model.put("forms", formularioServices.findAll());
        }else{
            model.put("forms", formularioServices.getFormsByUser(usuarioServices.getUsuarioByUsername(RequestUtil.getSessionCurrentUser(ctx)).getId()));
        }
        model.put("formularioFailed", ctx.sessionAttribute("formularioFailed"));
        model.put("mensaje", ctx.sessionAttribute("mensaje"));
        ctx.sessionAttribute("formularioFailed", null);
        ctx.sessionAttribute("mensaje",null);
        ctx.render(Path.Template.FETCH_FORMULARIO, model);
    };
}
