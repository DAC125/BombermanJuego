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

        containerpane.add(panelJuego,BorderLayout.PAGE_START);
        //containerpane.add(panelInfo,BorderLayout.PAGE_END);

        juego = panelJuego.getJuego();

        add(containerpane);

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        juego.iniciaJuego();
    }
}
