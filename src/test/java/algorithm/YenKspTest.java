package algorithm;

import static org.junit.jupiter.api.Assertions.*;

import config.Path;
import graph.Graph;
import graph.ShortestPath;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/6/15
 */
class YenKspTest
{
    Graph graph = new Graph(Path.DIJKSTRA_NODES, Path.DIJKSTRA_GRAPH, 1);
    YenKsp yenKsp;

    @BeforeEach
    void setUp()
    {
        yenKsp = new YenKsp(graph,0,3,3);
    }

    @Test
    void getShortestPaths()
    {
        Set<ShortestPath> sps = yenKsp.getShortestPaths();
        System.out.println(1);
    }
}