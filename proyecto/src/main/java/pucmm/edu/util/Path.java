package pucmm.edu.util;

public class Path {
    public static class Web {
        public static final String INDEX = "/";
        // FORMULARIOS
        public static final String FETCH_FORMULARIOS = "/formularios";
        public static final String VER = "/formulario";
        // USUARIOS
        public static final String FETCH_USUARIOS = "/usuarios";
        public static final String CREAR_EDITAR_USUARIO = "/usuario";
        public static final String ELIMINAR_USUARIO = "/eliminarUsuario";
        // LOGIN/OUT
        public static final String LOGIN = "/login";
        public static final String LOGOUT = "/logout";
    }

    public static class Template {
        public static final String LOGIN = "/public/login.vm";
        public static final String VER = "/public/formulario.vm";
        public static final String INDEX = "/public/index.vm";
        public static final String LAYOUT = "/public/layout.vm";
        public static final String ERROR_400 = "/public/page-error-400.vm";
        public static final String ERROR_401 = "/public/page-error-401.vm";
        public static final String ERROR_403 = "/public/page-error-403.vm";
        public static final String ERROR_404 = "/public/page-error-404.vm";
        public static final String ERROR_500 = "/public/page-error-500.vm";
        public static final String ERROR_503 = "/public/page-error-503.vm";
        public static final String CREAR_EDITAR_USUARIO = "/public/usuario.vm";
        public static final String FETCH_FORMULARIO = "/public/tablaFormulario.vm";
        public static final String FETCH_USUARIO = "/public/tablaUsuario.vm";
    }
}
