package graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/6/13
 */
class WeightedEdgeTest
{

    WeightedEdge e1, e2, e3, e4, e11, e12, e13;

    @BeforeEach
    void setUp()
    {
//        e1 = new WeightedEdge(new Node(1, 30, 30), new Node(2, 30, 40));
//        e2 = new WeightedEdge(new Node(1, -30, -30), new Node(2, 30, 40));
//        e3 = new WeightedEdge(new Node(1, -30, 30), new Node(2, 30, -40));
//        e4 = new WeightedEdge(new Node(1, 30, -30), new Node(2, -30, 40));
//
//        e11 = new WeightedEdge(new Node(1, 30, 30), new Node(2, 30, 40));
//        e12 = new WeightedEdge(new Node(1, 30, 30), new Node(2, 30, 40), 100);
//        e13 = new WeightedEdge(new Node(1, 30, 30), new Node(4, 30, 40));
    }

    @Test
    void testEdge()
    {
        assertEquals(e1.getWeight(), 964.5559669442341);
        assertEquals(e2.getWeight(), 9952.706443571686);
        assertEquals(e3.getWeight(), 9952.706443571686);
        assertEquals(e4.getWeight(), 9952.706443571686);
        assertEquals(e12.getWeight(), 100);
    }

    @Test
    void testEquals()
    {
        assertEquals(e1, e11);
        assertEquals(e1, e12);
        assertNotEquals(e1, e13);
    }

}