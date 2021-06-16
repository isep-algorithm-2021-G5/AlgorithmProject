package algorithm;

import graph.Edge;
import graph.Graph;
import graph.ShortestPath;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;
import lombok.Getter;

/**
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/6/15
 */
public class YenKsp
{

    @Getter
    private final Integer start;
    @Getter
    private final Integer end;
    @Getter
    private final Set<ShortestPath> shortestPaths = new TreeSet<>();
    private final Graph graph;


    public YenKsp(Graph graph, Integer start, Integer end, Integer k)
    {

        this.start = start;
        this.end = end;
        this.graph = graph;
        DijkstraShortestPath dij = new DijkstraShortestPath(graph, start, null, null);
        ShortestPath sp = dij.getShortestPath(end);
        shortestPaths.add(sp);

        while (shortestPaths.size() < k)
        {

            ShortestPath second = getSecondShort(sp);
            if (second == null)
            {
                break;
            }
            shortestPaths.add(second);
            sp = second;
        }


    }

    private ShortestPath getSecondShort(ShortestPath sp)
    {

        PriorityQueue<ShortestPath> paths = new PriorityQueue<>();
        Integer[] path = sp.getShortestPathList().toArray(new Integer[0]);
        for (int i = 1; i < path.length; i++)
        {
            Integer now = path[i - 1];
            Integer[] tmp;
            Set<Edge> banEdges = new HashSet<>();
            for (ShortestPath s : shortestPaths)
            {
                tmp = s.getShortestPathList().toArray(new Integer[0]);
                for (int j = 0; j < tmp.length - 1; j++)
                {
                    if (tmp[j].equals(now))
                    {
                        banEdges.add(new Edge(tmp[j], tmp[j + 1], Integer.MAX_VALUE));
                    }
                }
            }
            Set<Integer> banNodes = new HashSet<>();
            ShortestPath s = sp.copy();
            s.split(i);
            banNodes.addAll(s.getShortestPathList());
            DijkstraShortestPath dij = new DijkstraShortestPath(graph, now, banNodes,
                                                                banEdges);
            s.merge(dij.getShortestPath(end));
            paths.add(s);
        }
        for (ShortestPath res : paths)
        {
            if (res.getWeight() < Integer.MAX_VALUE)
            {
                return paths.remove();
            }
        }
        return null;
    }

}
