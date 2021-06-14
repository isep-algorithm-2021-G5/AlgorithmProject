package algorithm;


import com.google.common.collect.Multimap;
import graph.Distance;
import graph.Edge;
import graph.Graph;
import graph.Node;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;
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
    private final PriorityQueue<Distance> pq;
    private final HashSet<Integer> marked;
    private final Integer start;
    private final Map<Integer, Node> nodes;
    private final Multimap<Integer, Edge> adjList;

    public DijkstraShortestPath(Graph graph, Integer start)
    {
        this.nodes = graph.getNodes();
        this.adjList = graph.getAdjList();
        int nodeSize = nodes.size();
        this.distance = new double[nodeSize];
        this.previous = new int[nodeSize];
        this.pq = new PriorityQueue<>(adjList.size());
        this.marked = new HashSet<>();
        this.start = start;

        Arrays.fill(distance, Double.MAX_VALUE);
        distance[start] = 0;

        pq.add(new Distance(start, start, 0));

        while (marked.size() != nodeSize)
        {
            if (pq.size() == 0)
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
        double edgeDistance = -1;
        double newDistance = -1;

        // All the neighbors of v
        for (Edge n : adjList.get(now))
        {
            int to = n.getTo();

            // If current node hasn't already been processed
            if (!marked.contains(to))
            {
                edgeDistance = n.getWeight();
                newDistance = distance[now] + edgeDistance;

                // If new distance is cheaper in cost
                if (newDistance < distance[to])
                {
                    distance[to] = newDistance;
                    previous[to] = n.getFrom();
                }

                // Add the current node to the queue
                pq.add(new Distance(n.getFrom(), to, distance[to]));
            }
        }
    }

    public boolean hasPathTo(int v)
    {
        return distance[v] != Double.MAX_VALUE;
    }

    public double getDistanceTo(int v)
    {
        return distance[v];
    }

    public String getShortestPathTo(int v)
    {
        int pre = previous[v];
        Stack<Integer> stack = new Stack<>();
        while (pre != this.start)
        {
            stack.push(pre);
            pre = previous[pre];
        }
        StringBuilder str = new StringBuilder(this.start + " -> ");
        int size = stack.size();
        for (int i = 0; i < size; i++)
        {
            str.append(stack.pop()).append(" -> ");
        }
        str.append(v);

        return str.toString();

    }

}
