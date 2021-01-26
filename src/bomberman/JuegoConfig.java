package bomberman;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

//clase encargada de setear todas las variables "globales" que se ocupan durante el Juego
public class JuegoConfig {
    public static final int TAMAÑO_BLOQUE = 16,
                            FRECOBOMB = 1,
                            REDBOMB = 1,
                            TIEMPO = 200,//variables utilizadas en panelInfo en Frame
                            PUNTOS = 0,//variables utilizadas en panelInfo en Frame
                            VIDAS = 3,//variables utilizadas en panelInfo en Frame
                            DELAYTEMPLVL = 3,
                            ESCALA = 3;

    public static int LARGOMAPA=0,ANCHOMAPA=0;

    public static int LARGO, ANCHO;
    public static int numVillanos = 5;//Número de villanos totales que van a aparecer en el mapa
    public static int anchoVentana = 13,largoVentana = 15;
    public static int probMuro = 0;//probabilidad de que exista en muro en cierta pos
    public static int velocidadPlay=0;//velocidad del jugador

    //Metodo encargado de leer el archivo de configuración del Juego
    public static void Leertxt(){
        String[] texto = new String[7];
        int index = 0;

        try {
            File file =  new File("./config");//ruta del archivo
            System.out.println(file.getAbsolutePath());
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file.getAbsolutePath()));
            String bfRead;
            while ((bfRead = bufferedReader.readLine()) != null){
                texto[index] = bfRead;
                index++;
            }
            String[] dimentions = texto[0].split(" ");
            //se setean las variables
            ANCHOMAPA = Integer.parseInt(dimentions[0]);
            LARGOMAPA = Integer.parseInt(dimentions[1]);
            probMuro = Integer.parseInt(texto[1]);
            numVillanos = Integer.parseInt(texto[2]);
            velocidadPlay = Integer.parseInt(texto[3]);
            LARGO = TAMAÑO_BLOQUE*LARGOMAPA;
            ANCHO = TAMAÑO_BLOQUE*ANCHOMAPA;


        }catch (Exception e){
            System.err.println("Error con el archivo de configuracion");
            System.exit(1);
        }
    }


}
