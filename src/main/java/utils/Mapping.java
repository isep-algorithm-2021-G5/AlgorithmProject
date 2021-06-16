package utils;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import graph.Edge;
import graph.Graph;
import graph.Node;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import lombok.val;

/**
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/6/14
 */
public final class Mapping
{

    private Mapping()
    {
        //Won't be called
    }

    private static Map<Integer, Integer> getIdMap(Graph graph)
    {
        int i = 0;
        val nodes = graph.getNodes();
        val sortedNodes = new TreeMap<>(nodes);
        Map<Integer, Integer> map = new TreeMap<>();
        for (Integer stopId : sortedNodes.keySet())
        {
            map.put(stopId, i++);
        }
        return map;
    }

    public static Graph reMapGraph(Graph graph)
    {
        val idMap = getIdMap(graph);
        val nodes = graph.getNodes();
        val adjList = graph.getAdjList();
        Map<Integer, Node> newNodes = new HashMap<>(nodes.size());
        Multimap<Integer, Edge> newAdjList = ArrayListMultimap.create();
        for (Map.Entry<Integer, Node> entry : nodes.entrySet())
        {
            newNodes.put(idMap.get(entry.getKey()), entry.getValue());
        }
        for (Map.Entry<Integer, Edge> entry : adjList.entries())
        {
            newAdjList.put(idMap.get(entry.getKey()),
                           new Edge(idMap.get(entry.getValue().getFrom()),
                                    idMap.get(entry.getValue().getTo()),
                                    entry.getValue().getWeight()));
        }
        return new Graph(newNodes, newAdjList);
    }

}
