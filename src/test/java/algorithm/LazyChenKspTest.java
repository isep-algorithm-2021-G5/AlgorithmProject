package algorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import config.Path;
import graph.Graph;
import graph.ShortestPath;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/6/15
 */
class LazyChenKspTest
{

    LazyChenKsp lazyChenKsp;

    @BeforeEach
    void setUp()
    {
        lazyChenKsp = new LazyChenKsp(new Graph(Path.LAZY_CHEN_NODES, Path.LAZY_CHEN_GRAPH, 2),
                                      1, 6, 100);

    }

    @Test
    void getKshortestPaths()
    {
        List<ShortestPath> sp = lazyChenKsp.getKshortestPaths(3);
        assertEquals(75, sp.get(0).getWeight());
        assertEquals(80, sp.get(1).getWeight());
        assertEquals(85, sp.get(2).getWeight());

    }
}