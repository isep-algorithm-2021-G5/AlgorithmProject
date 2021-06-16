package utils;

import algorithm.BfsShortestPath;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import graph.Edge;
import graph.Graph;
import graph.Node;
import java.util.HashMap;
import java.util.Map;
import lombok.val;

/**
 * Detection of graph connectivity and remove small isolated parts (based on {@link
 * BfsShortestPath})
 *
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/6/14
 */
public final class Connectivity
{

    private Connectivity()
    {
        //Won't be called
    }

    /**
     * Calculate each node can connect to how many other nodes
     *
     * @param graph graph
     * @return ConnectivityMap
     */
    private static Map<Integer, Integer> calculateAll(Graph graph)
    {
        Map<Integer, Integer> count = new HashMap<>(graph.getNodes().size());
        val nodes = graph.getNodes();

        for (Integer from : nodes.keySet())
        {
            BfsShortestPath bfs = new BfsShortestPath(graph, from);
            for (Integer to : nodes.keySet())
            {
                if (bfs.hasPathTo(to))
                {
                    Integer c = count.get(from);
                    if (c != null)
                    {
                        c += 1;
                        count.put(from, c);
                    } else
                    {
                        count.put(from, 1);
                    }
                }
            }
        }
        return count;
    }

    /**
     * Remove small isolated parts from the diagram and keep only the main parts
     *
     * @param graph     graph
     * @param threshold Nodes that are connected to less than the threshold number of nodes will be
     *                  removed
     * @return New graph with isolated parts removed
     */
    public static Graph removeIsolated(Graph graph, int threshold)
    {
        val count = calculateAll(graph);
        Map<Integer, Node> nodes = new HashMap<>(graph.getNodes());
        Multimap<Integer, Edge> adjList = ArrayListMultimap.create();
        for (Map.Entry<Integer, Integer> entry : count.entrySet())
        {
            if (entry.getValue() < threshold)
            {
                nodes.remove(entry.getKey());
            }
        }
        for (Map.Entry<Integer, Edge> entry : graph.getAdjList().entries())
        {
            if (nodes.containsKey(entry.getKey()) && nodes.containsKey(entry.getValue().getTo()))
            {
                adjList.put(entry.getKey(), entry.getValue());
            }

        }
        return new Graph(nodes, adjList);
    }
}
