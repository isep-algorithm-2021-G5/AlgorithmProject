package algorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import config.Path;
import graph.Graph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author : Xianqi LIU
 * @version : 1.0.0
 * @date : 2021/6/14
 */
class BfsShortestPathTest
{

    BfsShortestPath bfs;
    Graph graph = new Graph(Path.BFS_NODES, Path.BFS_GRAPH, 0);

    @BeforeEach
    void setUp()
    {
        bfs = new BfsShortestPath(graph, 0);
    }

    @Test
    void hasPathTo()
    {
        for (int i = 1; i < 8; i++)
        {
            assertTrue(bfs.hasPathTo(i));
        }
        for (int i = 8; i < 10; i++)
        {
            assertFalse(bfs.hasPathTo(i));
        }
    }

    @Test
    void getShortestPathTo()
    {
        assertEquals("[0, 1]", bfs.getShortestPath(1).toString());
        assertEquals("[0, 1, 6, 2]", bfs.getShortestPath(2).toString());
        assertEquals("[0, 4, 7, 3]", bfs.getShortestPath(3).toString());
        assertEquals("[0, 4]", bfs.getShortestPath(4).toString());
        assertEquals("[0, 1, 5]", bfs.getShortestPath(5).toString());
        assertEquals("[0, 1, 6]", bfs.getShortestPath(6).toString());
        assertEquals("[0, 4, 7]", bfs.getShortestPath(7).toString());
    }

}
