package algorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import config.Path;
import graph.Graph;
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
    Graph graph = new Graph(Path.DIJKSTRA_NODES, Path.DIJKSTRA_GRAPH, 1);


    @BeforeEach
    void setUp()
    {
        dijkstra = new DijkstraShortestPath(graph, 0, null, null);
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
        assertEquals("[0, 1]", dijkstra.getShortestPath(1).getShortestPathList().toString());
        assertEquals("[0, 1, 6, 2]", dijkstra.getShortestPath(2).getShortestPathList().toString());
        assertEquals("[0, 1, 6, 7, 3]",
                     dijkstra.getShortestPath(3).getShortestPathList().toString());
        assertEquals("[0, 4]", dijkstra.getShortestPath(4).getShortestPathList().toString());
        assertEquals("[0, 1, 5]", dijkstra.getShortestPath(5).getShortestPathList().toString());
        assertEquals("[0, 1, 6]", dijkstra.getShortestPath(6).getShortestPathList().toString());
        assertEquals("[0, 1, 6, 7]", dijkstra.getShortestPath(7).getShortestPathList().toString());
    }

    @Test
    void getDistance()
    {
        double[] distance = {0, 1, 8, 16, 5, 9, 6, 10};
        double[] dijDistance = dijkstra.getDistance();
        for (int i = 0; i < distance.length; i++)
        {
            assertEquals(distance[i], dijDistance[i]);
        }
    }
}