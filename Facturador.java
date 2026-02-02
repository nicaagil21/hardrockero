public class Facturador {
    // Repertorio de conciertos del grupo
    static String[][] repertorio = {
         {"Tributo Robe", "heavy"}
        ,{"Homaneje Queen", "pop"}
        ,{"Magia Knoppler", "rock"}
        ,{"Demonios Rojos", "heavy"}
    };

    // Actuaciones realizadas indicando el concierto ofrecido y audiencias obtenidas.
    static Integer[][] actuaciones = {{0, 2222}, {2, 8888}, {0, 896}, {3, 999}};

    static String cliente = "Ayuntamiento de Badajoz";

    public static void main(String[] args) throws Exception {
        Double totalFactura = 0d;
        Integer creditos = 0;

        System.out.println("FACTURA DE ACTUACIONES");
        System.out.println("Cliente: " + cliente);

        for (int i = 0; i < actuaciones.length; i++) {
            Integer iConcierto = actuaciones[i][0];
            String tipo = repertorio[iConcierto][1];
            Integer asistentes = actuaciones[i][1];

            // Uso de los métodos extraídos
            totalFactura += calcularImporteActuacion(tipo, asistentes);
            creditos += calcularCreditos(tipo, asistentes);

            System.out.println("\tConcierto: " + repertorio[iConcierto][0]);
            System.out.println("\t\tAsistentes: " + asistentes);
        }

        System.out.println("BASE IMPONIBLE: " + totalFactura + " euros");
        System.out.printf("IVA (21%%): %.2f euros\n", totalFactura * 0.21);
        System.out.printf("TOTAL FACTURA: %.2f euros\n", totalFactura * 1.21);
        System.out.println("Créditos obtenidos: " + creditos);
    }

    public static Double calcularImporteActuacion(String tipo, Integer asistentes) throws Exception {
        Double importeActuacion = 0d;
        switch (tipo) {
            case "heavy":
                importeActuacion = 4000d;
                if (asistentes > 500)
                    importeActuacion += 20 * (asistentes - 500);
                break;
            case "rock":
                importeActuacion = 3000d;
                if (asistentes > 1000)
                    importeActuacion += 30 * (asistentes - 1000);
                break;
            default:
                throw new Exception("Tipo de concierto desconocido.");
        }
        return importeActuacion;
    }

    // EXTRAER MÉTODO: calcularCreditos
    public static Integer calcularCreditos(String tipo, Integer asistentes) {
        Integer creditosActuacion = Math.max(asistentes - 500, 0);
        if (tipo.equals("heavy")) {
            creditosActuacion += asistentes / 5;
        }
        return creditosActuacion;
    }
}