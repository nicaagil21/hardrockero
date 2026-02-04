import java.util.ArrayList;
import java.util.List;



public class Facturador {

    private static final Double PORCENTAJE_IVA = 0.21;

    static String[][] repertorio = {
        {"Tributo Robe", "heavy"},
        {"Homenaje Queen", "rock"},
        {"Magia Knoppler", "rock"},
        {"Demonios Rojos", "heavy"}
    };

public enum TipoConcierto {
    HEAVY, 
    ROCK;

    public static TipoConcierto desdeString(String texto) {
        return TipoConcierto.valueOf(texto.toUpperCase().trim());
    }
}

    static Integer[][] datosActuaciones = {{0, 2000}, {2, 1200}, {0, 950}, {3, 1140}};

    static String cliente = "Ayuntamiento de Badajoz";

    public static void main(String[] args) throws Exception {
        Double totalFactura = 0d;
        Integer creditos = 0;

        System.out.println("FACTURA DE ACTUACIONES");
        System.out.println("Cliente: " + cliente);

        List<Actuacion> listaActuaciones = crearListaActuaciones(datosActuaciones);

        for (Actuacion actuacion : listaActuaciones) {
            Integer indiceConcierto = actuacion.indiceConcierto();
            Integer asistentes = actuacion.asistentes();

            String tipoActuacion = repertorio[indiceConcierto][1];

            totalFactura += calcularImporteActuacion(tipoActuacion, asistentes);
            creditos += calcularCreditos(tipoActuacion, asistentes);

            System.out.println("\tConcierto: " + repertorio[indiceConcierto][0]);
            System.out.println("\t\tAsistentes: " + asistentes);
        }

        System.out.println("BASE IMPONIBLE: " + totalFactura + " euros");
        System.out.printf("IVA (21%%): %.2f euros\n", totalFactura * PORCENTAJE_IVA);
        System.out.printf("TOTAL FACTURA: %.2f euros\n", totalFactura * (1 + PORCENTAJE_IVA));
        System.out.println("Créditos obtenidos: " + creditos);
    }

   
    public static List<Actuacion> crearListaActuaciones(Integer[][] datosActuaciones) {
        List<Actuacion> actuaciones = new ArrayList<>();
        for (Integer[] datosActuacion : datosActuaciones) {
            Integer indiceConcierto = datosActuacion[0];
            Integer asistentes = datosActuacion[1];
            
            Actuacion actuacion = new Actuacion(indiceConcierto, asistentes);
            actuaciones.add(actuacion);
        }
        return actuaciones;
    }

    public static Double calcularImporteActuacion(String tipoActuacion, Integer asistentes) throws Exception {
        return 0.0; 
    }

    public static Integer calcularCreditos(String tipoActuacion, Integer asistentes) {
        return 0;
    }
}


record Actuacion(Integer indiceConcierto, Integer asistentes) {}