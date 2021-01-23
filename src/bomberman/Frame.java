package bomberman;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame {

    public PanelJuego panelJuego;
    private JPanel containerpane;
    private PanelInfo panelInfo;
    private Juego juego;

    public Frame(){


        containerpane = new JPanel(new BorderLayout());
        panelJuego = new PanelJuego(this);
        panelInfo = new PanelInfo(panelJuego.getJuego());

        containerpane.add(panelInfo,BorderLayout.PAGE_START);
        containerpane.add(panelJuego,BorderLayout.PAGE_END);

        juego = panelJuego.getJuego();

        add(containerpane);

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        juego.run();
    }
    /*
    public void nuevoJuego() {
        juego.getTablero().nuevoJuego();
    }
*/
    public void cambiarNivel(int i) {
        juego.getTablero().cambiarNivel(i);
    }
/*
    public void pausarJuego() {
        juego.getTablero().pausar();
    }

    public void reanudarJuego() {
        juego.getTablero().reanudar();
    }*/

    public boolean estaCoriendo() {
        return juego.estaCoriendo();
    }

    public void setTiempo(int tiempo) {
        panelInfo.setTiempo(tiempo);
    }

    public void setVidas(int vidas) {
        panelInfo.setVidas(vidas);
    }

    public void setPuntos(int puntos) {
        panelInfo.setPuntos(puntos);
    }

}
