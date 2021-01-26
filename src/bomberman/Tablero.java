package bomberman;

import java.awt.*;
import java.awt.image.BufferedImage;

//Clase encargada de manejar todo lo relacionado al tablero, que es la representación de lo que está en pantalla
public class Tablero implements ParametrosMapa{
    protected Nivel nivel;//Nivel actual del Juego
    protected Juego juego;
    protected Teclado teclado;
    protected Pantalla pantalla;
    private int tiempo = JuegoConfig.TIEMPO;
    private int puntos = JuegoConfig.PUNTOS;
    private int vidas = JuegoConfig.VIDAS;
    private int pantallaMostar = -1;  //1: fin de juego, 2: cambiar nivel, 3: juego en pausa
    public int xPlayer = 48;//Pos inicial del Juego
    public int yPlayer = 48;//Pos inicial del Juego
    private int movimiento = 0, estado = 1;

    private Animacion animAbajo,animArr,animIzq,animDer;

    //Constructor de la clase
    public Tablero(Juego juego, Teclado teclado, Pantalla pantalla){
        //Inicializa los componentes básicos
        this.juego = juego;
        this.teclado = teclado;
        this.pantalla = pantalla;

        //E inicia el juego en el nivel 1
        cambiarNivel(1);
    }

    //Actualiza como se ve el personaje
    public void actualiza(){
        if(juego.estaPausado())return;
        animAbajo.tick();
        animDer.tick();
        animIzq.tick();
        animArr.tick();
        actualizaPlayer();
        //System.out.println("x:"+xPlayer/48+" y:"+yPlayer/48);
        //nivel.imprimir(nivel.getMapa());

    }

    //encargado de animar el personaje
    public void animacion(){
        animAbajo = new Animacion(250,Imagenes.player_abajo);
        animArr = new Animacion(250,Imagenes.player_arriba);
        animIzq = new Animacion(250,Imagenes.player_izquierda);
        animDer = new Animacion(250,Imagenes.player_derecha);
    }

    //Renderiza al jugador o BomberMan en pantalla según el moviemiento que tenga
    public BufferedImage renderPlayer(){

        if (movimiento == 0){
            if (estado==1)
                return Imagenes.playerDer;
            if (estado==2)
                return Imagenes.playerAbj;
            if (estado==3)
                return Imagenes.playerIzq;
            if (estado==4)
                return Imagenes.playerArr;
        }

        if (movimiento == 1)
            return animDer.getCurrentFrame();
        if (movimiento == 2)
            return animAbajo.getCurrentFrame();
        if (movimiento == 3)
            return animIzq.getCurrentFrame();
        if (movimiento == 4)
            return animArr.getCurrentFrame();

        return null;
    }

    //renderiza a los villanos
    public Villano renderVillano(int index){
        Villano villano = nivel.getEnemigoEspecifico(index);
        return villano;

    }

    //detecta posiciones en el tablero de "char" para saber si las entidades se pueden seguir moviendo en x dirección
    public boolean colicion(int x, int y){
        char[][] mapa = nivel.getMapa();
        if (mapa[y][x]==Vacio||mapa[y][x]==Bomberman){//Si choca con algo, quiere decir que no puede pasar
            return false;
        }else{
            return true;
        }
    }

