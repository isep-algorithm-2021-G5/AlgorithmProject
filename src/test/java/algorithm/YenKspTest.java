package algorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import config.Path;
import graph.Graph;
import graph.ShortestPath;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Mapping;

/**
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/6/15
 */
class YenKspTest
{

    Graph graph = new Graph(Path.DIJKSTRA_NODES, Path.DIJKSTRA_GRAPH, 1);
    Graph bus = new Graph(Path.PHOENIX_STOPS, Path.PHOENIX_WEIGHTED_GRAPH, 1);
    Graph mappedGraph = Mapping.reMapGraph(bus);
    YenKsp yenKsp;

    @BeforeEach
    void setUp()
    {

    }

    @Test
    void getShortestPaths()
    {
        yenKsp = new YenKsp(graph, 0, 6, 3);
        Set<ShortestPath> sps = yenKsp.getShortestPaths();
        assertEquals(3,sps.size());
    }

    @Test
    void getBusShortestPaths()
    {
        yenKsp = new YenKsp(mappedGraph, 0, 3300, 3);
        Set<ShortestPath> sps = yenKsp.getShortestPaths();
        assertEquals(3,sps.size());
    }
}