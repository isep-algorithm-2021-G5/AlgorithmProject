package algorithm;

import graph.Edge;
import graph.Graph;
import graph.ShortestPath;
import lombok.val;
import java.util.*;


public class EdgeBTW {
    private final Deque<ShortestPath> spList;
    private final ArrayList<Edge> allEdges;

    public EdgeBTW(Graph graph){
        val adj = graph.getAdjList();
        this.spList = new LinkedList<>();
        this.allEdges = new ArrayList<>();

        // all edge
        for(int i = 0; i< adj.size(); i++){
            allEdges.addAll(adj.get(i));
        }

        // init
        for(int src : graph.getNodes().keySet()){
            for(int dest : graph.getNodes().keySet()){
                if(src != dest){
                    BfsShortestPath bfsSP = new BfsShortestPath(graph, src);
                    ShortestPath sp = new ShortestPath(src, dest, bfsSP.printShortestPath(dest));
                    spList.addLast(sp);
                }
            }
        }
    }

    public Deque<ShortestPath> getSpList() {
        return this.spList;
    }

    public Map<Edge, Integer> getEdgeBtw() {
        Map<Edge, Integer> edgeBtw = new HashMap<>();

        for (Edge edge : this.getAllEdges()){
            edgeBtw.put(edge,0);
        }

        for(ShortestPath sp : this.getSpList()){
            List<Integer> shortestPath = sp.getBfsSPList();

            if(shortestPath.size() > 0){
                for(int i = 0; i < shortestPath.size()-1; i++){
                    Edge edge = this.getEdge(shortestPath.get(i),shortestPath.get(i+1));
                    int btw = edgeBtw.get(edge);
                    edgeBtw.put(edge, btw+1);
                }
            }
        }
        return edgeBtw;
    }

    public ArrayList<Edge> getAllEdges() {
        return this.allEdges;
    }

    public Edge getEdge(int src, int dest) {
        Edge targetEdge = null;
        for(Edge edge : this.allEdges){
            if(edge.getFrom() == src && edge.getTo() == dest){
                targetEdge = edge;
            }
        }
        return targetEdge;
    }

    public Edge getEdgeMaxBTW(){
        Edge maxBtwEdge = this.getAllEdges().get(0);
        Map<Edge, Integer> edgeBtw = this.getEdgeBtw();
        for(Edge edge : this.getAllEdges()){
            if(edgeBtw.get(edge) > edgeBtw.get(maxBtwEdge)){
                maxBtwEdge = edge;
            }
        }
        return maxBtwEdge;
    }

}
