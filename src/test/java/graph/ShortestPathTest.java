package graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

import algorithm.DijkstraShortestPath;
import config.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/6/15
 */
class ShortestPathTest
{

    DijkstraShortestPath dijkstra;
    Graph graph = new Graph(Path.DIJKSTRA_NODES, Path.DIJKSTRA_GRAPH, 1);
    ShortestPath sp,sp2;

    @BeforeEach
    void setUp()
    {
        dijkstra = new DijkstraShortestPath(graph, 0);
        sp = dijkstra.getShortestPath(3);
        sp2 = dijkstra.getShortestPath(4);
    }

    @Test
    void split()
    {
        sp.split(0);
        sp.split(3);
        assertEquals(3, sp.getShortestPathList().size());
    }

    @Test
    void merge()
    {
        sp.merge(sp2);
        assertEquals(7, sp.getShortestPathList().size());
    }
}