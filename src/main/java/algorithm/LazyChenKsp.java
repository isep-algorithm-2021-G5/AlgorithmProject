package algorithm;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import graph.Edge;
import graph.Graph;
import graph.ShortestPath;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeSet;
import lombok.val;

/**
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/6/15
 */
public class LazyChenKsp
{

    Graph graph;
    Integer start;
    Integer end;
    Integer sigma;
    List<ShortestPath> shortestPaths;

    public LazyChenKsp(Graph graph, Integer start, Integer end, Integer sigma)
    {
        this.graph = graph;
        this.start = start;
        this.end = end;
        this.sigma = sigma;
        List<Graph> subGraphs = buildSubGraphs();
        this.shortestPaths = new ArrayList<>();
        for (Graph g : subGraphs)
        {
            DijkstraShortestPath dij = new DijkstraShortestPath(g, start,null,null);
            ShortestPath sp = dij.getShortestPath(end);
            sp.setWeight(sp.getWeight() + Double.valueOf(sigma) / getPathCapacity(sp));
            shortestPaths.add(sp);
        }

    }

    public List<ShortestPath> getKshortestPaths(Integer k)
    {
        int kk = k > shortestPaths.size() ? shortestPaths.size() : k;
        PriorityQueue<ShortestPath> spSorted = new PriorityQueue<>(shortestPaths);
        List<ShortestPath> res = new ArrayList<>();
        for (int i = 0; i < k; i++)
        {
            res.add(spSorted.remove());
        }

        return res;
    }

    private Integer getPathCapacity(ShortestPath sp)
    {
        Integer[] path = sp.getShortestPathList().toArray(new Integer[0]);
        int capacity = Integer.MAX_VALUE;
        for (int i = 0; i < path.length - 1; i++)
        {
            for (Map.Entry<Integer, Edge> entry : graph.getAdjList().entries())
            {
                if (entry.getKey().equals(path[i])
                        && entry.getValue().getTo().equals(path[i + 1])
                        && entry.getValue().getCapacity() < capacity)
                {
                    capacity = entry.getValue().getCapacity();
                }
            }

        }
        return capacity;

    }

    private List<Graph> buildSubGraphs()
    {
        List<Graph> graphs = new ArrayList<>();
        val adjList = this.graph.getAdjList();
        val nodes = this.graph.getNodes();
        TreeSet<Integer> capacities = new TreeSet<>();
        for (Edge edge : adjList.values())
        {
            capacities.add(edge.getCapacity());
        }
        for (Integer capacity : capacities)
        {
            Multimap<Integer, Edge> newAdjList = ArrayListMultimap.create();
            for (Map.Entry<Integer, Edge> entry : adjList.entries())
            {
                if (entry.getValue().getCapacity() >= capacity)
                {
                    newAdjList.put(entry.getKey(), entry.getValue());
                }
                BfsShortestPath bfsShortestPath = new BfsShortestPath(graph, start);
                if (!bfsShortestPath.hasPathTo(end))
                {
                    return graphs;
                }
            }
            graphs.add(new Graph(nodes, newAdjList));
        }
        return graphs;
    }
}
