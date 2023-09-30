package pucmm.edu.servicios;

import dev.morphia.DeleteOptions;
import dev.morphia.experimental.MorphiaSession;
import org.bson.types.ObjectId;
import pucmm.edu.encapsulaciones.Formulario;

import java.util.List;

import static dev.morphia.query.experimental.filters.Filters.eq;

public class FormularioServices extends GestionDb<Formulario> {

    private static FormularioServices instancia;

    private FormularioServices() {
        super(Formulario.class);
    }

    public static FormularioServices getInstancia() {
        if (instancia == null) {
            instancia = new FormularioServices();
        }
        return instancia;
    }

    public List<Formulario> getFormsByUser(ObjectId id) {
        try (MorphiaSession session = getDatastore().startSession()) {
            return session.find(Formulario.class).filter(eq("user", id)).stream().toList();
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
                        .find(Formulario.class)
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
