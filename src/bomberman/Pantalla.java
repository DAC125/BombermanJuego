package bomberman;

import java.awt.*;

public class Pantalla {
    protected int ancho, largo;
    public int[] pixeles;
    public static int xOffset = 0, yOffset = 0;
    private JuegoConfig  config = new JuegoConfig();

    public Pantalla(int ancho, int largo){
        this.ancho = ancho;
        this.largo = largo;
        pixeles = new int[ancho*largo];
    }

    public void drawChangeLevel(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, getRealLargo(), getRealancho());

        Font font = new Font("Arial", Font.PLAIN, 20 * JuegoConfig.ESCALA);
        g.setFont(font);
        g.setColor(Color.white);
        drawCenteredString("Ricky me la suda " , getRealLargo(), getRealancho(), g);

    }

    public void drawCenteredString(String s, int w, int h, Graphics g) {
        FontMetrics fm = g.getFontMetrics();
        int x = (w - fm.stringWidth(s)) / 2;
        int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2);
        g.drawString(s, x, y);
    }

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
