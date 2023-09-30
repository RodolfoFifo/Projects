package pucmm.edu.controladores;

import io.javalin.http.Handler;
import pucmm.edu.encapsulaciones.Formulario;
import pucmm.edu.encapsulaciones.Usuario;
import pucmm.edu.util.Path;
import pucmm.edu.util.RolesApp;
import pucmm.edu.util.ViewUtil;

import java.util.List;
import java.util.Map;

import static pucmm.edu.Main.formularioServices;
import static pucmm.edu.Main.usuarioServices;

public class IndexControlador {
    public static Handler serveIndexPage = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        List<Usuario> users = usuarioServices.findAll();
        List<Formulario> forms = formularioServices.findAll();
        long admins = users.stream().filter(p -> p.getRol().equals(RolesApp.ROLE_ADMIN)).count();
        long usuarios = users.size();
        model.put("cantUsers",usuarios);
        model.put("cantAdmins", admins);
        model.put("cantAutor", usuarios-admins);
        model.put("cantForms", forms.size());
        model.put("forms", forms);
        ctx.render(Path.Template.INDEX, model);
    };
}
