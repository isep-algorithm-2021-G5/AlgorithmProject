package graph;

import com.google.common.collect.Multimap;
import java.util.Map;
import lombok.Getter;
import utils.GraphFileReader;

/**
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/6/14
 */
@Getter
public class WeightedGraph
{

    Map<Integer, Node> nodes;

    Multimap<Integer, WeightedEdge> adjList;

    public WeightedGraph(String nodesPath, String weightedGraphPath)
    {
        this.nodes = GraphFileReader.readNodes(nodesPath);
        this.adjList = GraphFileReader.readWeightedGraph(weightedGraphPath);
    }
}
