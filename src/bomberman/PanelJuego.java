package bomberman;

import java.awt.*;
import javax.swing.*;

//Básicamente coloca la ventana donde se va a ver el juego y la hace visible al jugador
public class PanelJuego extends JPanel{

    private Juego juego;

    public PanelJuego(Frame frame) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(JuegoConfig.LARGO * 3, JuegoConfig.ANCHO * 3));


        juego = new Juego(frame);//Instancia el Juego

        add(juego);

        juego.setVisible(true);


        setVisible(true);
        setFocusable(true);

    }

    public Juego getJuego() {
        return juego;
    }
}
