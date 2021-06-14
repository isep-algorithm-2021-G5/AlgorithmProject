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

        Visualization.show(graph);

        graph = Mapping.reMapGraph(graph);

        Graph graphConnected = Connectivity.removeIsolated(graph, THRESHOLD);
        graphConnected = Mapping.reMapGraph(graphConnected);
        Visualization.show(graphConnected);

        System.out.println(1);
    }
}