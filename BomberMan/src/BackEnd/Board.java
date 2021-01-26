package BackEnd;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import Enemigos.ParametrosVillanos;
import Enemigos.Villano;

public class Board implements ParametrosMapa {
    public char tablero[][];
    private int largo;
    private int ancho;
    private float proba;
    private int nivel;
    private int numVill;
    private ArrayList<Node> obstaculos;
    private int[][] probabilidadesEnemigos;
    private ArrayList<Villano> enemigos = new ArrayList<>();

    public Board(){

    }
    public int getLargo(){
        return this.largo;
    }
    public int getAncho(){
        return this.ancho;
    }

    public ArrayList<Node> getObstaculos(){
        return this.obstaculos;
    }

    public Board(int largo, int ancho,float probabilidadMuro,int nivel, int numVillanos){
        this.ancho = ancho;
        this.largo = largo;
        this.proba = probabilidadMuro/100;
        this.nivel = nivel;
        this.numVill = numVillanos;
        this.obstaculos = new ArrayList<>();
        this.probabilidadesEnemigos = calcularProbabilidades();
        this.tablero = crearTablero(ancho,largo);
        this.enemigos = colocarEnemigos();
        imprimir(this.tablero);

    }

    public char[][] crearTablero(int a, int l){
        char board[][] = new char[l][a];
        for(int i = 0; i < l; i++){
            for(int j = 0; j < a; j++) {
                if(j == 0 || j == a-1 || i == 0 || i == l-1 ) {
                    Node nuevo = new Node(i,j);
                    board[i][j] = Acero;
                    this.obstaculos.add(nuevo);
                }else if(j%2 == 0 && i%2 == 0){
                    Node nuevo = new Node(i,j);
                    board[i][j] = Acero;
                    this.obstaculos.add(nuevo);
                }else{
                    if(i<=3 && j<=3){
                        board[i][j] = Vacio;
                    }else{
                        double rand = Math.random();
                        if(rand<=this.proba){
                            board[i][j] = Muralla;
                            Node nuevo = new Node(i,j);
                            this.obstaculos.add(nuevo);
                        }else{
                            board[i][j] = Vacio;
                        }
                    }
                }
            }
        }
        return board;
    }
    private ArrayList<Villano> colocarEnemigos(){
        ArrayList<Villano> lista = new ArrayList<>();
        int temp = this.numVill;
        while(temp!=0){
            int xRand = getRandomNumber(0,this.largo);
            int yRand = getRandomNumber(0,this.ancho);
            if( this.tablero[xRand][yRand] == Vacio && xRand>3 && yRand>3){
                int rand = (int)(Math.random()*100);
                int nivel = seleccionarEnemigo(this.probabilidadesEnemigos,rand);
                Villano nuevo = new Villano(nivel,xRand,yRand);
                char enemigo  = (char)(96 + nuevo.getNivel());
                lista.add(nuevo);
                this.tablero[xRand][yRand] = enemigo;
                temp--;
            }
        }return lista;
    }

    public int getRandomNumber(int min, int max) { //genera un numero random desde ciero rango, tierniendo el numero minimo inclusivo, pero el mayor numero exclusivo
        return (int) ((Math.random() * (max - min)) + min);
    }

    public int seleccionarEnemigo(int[][] rango, int valorRandom){
        for(int i = 0; i< rango.length; i++){
            //System.out.println("Random = " + valorRandom + " Min = " + rango[i][0] + " Max = " + rango[i][1]);
            if(valorRandom >= rango[i][0] && valorRandom <= rango[i][1]){
                return (i+1);
            }
        }
        return 1;
    }

    public void imprimir(char[][] tab){
        for(int i = 0; i<tab.length; i++){
            System.out.println(tab[i]);
        }
    }
    public int[][] calcularProbabilidades(){
        double[] pruebas = new double[this.nivel-1];
        for(int i = 1;i<this.nivel;i++){
            if(nivel>=1 && nivel<=3){
                pruebas[i-1] = distribucionEnemigos(i,nivel);
            }else if(nivel>3 && nivel<6){
                pruebas[i-1] = distribucionEnemigos(i,3);
            }else if(nivel>=6 && nivel<8){
                pruebas[i-1] = distribucionEnemigos(i,4);
            }else if(nivel>=8 && nivel<11){
                pruebas[i-1] = distribucionEnemigos(i,5);
            }else if(nivel>=11 && nivel<14){
                pruebas[i-1] = distribucionEnemigos(i,6);
            }else{
                pruebas[i-1] = distribucionEnemigos(i,7);
            }
        }
        return crearMatrizProba(pruebas);
    }

