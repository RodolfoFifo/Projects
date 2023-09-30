package pucmm.edu.servicios;

import dev.morphia.DeleteOptions;
import dev.morphia.experimental.MorphiaSession;
import dev.morphia.query.Query;
import org.bson.types.ObjectId;
import pucmm.edu.encapsulaciones.Usuario;

import static dev.morphia.query.experimental.filters.Filters.eq;

public class UsuarioServices extends GestionDb<Usuario>{

    private static UsuarioServices instancia;

    private UsuarioServices() {
        super(Usuario.class);
    }

    public static UsuarioServices getInstancia() {
        if (instancia == null) {
            instancia = new UsuarioServices();
        }
        return instancia;
    }

    /**
     *
     */
    public Usuario getUsuarioByUsername(String username) {
        try (MorphiaSession session = getDatastore().startSession()) {
            Query<Usuario> objetos = session.find(Usuario.class).filter(eq("usuario", username));
            return objetos.first();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean eliminarById(String id) {
        boolean ok = false;
        try {
            MorphiaSession session = getDatastore().startSession();
            try {
                session.startTransaction();
                session
                        .find(Usuario.class)
                        .filter(eq("_id", new ObjectId(id)))
                        .delete(new DeleteOptions());
                session.commitTransaction();
                ok = true;
            }finally {
                session.close();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return ok;
    }
}