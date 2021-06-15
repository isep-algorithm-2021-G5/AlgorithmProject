package graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/6/15
 */
class EdgeTest
{

    Edge e1, e2, e3, e4;

    @BeforeEach
    void setUp()
    {
        e1 = new Edge(1, 2);
        e2 = new Edge(1, 2, 10);
        e3 = new Edge(1, 2, 5, 10);
        e4 = new Edge(1, 5, 3);
    }

    @Test
    void testEquals()
    {
        assertEquals(e1, e2);
        assertEquals(e3, e2);
        assertEquals(e1, e3);
        assertNotEquals(e1, e4);
    }

    @Test
    void testHashCode()
    {
        assertEquals(e1.hashCode(), e2.hashCode());
        assertEquals(e3.hashCode(), e2.hashCode());
        assertEquals(e1.hashCode(), e3.hashCode());
        assertNotEquals(e1.hashCode(), e4.hashCode());
    }

    @Test
    void setTest()
    {
        Set<Edge> set = new HashSet<>();
        set.add(e1);
        assertEquals(1, set.size());
        set.add(e2);
        assertEquals(1, set.size());
        set.remove(e3);
        assertEquals(0, set.size());
        set.add(e3);
        assertEquals(1, set.size());
        set.add(e4);
        assertEquals(2, set.size());
    }
}