import graph.Graph;
import lombok.val;
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

    public static final String STOPS = "./data/processed/stops.csv";
    public static final String GRAPH = "./data/processed/graph.csv";
    public static final String WEIGHTED_GRAPH = "./data/processed/graph.csv";
    public static final int THRESHOLD = 6959;

    public static void main(String[] args)
    {

        Graph graph = new Graph(STOPS, GRAPH,false);
        Graph weightedGraph = new Graph(STOPS,WEIGHTED_GRAPH,true);

        Visualization.show(graph);

        Graph graphConnected = Connectivity.removeIsolated(graph, THRESHOLD);
        Visualization.show(graphConnected);



        val test = Mapping.getIdMap(graph);

        System.out.println(1);

        //test BfsShortestPath
//        BfsShortestPath bfsSP = new BfsShortestPath();
//        bfsSP.bfs(nodes, graph, 1453);
//        System.out.println(bfsSP.hasPathTo(1440));
//        System.out.println(bfsSP.printShortestPath(1440));

    }


}
