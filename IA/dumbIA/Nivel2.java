package IA.dumbIA;

import java.util.Random;

public class Nivel2 {
    char[][] Matriz;
    Random rand = new Random();
    public Nivel2(char[][] Matriz){
        this.Matriz = Matriz;
    }

    public int siguiente(int bx, int by, int ex, int ey, char[][] M){
        double rdm =  Math.random();

        if(rdm<0.75){
            return rand.nextInt(4);//0 ariiba, 1 derecha, 2 abajo, 3 izquierda-1, -1 se queda quieto
        }else{
            //Busco el Y del mae, si me puedo mover hacia izquierda-derecha, lo hago
            //hasta llegar al mismo x de Bomberman
            //una vez, llego lo mas a la izquierda-derecha, busco arriba-abajo
            if(by<ey && M[ex][ey-1] != ' '){
                return 3;
            }else if (by>ey && M[ex][ey+1] != ' '){
                return 1;
            }else if(by==ey){
                if(bx>ex && M[ex+1][ey] != ' '){
                    return 2;
                }else if (bx>ex && M[ex+1][ey] != ' '){
                    return 0;
                }
                else{
                    return -1;
                }
            }
            else{
                return -1;
            }
        }
    }
}
