package bomberman;

//clase encargada de almacenar la información necesaria para el algoritmo A* principalmente
//Aunque se usa en otros metodos
//representa un lugar en la matriz por la que se mueve BomberMan
public class Node {

    private int g;//costo actual, los pasos que hemos tenido que dart
    private int h;//heuristica;;x e y de la casilla actual y destino; cual es la diferencia= valor absoluto y lo sumamos
    private int f;;//g+h costo de llegar a hasta acá y cuanto crremos que nos
    private int row;//fila del Nodo
    private int col;//columna del Nodo
    private boolean isBlock;//Indica si el nodo actual, es un bloque, para saber si por acá, el algoritmo no puede pasar
    private Node parent;//Usado en A*, A través de los padres de conoce la ruta más corta del grafo(Matriz)

    //Constructor del Nodo; recibe el x y el en el que se encuentra el nodo
    public Node(int row, int col) {
        super();
        this.row = row;
        this.col = col;
    }

    //Recibe el nodo al que se quiere llegar, y mediante una heurística
    //calcula el costo aproximado de llegar hasta ella desde el Nodo Actual
    // h = |filaDestino-filaActual|+|colDestino-colActual|
    public void calculateHeuristic(Node finalNode) {
        this.h = Math.abs(finalNode.getRow() - getRow()) + Math.abs(finalNode.getCol() - getCol());
    }

    //Calcula el  costo que se ha recorrido, desde el nodo inicial de la ruta que se busca, hasta
    //el nodo actual
    public void setNodeData(Node currentNode, int cost) {
        int gCost = currentNode.getG() + cost;
        setParent(currentNode);
        setG(gCost);
        calculateFinalCost();
    }

    //El costo de ahora, es el mejor del que se tiene registro? Si es así, sigue por el nodo actual buscando
    public boolean checkBetterPath(Node currentNode, int cost) {
        int gCost = currentNode.getG() + cost;
        if (gCost < getG()) {
            setNodeData(currentNode, cost);
            return true;
        }
        return false;
    }

    //Suma el costo heurístico mas el costo actual
    //por lo que es el costo aprox total
    private void calculateFinalCost() {
        int finalCost = getG() + getH();
        setF(finalCost);
    }

    //Sí, si dos objetos son iguales
    @Override
    public boolean equals(Object arg0) {
        Node other = (Node) arg0;
        return this.getRow() == other.getRow() && this.getCol() == other.getCol();
    }

    @Override
    public String toString() {
        return "Node [row=" + row + ", col=" + col + "]";
    }

    /*Setters and getters*/

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public boolean isBlock() {
        return isBlock;
    }

    public void setBlock(boolean isBlock) {
        this.isBlock = isBlock;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
