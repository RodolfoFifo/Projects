package pucmm.edu.util;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.Map;
import io.javalin.http.Handler;
import pucmm.edu.encapsulaciones.Foto;

import static pucmm.edu.Main.*;

import static pucmm.edu.util.RequestUtil.*;

public class ViewUtil {
    public static Map<String, Object> baseModel(Context ctx) {
        Map<String, Object> model = new HashMap<>();
        model.put("currentUser", getSessionCurrentUser(ctx));
        if(getSessionCurrentUser(ctx) != null){
            Foto foto = usuarioServices.getUsuarioByUsername(getSessionCurrentUser(ctx)).getFoto();
            if(foto != null){
                model.put("fotoperfil", "data:"+foto.getMimeType()+";base64,"+foto.getFotoBase64());
            }
        }
        return model;
    }
    public static Handler error400 = ctx -> {
        ctx.render(Path.Template.ERROR_400, baseModel(ctx));
    };
    public static Handler error401 = ctx -> {
        ctx.render(Path.Template.ERROR_401, baseModel(ctx));
    };
    public static Handler error403 = ctx -> {
        ctx.render(Path.Template.ERROR_403, baseModel(ctx));
    };
    public static Handler error404 = ctx -> {
        ctx.render(Path.Template.ERROR_404, baseModel(ctx));
    };
    public static Handler error500 = ctx -> {
        ctx.render(Path.Template.ERROR_500, baseModel(ctx));
    };
    public static Handler error503 = ctx -> {
        ctx.render(Path.Template.ERROR_503, baseModel(ctx));
    };
}
