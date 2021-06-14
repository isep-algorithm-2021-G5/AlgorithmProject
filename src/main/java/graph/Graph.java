package graph;

import com.google.common.collect.Multimap;
import java.util.Map;
import lombok.Getter;
import utils.GraphReader;

/**
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/6/14
 */
@Getter
public class Graph
{

    Map<Integer, Node> nodes;

    Multimap<Integer, Edge> adjList;

    public Graph(String nodesPath, String graphPath, boolean weighted)
    {
        if (weighted = false)
        {
            this.nodes = GraphReader.readNodes(nodesPath);
            this.adjList = GraphReader.readAdjList(graphPath);
        } else
        {
            this.nodes = GraphReader.readNodes(nodesPath);
            this.adjList = GraphReader.readWeightedAdjList(graphPath);
        }

    }

    public Graph(Map<Integer, Node> nodes,
                 Multimap<Integer, Edge> adjList)
    {
        this.nodes = nodes;
        this.adjList = adjList;
    }
}
