package BackEnd;

import Entidad.Bomba;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Board tablero = new Board(25,40,25,8,9);//*probabilidad porcentual
        //Main thread = new Main();
        Bomba nueva = new Bomba(5,5,2);
        tablero.explotar(nueva.detonar());
        //IA inteligencia = new IA(tablero);
        //inteligencia.animarTemp(20,35,1,1);


        //System.out.println(tablero.distribucionEnemigos(10,20));
    }
    public void run() {
        System.out.println("This code is running in a thread");
    }

}
