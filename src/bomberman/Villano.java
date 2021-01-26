package bomberman;

import java.awt.image.BufferedImage;

//Clase que posee todos los metodos y atribuitos necesarios para el manejo de cualquier villano
public class Villano {
    private static IA ia;
    private float velocidad;//Está en relación a la velocidad de Bomberman
    private int puntaje;//Puntos que da a la hora de matarlo
    private int inteligencia;//Nivel de Inteligencia, especificadas en la clase IA
    private int muros;
    private static int nivel;
    private int x;//pos
    private int y;//pos
    public static BufferedImage animacion;
    private static Nivel nivelMapa;//Nivel para acceder a mapa
    public Tablero tablero;

    //Constructor de la clase
    public Villano(int nivel, int x, int y, Nivel nivelMapa){
        float[] parametros = clasificacion(nivel);
        this.nivel = nivel;
        this.velocidad = parametros[0];
        this.muros = (int)parametros[1];
        this.inteligencia = (int)parametros[2];
        this.puntaje = (int)parametros[3];
        this.x = x;
        this.y = y;
        this.nivelMapa = nivelMapa;
        System.out.println("Nivel = " +this.nivel+ " Velocidad = " +this.velocidad+ " Muros = "+this.muros+ " Inteligencia = "+this.inteligencia + " Puntaje = " +this.puntaje+" PosX = "+this.x+" PosY = "+this.y);
    }

    //Iniciliza todo lo necesario para tener un villano
    public static void init(){
        ia = new IA(nivelMapa);
        switch (nivel){
            case 1:
                animacion = Imagenes.globo;
        }
    }

    //Depende del nivel del villano, este tendrá diverdas caracteristicas, este metodo se encarga de
    //obtener esas estadísticas
    private float[] clasificacion(int nivel){
        if(nivel>=1 && nivel<=3){
            return ParametrosVillanos.primeros[nivel-1];
        }else if(nivel>3 && nivel<6){
            return ParametrosVillanos.primeros[2];
        }else if(nivel>=6 && nivel<8){
            return ParametrosVillanos.niv6;
        }else if(nivel>=8 && nivel<11){
            return ParametrosVillanos.niv8;
        }else if(nivel>=11 && nivel<14){
            return ParametrosVillanos.niv11;
        }else{
            return ParametrosVillanos.niv14;
        }
    }

    //Obtiene el x,y del siguiente que debería de realizar el Enemigo
    public void update(){
        siguienteMov(nivel);
    }

    //Según el Nivel del villano, este metodo actualiza el x,y del enemigo, según la inteligencia que el mismo posee
    public void siguienteMov(int nivel){
        int[] nextmov;
        switch (nivel){
            case 1:
                nextmov = ia.nivel1(tablero.xPlayer,tablero.yPlayer,x,y);
                this.x = nextmov[0];
                this.y = nextmov[1];
                break;
            case 2:
                nextmov = ia.nivel2(tablero.xPlayer,tablero.yPlayer,x,y);
                this.x = nextmov[0];
                this.y = nextmov[1];
                break;
            case 3:
                nextmov = ia.nivel3(tablero.xPlayer,tablero.yPlayer,x,y);
                this.x = nextmov[0];
                this.y = nextmov[1];
                break;
            case 4:
                nextmov = ia.nivel4(tablero.xPlayer,tablero.yPlayer,x,y);
                this.x = nextmov[0];
                this.y = nextmov[1];
                break;
        }
    }

    public int getNivel() {
        return nivel;
    }

    public static BufferedImage getAnimacion() {
        return animacion;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
