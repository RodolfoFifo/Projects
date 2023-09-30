package pucmm.edu.grpc;

import formulariorn.FormularioRnGrpc;
import formulariorn.FormularioRnOuterClass;
import io.grpc.stub.StreamObserver;
import pucmm.edu.encapsulaciones.Formulario;
import pucmm.edu.encapsulaciones.Foto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static pucmm.edu.Main.*;


/**
 * Representación de
 */
public class FormularioServicesGrpc extends FormularioRnGrpc.FormularioRnImplBase {

    @Override
    public void listaFormularioxUser(FormularioRnOuterClass.FormularioRequest request, StreamObserver<FormularioRnOuterClass.ListaFormulario> responseObserver) {
        List<Formulario> formularios = formularioServices.getFormsByUser(usuarioServices.getUsuarioByUsername(request.getUsuario()).getId());
        List<FormularioRnOuterClass.FormularioResponse> formularioResponseList = new ArrayList<>();
        for(Formulario e : formularios){
            formularioResponseList.add(convertir(e));
        }
        FormularioRnOuterClass.ListaFormulario build = FormularioRnOuterClass.ListaFormulario.newBuilder().addAllFormulario(formularioResponseList).build();
        responseObserver.onNext(build);
        responseObserver.onCompleted();
    }

    @Override
    public void crearFormulario(FormularioRnOuterClass.FormularioResponse request, StreamObserver<FormularioRnOuterClass.FormularioResponse> responseObserver) {
        Formulario e = formularioServices.crear(convertir(request));
        responseObserver.onNext(convertir(e));
        responseObserver.onCompleted();
    }

    @Override
    public void listaFormulario(FormularioRnOuterClass.Empty request, StreamObserver<FormularioRnOuterClass.ListaFormulario> responseObserver) {
        List<Formulario> formularios = formularioServices.findAll();
        List<FormularioRnOuterClass.FormularioResponse> formularioResponseList = new ArrayList<>();
        for(Formulario e : formularios){
            formularioResponseList.add(convertir(e));
        }
        FormularioRnOuterClass.ListaFormulario build = FormularioRnOuterClass.ListaFormulario.newBuilder().addAllFormulario(formularioResponseList).build();
        responseObserver.onNext(build);
        responseObserver.onCompleted();
    }

    /**
     * Convierte la clase del modelo para enviar por el servicio gRPC.
     * @param formulario
     * @return
     */
    private FormularioRnOuterClass.FormularioResponse convertir(Formulario formulario){
        return FormularioRnOuterClass.FormularioResponse.newBuilder()
                .setNombre(formulario.getNombre())
                .setSector(formulario.getSector())
                .setNivel(formulario.getNivelEscolar())
                .setFecha(formulario.getFecha())
                .setLatitude(formulario.getLatitude())
                .setLongitude(formulario.getLongitude())
                .setAccuracy(formulario.getAccuracy())
                .setUsuario(formulario.getUser().getUsuario())
                .setMimetype(formulario.getFoto().getMimeType())
                .setBase64(formulario.getFoto().getFotoBase64())
                .build();
    }

    /**
     *
     * @param e
     * @return
     */
    private Formulario convertir(FormularioRnOuterClass.FormularioResponse e){
        Foto foto = fotoServices.crear(new Foto(e.getNombre(), e.getMimetype(), e.getBase64()));

        return new Formulario(e.getNombre(), e.getSector(), e.getNivel(), usuarioServices.getUsuarioByUsername(e.getUsuario()), e.getLatitude(), e.getLongitude(), e.getAccuracy(), foto); 
    }
}
