package algorithm;

import graph.Graph;
import graph.ShortestPath;
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

    private final Integer start, end;
    @Getter
    private final Set<ShortestPath> shortestPaths = new TreeSet<>();
    private final Graph graph;

    public YenKsp(Graph graph, Integer start, Integer end, Integer k)
    {

        this.start = start;
        this.end = end;
        this.graph = graph;
        DijkstraShortestPath dij = new DijkstraShortestPath(graph, start);
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
        for (int i = 2; i < path.length ; i++)
        {
            ShortestPath s = (ShortestPath) sp.clone();
            s.split(i);
            DijkstraShortestPath dij = new DijkstraShortestPath(graph, path[i]);
            s.merge(dij.getShortestPath(end));
            paths.add(s);
        }
        for (ShortestPath res : paths)
        {
            if (!shortestPaths.contains(res))
            {
                return paths.remove();
            }
        }
        return null;
    }

}
