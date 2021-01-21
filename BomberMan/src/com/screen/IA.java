package com.screen;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class IA {
    private Board mapa;
    ArrayList<int[]> movimientosRand = new ArrayList<>();

    public IA(Board mapa){
        this.mapa = mapa;
        this.movimientosRand = genMov();
    }

    public ArrayList<int[]> genMov(){ //genera las posibilidades de movimientos randoms
        ArrayList<int[]> res = new ArrayList<>();
        int[] posibles = {0,1};
        for (int i = 0; i<2 ; i++){
            for(int j = 0;j<2;j++){
                if(i != j){
                    int[] a = new int[2];
                    int[] b = new int[2];
                    if(i == 1){
                        a[0]=posibles[i];
                        a[1]=posibles[j];
                        //System.out.println(a[0]+", "+a[1]);
                        res.add(a);
                        b[0]=-1;
                        b[1]=posibles[j];
                        //System.out.println(b[0]+", "+b[1]);
                        res.add(b);
                    }else{
                        a[0]=posibles[i];
                        a[1]=posibles[j];
                        //System.out.println(a[0]+", "+a[1]);
                        res.add(a);
                        b[1]=-1;
                        b[0]=posibles[i];
                        //System.out.println(b[0]+", "+b[1]);
                        res.add(b);
                    }
                }
            }
        }return res;
    }
    public int getRandomNumber(int min, int max) { //genera un numero random desde ciero rango, tierniendo el numero minimo inclusivo, pero el mayor numero exclusivo
        return (int) ((Math.random() * (max - min)) + min);
    }

    public int[] GenRandom(int x, int y){ //genera un numero random y sortea uno de los movimientros randoms que generaba la anterior funcion y evalua si es valida
        int flag = 0;//avisa cuando ya encontro una posicion random valida
        int[] res = new int[2];
        while(flag != 1){
            int rand = getRandomNumber(0,4);
            int[] prueba = this.movimientosRand.get(rand);
            int a = prueba[0];
            int b = prueba[1];
            if(this.mapa.tablero[a+x][b+y] != '_' && this.mapa.tablero[a+x][b+y] != 'X'){
                res[0] = a+x;
                res[1] = b+y;
                flag = 1;
                System.out.println("x = "+ res[0] +" y = "+ res[1]);
            }
        }//si flag es = a 1, quiere decir que la posicion random valida ha sido encontrada
        return res;
    }

    public int[] nivel1(int bx, int by, int posx ,int posy){//bx y by son las posiciones de bomberman y posx y posy son las posiciones del enemigo
        int[] res = new int[2];
        if(bx == posx && bx%2 != 0){//si la posicion del enemigo esta en la misma fila que bombermam y el numero es par
            System.out.println("X es igual");
            int i;
            if(by>posy){
                for(i = posy+1; i<by;i++) {
                    if (this.mapa.tablero[posx][i] == '_' || this.mapa.tablero[posx][i] == 'X') {
                        System.out.println("RANDOM en x");
                        return GenRandom(posx,posy);
                    }
                }
                res[0]= posx;
                res[1] = posy+1;
                return res;
            }else{
                for(i = posy-1; i>by;i--) {
                    if (this.mapa.tablero[posx][i] == '_' || this.mapa.tablero[posx][i] == 'X') {
                        System.out.println("RANDOM en x");
                        return GenRandom(posx,posy);
                    }
                }
                res[0]= posx;
                res[1] = posy-1;
                return res;
            }
        }else if(by == posy && bx%2 != 0){//si la posicion del enemigo esta en la misma columna que bombermam y el numero es par
            System.out.println("Y es igual");
            int i;
            if(bx>posx){
                for(i = posx+1; i<bx;i++) {
                    if (this.mapa.tablero[i][posy] == '_' || this.mapa.tablero[i][posy] == 'X') {
                        System.out.println("RANDOM en y");
                        return GenRandom(posx,posy);
                    }
                }
                res[0]= posx+1;
                res[1] = posy;
                return res;
            }else{
                for(i = posx-1; i>bx;i--) {
                    if (this.mapa.tablero[i][posy] == '_' || this.mapa.tablero[i][posy] == 'X') {
                        System.out.println("RANDOM en y");
                        return GenRandom(posx,posy);
                    }
                }
                res[0]= posx-1;
                res[1] = posy;
                return res;
            }
        }else{
            System.out.println("RANDOM");
            return GenRandom(posx,posy);
        }
    }
    public int[] nivel2(int bx, int by, int posx ,int posy){
        double rand = Math.random();
        if(rand>=0.75){

        }else{
            return GenRandom(posx,posy);
        }
    }
  x
xxBxx

    public void animarTemp(int bx, int by, int pEx, int pEy){//funcion temporal que grafica el mapa del juego y sirve para probar las IAs, tiene un delay de 1s por cada iteracion
        this.mapa.tablero[bx][by] = 'B';
        boolean igual = false;
        while(igual == false){
            if(bx==pEx && by == pEy){
                igual = true;
            }
            try {
                this.mapa.tablero[pEx][pEy] = ' ';
                int[] pos = nivel1(bx,by,pEx,pEy);
                this.mapa.tablero[pos[0]][pos[1]] = '*';
                TimeUnit.SECONDS.sleep(1);
                pEx = pos[0];
                pEy = pos[1];
                System.out.println(pEx+", "+pEy);
                this.mapa.imprimir(this.mapa.tablero);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }System.out.println(pEx+", "+pEy);
    }

    /*public ArrayList<int[]> rutaCorta(int bx, int by, int pEx, int pEy){
        ArrayList<int[]> res = new ArrayList<>();
        rutaCortaAux(bx, by, pEx+1,pEy,res);
        rutaCortaAux(bx, by, pEx-1,pEy,res);
        rutaCortaAux(bx, by, pEx,pEy+1,res);
        rutaCortaAux(bx, by, pEx,pEy-1,res);
        int costo = 0;
        int ganador = 0;
    }

    private ArrayList<int[]> rutaCortaAux(int bx, int by, int pEx, int pEy,ArrayList<int[]> acum) {
        if (this.mapa.tablero[pEx][pEy] != '_' || this.mapa.tablero[pEx][pEy] != 'X') {
            
        }
    }*/
}
