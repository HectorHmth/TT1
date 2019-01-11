package mx.ipn.escom.prueba.coffeeapp.constants;

public class StatesConstants {
    private final String ESTADO_O = "Procesado";
    private final String ESTADO_1 = "En Proceso";
    private final String ESTADO_2 = "Terminado";
    private final String ESTADO_3 = "Entregado";

    public String getEstado(Integer idEstado) {
        String estado;
        switch (idEstado) {
            case 0:
                estado = ESTADO_O;
                break;
            case 1:
                estado = ESTADO_1;
                break;
            case 2:
                estado = ESTADO_2;
                break;
            default:
                estado = ESTADO_3;
                break;
        }
        return estado;
    }
}
