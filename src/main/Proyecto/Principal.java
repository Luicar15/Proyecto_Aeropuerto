import java.util.Scanner;

public class Principal {
    static Scanner entrada = new Scanner(System.in);
    final static int num = 3; //Numero de Aeropuertos
    static Aeropuerto aeropuertos[] = new Aeropuerto[num];
    private static Aeropuerto aeropuerto;

    public static void main (String[] args){
        //Insertar datos del aeropuerto
        insertarDatosAeropuerto(aeropuertos);
        menu();
    }


    public static void insertarDatosAeropuerto(Aeropuerto aero[]){
        aero[0] = new AeropuertoPublico("Simon Bolivar", "Santa Marta", "Colombia", 8000000);
        aero[0].insertarCompañia(new Compañia("AVIANCA"));
        aero[0].insertarCompañia(new Compañia("LATAM"));
        aero[0].getCompañia("AVIANCA").insertarVuelo(new Vuelo("IB20","Santa Marta", "Bogota", 150, 200 ));
        aero[0].getCompañia("LATAM").insertarVuelo(new Vuelo("IB21", "Santa Marta", "Medellin", 120, 200));
        aero[0].getCompañia("AVIANCA").getVuelo("IB20").insertarPasajero(new Pasajero("David Luiz", "1084468013", "Colombiano"));
        aero[0].getCompañia("AVIANCA").getVuelo("IB20").insertarPasajero(new Pasajero("Luis Carlos", "1082898550", "Colombiano"));
        aero[0].getCompañia("LATAM").getVuelo("IB21").insertarPasajero(new Pasajero("Danna Sofia", "1080434011", "Colombiano"));
        aero[0].getCompañia("LATAM").getVuelo("IB21").insertarPasajero(new Pasajero("Kelly", "1082907976", "Colombiano"));

        aero[1] = new AeropuertoPrivado("El Dorado", "Bogota", "Colombia");
        aero[1].insertarCompañia(new Compañia("WINGO"));
        String empresas[] = {"Postobon","Aguila"};
        ((AeropuertoPrivado)aero[1]).insertarEmpresas(empresas);
        aero[1].getCompañia("WINGO").insertarVuelo(new Vuelo("IB22", "Bogota", "Miami", 250, 150));
        aero[1].getCompañia("WINGO").getVuelo("IB22").insertarPasajero(new Pasajero("Maryluz", "102125478", "Colombiana"));

        aero[2] = new AeropuertoPublico("Jose Maria Uribe", "Medellin", "Colombia", 7000000);
        aero[2].insertarCompañia(new Compañia("VIVACOLOMBIA"));
        aero[2].getCompañia("VIVACOLOMBIA").insertarVuelo(new Vuelo("IB50","Medellin", "Buenos Aires", 210, 100 ));
        aero[2].getCompañia("VIVACOLOMBIA").getVuelo("IB50").insertarPasajero(new Pasajero("Almir", "54789563", "Mexicano"));
        aero[2].getCompañia("VIVACOLOMBIA").getVuelo("IB50").insertarPasajero(new Pasajero("Salma", "1084897563", "Mexicano"));
    }
    public static void menu(){
        int opcion;
        String nombreAeropuerto, nombreCompañia, ciudadOrigen, ciudadDestino;
        Compañia compañia;

        do{
            System.out.println("\t.:MENU:");
            System.out.println("1. Ver Aeropuertos Gestionados (Publicos o Privados)");
            System.out.println("2. Ver empresas(Privado) o Subvencion(Publico)");
            System.out.println("3. Lista de compañias de un Aeropuerto");
            System.out.println("4. Lista de Vuelos por Compañia");
            System.out.println("5. Listar posibles Vuelos de Origen a Destino");
            System.out.println("6. Salir");
            System.out.println("Opcion: ");
            opcion = entrada.nextInt();

            switch (opcion){
                case 1: //Ver Aeropuertos Gestionados (Publicos o Privados)
                    System.out.println("");
                    mostrarDatosAeropuertos(aeropuertos);
                    break;
                case 2: //Ver empresas(Privado) o Subvención(Publico)
                    System.out.println("");
                    mostrarPatrocinio(aeropuertos);
                    break;
                case 3: //Listas de compañias de un Aeropuerto
                    entrada.nextLine();
                    System.out.println("\nDigite el nombre del Aeropuerto: ");
                    nombreAeropuerto = entrada.nextLine();
                    aeropuerto = buscarAeropuerto(nombreAeropuerto, aeropuertos);
                    if (aeropuerto==null){
                        System.out.println("El Aeropuerto no existe");
                    }
                    else {
                        mostrarCompañias(aeropuerto);
                    }
                    break;
                case 4: //Lista de Vuelos por Compañia
                    entrada.nextLine();
                    System.out.println("Digite el nombre del Aeropuerto: ");
                    nombreAeropuerto = entrada.nextLine();
                    aeropuerto = buscarAeropuerto(nombreAeropuerto, aeropuertos);
                    if (aeropuerto==null){
                        System.out.println("El Aeropuerto no existe");
                    }
                    else {
                        System.out.print("Digite el nombre de la Compañia: ");
                        nombreCompañia = entrada.nextLine();
                        compañia = aeropuerto.getCompañia(nombreCompañia);
                        mostrarVuelos(compañia);
                    }
                    break;
                case 5: //Listar posibles Vuelos de Origen a Destino
                    entrada.nextLine();
                    System.out.println("Digite la ciudad de Origen: ");
                    ciudadOrigen = entrada.nextLine();
                    System.out.println("Digite la ciudad Destino: ");
                    ciudadDestino = entrada.nextLine();
                    mostrarVuelosOrigenDestino(ciudadOrigen, ciudadDestino, aeropuertos);
                    break;
                case 6: break;
                default:
                    System.out.println("Error, se equivoco de opcion de menu");
            }
        }while (opcion!=6);
    }
    public static void mostrarDatosAeropuertos(Aeropuerto aeropuertos[]){
        for (int i=0; i<aeropuertos.length;i++){
            if (aeropuertos[i] instanceof AeropuertoPrivado){
                System.out.println("Aeropuerto Privado");
                System.out.println("Nombre: "+aeropuertos[i].getNombre());
                System.out.println("Ciudad: "+aeropuertos[i].getCiudad());
                System.out.println("Pais: "+aeropuertos[i].getPais());
            }
            else {
                System.out.println("Aeropuerto Publico");
                System.out.println("Nombre: "+aeropuertos[i].getNombre());
                System.out.println("Ciudad: "+aeropuertos[i].getCiudad());
                System.out.println("Pais: "+aeropuertos[i].getPais());
            }
            System.out.println("");
        }
    }
    public static void mostrarPatrocinio(Aeropuerto aeropuertos[]){
        String empresas[];
        for (int i=0; i<aeropuertos.length;i++){
            if (aeropuertos[i] instanceof AeropuertoPrivado ){
                System.out.println("Aeropuerto Privado: "+aeropuertos[i].getNombre());
                empresas = ((AeropuertoPrivado) aeropuertos[i]).getListaEmpresas();
                System.out.println("Empresas: ");
                for (int j=0; j< empresas.length;j++){
                    System.out.println(empresas[j]);
                }
            }
            else {
                System.out.println("Aeropuerto Publico: "+aeropuertos[i].getNombre());
                System.out.println("Subvencion: "+((AeropuertoPublico)aeropuertos[i]).getSubvencion());
            }
            System.out.println("");
        }
    }
    public static Aeropuerto buscarAeropuerto(String nombre,Aeropuerto aeropuertos[]){
        boolean encontrado = false;
        int i=0;
        Aeropuerto aero = null;
        while ((!encontrado)&&(i< aeropuertos.length)){
            if (nombre.equals(aeropuertos[i].getNombre())){
                encontrado = true;
                aero = aeropuertos[i];
            }
            i++;
        }
        return aero;
    }
    public static void mostrarCompañias (Aeropuerto aeropuerto){
        System.out.println("\nLas compañias de Aeropuerto: "+aeropuerto.getNombre());
        for (int i=0;i<aeropuerto.getNumCompañia();i++){
            System.out.println(aeropuerto.getCompañia(i).getNombre());
        }
    }

