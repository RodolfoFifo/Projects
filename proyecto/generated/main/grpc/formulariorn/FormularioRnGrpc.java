package formulariorn;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.39.0)",
    comments = "Source: FormularioRn.proto")
public final class FormularioRnGrpc {

  private FormularioRnGrpc() {}

  public static final String SERVICE_NAME = "formulariorn.FormularioRn";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<formulariorn.FormularioRnOuterClass.FormularioRequest,
      formulariorn.FormularioRnOuterClass.ListaFormulario> getListaFormularioxUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "listaFormularioxUser",
      requestType = formulariorn.FormularioRnOuterClass.FormularioRequest.class,
      responseType = formulariorn.FormularioRnOuterClass.ListaFormulario.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<formulariorn.FormularioRnOuterClass.FormularioRequest,
      formulariorn.FormularioRnOuterClass.ListaFormulario> getListaFormularioxUserMethod() {
    io.grpc.MethodDescriptor<formulariorn.FormularioRnOuterClass.FormularioRequest, formulariorn.FormularioRnOuterClass.ListaFormulario> getListaFormularioxUserMethod;
    if ((getListaFormularioxUserMethod = FormularioRnGrpc.getListaFormularioxUserMethod) == null) {
      synchronized (FormularioRnGrpc.class) {
        if ((getListaFormularioxUserMethod = FormularioRnGrpc.getListaFormularioxUserMethod) == null) {
          FormularioRnGrpc.getListaFormularioxUserMethod = getListaFormularioxUserMethod =
              io.grpc.MethodDescriptor.<formulariorn.FormularioRnOuterClass.FormularioRequest, formulariorn.FormularioRnOuterClass.ListaFormulario>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "listaFormularioxUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  formulariorn.FormularioRnOuterClass.FormularioRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  formulariorn.FormularioRnOuterClass.ListaFormulario.getDefaultInstance()))
              .setSchemaDescriptor(new FormularioRnMethodDescriptorSupplier("listaFormularioxUser"))
              .build();
        }
      }
    }
    return getListaFormularioxUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<formulariorn.FormularioRnOuterClass.FormularioResponse,
      formulariorn.FormularioRnOuterClass.FormularioResponse> getCrearFormularioMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "crearFormulario",
      requestType = formulariorn.FormularioRnOuterClass.FormularioResponse.class,
      responseType = formulariorn.FormularioRnOuterClass.FormularioResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<formulariorn.FormularioRnOuterClass.FormularioResponse,
      formulariorn.FormularioRnOuterClass.FormularioResponse> getCrearFormularioMethod() {
    io.grpc.MethodDescriptor<formulariorn.FormularioRnOuterClass.FormularioResponse, formulariorn.FormularioRnOuterClass.FormularioResponse> getCrearFormularioMethod;
    if ((getCrearFormularioMethod = FormularioRnGrpc.getCrearFormularioMethod) == null) {
      synchronized (FormularioRnGrpc.class) {
        if ((getCrearFormularioMethod = FormularioRnGrpc.getCrearFormularioMethod) == null) {
          FormularioRnGrpc.getCrearFormularioMethod = getCrearFormularioMethod =
              io.grpc.MethodDescriptor.<formulariorn.FormularioRnOuterClass.FormularioResponse, formulariorn.FormularioRnOuterClass.FormularioResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "crearFormulario"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  formulariorn.FormularioRnOuterClass.FormularioResponse.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  formulariorn.FormularioRnOuterClass.FormularioResponse.getDefaultInstance()))
              .setSchemaDescriptor(new FormularioRnMethodDescriptorSupplier("crearFormulario"))
              .build();
        }
      }
    }
    return getCrearFormularioMethod;
  }

  private static volatile io.grpc.MethodDescriptor<formulariorn.FormularioRnOuterClass.Empty,
      formulariorn.FormularioRnOuterClass.ListaFormulario> getListaFormularioMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "listaFormulario",
      requestType = formulariorn.FormularioRnOuterClass.Empty.class,
      responseType = formulariorn.FormularioRnOuterClass.ListaFormulario.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<formulariorn.FormularioRnOuterClass.Empty,
      formulariorn.FormularioRnOuterClass.ListaFormulario> getListaFormularioMethod() {
    io.grpc.MethodDescriptor<formulariorn.FormularioRnOuterClass.Empty, formulariorn.FormularioRnOuterClass.ListaFormulario> getListaFormularioMethod;
    if ((getListaFormularioMethod = FormularioRnGrpc.getListaFormularioMethod) == null) {
      synchronized (FormularioRnGrpc.class) {
        if ((getListaFormularioMethod = FormularioRnGrpc.getListaFormularioMethod) == null) {
          FormularioRnGrpc.getListaFormularioMethod = getListaFormularioMethod =
              io.grpc.MethodDescriptor.<formulariorn.FormularioRnOuterClass.Empty, formulariorn.FormularioRnOuterClass.ListaFormulario>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "listaFormulario"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  formulariorn.FormularioRnOuterClass.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  formulariorn.FormularioRnOuterClass.ListaFormulario.getDefaultInstance()))
              .setSchemaDescriptor(new FormularioRnMethodDescriptorSupplier("listaFormulario"))
              .build();
        }
      }
    }
    return getListaFormularioMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static FormularioRnStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FormularioRnStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FormularioRnStub>() {
        @java.lang.Override
        public FormularioRnStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FormularioRnStub(channel, callOptions);
        }
      };
    return FormularioRnStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static FormularioRnBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FormularioRnBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FormularioRnBlockingStub>() {
        @java.lang.Override
        public FormularioRnBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FormularioRnBlockingStub(channel, callOptions);
        }
      };
    return FormularioRnBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static FormularioRnFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FormularioRnFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FormularioRnFutureStub>() {
        @java.lang.Override
        public FormularioRnFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FormularioRnFutureStub(channel, callOptions);
        }
      };
    return FormularioRnFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class FormularioRnImplBase implements io.grpc.BindableService {

    /**
     */
    public void listaFormularioxUser(formulariorn.FormularioRnOuterClass.FormularioRequest request,
        io.grpc.stub.StreamObserver<formulariorn.FormularioRnOuterClass.ListaFormulario> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListaFormularioxUserMethod(), responseObserver);
    }

    /**
     */
    public void crearFormulario(formulariorn.FormularioRnOuterClass.FormularioResponse request,
        io.grpc.stub.StreamObserver<formulariorn.FormularioRnOuterClass.FormularioResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCrearFormularioMethod(), responseObserver);
    }

    /**
     * <pre>
     *utilizando los formatos predefinidos.
     * </pre>
     */
    public void listaFormulario(formulariorn.FormularioRnOuterClass.Empty request,
        io.grpc.stub.StreamObserver<formulariorn.FormularioRnOuterClass.ListaFormulario> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListaFormularioMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getListaFormularioxUserMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                formulariorn.FormularioRnOuterClass.FormularioRequest,
                formulariorn.FormularioRnOuterClass.ListaFormulario>(
                  this, METHODID_LISTA_FORMULARIOX_USER)))
          .addMethod(
            getCrearFormularioMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                formulariorn.FormularioRnOuterClass.FormularioResponse,
                formulariorn.FormularioRnOuterClass.FormularioResponse>(
                  this, METHODID_CREAR_FORMULARIO)))
          .addMethod(
            getListaFormularioMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                formulariorn.FormularioRnOuterClass.Empty,
                formulariorn.FormularioRnOuterClass.ListaFormulario>(
                  this, METHODID_LISTA_FORMULARIO)))
          .build();
    }
  }

  /**
   */
  public static final class FormularioRnStub extends io.grpc.stub.AbstractAsyncStub<FormularioRnStub> {
    private FormularioRnStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FormularioRnStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FormularioRnStub(channel, callOptions);
    }

    /**
     */
    public void listaFormularioxUser(formulariorn.FormularioRnOuterClass.FormularioRequest request,
        io.grpc.stub.StreamObserver<formulariorn.FormularioRnOuterClass.ListaFormulario> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListaFormularioxUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void crearFormulario(formulariorn.FormularioRnOuterClass.FormularioResponse request,
        io.grpc.stub.StreamObserver<formulariorn.FormularioRnOuterClass.FormularioResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCrearFormularioMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *utilizando los formatos predefinidos.
     * </pre>
     */
    public void listaFormulario(formulariorn.FormularioRnOuterClass.Empty request,
        io.grpc.stub.StreamObserver<formulariorn.FormularioRnOuterClass.ListaFormulario> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListaFormularioMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class FormularioRnBlockingStub extends io.grpc.stub.AbstractBlockingStub<FormularioRnBlockingStub> {
    private FormularioRnBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FormularioRnBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FormularioRnBlockingStub(channel, callOptions);
    }

    /**
     */
    public formulariorn.FormularioRnOuterClass.ListaFormulario listaFormularioxUser(formulariorn.FormularioRnOuterClass.FormularioRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListaFormularioxUserMethod(), getCallOptions(), request);
    }

    /**
     */
    public formulariorn.FormularioRnOuterClass.FormularioResponse crearFormulario(formulariorn.FormularioRnOuterClass.FormularioResponse request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCrearFormularioMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *utilizando los formatos predefinidos.
     * </pre>
     */
    public formulariorn.FormularioRnOuterClass.ListaFormulario listaFormulario(formulariorn.FormularioRnOuterClass.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListaFormularioMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class FormularioRnFutureStub extends io.grpc.stub.AbstractFutureStub<FormularioRnFutureStub> {
    private FormularioRnFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FormularioRnFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FormularioRnFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<formulariorn.FormularioRnOuterClass.ListaFormulario> listaFormularioxUser(
        formulariorn.FormularioRnOuterClass.FormularioRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListaFormularioxUserMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<formulariorn.FormularioRnOuterClass.FormularioResponse> crearFormulario(
        formulariorn.FormularioRnOuterClass.FormularioResponse request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCrearFormularioMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *utilizando los formatos predefinidos.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<formulariorn.FormularioRnOuterClass.ListaFormulario> listaFormulario(
        formulariorn.FormularioRnOuterClass.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListaFormularioMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LISTA_FORMULARIOX_USER = 0;
  private static final int METHODID_CREAR_FORMULARIO = 1;
  private static final int METHODID_LISTA_FORMULARIO = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final FormularioRnImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(FormularioRnImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LISTA_FORMULARIOX_USER:
          serviceImpl.listaFormularioxUser((formulariorn.FormularioRnOuterClass.FormularioRequest) request,
              (io.grpc.stub.StreamObserver<formulariorn.FormularioRnOuterClass.ListaFormulario>) responseObserver);
          break;
        case METHODID_CREAR_FORMULARIO:
          serviceImpl.crearFormulario((formulariorn.FormularioRnOuterClass.FormularioResponse) request,
              (io.grpc.stub.StreamObserver<formulariorn.FormularioRnOuterClass.FormularioResponse>) responseObserver);
          break;
        case METHODID_LISTA_FORMULARIO:
          serviceImpl.listaFormulario((formulariorn.FormularioRnOuterClass.Empty) request,
              (io.grpc.stub.StreamObserver<formulariorn.FormularioRnOuterClass.ListaFormulario>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class FormularioRnBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    FormularioRnBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return formulariorn.FormularioRnOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("FormularioRn");
    }
  }

  private static final class FormularioRnFileDescriptorSupplier
      extends FormularioRnBaseDescriptorSupplier {
    FormularioRnFileDescriptorSupplier() {}
  }

  private static final class FormularioRnMethodDescriptorSupplier
      extends FormularioRnBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    FormularioRnMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (FormularioRnGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new FormularioRnFileDescriptorSupplier())
              .addMethod(getListaFormularioxUserMethod())
              .addMethod(getCrearFormularioMethod())
              .addMethod(getListaFormularioMethod())
              .build();
        }
      }
    }
    return result;
  }
}
