package bomberman;

import java.awt.image.BufferedImage;

public class Imagenes {

    public static BufferedImage muroAcero, pasto, muroPiedra;;
    public static BufferedImage[] player_abajo;
    public static BufferedImage[] player_arriba;
    public static BufferedImage[] player_izquierda;
    public static BufferedImage[] player_derecha;


    public static BufferedImage playerDer,playerArr,playerAbj,playerIzq;

    public static void init(){
        playerDer = CargaImagenes.cargaImagen("/BomberMan/BomberDer.png");
        playerAbj = CargaImagenes.cargaImagen("/BomberMan/BomberDown.png");
        playerIzq = CargaImagenes.cargaImagen("/BomberMan/BomberIzq.png");
        playerArr = CargaImagenes.cargaImagen("/BomberMan/BomberUp.png");

        //Carga imagenes para animacion player abajo
        player_abajo = new BufferedImage[2];
        player_abajo[0] = CargaImagenes.cargaImagen("/BomberMan/BomberDown1.png");
        player_abajo[1] = CargaImagenes.cargaImagen("/BomberMan/BomberDown2.png");

        //Carga imagenes para animacion player arriba
        player_arriba = new BufferedImage[2];
        player_arriba[0] = CargaImagenes.cargaImagen("/BomberMan/BomberUp1.png");
        player_arriba[1] = CargaImagenes.cargaImagen("/BomberMan/BomberUp2.png");

        //Carga imagenes para animacion player derecho
        player_derecha = new BufferedImage[2];
        player_derecha[0] = CargaImagenes.cargaImagen("/BomberMan/BomberDer1.png");
        player_derecha[1] = CargaImagenes.cargaImagen("/BomberMan/BomberDer2.png");

        //Carga imagnes para animacion player izquierda
        player_izquierda = new BufferedImage[2];
        player_izquierda[0] = CargaImagenes.cargaImagen("/BomberMan/BomberIzq1.png");
        player_izquierda[1] = CargaImagenes.cargaImagen("/BomberMan/BomberIzq2.png");


        muroAcero = CargaImagenes.cargaImagen("/MuroAcero.png");
        pasto = CargaImagenes.cargaImagen("/Fondo.png");
        muroPiedra = CargaImagenes.cargaImagen("/Muro.png");


    }



}
