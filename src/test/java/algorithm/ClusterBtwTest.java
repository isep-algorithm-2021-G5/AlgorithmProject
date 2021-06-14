package algorithm;

import config.Path;
import graph.Graph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author : Xianqi LIU
 * @version : 1.0.0
 * @date : 2021/6/14
 */

class ClusterBtwTest {

    EdgeBtw edgeBtw;
    Cluster cluster;
    Graph graph = new Graph(Path.CLUSTER_NODES, Path.CLUSTER_GRAPH, false);

    @BeforeEach
    void setUp()
    {
        edgeBtw = new EdgeBtw(graph);
        cluster = new Cluster(graph);
        cluster.getClusters(2);
    }

    @Test
    void getMaxBtwEdge(){
        assertEquals("4",edgeBtw.getMaxBtwEdge().getFrom().toString());
        assertEquals("5",edgeBtw.getMaxBtwEdge().getTo().toString());
    }

    @Test
    void getClusters(){
        assertEquals(2, cluster.getClusterCount());
        assertEquals("[[1, 2, 3, 4], [5, 6, 7, 8]]",
                cluster.getResultSet().toString());
    }

}
