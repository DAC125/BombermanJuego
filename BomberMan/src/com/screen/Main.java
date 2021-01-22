package com.screen;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Board tablero = new Board(10,10,5,10,20);
        IA inteligencia = new IA(tablero);
        //int[] a =inteligencia.nivel2(5,2,1,5);
        //int[] a = inteligencia.nivel1(5,2,1,5);
        inteligencia.animarTemp(1,5,5,2);
        System.out.println("---------------");
        //int[] a = inteligencia.movimientosRand.get(0);
        //System.out.println(a[0]+", "+ a[1]);
        /*a = inteligencia.movimientosRand.get(1);
        System.out.println(a[0]+", "+ a[1]);
        a = inteligencia.movimientosRand.get(2);
        System.out.println(a[0]+", "+ a[1]);
        a = inteligencia.movimientosRand.get(3);
        System.out.println(a[0]+", "+ a[1]);*/

        //System.out.println(tablero.distribucionEnemigos(10,20));
    }

}
