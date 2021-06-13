package LIU.graph;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.*;

public class Graph {

    private ArrayList<Edge> edges;
    private List<String> nodes;
    private HashMap<String,List<String>> mapGraph;

    public Graph() {
        this.edges = new ArrayList<>();
        this.nodes = new ArrayList<String>();
        this.mapGraph = new HashMap<String, List<String>>();
    }

    public void BuildGraph() throws IOException {

        JsonParser parser = new JsonParser();

        JSONObject stopsJsonData = parser.getStops();
        Iterator<String> keys = stopsJsonData.keys();
        while (keys.hasNext()) {
            String keyStopId = keys.next();
            this.nodes.add(keyStopId);
            this.mapGraph.put(keyStopId,new ArrayList<String>());
        }

        //System.out.println(this.nodes.size());

        for(int i=0; i< this.nodes.size(); i++){
            String src = this.nodes.get(i);
            int srcId = Integer.parseInt(src);
            JSONObject srcObj = (JSONObject) stopsJsonData.get(src);
            JSONArray adjList = (JSONArray) srcObj.get("adjList");

            Double lat1 = Double.parseDouble(srcObj.get("lat").toString());
            Double lon1 = Double.parseDouble(srcObj.get("lon").toString());

            for(int j=0; j<adjList.length(); j++){
                String dest = adjList.get(j).toString();
                int destId = Integer.parseInt(dest);
                JSONObject destObj = (JSONObject) stopsJsonData.get(dest);

                Double lat2 = Double.parseDouble(destObj.get("lat").toString());
                Double lon2 = Double.parseDouble(destObj.get("lon").toString());

                double weight = EdgeWeightCalculate.compute(lat1,lon1,lat2,lon2);

                this.edges.add(new Edge(srcId,destId,weight));

                if(this.mapGraph.containsKey(src)){
                    this.mapGraph.get(src).add(dest);
                }
                //System.out.print(src);
                //System.out.println(mapGraph.get(src));
            }
        }
        //System.out.println(this.nodes.get(0));
    }

    public void printGraph(){
        for(int i=0; i<3; i++){
            System.out.print(this.nodes.get(i));
            System.out.println(this.mapGraph.get(this.nodes.get(i)));
        }
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public Edge getEdges(int src, int dest){
        Edge selected = null;
        for(Edge e : this.edges){
            if(e.from() == src && e.to() == dest){
                selected = e;
            }
        }
        return selected;
    }

    public void addDiEdge(Edge diEdge){
        this.edges.add(diEdge);
    }

    public TreeSet<Integer> getNeighbors(int v) {
        TreeSet<Integer> nodeNeighbors = new TreeSet<>();
        for(Edge edge : this.edges){
            if(edge.from() == v){
                nodeNeighbors.add(edge.to());
            }
        }
        return nodeNeighbors;
    }

    public TreeSet<Integer> getAllNodes() {
        TreeSet<Integer> nodeTree = new TreeSet<>();
        for (Edge edge : this.edges) {
            nodeTree.add(edge.from());
        }
        return nodeTree;
    }

    public double getEdgeWeight(int from, int to) {
        boolean isFound = false;
        int cursor = 0;
        double result = Double.POSITIVE_INFINITY;

        while (!isFound) {

            Edge edge = this.getEdges().get(cursor);
            if (edge.from() == from && edge.to() == to) {
                result = edge.weight();
                isFound = true;
            }
            cursor++;
        }
        return result;
    }

}
