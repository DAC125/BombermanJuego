package bomberman;

import java.awt.*;
import java.awt.image.BufferedImage;

//Clase encargada de renderizar la pantalla del Juego cuando se cambia de nivel y otros
public class Pantalla {
    BufferedImage entidades[][] = new BufferedImage[JuegoConfig.ANCHO][JuegoConfig.LARGO];
    protected int ancho, largo;
    public int[] pixeles;
    public static int xOffset = 0, yOffset = 0;
    private JuegoConfig config = new JuegoConfig();

    //Constructor de la clase
    public Pantalla(int ancho, int largo) {
        this.ancho = ancho;
        this.largo = largo;
        pixeles = new int[ancho * largo];
    }


   /* public void renderEntidades(int xp, int yp,char entidad){



/*
for (int y = 0; y < 16; y++) {
                    int ya = y + yp; //add offset
                    for (int x = 0; x < 16; x++) {
                        int xa = x + xp; //add offset
                        if(xa < -16 || xa >= largo || ya < 0 || ya >= ancho) break; //fix black margins
                        if(xa < 0) xa = 0; //start at 0 from left
                        int color = imagen.getPixel(x + y * 16);
                        pixeles[xa + ya * largo] = color;
                        //System.out.println(color);
                    }
                }

    }*/

    //Renderiza la pantalla cuando se cambia de nivel
    public void drawChangeLevel(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, getRealLargo(), getRealancho());

        Font font = new Font("Arial", Font.PLAIN, 20 * JuegoConfig.ESCALA);
        g.setFont(font);
        g.setColor(Color.white);
        drawCenteredString("Nivel " + Nivel.nivel , getRealLargo(), getRealancho(), g);

    }

    //Coloca lo que se quiera renderizar enn el centro de la pantalla
    public void drawCenteredString(String s, int w, int h, Graphics g) {
        FontMetrics fm = g.getFontMetrics();
        int x = (w - fm.stringWidth(s)) / 2;
        int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2);
        g.drawString(s, x, y);
    }

    //Limpia toda la pantllla
    public void limpiar(){
        for(int i = 0; i < pixeles.length; i++){
            pixeles[i] = 0;
        }
    }

    public int getLargo(){return largo;}

    public int getAncho(){return ancho;}

    public int getRealLargo(){return largo * JuegoConfig.ESCALA;}

    public int getRealancho(){return ancho * JuegoConfig.ESCALA;}
}