    private int[][] crearMatrizProba(double[] pruebas){
        int[][] rangos = new int[pruebas.length+1][2];
        int acum = 0;
        for(int i = 0; i < pruebas.length;i++){
            rangos[i][0] = acum+1;
            acum+=1;
            //System.out.println("Nivel = " + (i+1));
            //System.out.println("Min = " + i);
            acum += (int)(pruebas[i]*100);
            rangos[i][1] = acum;
            //System.out.println("Max = " + acum);
        }
        rangos[pruebas.length][0] = acum+1;
        //System.out.println("Nivel = " + (pruebas.length+1));
        //System.out.println("Min = " + (acum+1));
        rangos[pruebas.length][1] = 100;
        //System.out.println("Max = " + 100);
        return rangos;
    }
    private double distribucionEnemigos(int level,int numVill){
        double e = Math.exp(1);
        double res = ((Math.pow(e,(-1 * level))*Math.pow(level,numVill))/(factorial(numVill)));
        System.out.println(res);
        return res;
    }


    public long factorial(int n) { //eficiente para numeros hasta el 20 (suficiente)
        long fact = 1;
        for (int i = 2; i <= n; i++) {
            fact = fact * i;
        }
        return fact;
    }
    public void explotar(ArrayList<int[]> detonacion) throws InterruptedException {
        //TimeUnit.SECONDS.sleep(tiempoBomba);
        int maxX = detonacion.get(detonacion.size()-4)[0]+1;
        int maxY = detonacion.get(detonacion.size()-3)[1]+1;
        int minY = detonacion.get(detonacion.size()-1)[1]-1;
        int minX = detonacion.get(detonacion.size()-2)[0]-1;
        for(int i = 0; i<detonacion.size();i++){
            int x = detonacion.get(i)[0];
            int y = detonacion.get(i)[1];
            //detonacion.get(detonacion.size()-4)[0]
            if(x>0 && y>0){
                if(this.tablero[x][y] == Acero || this.tablero[x][y] == Muralla){
                    //System.out.println("Obstaculo: " +x + ", " + y);
                    if(x>detonacion.get(0)[0] && y==detonacion.get(0)[1]){
                        maxX = x;
                        if(this.tablero[x][y] == Muralla){
                            System.out.println("Muro: " +x + ", " + y);
                            eliminarMuro(x,y);
                        }
                        //System.out.println("Xmax = " + maxX);
                    }else if(x<detonacion.get(0)[0] && y==detonacion.get(0)[1]){
                        minX = x;
                        if(this.tablero[x][y] == Muralla){
                            System.out.println("Muro: " +x + ", " + y);
                            eliminarMuro(x,y);
                        }
                        //System.out.println("Xmin = " + minX);
                    }else if(y>detonacion.get(0)[1] && x==detonacion.get(0)[0]){
                        maxY = y;
                        if(this.tablero[x][y] == Muralla){
                            System.out.println("Muro: " +x + ", " + y);
                            eliminarMuro(x,y);
                        }
                       //System.out.println("Ymax = " + maxY);
                    }else if(y<detonacion.get(0)[1] && x==detonacion.get(0)[0]){
                        minY = y;
                        if(this.tablero[x][y] == Muralla){
                            System.out.println("Muro: " +x + ", " + y);
                            eliminarMuro(x,y);
                        }
                        //System.out.println("Ymin = " + minY);
                    }
                }

            }

        }
        for(int i = 0; i<detonacion.size();i++){
            int x = detonacion.get(i)[0];
            int y = detonacion.get(i)[1];
            if(x>0 && y>0){
                if(this.tablero[x][y] != Acero && x>minX && x<maxX && y>minY && y<maxY){
                    if(this.tablero[x][y]!=' '){
                        System.out.println("Enemigo: " +x + ", " + y);
                        eliminarEnemigos(x,y);
                    }
                    this.tablero[x][y] = Explosion;
                }
            }
        }imprimir(this.tablero);
    }
    public void eliminarMuro(int x, int y){
        int buscar[] = new int[2];
        for (int i = 0;i<obstaculos.size();i++){
            if(obstaculos.get(i).getCol() == y && obstaculos.get(i).getRow() == x){
                this.obstaculos.remove(i);
                System.out.println("Se elimino Muralla en " + x + ", "+y);
                this.tablero[x][y] = Vacio;
            }
        }


    }

    public void eliminarEnemigos(int x, int y){
        int buscar[] = new int[2];
        for (int i = 0;i<enemigos.size();i++){
            if(enemigos.get(i).getY() == y && enemigos.get(i).getX() == x){
                this.enemigos.remove(i);
                System.out.println("Se elimino un Villano en " + x + ", "+y);
                this.tablero[x][y] = 'D';
            }
        }


    }

}
