package bomberman;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame {

    public PanelJuego panelJuego;//Contiene la parte gráfica del Juego en sí, donde se mueve Bomberman
    private JPanel containerpane;//Contiene el área matricial del juego(panelJuego)
                                // y la información del Juego(panelInfo)
    private PanelInfo panelInfo;///información necesaria para el juego(puntos, vida,tiempo)
    private Juego juego;//Todo lo necesario para que el juego funcione, el backend en sí

    //Contiene todo lo gráfico y comienza el juego en sí
    public Frame(){

        JuegoConfig.Leertxt();//Lee el archivo de configuración del juego
        containerpane = new JPanel(new BorderLayout());
        panelJuego = new PanelJuego(this);//inicializa el panel del Juego
        panelInfo = new PanelInfo(panelJuego.getJuego());//inicializa la información del juego

        containerpane.add(panelInfo,BorderLayout.PAGE_START);//coloca los dos paneles
        containerpane.add(panelJuego,BorderLayout.PAGE_END);//coloca los dos paneles

        juego = panelJuego.getJuego();

        add(containerpane);

        setResizable(false);//no se puede cambiar el tamaño
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        //Inicia el Juego
        juego.start();
    }
    /*
    public void nuevoJuego() {
        juego.getTablero().nuevoJuego();
    }
*/
    //indica al tablero que cambie de Nivel
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
