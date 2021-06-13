package graph;

import com.google.common.collect.Multimap;
import java.util.ArrayList;
import java.util.Map;
import lombok.Getter;
import utils.GraphFileReader;

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

    public Graph(String nodesPath, String graphPath)
    {
        this.nodes = GraphFileReader.readNodes(nodesPath);
        this.adjList = GraphFileReader.readGraph(graphPath);
    }

    public Graph(Map<Integer, Node> nodes,
                 Multimap<Integer, Edge> adjList)
    {
        this.nodes = nodes;
        this.adjList = adjList;
    }
}
