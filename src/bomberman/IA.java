package bomberman;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

//Esta clase se encarga de, según el nivel de inteligencia de cierto enemigo, devolver el
//siguiente movimiento que este debe de realizar dentro del mapa
public class IA {
    private Nivel mapa;
    ArrayList<int[]> movimientosRand = new ArrayList<>();

    //Constructor de la IA
    public IA(Nivel mapa){
        this.mapa = mapa;
        this.movimientosRand = genMov();
    }

    //genera las posibilidades de movimientos randoms
    public ArrayList<int[]> genMov(){
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
                        res.add(a);
                        b[0]=-1;
                        b[1]=posibles[j];
                        res.add(b);
                    }else{
                        a[0]=posibles[i];
                        a[1]=posibles[j];
                        res.add(a);
                        b[1]=-1;
                        b[0]=posibles[i];
                        res.add(b);
                    }
                }
            }
        }return res;
    }

    //Genera un número random entre cierto rango
    public int getRandomNumber(int min, int max) { //genera un numero random desde ciero rango, tierniendo el numero minimo inclusivo, pero el mayor numero exclusivo
        return (int) ((Math.random() * (max - min)) + min);
    }

    //Genera un número random y sortea uno de los movimientros randoms que generaba la anterior funcion
    //y evalua si es valida avisa cuando ya encontro una posicion random valida
    public int[] GenRandom(int x, int y){
        int flag = 0;
        int[] res = new int[2];
        while(flag != 1){
            int rand = getRandomNumber(0,4);
            int[] prueba = this.movimientosRand.get(rand);
            int a = prueba[0];
            int b = prueba[1];
            if(this.mapa.mapa[a+x][b+y] != '_' && this.mapa.mapa[a+x][b+y] != 'X'){
                res[0] = a+x;
                res[1] = b+y;
                flag = 1;
                System.out.println("x = "+ res[0] +" y = "+ res[1]);
            }
        }//si flag es = a 1, quiere decir que la posicion random valida ha sido encontrada
        return res;
    }

    /*Todos los niveles reciben los mismo: "x" y "y" de Bomberman y del Enemigo */

    //Solo persigue a BomberMan si está en el mismo x,y y sin muros en el camino
    public int[] nivel1(int bx, int by, int posx ,int posy){//bx y by son las posiciones de bomberman y posx y posy son las posiciones del enemigo
        int[] res = new int[2];
        if(bx == posx && bx%2 != 0){//si la posicion del enemigo esta en la misma fila que bombermam y el numero es par
            System.out.println("X es igual");
            int i;
            if(by>posy){
                for(i = posy+1; i<by;i++) {
                    if (this.mapa.mapa[posx][i] == '_' || this.mapa.mapa[posx][i] == 'X') {
                        System.out.println("RANDOM en x");
                        return GenRandom(posx,posy);
                    }
                }
                res[0]= posx;
                res[1] = posy+1;
                return res;
            }else{
                for(i = posy-1; i>by;i--) {
                    if (this.mapa.mapa[posx][i] == '_' || this.mapa.mapa[posx][i] == 'X') {
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
                    if (this.mapa.mapa[i][posy] == '_' || this.mapa.mapa[i][posy] == 'X') {
                        System.out.println("RANDOM en y");
                        return GenRandom(posx,posy);
                    }
                }
                res[0]= posx+1;
                res[1] = posy;
                return res;
            }else{
                for(i = posx-1; i>bx;i--) {
                    if (this.mapa.mapa[i][posy] == '_' || this.mapa.mapa[i][posy] == 'X') {
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

    //se mueve aleatoriamente el 75 % de las veces y otras persigue a nuestro héroe
    public int[] nivel2(int bx, int by, int posx ,int posy){
        double rand = Math.random();
        if(rand<=0.75){
            int res[] = new int[2];
            Node objetivo = new Node(bx,by);
            Node inicio = new Node(posx,posy);
            AStar aStar = new AStar(this.mapa.getLargo(), this.mapa.getAncho(), inicio, objetivo);//Busca el siguiente mov mas corto hasta bomberman
            aStar.setBlocks(this.mapa.getObstaculos());
            List<Node> camino = aStar.findPath();
            try {
                Node respuesta = camino.get(1);
                res[0] = respuesta.getRow();
                res[1] = respuesta.getCol();
            }catch (Exception e){
                System.out.println(e);
            }

            return res;
        }else{
            return GenRandom(posx,posy);
        }
    }

    //Si Bomberman está a menos de 30 cuadros de distancia del enemigo, el enemigo lo persigué, si no
    //se mueve aleatorio
    public int[] nivel3(int bx, int by, int posx ,int posy){
        int res[] = new int[2];
        Node objetivo = new Node(bx,by);
        Node inicio = new Node(posx,posy);
        AStar aStar = new AStar(this.mapa.getLargo(), this.mapa.getAncho(), inicio, objetivo);
        aStar.setBlocks(this.mapa.getObstaculos());
        List<Node> camino = aStar.findPath();
        int total = camino.size();//obtiene el largo de la ruta más corta hasta Bomber
        if(total<=30){
            try {
                Node respuesta = camino.get(1);
                res[0] = respuesta.getRow();
                res[1] = respuesta.getCol();
            }catch (Exception e){
                System.out.println(e);
            }

            return res;
        }else{
            return GenRandom(posx,posy);
        }
    }

    //Siempre persigue a BomberMan, mediante el algoritmo A*
    public int[] nivel4(int bx, int by, int posx ,int posy){
        int res[] = new int[2];
        Node objetivo = new Node(bx,by);
        Node inicio = new Node(posx,posy);
        AStar aStar = new AStar(this.mapa.getLargo(), this.mapa.getAncho(), inicio, objetivo);
        aStar.setBlocks(this.mapa.getObstaculos());
        List<Node> camino = aStar.findPath();
        try {
            Node respuesta = camino.get(1);
            res[0] = respuesta.getRow();
            res[1] = respuesta.getCol();
        }catch (Exception e){
            System.out.println(e);
        }
        return res;
    }

    //funcion temporal que grafica el mapa del juego y sirve para probar las IAs, tiene un delay de 1s por cada iteracion
    public void animarTemp(int bx, int by, int pEx, int pEy){
        this.mapa.mapa[bx][by] = 'B';
        boolean igual = false;
        while(igual == false){
            if(bx==pEx && by == pEy){
                igual = true;
            }
            try {
                this.mapa.mapa[pEx][pEy] = ' ';
                int[] pos = nivel3(bx,by,pEx,pEy);
                this.mapa.mapa[pos[0]][pos[1]] = '*';
                TimeUnit.SECONDS.sleep(1);
                pEx = pos[0];
                pEy = pos[1];
                System.out.println(pEx+", "+pEy);
                this.mapa.imprimir(this.mapa.mapa);
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
