package pucmm.edu.grpc;

import pucmm.edu.encapsulaciones.Formulario;

public class RouteMapper {
    public static byte[] getFormularioResponse(Formulario data){
        return data.toBuffer().toByteArray();
    }

}