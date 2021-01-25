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
        playerDer = CargaImagenes.cargaImagen("/texturas/BomberMan/BomberDer.png");
        playerAbj = CargaImagenes.cargaImagen("/texturas/BomberMan/BomberDown.png");
        playerIzq = CargaImagenes.cargaImagen("/texturas/BomberMan/BomberIzq.png");
        playerArr = CargaImagenes.cargaImagen("/texturas/BomberMan/BomberUp.png");

        //Carga imagenes para animacion player abajo
        player_abajo = new BufferedImage[2];
        player_abajo[0] = CargaImagenes.cargaImagen("/texturas/BomberMan/BomberDown1.png");
        player_abajo[1] = CargaImagenes.cargaImagen("/texturas/BomberMan/BomberDown2.png");

        //Carga imagenes para animacion player arriba
        player_arriba = new BufferedImage[2];
        player_arriba[0] = CargaImagenes.cargaImagen("/texturas/BomberMan/BomberUp1.png");
        player_arriba[1] = CargaImagenes.cargaImagen("/texturas/BomberMan/BomberUp2.png");

        //Carga imagenes para animacion player derecho
        player_derecha = new BufferedImage[2];
        player_derecha[0] = CargaImagenes.cargaImagen("/texturas/BomberMan/BomberDer1.png");
        player_derecha[1] = CargaImagenes.cargaImagen("/texturas/BomberMan/BomberDer2.png");

        //Carga imagnes para animacion player izquierda
        player_izquierda = new BufferedImage[2];
        player_izquierda[0] = CargaImagenes.cargaImagen("/texturas/BomberMan/BomberIzq1.png");
        player_izquierda[1] = CargaImagenes.cargaImagen("/texturas/BomberMan/BomberIzq2.png");

        //Carga imagenes del tablero
        muroAcero = CargaImagenes.cargaImagen("/texturas/Board/MuroAcero.png");
        pasto = CargaImagenes.cargaImagen("/texturas/Board/Fondo.png");
        muroPiedra = CargaImagenes.cargaImagen("/texturas/Board/Muro.png");


    }



}
