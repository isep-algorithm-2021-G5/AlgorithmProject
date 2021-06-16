package graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/6/14
 */
class DistanceTest
{

    Distance d1, d2, d3, d4;

    @BeforeEach
    void setUp()
    {
        d1 = new Distance(1, 2, 10);
        d2 = new Distance(1, 2, 10);
        d3 = new Distance(1, 2, 20);
        d4 = new Distance(1, 3, 5);
    }

    @Test
    void testCompare()
    {
        assertEquals(0, d1.compareTo(d2));
        assertTrue(d1.compareTo(d3) < 0);
        assertTrue(d1.compareTo(d4) > 0);
    }
}