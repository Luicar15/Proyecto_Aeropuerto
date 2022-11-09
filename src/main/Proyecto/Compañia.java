public class Compañia {
    private String nombre;
    private Vuelo listarVuelo[] = new Vuelo[10];
    private int numVuelo=0;

    public Compañia(String nombre) {

        this.nombre = nombre;
    }

    public Compañia(String nombre, Vuelo v[]) {
        this.nombre = nombre;
        listarVuelo = v;
            numVuelo = v.length;
    }

    public void insertarVuelo (Vuelo vuelo){
        listarVuelo[numVuelo] = vuelo;
        numVuelo++;
    }

    public String getNombre() {

        return nombre;
    }

    public int getNumVuelo() {

        return numVuelo;
    }

    public Vuelo getVuelo(int i){

        return listarVuelo[i];
    }

    public Vuelo getVuelo(String id){
        boolean encontrado = false;
        int i=0;
        Vuelo v = null;
        while ((!encontrado)&&(i<listarVuelo.length)){
            if (id.equals(listarVuelo[i].getIdentificador())){
                encontrado = true;
                v = listarVuelo[i];
            }
            i++;
        }
        return v;
    }

}
