package LIU;

import LIU.graph.Edge;
import LIU.graph.Graph;

import java.io.IOException;

public class TestMain {
    public static void main( String[] args ) throws IOException {
        Graph graph = new Graph();
        graph.BuildGraph();
        Edge testEdge = graph.getEdges().get(0);
        System.out.println(testEdge.from());
        System.out.println(testEdge.to());
        System.out.println(testEdge.weight());
    }
}
