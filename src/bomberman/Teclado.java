package bomberman;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclado implements KeyListener {
    private boolean[] teclas = new boolean[120];
    public boolean arriba, abajo, izquierda, derecha, espacio;

    public void actualiza(){
        arriba = teclas[KeyEvent.VK_UP] || teclas[KeyEvent.VK_W];
        abajo = teclas[KeyEvent.VK_DOWN] || teclas[KeyEvent.VK_S];
        izquierda = teclas[KeyEvent.VK_LEFT] || teclas[KeyEvent.VK_A];
        derecha = teclas[KeyEvent.VK_RIGHT] || teclas[KeyEvent.VK_D];
        espacio = teclas[KeyEvent.VK_SPACE] || teclas[KeyEvent.VK_X];
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
