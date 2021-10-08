package bomberman;

import javax.swing.*;
import java.awt.*;

//Clase que contiene la info del Panel de info,
//Las vidas restantes, puntos totales, y tiempo restante
public class PanelInfo extends JPanel{
    private JLabel timeLabel;
    private JLabel pointsLabel;
    private JLabel livesLabel;

    public PanelInfo(Juego juego) {
        setLayout(new GridLayout());

        //Configura lo necesario para mostrar el tiempo de Juego restante en el nivel
        timeLabel = new JLabel("Time: " + juego.getTablero().getTimpo());
        timeLabel.setForeground(Color.white);
        timeLabel.setHorizontalAlignment(JLabel.CENTER);

        //Configura lo necesario para mostrar los puntos acumulados por el jugardor
        pointsLabel = new JLabel("Points: " + juego.getTablero().getPuntos());
        pointsLabel.setForeground(Color.white);
        pointsLabel.setHorizontalAlignment(JLabel.CENTER);

        //Configura lo necesario para mostrar las vidas restantes del Jugador
        livesLabel = new JLabel("Lives: " + juego.getTablero().getVidas());
        livesLabel.setForeground(Color.white);
        livesLabel.setHorizontalAlignment(JLabel.CENTER);

        add(timeLabel);
        add(pointsLabel);
        add(livesLabel);


        setBackground(Color.black);
        setPreferredSize(new Dimension(0, 40));
    }

    /*Setter de las variables anteriores*/

    public void setTiempo(int t) {
        timeLabel.setText("Time: " + t);
    }

    public void setVidas(int t) {
        livesLabel.setText("Lives: " + t);

    }

    public void setPuntos(int t) {
        pointsLabel.setText("Points: " + t);
    }

}
