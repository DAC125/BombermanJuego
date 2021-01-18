package bomberman;
import java.awt.*;
public class Juego extends Canvas{

    private Frame frame;
    private JuegoConfig config;
    private Teclado teclado;

    private boolean corriendo = false;
    private boolean pausa = true;

    private Tablero tablero;
    private Pantalla pantalla;


    public Juego(Frame frame){
        this.frame = frame;
        frame.setTitle("Bomberman");

        pantalla = new Pantalla(config.ANCHO, config.LARGO);

        teclado = new Teclado();

        tablero = new Tablero(this, teclado, pantalla);
        addKeyListener(teclado);
    }

    private void actualizar() {
        //teclado.actualizar();
        //tablero.actualizar();
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
        }

    }

    public Tablero getTablero(){
        return tablero;
    }
}
