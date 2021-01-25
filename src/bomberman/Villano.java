package bomberman;

public class Villano {
    private float velocidad;
    private int puntaje;
    private int inteligencia;
    private int muros;
    private int nivel;
    private int x;
    private int y;


    public Villano(int nivel, int x, int y){
        float[] parametros = clasificacion(nivel);
        this.nivel = nivel;
        this.velocidad = parametros[0];
        this.muros = (int)parametros[1];
        this.inteligencia = (int)parametros[2];
        this.puntaje = (int)parametros[3];
        this.x = x;
        this.y = y;
        System.out.println("Nivel = " +this.nivel+ " Velocidad = " +this.velocidad+ " Muros = "+this.muros+ " Inteligencia = "+this.inteligencia + " Puntaje = " +this.puntaje+" PosX = "+this.x+" PosY = "+this.y);
    }

    private float[] clasificacion(int nivel){
        if(nivel>=1 && nivel<=3){
            return ParametrosVillanos.primeros[nivel-1];
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
}
