package bomberman;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Juego extends Canvas implements Runnable {

    private Frame frame;
    private JuegoConfig config;
    private Teclado teclado;

    private boolean corriendo = false;
    private boolean pausa = true;

    private Tablero tablero;
    private Pantalla pantalla;
    private int delayTempLvl = config.DELAYTEMPLVL;

    private Thread thread;

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();



    public Juego(Frame frame){
        this.frame = frame;
        frame.setTitle("Bomberman");
        pantalla = new Pantalla(config.ANCHO, config.LARGO);

        teclado = new Teclado();

        tablero = new Tablero(this, teclado, pantalla);

        addKeyListener(teclado);

    }

    private void init(){
        Imagenes.init();


    }
    private void renderJuego(){
        BufferStrategy bs = getBufferStrategy();
        if(bs == null) {
            createBufferStrategy(3);
            return;
        }

        pantalla.limpiar();

        BufferedImage render[][] = tablero.render(pantalla);



        Graphics g = bs.getDrawGraphics();
        for (int y = 0; y <13; y++) {
            for (int x = 0; x < 31; x++) {
                g.drawImage(render[y][x],x*48,y*48,null);
            }
        }


        g.drawImage(tablero.renderPlayer(), tablero.xPlayer, tablero.yPlayer, null);


        g.dispose(); //release resources
        bs.show(); //make next buffer visible
    }

    private void renderPantalla() { //TODO: merge these render methods
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

    private void actualizar() {
        teclado.actualiza();
        tablero.actualiza();
    }


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

            if (delta >= 1){
                actualizar();
                frames++;
                delta--;
            }

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

    public synchronized void start(){
        if (corriendo)
            return;
        corriendo = true;
        thread = new Thread(this);
        thread.start();
    }

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
