import com.google.common.collect.Multimap;
import graph.Edge;
import graph.Node;
import graph.WeightedEdge;
import java.util.Map;

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

    public static void main(String[] args)
    {
        GraphFileReader graphFileReader = new GraphFileReader();

        Map<Integer, Node> nodes = graphFileReader.readNodes(STOPS);

        Multimap<Integer, Edge> graph = graphFileReader.readGraph(GRAPH);

        Multimap<Integer, WeightedEdge> weightedGraph = graphFileReader
                .readWeightedGraph(WEIGHTED_GRAPH);

        System.out.println(1);

    }
}
