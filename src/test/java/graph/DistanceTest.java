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

    Distance d1, d2, d3, d4, d5;

    @BeforeEach
    void setUp()
    {
        d1 = new Distance(1, 2, 10);
        d2 = new Distance(1, 2, 10);
        d3 = new Distance(1, 2, 20);
        d4 = new Distance(1, 3, 10);
        d5 = new Distance(1, 3, 5);
    }

    @Test
    void compare()
    {
        assertEquals(d1, d2);
        assertEquals(d2, d1);
        assertNotEquals(d1, d3);
        assertNotEquals(d1, d4);
        assertNotEquals(d1, d5);
    }

    @Test
    void testEquals()
    {
        assertEquals(0, d1.compareTo(d2));
        assertTrue(d1.compareTo(d3) < 0);
        assertTrue(d1.compareTo(d5) > 0);
    }
}