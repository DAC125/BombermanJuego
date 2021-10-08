package bomberman;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//clase encargada de actualizar las entradas del usuario por medio del teclado
public class Teclado implements KeyListener {

    private boolean[] teclas = new boolean[120];//Códigos de las flechas
    public boolean arriba, abajo, izquierda, derecha, espacio;

    //cuando se presiona una tecla, setea su código en "teclas" en true, lo que indica que esa tecla está
    //siendo pulsada
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
        teclas[e.getKeyCode()] = true;

    }

    @Override
    public void keyReleased(KeyEvent e) {
        teclas[e.getKeyCode()] = false;


    }
}
