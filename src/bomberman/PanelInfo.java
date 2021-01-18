package bomberman;

import javax.swing.*;
import java.awt.*;

public class PanelInfo extends JPanel{
    private JLabel timeLabel;
    private JLabel pointsLabel;
    private JLabel livesLabel;

    public PanelInfo(Juego juego) {
        setLayout(new GridLayout());

        timeLabel = new JLabel("Time: " + juego.getTablero().getTimpo());
        timeLabel.setForeground(Color.white);
        timeLabel.setHorizontalAlignment(JLabel.CENTER);

        pointsLabel = new JLabel("Points: " + juego.getTablero().getPuntos());
        pointsLabel.setForeground(Color.white);
        pointsLabel.setHorizontalAlignment(JLabel.CENTER);

        livesLabel = new JLabel("Lives: " + juego.getTablero().getVidas());
        livesLabel.setForeground(Color.white);
        livesLabel.setHorizontalAlignment(JLabel.CENTER);

        add(timeLabel);
        add(pointsLabel);
        add(livesLabel);


        setBackground(Color.black);
        setPreferredSize(new Dimension(0, 40));
    }

    public void setTime(int t) {
        timeLabel.setText("Time: " + t);
    }

    public void setLives(int t) {
        livesLabel.setText("Lives: " + t);

    }

    public void setPoints(int t) {
        pointsLabel.setText("Points: " + t);
    }

}
