package bomberman;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Juego extends Canvas{

    private Frame frame;
    private JuegoConfig config;
    private Teclado teclado;

    private boolean corriendo = false;
    private boolean pausa = true;

    private Tablero tablero;
    private Pantalla pantalla;
    private int delayTempLvl = config.DELAYTEMPLVL;


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

    private void renderJuego(){
        BufferStrategy bs = getBufferStrategy();
        if(bs == null) {
            createBufferStrategy(3);
            return;
        }

        pantalla.limpiar();

        tablero.render(pantalla);

        for (int i = 0; i < pixels.length; i++) { //create the image to be rendered
            pixels[i] = pantalla.pixeles[i];
        }

        Graphics g = bs.getDrawGraphics();

        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);


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

    public void iniciaJuego(){
        corriendo = true;
        long ultimoTemp = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double nanosegs = 1000000000.0 / 60.0;
        double delta = 0;
        int frames = 0;
        int actualizaciones = 0;
        requestFocus();
        while(corriendo){
            long tempAhora = System.nanoTime();
            delta += (tempAhora - ultimoTemp) / nanosegs;
            ultimoTemp = tempAhora;
            while (delta >=1){
                actualizar();
                actualizaciones++;
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
            frames++;
            if(System.currentTimeMillis() - timer > 1000) { //once per second
                frame.setTiempo(tablero.restaTiempo());
                frame.setPuntos(tablero.getPuntos());
                frame.setVidas(tablero.getVidas());
                timer += 1000;
                actualizaciones = 0;
                frames = 0;

                if(tablero.getPantallaMostar() == 2)
                    --delayTempLvl;
            }
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
