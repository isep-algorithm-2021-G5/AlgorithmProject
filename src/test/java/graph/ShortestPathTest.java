package graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import algorithm.DijkstraShortestPath;
import com.google.common.collect.Lists;
import config.Path;
import java.util.ArrayDeque;
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
    ShortestPath sp, sp2;
    ShortestPath s1, s2,s3;

    @BeforeEach
    void setUp()
    {
        dijkstra = new DijkstraShortestPath(graph, 0, null, null);
        sp = dijkstra.getShortestPath(3);
        sp2 = dijkstra.getShortestPath(4);

        s1 = new ShortestPath(1,2,new ArrayDeque<>(Lists.newArrayList(1,2,3,4,5)),20);
        s2 = new ShortestPath(1,3,new ArrayDeque<>(Lists.newArrayList(1,2,3,4,5)),10);
        s3 = new ShortestPath(1,2,new ArrayDeque<>(Lists.newArrayList(1,4,5)),20);
    }

    @Test
    void split()
    {
        sp.split(3);
        assertEquals(3, sp.getShortestPathList().size());
    }

    @Test
    void merge()
    {
        sp.merge(sp2);
        assertEquals(7, sp.getShortestPathList().size());
    }

    @Test
    void testHashCode()
    {
        assertEquals(s1.hashCode(),s2.hashCode());
        assertNotEquals(s1.hashCode(),s3.hashCode());

    }

    @Test
    void testEquals()
    {
        assertEquals(s1,s2);
        assertNotEquals(s1,s3);

    }
}