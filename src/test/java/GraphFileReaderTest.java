import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.csvreader.CsvReader;
import com.google.common.collect.Multimap;
import graph.Edge;
import graph.Node;
import graph.WeightedEdge;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/6/13
 */
class GraphFileReaderTest
{

    public static final String STOPS = "./data/processed/stops.csv";
    public static final String GRAPH = "./data/processed/graph.csv";
    public static final String WEIGHTED_GRAPH = "./data/processed/graph.csv";
    GraphFileReader graphFileReader;
    Map<Integer, Node> nodes;
    Multimap<Integer, Edge> graph;
    Multimap<Integer, WeightedEdge> weightedGraph;

    @BeforeEach
    void setUp()
    {
        graphFileReader = new GraphFileReader();
        nodes = graphFileReader.readNodes(STOPS);
        graph = graphFileReader.readGraph(GRAPH);
        weightedGraph = graphFileReader.readWeightedGraph(WEIGHTED_GRAPH);
    }

    @Test
    void testSize()
    {
        assertEquals(nodes.size(), 7772);
        assertEquals(graph.size(), 8193);
        assertEquals(weightedGraph.size(), 8193);
    }

    @Test
    void testReadNodes()
    {
        try
        {
            CsvReader csvReader = new CsvReader(STOPS, ',', StandardCharsets.UTF_8);
            csvReader.readHeaders();
            while (csvReader.readRecord())
            {
                assertEquals(nodes.get(Integer.valueOf(csvReader.get("id"))).getLon(),
                             Double.parseDouble(csvReader.get("lon")));
                assertEquals(nodes.get(Integer.valueOf(csvReader.get("id"))).getLat(),
                             Double.parseDouble(csvReader.get("lat")));
            }
            csvReader.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    void testReadGraph()
    {
        try
        {
            CsvReader csvReader = new CsvReader(GRAPH, ',', StandardCharsets.UTF_8);
            csvReader.readHeaders();
            while (csvReader.readRecord())
            {
                Edge edge = new Edge(Integer.valueOf(csvReader.get("from")),
                                     Integer.valueOf(csvReader.get("to")));
                assertTrue(graph.get(Integer.valueOf(csvReader.get("from"))).contains(edge));
            }
            csvReader.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    void testReadWeightedGraph()
    {
        try
        {
            CsvReader csvReader = new CsvReader(WEIGHTED_GRAPH, ',', StandardCharsets.UTF_8);
            csvReader.readHeaders();
            while (csvReader.readRecord())
            {
                WeightedEdge edge = new WeightedEdge(Integer.valueOf(csvReader.get("from")),
                                                     Integer.valueOf(csvReader.get("to")),
                                                     Double.parseDouble(csvReader.get("weight")));
                assertTrue(
                        weightedGraph.get(Integer.valueOf(csvReader.get("from"))).contains(edge));

            }
            csvReader.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}