package com.screen;

public class Main {

    public static void main(String[] args) {
        Board tablero = new Board(15,35,5,10,20);
        System.out.println(tablero.distribucionEnemigos(10,20));
    }
}
