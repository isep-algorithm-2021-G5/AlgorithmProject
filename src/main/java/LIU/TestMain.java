package LIU;

import LIU.graph.Edge;
import LIU.graph.Graph;

import java.io.IOException;

public class TestMain {
    public static void main( String[] args ) throws IOException {
        Graph graph = new Graph();
        graph.BuildGraph();
        //test first edge
        System.out.println("第一条边");
        Edge testEdge = graph.getEdges().get(0);
        System.out.println("from:"+testEdge.from());
        System.out.println("to:"+testEdge.to());
        System.out.println("computed weight:"+testEdge.weight());
        //test printf graph
        System.out.println("我只打印了前三条");
        graph.printGraph();
    }
}
