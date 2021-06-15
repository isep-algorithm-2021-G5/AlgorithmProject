import algorithm.DijkstraShortestPath;
import algorithm.YenKsp;
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

        Graph graph = new Graph(Path.PHOENIX_STOPS, Path.PHOENIX_GRAPH, 0);
        Graph weightedGraph = new Graph(Path.PHOENIX_STOPS, Path.PHOENIX_WEIGHTED_GRAPH, 1);

        Visualization.show(Mapping.reMapGraph(graph),null);

        Graph  mappedGraph = Mapping.reMapGraph(weightedGraph);
//        Graph graphConnected = Connectivity.removeIsolated(graph, THRESHOLD);
//        graphConnected = Mapping.reMapGraph(graphConnected);


        YenKsp yenKsp = new YenKsp(mappedGraph,0,7700,3);
        Visualization.show(mappedGraph,yenKsp.getShortestPaths());


//        Visualization.show(graphConnected,null);

        System.out.println(1);
    }
}