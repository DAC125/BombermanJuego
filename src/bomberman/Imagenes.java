package bomberman;

import java.awt.image.BufferedImage;

public class Imagenes {

    public static BufferedImage muroAcero, pasto, muroPiedra;

    public static BufferedImage playerDer;

    public static void init(){
       muroAcero = CargaImagenes.cargaImagen("/MuroAcero.png");
       pasto = CargaImagenes.cargaImagen("/Fondo.png");
       muroPiedra = CargaImagenes.cargaImagen("/Muro.png");
       playerDer = CargaImagenes.cargaImagen("/BomberDer.png");
    }



}
