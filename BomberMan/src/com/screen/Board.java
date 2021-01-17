package com.screen;
public class Board {
    public char tablero[][];
    private int largo;
    private int ancho;
    private float proba;
    private int nivel;
    private int numVill;
    private double distribucion;
    private double distribucionMayor;

    public Board(){

    }
    public Board(int l, int a,float p,int n, int num){
        this.ancho = a;
        this.largo = l;
        this.proba = p/100;
        this.nivel = n;
        this.numVill = num;
        this.distribucion = distribucionEnemigos(n,num);
        this.distribucionMayor = distribucionMenor(n,num);
        this.tablero = crearTablero(a,l);
    }

    public char[][] crearTablero(int a, int l){
        int temp = this.numVill; //numero temporal que indica la cantidad de enemigos maxima para el tablero, este numero se disminuye cada vez que un enemigo es insertado
        char enemigo = 'E'; //E significa que el enemigo es el indicado para el nivel en el que se esta
        if(distribucionMayor > distribucion){
            enemigo = 'R'; //R significa que el enemigo va a ser el que mas se tiene puntaje ene el juego con el nivel actual del  tablero
        }
        char board[][] = new char[l][a];
        for(int i = 0; i < l; i++){
            for(int j = 0; j < a; j++) {
                if(j == 0 || j == a-1 || i == 0 || i == l-1 ) {
                    board[i][j] = 'X';
                }else if(j%2 == 0 && i%2 == 0){
                    board[i][j] = 'X';
                }else{
                    if(i<=3 && j<=3){
                        board[i][j] = ' ';
                    }else{
                        double rand = Math.random();
                        if(temp!=0 && rand<=this.distribucion){
                            board[i][j] = enemigo;
                            temp--;
                        }else if(rand<=this.proba){
                            board[i][j] = '_';
                        }else{
                            board[i][j] = ' ';
                        }
                    }
                }
            }
        }
        imprimir(board);
        return board;
    }

    public void imprimir(char[][] tab){
        for(int i = 0; i<this.largo; i++){
            System.out.println(tab[i]);
        }
    }

    public double distribucionEnemigos(int level,int numVill){
        double e = Math.exp(1);
        double res = ((Math.pow(e,(-1 * level))*Math.pow(level,numVill))/(factorial(numVill)));
        System.out.println(res);
        return res;
    }

    public double distribucionMenor(int level,int numVill){
        double temp = 0;
        for (int i = 0;i < numVill;i++){
            temp+=distribucionEnemigos(level,i);
        }
        System.out.println(1-temp-this.distribucion);
        return 1-temp-this.distribucion;
    }

    public long factorial(int n) { //eficiente para numeros hasta el 20 (suficiente)
        long fact = 1;
        for (int i = 2; i <= n; i++) {
            fact = fact * i;
        }
        return fact;
    }
}
