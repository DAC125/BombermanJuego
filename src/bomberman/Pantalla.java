package bomberman;

public class Pantalla {
    protected int ancho, largo;
    public int[] pixeles;
    public static int xOffset = 0, yOffset = 0;

    public Pantalla(int ancho, int largo){
        this.ancho = ancho;
        this.largo = largo;
        pixeles = new int[ancho*largo];
    }

    public void limpiar(){
        for(int i = 0; i < pixeles.length; i++){
            pixeles[i] = 0;
        }
    }
}
