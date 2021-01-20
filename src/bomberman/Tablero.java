package bomberman;

import java.awt.*;

public class Tablero {
    protected Nivel nivel;
    protected Juego juego;
    protected Teclado teclado;
    protected Pantalla pantalla;
    private int tiempo = JuegoConfig.TIEMPO;
    private int puntos = JuegoConfig.PUNTOS;
    private int vidas = JuegoConfig.VIDAS;
    private int pantallaMostar = -1;  //1: fin de juego, 2: cambiar nivel, 3: juego en pausa

    public Tablero(Juego juego, Teclado teclado, Pantalla pantalla){
        this.juego = juego;
        this.teclado = teclado;
        this.pantalla = pantalla;

        cambiarNivel(1);
    }

    public void actualiza(){
        if(juego.estaPausado())return;
    }

    public void render(Pantalla pantalla) {
        if( juego.estaPausado()) return;

        //only render the visible part of screen
        int x0 = Pantalla.xOffset >> 4; //tile precision, -> left X
        int x1 = (Pantalla.xOffset + pantalla.getLargo() + JuegoConfig.TAMAÑO_BLOQUE) / JuegoConfig.TAMAÑO_BLOQUE; // -> right X
        int y0 = Pantalla.yOffset >> 4;
        int y1 = (Pantalla.yOffset + pantalla.getAncho()) / JuegoConfig.TAMAÑO_BLOQUE; //render one tile plus to fix black margins

        char[][] mapa = nivel.getMapa();

        for (int y = y0; y <y1; y++){
            for (int x = x0; y< x1; x++){
                //pantalla.renderEntidades(x*16,y*16,mapa[x][y]);
            }
        }
        /*
        renderBombs(screen);
        renderMobs(screen);*/

    }



    public void cambiarNivel(int lvl){
        tiempo = JuegoConfig.TIEMPO;
        pantallaMostar = 2;
        nivel = new Nivel(13, 31, 25, 1, 20);
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
