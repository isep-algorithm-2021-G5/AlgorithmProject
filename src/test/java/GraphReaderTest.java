import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.csvreader.CsvReader;
import com.google.common.collect.Multimap;
import graph.Edge;
import graph.Node;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.GraphReader;

/**
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/6/13
 */
class GraphReaderTest
{

    public static final String STOPS = "./data/processed/stops.csv";
    public static final String GRAPH = "./data/processed/graph.csv";
    public static final String WEIGHTED_GRAPH = "./data/processed/graph.csv";
    Map<Integer, Node> nodes;
    Multimap<Integer, Edge> adjList;
    Multimap<Integer, Edge> weightedAdjList;

    @BeforeEach
    void setUp()
    {
        nodes = GraphReader.readNodes(STOPS);
        adjList = GraphReader.readAdjList(GRAPH);
        weightedAdjList = GraphReader.readWeightedAdjList(WEIGHTED_GRAPH);
    }

    @Test
    void testSize()
    {
        assertEquals(7772, nodes.size());
        assertEquals(8193, adjList.size());
        assertEquals(8193, weightedAdjList.size());
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
                assertEquals(Double.parseDouble(csvReader.get("lon")),
                             nodes.get(Integer.valueOf(csvReader.get("id"))).getLon());
                assertEquals(Double.parseDouble(csvReader.get("lat")),
                             nodes.get(Integer.valueOf(csvReader.get("id"))).getLat());
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
                                     Integer.valueOf(csvReader.get("to")), 1D);
                assertTrue(adjList.get(Integer.valueOf(csvReader.get("from"))).contains(edge));
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
                Edge edge = new Edge(Integer.valueOf(csvReader.get("from")),
                                     Integer.valueOf(csvReader.get("to")),
                                     Double.parseDouble(csvReader.get("weight")));
                assertTrue(
                        weightedAdjList.get(Integer.valueOf(csvReader.get("from"))).contains(edge));

            }
            csvReader.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}