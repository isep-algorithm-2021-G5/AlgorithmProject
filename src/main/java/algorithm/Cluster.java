package algorithm;

import graph.Edge;
import graph.Graph;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArrayList;

public class Cluster {

    private Graph graph;

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public Cluster(Graph graph){
        this.graph = graph;
    }

    public void findClusters(int max) {
        int count = 1;
        CopyOnWriteArrayList<ArrayList<Integer>> clusterList = new CopyOnWriteArrayList<>();
        ArrayList<Integer> cluster1 = new ArrayList<>();
        clusterList.add(cluster1);
        Set<Integer> addedNodes = new TreeSet<>();

        while (count < max) {
            //find maxBtwEdge
            EdgeBTW edgeClustering = new EdgeBTW(this.graph);
            Edge maxBtwEdge = edgeClustering.getEdgeMaxBTW();
            System.out.println("MaxBtwEdge : " + maxBtwEdge.getFrom() + " --✂-- " + maxBtwEdge.getTo());

            //New graph without the previous maxBtwEdge
            Graph newGraph = this.getGraph();
            newGraph.getAdjList().get(maxBtwEdge.getFrom()).remove(maxBtwEdge);

            ArrayList<Integer> currentCluster = cluster1;
            Set<Integer> nodeSet = newGraph.getNodes().keySet();

            for (int src : nodeSet) {
                if (!addedNodes.contains(src))
                    for (int dest : nodeSet) {
                        if (!addedNodes.contains(dest)) {
                            if (src != dest) {
                                BfsShortestPath bfsSP= new BfsShortestPath(newGraph, src);
                                if (!bfsSP.hasPathTo(dest)) {
                                    //for (ArrayList<Integer> cluster : clusterList) {
                                        if (currentCluster.contains(src) && !currentCluster.contains(dest)) {
                                            if (count < max) {
                                                ArrayList<Integer> newCluster = new ArrayList<>();
                                                newCluster.add(dest);
                                                addedNodes.add(dest);
                                                clusterList.add(newCluster);
                                                //System.out.println(":)");
                                                count += 1;
                                                currentCluster = newCluster;
                                            } else {
                                                for (Integer node : nodeSet) {
                                                    if (!addedNodes.contains(node)) {
                                                        currentCluster.add(node);
                                                    }
                                                }
                                            }
                                        }
                                    //}
                                } else {
                                    if (!currentCluster.contains(src) && !addedNodes.contains(src)) {
                                        currentCluster.add(src);
                                        addedNodes.add(src);
                                    }
                                    if (!currentCluster.contains(dest) && !addedNodes.contains(dest)) {
                                        currentCluster.add(dest);
                                        addedNodes.add(dest);
                                    }
                                }
                            }
                        }
                    }
                this.setGraph(newGraph);
            }
            System.out.println("Number of clusters : "+ clusterList.size());
            for (ArrayList<Integer> cluster: clusterList) {
                int clusterIndex = clusterList.indexOf(cluster)+1;
                System.out.println("Cluster "+ clusterIndex +" : "+cluster);
            }
        }
    }
}
