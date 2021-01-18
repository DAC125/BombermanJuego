package Prueba;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui extends JFrame implements ActionListener{
    JButton boton1 = new JButton("Pulsame");
    JButton boton2 = new JButton("Pulsame2");
    Sound a = new Sound();
    public Gui(){
        super("Pulse el boton");
        setSize(400,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        add(boton1);
        add(boton2);
        boton1.addActionListener(this);
        boton2.addActionListener(this);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == boton1){
            a.reproducirSonidoMuerte();
        }
        if(e.getSource() == boton2){
            a.reproducirSonidoBG();
        }
    }
}