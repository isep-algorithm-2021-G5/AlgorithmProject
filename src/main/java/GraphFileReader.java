import com.csvreader.CsvReader;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import graph.Edge;
import graph.Node;
import graph.WeightedEdge;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/6/13
 */
public class GraphFileReader
{

    public Map<Integer, Node> readNodes(String path)
    {
        Map<Integer, Node> nodes = new HashMap<>(7772);
        try
        {
            CsvReader csvReader = new CsvReader(path, ',', StandardCharsets.UTF_8);
            csvReader.readHeaders();
            while (csvReader.readRecord())
            {
                nodes.put(Integer.valueOf(csvReader.get("id")),
                          new Node(csvReader.get("lat"), csvReader.get("lon")));
            }
            csvReader.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return nodes;
    }

    public Multimap<Integer, Edge> readGraph(String path)
    {
        Multimap<Integer, Edge> graph = ArrayListMultimap.create();
        try
        {
            CsvReader csvReader = new CsvReader(path, ',', StandardCharsets.UTF_8);
            csvReader.readHeaders();
            while (csvReader.readRecord())
            {
                graph.put(Integer.valueOf(csvReader.get("from")),
                          new Edge(Integer.valueOf(csvReader.get("from")),
                                   Integer.valueOf(csvReader.get("to"))));
            }
            csvReader.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return graph;
    }

    public Multimap<Integer, WeightedEdge> readWeightedGraph(String path)
    {
        Multimap<Integer, WeightedEdge> weightedGraph = ArrayListMultimap.create();
        try
        {
            CsvReader csvReader = new CsvReader(path, ',', StandardCharsets.UTF_8);
            csvReader.readHeaders();
            while (csvReader.readRecord())
            {
                weightedGraph.put(Integer.valueOf(csvReader.get("from")),
                                  new WeightedEdge(Integer.valueOf(csvReader.get("from")),
                                                   Integer.valueOf(csvReader.get("to")),
                                                   Double.parseDouble(csvReader.get("weight"))));
            }
            csvReader.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return weightedGraph;
    }


}
