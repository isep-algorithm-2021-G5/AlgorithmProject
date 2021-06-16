package algorithm;


import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import graph.Distance;
import graph.Edge;
import graph.Graph;
import graph.Node;
import graph.ShortestPath;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import lombok.Getter;

/**
 * @author : Xuan MIAO
 * @version : 2.0.0
 * @date : 2021/6/14
 */
public class DijkstraShortestPath
{


    @Getter
    private final double[] distance;
    private final int[] previous;
    /**
     * Optimize algorithm time complexity by using PriorityQueue
     */
    private final PriorityQueue<Distance> pq;
    private final HashSet<Integer> marked;
    private final Integer start;
    private final Multimap<Integer, Edge> adjList;

    public DijkstraShortestPath(Graph graph, Integer start, Set<Integer> banNodes,
                                Set<Edge> banEdges)
    {
        Map<Integer, Node> nodes = graph.getNodes();
        this.adjList = ArrayListMultimap.create(graph.getAdjList());
        // +1 to process node id start from 1
        int nodeSize = nodes.size() + 1;
        this.distance = new double[nodeSize];
        this.previous = new int[nodeSize];
        this.pq = new PriorityQueue<>(adjList.size());
        this.marked = new HashSet<>();
        this.start = start;

        Arrays.fill(distance, Double.MAX_VALUE);
        distance[start] = 0;

        if (banNodes != null && !banNodes.isEmpty())
        {
            marked.addAll(banNodes);
        }

        if (banNodes != null && !banNodes.isEmpty())
        {
            for (Edge e : banEdges)
            {
                Set<Edge> set = new HashSet<>(adjList.get(e.getFrom()));
                set.remove(e);
                adjList.removeAll(e.getFrom());
                e.setWeight(Integer.MAX_VALUE);
                set.add(e);
                adjList.putAll(e.getFrom(), set);
            }
        }

        pq.add(new Distance(start, start, 0));

        while (marked.size() != nodeSize)
        {
            if (pq.isEmpty())
            {
                break;
            }
            int now = pq.remove().getTo();
            marked.add(now);
            calculate(now);

        }

    }

    private void calculate(int now)
    {
        double edgeDistance;
        double newDistance;

        for (Edge n : adjList.get(now))
        {
            int to = n.getTo();

            if (!marked.contains(to))
            {
                edgeDistance = n.getWeight();
                newDistance = distance[now] + edgeDistance;

                if (newDistance <= distance[to])
                {
                    distance[to] = newDistance;
                    previous[to] = n.getFrom();
                }

                pq.add(new Distance(n.getFrom(), to, distance[to]));
            }
        }
    }

    public boolean hasPathTo(int v)
    {
        return distance[v] != Double.MAX_VALUE;
    }

    public ShortestPath getShortestPath(int v)
    {
        if (!hasPathTo(v))
        {
            return null;
        }
        int pre = previous[v];
        Deque<Integer> stack = new LinkedList<>();
        while (pre != this.start)
        {
            stack.push(pre);
            pre = previous[pre];
        }
        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(this.start);
        int size = stack.size();
        for (int i = 0; i < size; i++)
        {
            deque.add(stack.pop());
        }
        deque.add(v);

        return new ShortestPath(this.start, v, deque, distance[v], distance);

    }

}
