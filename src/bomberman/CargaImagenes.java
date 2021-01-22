package bomberman;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class CargaImagenes {

    private String ruta;
    public final int TAMAÑO = 16;
    public int[] pixeles = new int[16*16];;

    public static String muroAcero = "res/Board/MuroAcero.png";
   // public static String pasto = "/res/Board/Fondo.png";


    public CargaImagenes(String ruta){
        this.ruta = ruta;
        cargar();
    }

    private void cargar(){
        try {
            //URL url = CargaImagenes.class.getResource(ruta);
            File file = new File(ruta);
            BufferedImage image = ImageIO.read(file);
            int largo = image.getWidth();
            int ancho = image.getHeight();
            image.getRGB(0, 0, largo, ancho, pixeles, 0, largo);
        }catch (IOException e){
            e.printStackTrace();
            System.exit(0);
        }
    }
}
