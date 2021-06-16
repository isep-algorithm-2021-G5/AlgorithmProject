package algorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import config.Path;
import graph.Graph;
import graph.ShortestPath;
import java.util.ArrayList;
import java.util.List;
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
        List<ShortestPath> spsList = new ArrayList<>(sps);
        assertEquals(3, sps.size());
        assertEquals("[0, 1, 6]", spsList.get(0).getShortestPathList().toString());
        assertEquals("[0, 1, 5, 6]", spsList.get(1).getShortestPathList().toString());
        assertEquals("[0, 4, 7, 6]", spsList.get(2).getShortestPathList().toString());
    }
}