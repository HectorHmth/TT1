package mx.ipn.escom.prueba.coffeeapp.constants;

public class Constants {
    public final static String IP = "10.100.77.80";
    public final static String PORT = ":8081";
    public final static String URL = "http://"+IP+PORT+"/backend/webapi/";


    public final static int LO_ANTES_POSIBLE = 0;
    public final static int PEDIDO_PROGRAMADO = 1;

    /*Cuentas*/
    public final static int CUENTA_NOT_FOUND = 200;
    public final static int CUENTA_EXISTE = 800;
    public final static int REGISTRO_EXITOSO = 801;
    public final static int REGISTRO_ERRONEO = 802;
    public final static int CIERRE_SESION_ERRONEO = 803;
    public final static int CIERRE_SESION_EXITOSO = 804;



    /*Locales*/
    public final static String LOCAL_ABIERTO = "Abierto";
    public final static String LOCAL_CERRADO = "Cerrado";



    /*ProductoLocal*/
    public final static int PRODUCTOLOCAL_NO_EXISTE = 900;
    public final static int PRODUCTOLOCAL_CANTIDAD_MAXIMA = 901;
    public final static int PRODUCTOLOCAL_CANTIDAD_MINIMA = 902;


    /*Ordenes*/
    public final static int PRODUCTO_AGREGADO_EXITOSAMENTE = 1000;
    public final static int PRODUCTO_EXISTE_EN_LA_ORDEN = 1001;
    public final static int PRODUCTO_AGOTADO = 1002;
    public final static int PRODUCTO_NO_AGREGADO = 1003;
    public final static int NO_EXISTE_ORDEN_SIN_CONFIRMAR = 1004;
    public final static int PRODUCTO_CERO = 1005;
    public final static int PRODUCTO_ELIMINADO_CORRECTAMENTE = 1006;
    public final static int NO_SE_PUEDE_ELIMINAR_PRODUCTO = 1007;
    public final static int ORDEN_VACIA = 1008;
    public final static int ORDEN_ENVIADA = 1009;
    public final static int ERROR_FINALIZAR_ORDEN = 1010;


    /*Formas de Pago*/
    public final static int FORMA_PAGO_EFECTIVO = 2000;
    public final static int FORMA_PAGO_TARJETA = 2001;
    public final static int FORMA_PAGO_PAYPAL = 2002;






}
