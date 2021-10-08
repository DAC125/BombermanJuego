package bomberman;

import java.util.*;

//Encuentra el camino mas corto entre dos nodos
public class AStar {
    private static int DEFAULT_COST = 1; // Costo de moverse una casilla vertical u horizontalmente
    private int hvCost;
    private Node[][] searchArea;//Matriz de nodos igual a la matriz de juego
    private PriorityQueue<Node> openList;//Listas con las que trabaja A estrella
    private Set<Node> closedSet;//Listas con las que trabaja A estrella
    private Node initialNode;//Nodo al que se quiere llegar desde x nodo actual
    private Node finalNode;//Nodo destino

    //Constructor de la clase(filas y columnas de la matriz, nodo inicial, final y el coste de pasar entre nodo y nodo)
    public AStar(int rows, int cols, Node initialNode, Node finalNode, int hvCost) {
        this.hvCost = hvCost;
        setInitialNode(initialNode);
        setFinalNode(finalNode);
        this.searchArea = new Node[rows][cols];
        this.openList = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node node0, Node node1) {
                return Integer.compare(node0.getF(), node1.getF());
            }
        });
        setNodes();
        this.closedSet = new HashSet<>();
    }

    public AStar(int rows, int cols, Node initialNode, Node finalNode) {
        this(rows, cols, initialNode, finalNode, DEFAULT_COST);
    }

    //Método encargado de crear la matriz de nodos, y calcula la heuristica de una vez de todos los nodos
    //O sea, la distancia aprox hasta el nodo final; al principio solo inicializa la matriz, por lo que no
    //Existen muros
    private void setNodes() {
        for (int i = 0; i < searchArea.length; i++) {
            for (int j = 0; j < searchArea[0].length; j++) {
                Node node = new Node(i, j);
                node.calculateHeuristic(getFinalNode());
                this.searchArea[i][j] = node;
            }
        }
    }

    //Dada una lista de coordenadas en la que existe un bloque, esta función se encarga de
    //Setear en true, la variable que indica sí existe un bloque en esa posición
    //Esto lo realiza en la matriz de nodos "searchArea"
    public void setBlocks(ArrayList<Node> blocksArray) {
        for (int i = 0; i < blocksArray.size(); i++) {
            int row = blocksArray.get(i).getRow();
            int col = blocksArray.get(i).getCol();
            setBlock(row, col);
        }
    }

    //Encuentra el camino mas corto entre dos nodos
    public List<Node> findPath() {
        openList.add(initialNode);
        while (!isEmpty(openList)) {
            Node currentNode = openList.poll();
            closedSet.add(currentNode);
            if (isFinalNode(currentNode)) {
                return getPath(currentNode);
            } else {
                addAdjacentNodes(currentNode);
            }
        }
        return new ArrayList<Node>();
    }

    //Una vez se llega al nodo final, se devuelve el camino final, esto se hace
    //recorriendo los padres de los mejores nodos
    private List<Node> getPath(Node currentNode) {
        List<Node> path = new ArrayList<Node>();
        path.add(currentNode);
        Node parent;
        while ((parent = currentNode.getParent()) != null) {
            path.add(0, parent);
            currentNode = parent;
        }
        return path;
    }

    //Llama a la funciones encargadas de revisar los nodos adyacentes a un nodo actual
    private void addAdjacentNodes(Node currentNode) {
        addAdjacentUpperRow(currentNode);
        addAdjacentMiddleRow(currentNode);
        addAdjacentLowerRow(currentNode);
    }

    //revisa los nodos adyacentes en la siguiente fila del nodo actual
    private void addAdjacentLowerRow(Node currentNode) {
        int row = currentNode.getRow();
        int col = currentNode.getCol();
        int lowerRow = row + 1;
        if (lowerRow < getSearchArea().length) {
            checkNode(currentNode, col, lowerRow, getHvCost());
        }
    }

    //revisa los nodos adyacentes a los lados del nodo actual
    private void addAdjacentMiddleRow(Node currentNode) {
        int row = currentNode.getRow();
        int col = currentNode.getCol();
        int middleRow = row;
        if (col - 1 >= 0) {
            checkNode(currentNode, col - 1, middleRow, getHvCost());
        }
        if (col + 1 < getSearchArea()[0].length) {
            checkNode(currentNode, col + 1, middleRow, getHvCost());
        }
    }

    //revisa los nodos adyacentes en la anterior fila del nodo actual
    private void addAdjacentUpperRow(Node currentNode) {
        int row = currentNode.getRow();
        int col = currentNode.getCol();
        int upperRow = row - 1;
        if (upperRow >= 0) {
            checkNode(currentNode, col, upperRow, getHvCost());
        }
    }

    //Revisa si el nodo no es un muro o sí ya fue visitado con anterioridad, en cuyo caso
    //lo agrega el openList, o cambia el valor actual, en caso de que este esté en la openList
    private void checkNode(Node currentNode, int col, int row, int cost) {
        Node adjacentNode = getSearchArea()[row][col];
        if (!adjacentNode.isBlock() && !getClosedSet().contains(adjacentNode)) {
            if (!getOpenList().contains(adjacentNode)) {
                adjacentNode.setNodeData(currentNode, cost);
                getOpenList().add(adjacentNode);
            } else {
                boolean changed = adjacentNode.checkBetterPath(currentNode, cost);
                if (changed) {
                    // borra y agrega de nuevo el nodo cambiado, así la cola de prioridad puede volver a ordenar
                    // su contenido con el costo final del nodo cambiado
                    getOpenList().remove(adjacentNode);
                    getOpenList().add(adjacentNode);
                }
            }
        }
    }

    private boolean isFinalNode(Node currentNode) {
        return currentNode.equals(finalNode);
    }

    private boolean isEmpty(PriorityQueue<Node> openList) {
        return openList.size() == 0;
    }

    //setea el nodo para que sea un obstaculo
    private void setBlock(int row, int col) {
        this.searchArea[row][col].setBlock(true);
    }

    public Node getInitialNode() {
        return initialNode;
    }

    public void setInitialNode(Node initialNode) {
        this.initialNode = initialNode;
    }

    public Node getFinalNode() {
        return finalNode;
    }

    public void setFinalNode(Node finalNode) {
        this.finalNode = finalNode;
    }

    public Node[][] getSearchArea() {
        return searchArea;
    }

    public void setSearchArea(Node[][] searchArea) {
        this.searchArea = searchArea;
    }

    public PriorityQueue<Node> getOpenList() {
        return openList;
    }

    public void setOpenList(PriorityQueue<Node> openList) {
        this.openList = openList;
    }

    public Set<Node> getClosedSet() {
        return closedSet;
    }

    public void setClosedSet(Set<Node> closedSet) {
        this.closedSet = closedSet;
    }

    public int getHvCost() {
        return hvCost;
    }

    public void setHvCost(int hvCost) {
        this.hvCost = hvCost;
    }
}
