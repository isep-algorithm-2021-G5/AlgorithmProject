import algorithm.BfsShortestPath;
import algorithm.YenKsp;
import config.Path;
import graph.Graph;
import graph.ShortestPath;
import java.util.Set;
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

        Visualization.show(Mapping.reMapGraph(graph), null);

        Graph mappedGraph = Mapping.reMapGraph(weightedGraph);
//        Graph graphConnected = Connectivity.removeIsolated(graph, THRESHOLD);
//        graphConnected = Mapping.reMapGraph(graphConnected);


        YenKsp yenKsp = new YenKsp(mappedGraph, 800, 4100, 2);
        Set<ShortestPath> sps = yenKsp.getShortestPaths();
        for (ShortestPath sp : sps)
        {
            System.out.println(
                    "Size: " + sp.getShortestPathList().size() + "Weight: " + sp.getWeight());
            System.out.println(sp.getShortestPathList().toString());
        }
        Visualization.show(mappedGraph, sps);

//        Visualization.show(graphConnected,null);

    }
}