    //Método encargado de actualizar el movimiento de BomberMan captando la entrada por teclado
    //del Jugador, se encarga de ver si hay colisiones y moverlo
    private void actualizaPlayer(){

        movimiento = 0;
        char[][] mapa = nivel.getMapa();
        if(teclado.derecha) {
            if (!colicion((xPlayer+48)/48, yPlayer/48)) {
                xPlayer += JuegoConfig.velocidadPlay;
                movimiento = 1;
            }else{
              movimiento = 0;
            }
            estado = 1;
            if (mapa[yPlayer/48][xPlayer/48]!=Bomberman){
                nivel.setMapaMov((xPlayer-2)/48,yPlayer/48,xPlayer/48, yPlayer/48);
            }

        }

        if (teclado.abajo){
            if (!colicion(xPlayer/48, (yPlayer+48)/48)){
                yPlayer += JuegoConfig.velocidadPlay;
                movimiento = 2;
            }else{
                movimiento = 0;
            }
            estado=2;
            if (mapa[yPlayer/48][xPlayer/48]!=Bomberman){
                nivel.setMapaMov(xPlayer/48,(yPlayer-2)/48,xPlayer/48, yPlayer/48);
            }
        }

        if(teclado.izquierda) {
            if (!colicion((xPlayer-2)/48, (yPlayer)/48)){
                xPlayer -= JuegoConfig.velocidadPlay;
                movimiento=3;
            }else{
                movimiento = 0;
            }
            estado=3;
            if (mapa[yPlayer/48][xPlayer/48]!=Bomberman){
                nivel.setMapaMov((xPlayer+2)/48,yPlayer/48,xPlayer/48, yPlayer/48);
            }
        }

        if (teclado.arriba) {
            if (!colicion((xPlayer)/48, (yPlayer-2)/48)) {
                yPlayer -= JuegoConfig.velocidadPlay;
                movimiento = 4;
            }
            estado=4;
            if (mapa[yPlayer/48][xPlayer/48]!=Bomberman){
                nivel.setMapaMov(xPlayer/48,(yPlayer+2)/48,xPlayer/48, yPlayer/48);
            }
        }

    }

    //se encarga de renderizar todo lo relacionado al tablero
    public BufferedImage[][] renderTablero() {
        if( juego.estaPausado()) return null;


        char[][] mapa = nivel.getMapa();
        BufferedImage entidades[][] = new BufferedImage[13][31];

        for (int y = 0; y <JuegoConfig.ANCHOMAPA; y++){
            for (int x = 0; x < JuegoConfig.LARGOMAPA; x++){
                switch (mapa[y][x]){
                    case Acero:
                        entidades[y][x]=Imagenes.muroAcero;
                        break;
                    case Vacio:
                        entidades[y][x]=Imagenes.pasto;
                        break;
                    case Muralla:
                        entidades[y][x]=Imagenes.muroPiedra;
                        break;
                    case Globo:
                        entidades[y][x]=Imagenes.globo;
                        break;
                    case Haki:
                        entidades[y][x]=Imagenes.haki;
                        break;
                    case Cel:
                        entidades[y][x]=Imagenes.cel;
                        break;
                    case Espon:
                        entidades[y][x]=Imagenes.espon;
                        break;
                    case Fant:
                        entidades[y][x]=Imagenes.fant;
                        break;
                    case Mon:
                        entidades[y][x]=Imagenes.mon;
                        break;
                    case MonG:
                        entidades[y][x]=Imagenes.mong;
                        break;
                    default:
                        entidades[y][x]=Imagenes.pasto;
                        break;

                }
            }
        }

        return entidades;


    }

    //Método encargado de cambiar y generar el nivel que se muestra en pantalla
    public void cambiarNivel(int lvl){
        tiempo = JuegoConfig.TIEMPO;
        pantallaMostar = 2;
        nivel = new Nivel(JuegoConfig.ANCHOMAPA, JuegoConfig.LARGOMAPA, JuegoConfig.probMuro, lvl, JuegoConfig.numVillanos);
        nivel.imprimir(nivel.getMapa());

    }

    //Dibuja la pantalla para ser capaz de verla en "pantalla"
    public void drawScreen(Graphics g) {
        switch (pantallaMostar) {
            /*case 1:
                _screen.drawEndGame(g, _points, _level.getActualCode());
                break;*/
            case 2:
                pantalla.drawChangeLevel(g);
                break;/*
            case 3:
                _screen.drawPaused(g);
                break;*/
        }
    }

    public void setPantallaMostar(int patalla){
        this.pantallaMostar = patalla;
    }
    public int getPantallaMostar(){
        return pantallaMostar;
    }
    public int getTimpo() {
        return tiempo;
    }

    public int getPuntos() {
        return puntos;
    }

    public int getVidas(){
        return vidas;
    }

    //resta al tiempo restante del Juego
    public int restaTiempo() {
        if(juego.estaPausado())
            return this.tiempo;
        else
            return this.tiempo--;
    }

}
