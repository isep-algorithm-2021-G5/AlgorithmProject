import algorithm.BfsShortestPath;
import algorithm.Cluster;
import algorithm.EdgeBTW;
import com.google.common.collect.Multimap;
import graph.Edge;
import graph.Graph;
import graph.WeightedEdge;
import utils.Connectivity;
import utils.GraphFileReader;
import utils.Visualization;

/**
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/6/13
 */
public class Main
{

    public static final String STOPS = "./data/processed/stops.csv";
    public static final String GRAPH = "./data/processed/graph.csv";
    public static final String WEIGHTED_GRAPH = "./data/processed/graph.csv";
    public static final int THRESHOLD = 6959;

//    public static final String STOPS = "./data/processed/clusterNodesDataSet.csv";
//    public static final String GRAPH = "./data/processed/clusterGraphDataSet.csv";

    public static void main(String[] args)
    {

        Graph graph = new Graph(STOPS, GRAPH);

        Multimap<Integer, WeightedEdge> weightedGraph = GraphFileReader
                .readWeightedGraph(WEIGHTED_GRAPH);

        Visualization.show(graph);

        Graph graphConnected = Connectivity.removeIsolated(graph, THRESHOLD);
        Visualization.show(graphConnected);

        System.out.println(1);


/*        //test BfsShortestPath
        BfsShortestPath bfsSP = new BfsShortestPath(graph,2);
        System.out.println(bfsSP.hasPathTo(7));
        System.out.println(bfsSP.printShortestPath(7));

        //test EdgeBetweenness
        EdgeBTW edgeClustering = new EdgeBTW(graph);
        Edge maxBtwEdge = edgeClustering.getEdgeMaxBTW();
        System.out.println("Max Btw Edge: "+maxBtwEdge.getFrom()+" "+maxBtwEdge.getTo());

        //test Cluster
        Cluster cluster = new Cluster(graph);
        cluster.findClusters(2);*/
    }


}