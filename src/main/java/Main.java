import algorithm.BfsShortestPath;
import algorithm.DijkstraShortestPath;
import algorithm.YenKsp;
import config.Path;
import graph.Graph;
import graph.ShortestPath;
import java.util.Collections;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import utils.Mapping;
import utils.Visualization;

/**
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/6/13
 */
public class Main
{

    private static final int START = 800;
    private static final int END = 4100;
    private static final int K = 3;

    public static void main(String[] args)
    {
        System.out.println("\033[36;4m"
                                   + "This is a visualization portal of some of the algorithm "
                                   + "results, please check the report for more details."
                                   + "\033[0m");
        System.out.println("Please choose:");
        System.out.println("[1] Bus stop graph of Phoenix");
        System.out.println("[2] Dijkstra's shortest path");
        System.out.println("[3] K-shortest paths(It will take one to two minutes)");
        System.out.println("[4] BFS shortest path");
        Scanner in = new Scanner(System.in);
        Graph graph = new Graph(Path.PHOENIX_STOPS, Path.PHOENIX_GRAPH, 0);
        Graph weightedGraph = new Graph(Path.PHOENIX_STOPS, Path.PHOENIX_WEIGHTED_GRAPH, 1);
        Graph mappedGraph = Mapping.reMapGraph(weightedGraph);
        Graph mappedGraph2 = Mapping.reMapGraph(graph);
        switch (in.nextInt())
        {
            case 1:
                Visualization.show(Mapping.reMapGraph(graph), null, "Graph", true);
                break;
            case 2:
                DijkstraShortestPath dij = new DijkstraShortestPath(mappedGraph, START, null, null);
                Visualization.show(mappedGraph,
                                   Collections.singleton(dij.getShortestPath(END)), "Dijkstra",
                                   true);
                break;
            case 3:
                YenKsp yenKsp = new YenKsp(mappedGraph, START, END, K);
                Set<ShortestPath> sps = yenKsp.getShortestPaths();
                for (ShortestPath sp : sps)
                {
                    System.out.println(
                            "Size: " + sp.getShortestPathList().size() + "Weight: " + sp
                                    .getWeight());
                    System.out.println(sp.getShortestPathList().toString());
                }
                Visualization.show(mappedGraph, sps, "Yen", true);
                break;
            case 4:
                BfsShortestPath bfs = new BfsShortestPath(mappedGraph2,START);
                ShortestPath sp = new ShortestPath(START,END,bfs.getShortestPath(END));
                System.out.println("BFS Shortest Path: "+START+" -> "+END);
                System.out.println("The stops number passed is: " + sp.getShortestPathList().size());
                for(int i = 0; i < sp.getShortestPathList().size(); i++){
                    System.out.print(sp.getShortestPathList().get(i) + "->");
                    if(i % 20 == 0 && i != 0) System.out.println();
                }
                Set<ShortestPath> spSet = new TreeSet<>();
                spSet.add(sp);
                Visualization.show(mappedGraph2, spSet, "BFS", true);
                break;
            default:
                System.out.println("Wrong input");
        }

    }
}