    public static void mostrarVuelos(Compañia compañia){
        Vuelo vuelo;
        System.out.println("Los vuelos de la compañia: "+compañia.getNombre() );
        for (int i=0; i<compañia.getNumVuelo();i++){
            vuelo = compañia.getVuelo(i);
            System.out.println("Identificador: "+vuelo.getIdentificador());
            System.out.println("Ciudad Origen: "+vuelo.getCiudadOrigen());
            System.out.println("Ciudad Destino: "+vuelo.getCiudadDestino());
            System.out.println("Precio: $"+vuelo.getPrecio());
            System.out.println("");
        }
    }
    public static Vuelo[] buscarVuelosOrigenDestino (String origen, String destino, Aeropuerto aeropuertos[]) {
        Vuelo vuelo;
        int contador = 0;
        Vuelo listaVuelo[];

        for (int i = 0; i < aeropuertos.length; i++) { //recorremos los aeropuertos
            for (int j = 0; j < aeropuerto.getNumCompañia(); j++) { //recorremos las compañias
                for (int k = 0; k < aeropuertos[i].getCompañia(j).getNumVuelo(); k++) { //recorremos los vuelos
                    vuelo = aeropuertos[i].getCompañia(j).getVuelo(k);
                    if ((origen.equals(vuelo.getCiudadOrigen()) && destino.equals(vuelo.getCiudadDestino()))) {
                        contador++;
                    }

                }

            }
        }
        listaVuelo = new Vuelo[contador];
        int q=0;

        for (int i = 0; i < aeropuertos.length; i++) { //recorremos los aeropuertos
            for (int j = 0; j < aeropuerto.getNumCompañia(); j++) { //recorremos las compañias
                for (int k = 0; k < aeropuertos[i].getCompañia(j).getNumVuelo(); k++) { //recorremos los vuelos
                    vuelo = aeropuertos[i].getCompañia(j).getVuelo(k);
                    if ((origen.equals(vuelo.getCiudadOrigen()) && destino.equals(vuelo.getCiudadDestino()))) {
                        listaVuelo[q] = vuelo;
                        q++;
                    }

                }

            }
        }
        return listaVuelo;
    }

    public static void mostrarVuelosOrigenDestino(String origen, String destino, Aeropuerto aeropuertos[]){
        Vuelo vuelos[];
        vuelos = buscarVuelosOrigenDestino(origen, destino, aeropuertos);
        if (vuelos.length==0){
            System.out.println("No existen vuelos de esa ciudad origen a destino");
        }else {
            System.out.println("\nVuelos Encontrados: ");
            for (int i=0; i< vuelos.length;i++){
                System.out.println("Identificador: "+vuelos[i].getIdentificador());
                System.out.println("Ciudad de Origen: "+vuelos[i].getCiudadOrigen());
                System.out.println("Ciudad Destino: "+vuelos[i].getCiudadDestino());
                System.out.println("El Precio del Vuelo es: $"+vuelos[i].getPrecio());
                System.out.println("");
            }

        }
    }
}
