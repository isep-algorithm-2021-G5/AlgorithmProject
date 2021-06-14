package algorithm;

import config.Path;
import graph.Graph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : Xianqi LIU
 * @version : 1.0.0
 * @date : 2021/6/14
 */
class BfsShortestPathTest {

    BfsShortestPath bfs;
    Graph graph = new Graph(Path.BFS_NODES,Path.BFS_GRAPH,0);

    @BeforeEach
    void setUp()
    {
        bfs = new BfsShortestPath(graph,0);
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
        assertEquals("[0, 1]",bfs.printShortestPath(1).toString());
        assertEquals("[0, 1, 6, 2]",bfs.printShortestPath(2).toString());
        assertEquals("[0, 4, 7, 3]",bfs.printShortestPath(3).toString());
        assertEquals("[0, 4]",bfs.printShortestPath(4).toString());
        assertEquals("[0, 1, 5]",bfs.printShortestPath(5).toString());
        assertEquals("[0, 1, 6]",bfs.printShortestPath(6).toString());
        assertEquals("[0, 4, 7]",bfs.printShortestPath(7).toString());
    }

}
