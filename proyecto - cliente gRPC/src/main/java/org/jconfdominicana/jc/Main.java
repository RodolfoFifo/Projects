package org.jconfdominicana.jc;

import com.google.protobuf.StringValue;
import formulariorn.FormularioRnGrpc;
import formulariorn.FormularioRnOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        //
        String host = "localhost";
        int puerto = 50051;

        //Abriendo la conectividad
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50051)
                .usePlaintext() //configuración para la conexión.
                .build();
        // Abriendo el servicio.
        FormularioRnGrpc.FormularioRnBlockingStub formularioInterfaz = FormularioRnGrpc.newBlockingStub(channel);

        //
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion;

        while (!salir) {

            System.out.println("Cliente gRPC en Java - Opciones de Operaciones");
            System.out.println("1. Consultar Formulario");
            System.out.println("2. Listar Formulario");
            System.out.println("4. Crear Formulario");
            System.out.println("5. Salir");

            try {

                System.out.println("Escribe una de las opciones:");
                opcion = sn.nextInt();

                switch (opcion) {
                    case 1:
                        //consultando el servicio.
                        System.out.print("Ingrese nombre de usuario:");
                        String m = sn.next();
                        FormularioRnOuterClass.ListaFormulario listaFormularioxUser = formularioInterfaz
                                .listaFormularioxUser(
                                        FormularioRnOuterClass
                                                .FormularioRequest
                                                .newBuilder()
                                                .setUsuario(m)
                                                .build()
                                );
                        for (FormularioRnOuterClass.FormularioResponse e : listaFormularioxUser.getFormularioList()) {
                            imprimirFomrulario(e);
                        }
                        break;
                    case 2:
                        System.out.println("Listar Formularios vía gRpc:");
                        FormularioRnOuterClass.ListaFormulario listaFormulario = formularioInterfaz.listaFormulario(FormularioRnOuterClass.Empty.newBuilder().build());
                        System.out.println("La cantidad de formularios: " + listaFormulario.getFormularioCount());
                        for (FormularioRnOuterClass.FormularioResponse e : listaFormulario.getFormularioList()) {
                            imprimirFomrulario(e);
                        }
                        break;
                    case 4:
                        System.out.println("Creación de Formulario\n");
                        System.out.print("Nombre:");
                        String nombre = sn.next();
                        System.out.print("Sector:");
                        String sector = sn.next();
                        System.out.print("Nivel Escolar:");
                        String nivel = sn.next();
                        System.out.print("Fecha:");
                        String fecha = sn.next();
                        System.out.print("Latitud:");
                        String latitud = sn.next();
                        System.out.print("Longitud:");
                        String longitud = sn.next();
                        System.out.print("Precision:");
                        String precision = sn.next();
                        System.out.print("Usuario:");
                        String usuario = sn.next();
                        System.out.print("Mime Type:");
                        String mimetype = sn.next();
                        System.out.print("Base 64:");
                        String base64 = sn.next();
                        formularioInterfaz.crearFormulario(FormularioRnOuterClass.FormularioResponse.newBuilder()
                                .setNombre(nombre)
                                .setSector(sector)
                                .setNivel(nivel)
                                .setFecha(fecha)
                                .setLatitude(latitud)
                                .setLongitude(longitud)
                                .setAccuracy(precision)
                                .setUsuario(usuario)
                                .setMimetype(mimetype)
                                .setBase64(base64)
                                .build());
                        break;
                    case 5:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 5");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
        }
    }

    /**
     *
     * @param e
     */
    private static void imprimirFomrulario(FormularioRnOuterClass.FormularioResponse e){
        System.out.printf("============================\n");
        System.out.printf("\t Nombre: %s \n", e.getNombre());
        System.out.printf("\t Sector: %s\n", e.getSector());
        System.out.printf("\t Nivel Escolar: %s \n", e.getNivel());
        System.out.printf("\t Fecha: %s\n", e.getFecha());
        System.out.printf("\t Latitud: %s \n", e.getLatitude());
        System.out.printf("\t Longitud: %s\n", e.getLongitude());
        System.out.printf("\t Precision: %s \n", e.getAccuracy());
        System.out.printf("\t Usuario: %s\n", e.getUsuario());
        System.out.printf("\t Tipo Foto: %s \n", e.getMimetype());
        System.out.printf("\t Foto Base64: %s\n", e.getBase64());
        System.out.println("===========================");
    }
}
