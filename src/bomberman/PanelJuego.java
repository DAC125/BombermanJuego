package bomberman;

import java.awt.*;
import javax.swing.*;


public class PanelJuego extends JPanel{

    private Juego juego;

    public PanelJuego(Frame frame) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(JuegoConfig.LARGO * 3, JuegoConfig.ANCHO * 3));


        juego = new Juego(frame);

        add(juego);

        juego.setVisible(true);


        setVisible(true);
        setFocusable(true);

    }

    public Juego getJuego() {
        return juego;
    }
}
