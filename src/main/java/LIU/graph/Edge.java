package LIU.graph;

public class Edge {
    private final int src;
    private final int dest;
    private final double weight;


    public Edge(int v, int w, double weight) {
        this.src = v;
        this.dest = w;
        this.weight = weight;
    }

    public int from() {
        return src;
    }
    public int to() {
        return dest;
    }
    public double weight() {
        return weight;
    }
}
