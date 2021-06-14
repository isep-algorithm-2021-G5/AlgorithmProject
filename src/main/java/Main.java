import config.Path;
import graph.Graph;
import utils.Connectivity;
import utils.Mapping;
import utils.Visualization;

/**
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/6/13
 */
public class Main
{


    private static final int THRESHOLD = 6959;

    public static void main(String[] args)
    {

        Graph graph = new Graph(Path.PHOENIX_STOPS, Path.PHOENIX_GRAPH, false);
        Graph weightedGraph = new Graph(Path.PHOENIX_STOPS, Path.PHOENIX_WEIGHTED_GRAPH, true);

        Visualization.show(graph);

        graph = Mapping.reMapGraph(graph);

        Graph graphConnected = Connectivity.removeIsolated(graph, THRESHOLD);
        graphConnected = Mapping.reMapGraph(graphConnected);
        Visualization.show(graphConnected);

        System.out.println(1);


/*        //test BfsShortestPath
        BfsShortestPath bfsSP = new BfsShortestPath(graph,2);
        System.out.println(bfsSP.hasPathTo(7));
        System.out.println(bfsSP.printShortestPath(7));

        //test EdgeBetweenness
        EdgeBtw edgeClustering = new EdgeBtw(graph);
        Edge maxBtwEdge = edgeClustering.getEdgeMaxBtw();
        System.out.println("Max Btw Edge: "+maxBtwEdge.getFrom()+" "+maxBtwEdge.getTo());

        //test Cluster
        Cluster cluster = new Cluster(graph);
        cluster.findClusters(2);*/
    }


}