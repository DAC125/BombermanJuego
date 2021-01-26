package Entidad;

import BackEnd.ParametrosMapa;

import java.util.ArrayList;

public class Bomba implements ParametrosMapa {
    private int rango; //rango de explosion
    private int posX;
    private int posY;

    public Bomba(int posX, int posY, int rango){
        this.rango = rango;
        this.posX = posX;
        this.posY = posY;
    }

    public void setRango(int nRango){
        this.rango = nRango;
    }

    public ArrayList<int[]> detonar(){
        ArrayList<int[]> respuesta = new ArrayList<>();
        int x1 = this.posX+1;
        int x2 = this.posX-1;
        int y1 = this.posY+1;
        int y2 = this.posY-1;
        int[] res = {this.posX,this.posY};
        respuesta.add(res);
        for(int i = 0; i<this.rango;i++){
            int[] res1 = {x1++,this.posY};
            //System.out.println(res1[0] + ", " + res1[1]);
            respuesta.add(res1);
            int[] res2 = {this.posX,y1++};
            //System.out.println(res2[0] + ", " + res2[1]);
            respuesta.add(res2);
            int[] res3 = {x2--,this.posY};
            //System.out.println(res3[0] + ", " + res3[1]);
            respuesta.add(res3);
            int[] res4 = {this.posX,y2--};
            //System.out.println(res4[0] + ", " + res4[1]);
            respuesta.add(res4);
        }
        return respuesta;
    }


}
