package bomberman;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

//Esta clase se encarga de correr el while principal del Juego
//Donde todo se va a actualizar, ademas de inicializar todo lo referente a entrada por teclado y sonidos
//del juego
public class Juego extends Canvas implements Runnable {

    private Frame frame;
    private JuegoConfig config;
    private Teclado teclado;//Encagado de recibir entrada por teclado del jugador

    private boolean corriendo = false;
    private boolean pausa = true;

    private Tablero tablero;//Información de donde se encuentra todo en el backend
    private Pantalla pantalla;
    private int delayTempLvl = config.DELAYTEMPLVL;

    private Thread thread;

    public Sound sound;

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

    //Constructor de la clase, Inicializa el Juego
    public Juego(Frame frame){
        this.frame = frame;
        frame.setTitle("Bomberman");
        pantalla = new Pantalla(config.ANCHO, config.LARGO);

        teclado = new Teclado();

        tablero = new Tablero(this, teclado, pantalla);
        sound = new Sound();

        addKeyListener(teclado);

    }

    private void init(){
        Imagenes.init();//Carga las imágenes
        sound.reproducirSonidoBG();//reeproduce el sonido de Fondo
        Villano.init();//Inicializa los villanos
    }

    //renderuiza en pantalla el juego en sí
    private void renderJuego(){
        BufferStrategy bs = getBufferStrategy();
        if(bs == null) {
            createBufferStrategy(3);
            return;
        }

        pantalla.limpiar();

        //renderiza el tablero || pasto, Muro Piedra, Muro Acero
        BufferedImage render[][] = tablero.renderTablero();
        Graphics g = bs.getDrawGraphics();
        for (int y = 0; y <JuegoConfig.ANCHOMAPA; y++) {
            for (int x = 0; x < JuegoConfig.LARGOMAPA; x++) {
                g.drawImage(render[y][x],x*48,y*48,null);
            }
        }

        //renderiza el player, BomberMan
        g.drawImage(tablero.renderPlayer(), tablero.xPlayer, tablero.yPlayer, null);

        //renderiza los villanos
        for (int x = 0; x<tablero.nivel.getEnemigos().size();x++) {
            g.drawImage(tablero.renderVillano(x).getAnimacion(), tablero.renderVillano(x).getY()*48, tablero.renderVillano(x).getX()*48, null);
        }


        g.dispose(); //Libera recursos
        bs.show(); //Hace el siguiente Buffer Visible
    }

    //Limpia la pantalla, para posteriormente volver a dibujar en ella
    private void renderPantalla() {
        BufferStrategy bs = getBufferStrategy();
        if(bs == null) {
            createBufferStrategy(3);
            return;
        }

        pantalla.limpiar();

        Graphics g = bs.getDrawGraphics();


        tablero.drawScreen(g);

        g.dispose();
        bs.show();
    }

    //Actualiza las entradas por teclado y el tablero o matriz del juego
    private void actualizar() {
        teclado.actualiza();
        tablero.actualiza();
    }


    //Game loop, el ciclo principal del juego, para que este siga corriendo y actualizando todo lo necesario
    //Se vuelve a pasar por el ciclo una y otra vez, hasta que de alguna manera este se detenga
    @Override
    public void run() {
        init();
        tablero.animacion();
        corriendo = true;
        int fps = 60;
        double timePerFrame = 1000000000 / fps;
        double delta = 0;
        long tiempoAhora;
        long tiempoUltimo = System.nanoTime();
        long timer = 0;
        int frames = 0;
        requestFocus();
        while (corriendo){
            tiempoAhora = System.nanoTime();
            delta += (tiempoAhora - tiempoUltimo)/timePerFrame;
            timer += tiempoAhora - tiempoUltimo;
            tiempoUltimo = tiempoAhora;

            //se actualiza cada x tiempo
            if (delta >= 1){
                actualizar();
                frames++;
                delta--;
            }

            //Si está en pausa no es necesario estar actualizando todo una y otra vez
            //Si no se renderiza
            if(pausa){
                if (delayTempLvl <= 0){
                    tablero.setPantallaMostar(-1);
                    pausa = false;
                }
                renderPantalla();
            }else{
                renderJuego();
            }

            if (timer >= 1000000000){
                frame.setTiempo(tablero.restaTiempo());
                frame.setPuntos(tablero.getPuntos());
                frame.setVidas(tablero.getVidas());
                //System.out.println("fps:" + frames);
                frames = 0;
                timer = 0;
                if(tablero.getPantallaMostar() == 2)
                    --delayTempLvl;
            }
        }
    }

    //Inicializa el hilo principal del Juego
    public synchronized void start(){
        if (corriendo)
            return;
        corriendo = true;
        thread = new Thread(this);
        thread.start();
    }

    //detiene el hilo principal del Juego
    public synchronized void stop(){
        if (!corriendo)
            return;
        corriendo= false;
        try {
            thread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }




    public boolean estaPausado(){
        return pausa;
    }

    public boolean estaCoriendo(){
        return corriendo;
    }

    public Tablero getTablero(){
        return tablero;
    }


}
