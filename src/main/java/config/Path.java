package config;

/**
 * Store paths
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/6/14
 */
public final class Path
{

    public static final String PHOENIX_STOPS = "./data/stops.csv";
    public static final String PHOENIX_GRAPH = "./data/graph.csv";
    public static final String PHOENIX_WEIGHTED_GRAPH = "./data/graph.csv";
    public static final String DIJKSTRA_GRAPH = "./data/dijkstraGraphDataSet.csv";
    public static final String DIJKSTRA_NODES = "./data/dijkstraNodesDataSet.csv";
    public static final String BFS_GRAPH = "./data/bfsGraphDataSet.csv";
    public static final String BFS_NODES = "./data/bfsNodesDataSet.csv";
    public static final String CLUSTER_GRAPH = "./data/clusterGraphDataSet.csv";
    public static final String CLUSTER_NODES = "./data/clusterNodesDataSet.csv";
    public static final String LAZY_CHEN_GRAPH = "./data/lazyChenGraphDataSet.csv";
    public static final String LAZY_CHEN_NODES = "./data/lazyChenNodesDataSet.csv";
    private Path()
    {
        //Won't be called
    }
}
