package bomberman;

import java.awt.image.BufferedImage;

//clase encargada de cargar de memoria todas las imagenes necesitadas en el juego
//Jugador, enemifo, muros, etc
public class Imagenes {

    public static BufferedImage muroAcero, pasto, muroPiedra;;

    public static BufferedImage globo;
    public static BufferedImage cel;
    public static BufferedImage espon;
    public static BufferedImage fant;
    public static BufferedImage haki;
    public static BufferedImage mon;
    public static BufferedImage mong;

    //Motivo de que sea arreglo: Lograr una animación de caminar, aunque este en la misma direcciónm
    public static BufferedImage[] player_abajo;
    public static BufferedImage[] player_arriba;
    public static BufferedImage[] player_izquierda;
    public static BufferedImage[] player_derecha;


    public static BufferedImage playerDer,playerArr,playerAbj,playerIzq;

    public static void init(){
        //Carga imagenes para player detenido
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

        globo = CargaImagenes.cargaImagen("/texturas/Enemies/Bomba.png");
        cel = CargaImagenes.cargaImagen("/texturas/Enemies/Cel.png");
        espon = CargaImagenes.cargaImagen("/texturas/Enemies/Espon.png");
        fant = CargaImagenes.cargaImagen("/texturas/Enemies/Fant.png");
        haki = CargaImagenes.cargaImagen("/texturas/Enemies/Haki.png");
        mon = CargaImagenes.cargaImagen("/texturas/Enemies/Mon.png");
        mong = CargaImagenes.cargaImagen("/texturas/Enemies/MonG.png");




    }



}
