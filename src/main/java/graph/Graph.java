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

    /**
     * Use {@link Multimap} to store adjList
     */
    Multimap<Integer, Edge> adjList;


    public Graph(String nodesPath, String graphPath, int type)
    {
        this.nodes = GraphReader.readNodes(nodesPath);
        if (type == 0)
        {
            this.adjList = GraphReader.readAdjList(graphPath);
        } else if (type == 1)
        {
            this.adjList = GraphReader.readWeightedAdjList(graphPath);
        } else
        {
            this.adjList = GraphReader.readCapacityAdjList(graphPath);
        }

    }

    public Graph(Map<Integer, Node> nodes,
                 Multimap<Integer, Edge> adjList)
    {
        this.nodes = nodes;
        this.adjList = adjList;
    }
}
