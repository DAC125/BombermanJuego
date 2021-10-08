package bomberman;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

//CLASE encargada de cargar una imagen en un BufferedImage dada una dirección
//clase usada en Imagenes para cargar las imagenes del juego
public class CargaImagenes {

    public static BufferedImage cargaImagen(String ruta){
        try {
            return ImageIO.read(CargaImagenes.class.getResource(ruta));
        }catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
    /*
    private String ruta;
    public final int TAMAÑO = 16;
    public int[] pixeles = new int[16*16];;

    public static String muroAcero = "res/Board/Muro.png";
    public static String pasto = "res/Board/Fondo.png";

    public CargaImagenes(){

    }
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
    }*/
}
