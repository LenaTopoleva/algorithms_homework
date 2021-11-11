package lesson7;

import java.util.LinkedList;

//  Реализовать программу, в которой задается граф из 10 вершин.
//  Задать ребра и найти кратчайший путь с помощью поиска в ширину. (сам путь, его длину)

public class HomeTask {

    public static LinkedList<Integer> findPath(Graph graph, int from, int to){
        if (to >= graph.getVertexCount() || to < 0 || from < 0 || from >= graph.getVertexCount()) {
            throw new IllegalArgumentException();
        }
        GraphPaths breadthFirstPaths = new BreadthFirstPaths(graph, from);
        return breadthFirstPaths.pathTo(to);
    }

    public static Integer getPathLength(LinkedList<Integer> path){
        return path.size();
    }

    public static void main(String[] args) {
        Graph graph = new Graph(10);
        graph.addEdge(1,3);
        graph.addEdge(0,1);
        graph.addEdge(3,2);
        graph.addEdge(4,2);
        graph.addEdge(4,3);
        graph.addEdge(5,1);
        graph.addEdge(6,5);
        graph.addEdge(7,4);
        graph.addEdge(8,1);
        graph.addEdge(9,1);

        LinkedList<Integer> path = findPath(graph, 0, 3);
        System.out.println("Path: " + path + ", path length: " + getPathLength(path) );
    }

}
