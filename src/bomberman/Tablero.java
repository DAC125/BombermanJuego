package bomberman;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tablero {
    protected Nivel nivel;
    protected Juego juego;
    protected Teclado teclado;
    protected Pantalla pantalla;
    private int tiempo = JuegoConfig.TIEMPO;
    private int puntos = JuegoConfig.PUNTOS;
    private int vidas = JuegoConfig.VIDAS;
    private int pantallaMostar = -1;  //1: fin de juego, 2: cambiar nivel, 3: juego en pausa
    public int xPlayer = 48;
    public int yPlayer = 48;
    private int movimiento = 0, estado = 1;

    private Animacion animAbajo,animArr,animIzq,animDer;

    public Tablero(Juego juego, Teclado teclado, Pantalla pantalla){
        this.juego = juego;
        this.teclado = teclado;
        this.pantalla = pantalla;

        cambiarNivel(1);

    }

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
    public void animacion(){
        animAbajo = new Animacion(250,Imagenes.player_abajo);
        animArr = new Animacion(250,Imagenes.player_arriba);
        animIzq = new Animacion(250,Imagenes.player_izquierda);
        animDer = new Animacion(250,Imagenes.player_derecha);
    }
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

    public Villano renderVillano(int index){
        Villano villano = nivel.getEnemigoEspecifico(index);
        return villano;

    }

    public boolean colicion(int x, int y){
        char[][] mapa = nivel.getMapa();
        if (mapa[y][x]==' '||mapa[y][x]=='B'){
            return false;
        }else{
            return true;
        }
    }

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
            if (mapa[yPlayer/48][xPlayer/48]!='B'){
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
            if (mapa[yPlayer/48][xPlayer/48]!='B'){
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
            if (mapa[yPlayer/48][xPlayer/48]!='B'){
                nivel.setMapaMov((xPlayer+2)/48,yPlayer/48,xPlayer/48, yPlayer/48);
            }
        }

        if (teclado.arriba) {
            if (!colicion((xPlayer)/48, (yPlayer-2)/48)) {
                yPlayer -= JuegoConfig.velocidadPlay;
                movimiento = 4;
            }
            estado=4;
            if (mapa[yPlayer/48][xPlayer/48]!='B'){
                nivel.setMapaMov(xPlayer/48,(yPlayer+2)/48,xPlayer/48, yPlayer/48);
            }
        }

    }

    public BufferedImage[][] renderTablero() {
        if( juego.estaPausado()) return null;


        char[][] mapa = nivel.getMapa();
        BufferedImage entidades[][] = new BufferedImage[13][31];

        for (int y = 0; y <JuegoConfig.ANCHOMAPA; y++){
            for (int x = 0; x < JuegoConfig.LARGOMAPA; x++){
                switch (mapa[y][x]){
                    case 'X':
                        entidades[y][x]=Imagenes.muroAcero;
                        break;
                    case ' ':
                        entidades[y][x]=Imagenes.pasto;
                        break;
                    case '_':
                        entidades[y][x]=Imagenes.muroPiedra;
                        break;
                    default:
                        entidades[y][x]=Imagenes.pasto;
                        break;

                }
            }
        }

        return entidades;


    }



    public void cambiarNivel(int lvl){
        tiempo = JuegoConfig.TIEMPO;
        pantallaMostar = 2;
        nivel = new Nivel(JuegoConfig.ANCHOMAPA, JuegoConfig.LARGOMAPA, JuegoConfig.probMuro, lvl, JuegoConfig.numVillanos);
        nivel.imprimir(nivel.getMapa());

    }

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

    public int restaTiempo() {
        if(juego.estaPausado())
            return this.tiempo;
        else
            return this.tiempo--;
    }

}
