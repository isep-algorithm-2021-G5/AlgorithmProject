package algorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import config.Path;
import graph.Graph;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/6/14
 */
class DijkstraShortestPathTest
{

    DijkstraShortestPath dijkstra;
    Graph graph = new Graph(Path.DIJKSTRA_NODES, Path.DIJKSTRA_GRAPH, true);


    @BeforeEach
    void setUp()
    {
        dijkstra = new DijkstraShortestPath(graph, 0);
    }

    @Test
    void hasPathTo()
    {
        for (int i = 1; i < 8; i++)
        {
            assertTrue(dijkstra.hasPathTo(i));
        }
        for (int i = 8; i < 10; i++)
        {
            assertFalse(dijkstra.hasPathTo(i));
        }
    }

    @Test
    void getShortestPathTo()
    {
        assertEquals("0 -> 1",dijkstra.getShortestPathTo(1));
        assertEquals("0 -> 1 -> 6 -> 2", dijkstra.getShortestPathTo(2));
        assertEquals("0 -> 1 -> 6 -> 7 -> 3", dijkstra.getShortestPathTo(3));
        assertEquals("0 -> 4", dijkstra.getShortestPathTo(4));
        assertEquals("0 -> 1 -> 5", dijkstra.getShortestPathTo(5));
        assertEquals("0 -> 1 -> 6", dijkstra.getShortestPathTo(6));
        assertEquals("0 -> 1 -> 6 -> 7", dijkstra.getShortestPathTo(7));
    }

    @Test
    void getDistance()
    {
        double[] distance = {0,1,8,16,5,9,6,10,Double.MAX_VALUE,Double.MAX_VALUE};
        assertEquals(Arrays.toString(distance), Arrays.toString(dijkstra.getDistance()));
    }
}