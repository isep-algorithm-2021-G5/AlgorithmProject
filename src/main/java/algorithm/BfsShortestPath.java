package algorithm;

import graph.Edge;
import graph.Graph;

import java.util.*;

import lombok.val;

/**
 * @author : Xianqi LIU
 * @version : 1.0.0
 * @date : 2021/6/13
 */
public class BfsShortestPath
{

    private final boolean[] marked;
    private final int[] previous;
    private final int[] distance;


    public BfsShortestPath(Graph graph, Integer start)
    {

        val nodes = graph.getNodes();
        val adj = graph.getAdjList();
        //TODO 改成 map
        int initCapacity = nodes.size() + 100000;

        this.marked = new boolean[initCapacity];
        this.previous = new int[initCapacity];
        this.distance = new int[initCapacity];

        // a queue for BFS
        Queue<Integer> queue = new LinkedList<>();
        // Mark the current node as visited and enqueue it
        queue.add(start);
        distance[start] = 0;
        marked[start] = true;

        while (queue.size() != 0)
        {
            // Init adjList
            Collection<Edge> adjList;
            // Dequeue a node from queue
            int v = queue.poll();
            adjList = adj.get(v);

            // Get all neighbors of the dequeued node v
            // If a neighbor has not been visited, then mark it
            // visited and enqueue it
            for (Edge edge : adjList)
            {
                if (!marked[edge.getTo()])
                {
                    queue.add(edge.getTo());
                    marked[edge.getTo()] = true;
                    previous[edge.getTo()] = v;
                    distance[edge.getTo()] = distance[edge.getFrom()] + 1;
                }
            }
        }
    }

    /**
     * return true if there is a path from s to v
     */
    public boolean hasPathTo(int v)
    {
        return this.marked[v];
    }

    /**
     * return the length pf the shortest path from s to v
     */
    public int distTo(int v)
    {
        return this.distance[v];
    }

    /**
     * print the shortest path from s to v
     */
    public Deque<Integer> printShortestPath(int v)
    {
        Deque<Integer> path = new LinkedList<>();
        boolean done = false;
        int currentNode = v;

        if (!hasPathTo(v)) {
            return null;
        }
        while (!done) {
            path.addFirst(currentNode);
            if (this.distance[currentNode] == 0) {
                done = true;
            } else {
                currentNode = this.previous[currentNode];
            }

        }
        return path;
    }
}
