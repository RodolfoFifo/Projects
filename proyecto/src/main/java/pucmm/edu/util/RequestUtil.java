package pucmm.edu.util;
import io.javalin.http.Context;
import io.javalin.http.UploadedFile;

public class RequestUtil {
    public static String getSessionCurrentUser(Context ctx) {
        return (String) ctx.sessionAttribute("currentUser");
    }
    public static boolean removeSessionAttrLoggedOut(Context ctx) {
        String loggedOut = ctx.sessionAttribute("loggedOut");
        ctx.sessionAttribute("loggedOut", null);
        return loggedOut != null;
    }
    public static String getQueryUsername(Context ctx) {
        return ctx.formParam("val-usuario");
    }

    public static String getQueryNombre(Context ctx) {
        return ctx.formParam("val-nombre");
    }

    public static String getQueryPassword(Context ctx) {
        return ctx.formParam("val-password");
    }
    public static String getQueryLoginUsername(Context ctx) {
        return ctx.formParam("login-usuario");
    }
    public static String getQueryLoginPassword(Context ctx) {
        return ctx.formParam("login-clave");
    }
    public static boolean getQueryLoginCookie(Context ctx) { return ctx.formParam("login-cookie") != null;}

    public static String getQueryRol(Context ctx) {
        return ctx.formParam("val-rol");
    }
    public static String getParamId(Context ctx) {
        return ctx.queryParam("id");
    }
    public static UploadedFile getUploadedFile(Context ctx) { return ctx.uploadedFile("val-foto");}

    public static String getQuerySector(Context ctx) {
        return ctx.formParam("val-sector");
    }

    public static String getQueryNivel(Context ctx) { return ctx.formParam("val-nivel"); }

}
