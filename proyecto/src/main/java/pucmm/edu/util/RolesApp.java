package pucmm.edu.util;

import io.javalin.security.RouteRole;

/**
 * Enum para manejar los roles de la aplicacion.
 */
public enum RolesApp implements RouteRole {
    ROLE_USUARIO,
    ROLE_ADMIN;
}
