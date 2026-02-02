public class Facturador {
    // Constantes No Primitivas
    static final Double BASE_HEAVY = 4000.0;
    static final Double BASE_ROCK = 3000.0;
    static final Integer UMBRAL_HEAVY = 500;
    static final Integer UMBRAL_ROCK = 1000;
    static final Integer EXTRA_HEAVY = 20;
    static final Integer EXTRA_ROCK = 30;
    static final Integer DIVISOR_CREDITOS_HEAVY = 5;
    static final Double IVA = 0.21;
    static final Double FACTOR_TOTAL = 1.21;

    // Renombrado: repertorio → catalogoConciertos
    static String[][] catalogoConciertos = {
         {"Tributo Robe", "heavy"}
        ,{"Homaneje Queen", "pop"}
        ,{"Magia Knoppler", "rock"}
        ,{"Demonios Rojos", "heavy"}
    };

    // Renombrado: actuaciones → actuacionesRealizadas
    static Integer[][] actuacionesRealizadas = {{0, 2222}, {2, 8888}, {0, 896}, {3, 999}};
    static String cliente = "Ayuntamiento de Badajoz";

    public static void main(String[] args) throws Exception {
        Double totalFactura = 0d;
        Integer creditosTotales = 0;

        System.out.println("FACTURA DE ACTUACIONES");
        System.out.println("Cliente: " + cliente);

        for (int i = 0; i < actuacionesRealizadas.length; i++) {
            // Renombrado: iConcierto → indiceConcierto
            Integer indiceConcierto = actuacionesRealizadas[i][0];
            String tipoConcierto = catalogoConciertos[indiceConcierto][1];
            Integer numeroAsistentes = actuacionesRealizadas[i][1];

            totalFactura += calcularImporteActuacion(tipoConcierto, numeroAsistentes);
            creditosTotales += calcularCreditos(tipoConcierto, numeroAsistentes);

            System.out.println("\tConcierto: " + catalogoConciertos[indiceConcierto][0]);
            System.out.println("\t\tAsistentes: " + numeroAsistentes);
        }

        System.out.println("BASE IMPONIBLE: " + totalFactura + " euros");
        System.out.printf("IVA (21%%): %.2f euros\n", totalFactura * IVA);
        System.out.printf("TOTAL FACTURA: %.2f euros\n", totalFactura * FACTOR_TOTAL);
        System.out.println("Créditos obtenidos: " + creditosTotales);
    }

    public static Double calcularImporteActuacion(String tipo, Integer asistentes) throws Exception {
        Double importeActuacion = 0d;
        switch (tipo) {
            case "heavy":
                importeActuacion = BASE_HEAVY;
                if (asistentes > UMBRAL_HEAVY)
                    importeActuacion += (double) EXTRA_HEAVY * (asistentes - UMBRAL_HEAVY);
                break;
            case "rock":
                importeActuacion = BASE_ROCK;
                if (asistentes > UMBRAL_ROCK)
                    importeActuacion += (double) EXTRA_ROCK * (asistentes - UMBRAL_ROCK);
                break;
            default:
                throw new Exception("Tipo de concierto desconocido.");
        }
        return importeActuacion;
    }

    public static Integer calcularCreditos(String tipo, Integer asistentes) {
        Integer creditosActuacion = Math.max(asistentes - UMBRAL_HEAVY, 0);
        if (tipo.equals("heavy")) {
            creditosActuacion += asistentes / DIVISOR_CREDITOS_HEAVY;
        }
        return creditosActuacion;
    }
}