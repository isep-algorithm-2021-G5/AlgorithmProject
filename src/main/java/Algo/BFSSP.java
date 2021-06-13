package Algo;

import com.google.common.collect.Multimap;
import graph.Edge;
import graph.Node;

import java.util.*;

public class BFSSP {

    private boolean[] marked;
    private int[] previous;
    private int[] distance;

    private Multimap<Integer, Edge> graph;
    private Map<Integer, Node> nodes;


    public void bfs(Map<Integer, Node> nodes, Multimap<Integer, Edge> graph, int start)
    {
        this.nodes = nodes;
        this.graph = graph;

        int initCapacity = nodes.size()+100000;

        this.marked = new boolean[initCapacity];
        this.previous = new int[initCapacity];
        this.distance = new int[initCapacity];

        // a queue for BFS
        Queue<Integer> queue = new LinkedList<>();
        // Mark the current node as visited and enqueue it
        queue.add(start);
        distance[start] = 0;
        marked[start] = true;

        while (queue.size() != 0) {
            // Init adjList
            Collection<Edge> adjList = null ;
            // Dequeue a node from queue
            int v = queue.poll();
            adjList = graph.get(v);

            // Get all neighbors of the dequeued node v
            // If a neighbor has not been visited, then mark it
            // visited and enqueue it
            for (Edge edge : adjList) {
                if(!marked[edge.getTo()]){
                    queue.add(edge.getTo());
                    marked[edge.getTo()] = true;
                    previous[edge.getTo()] = v;
                    distance[edge.getTo()] = distance[edge.getFrom()] + 1;
                }
            }
        }
    }

    // return true if there is a path from s to v
    public boolean hasPathTo(int v)
    {
        return this.marked[v];
    }

    // return the length pf the shortest path from s to v
    public int distTo(int v)
    {
        return this.distance[v];
    }

    // print the shortest path from s to v
    public List<Integer> printSP(int v)
    {
        if(!hasPathTo(v))
        {
            return null;
        }
        Stack<Integer> path = new Stack<>();
        int x;
        for(x = v; this.distance[x] != 0; x = this.previous[x])
        {
            path.push(x);
        }
        path.push(x);
        Collections.reverse(path);
        return path;
    }
}
