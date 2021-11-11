package lesson7;

import java.util.LinkedList;

public class DepthFirstPaths extends GraphPaths{

    public DepthFirstPaths(Graph g, int source) {
       super(g, source);
    }

    @Override
    public void search(Graph g, int v) {
        marked[v] = true;
        for (int w : g.getAdjList(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                search(g, w);
            }
        }
    }

}
