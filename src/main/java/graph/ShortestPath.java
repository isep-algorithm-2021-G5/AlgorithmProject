package graph;

import com.sun.istack.internal.NotNull;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : Xianqi LIU
 * @version : 1.0.0
 * @date : 2021/6/14
 */
@Getter
@AllArgsConstructor
public class ShortestPath implements Comparable<ShortestPath>
{

    private final int src;
    private final int dest;
    private final Deque<Integer> bfsSP;
    @Setter
    private double weight;

    public ShortestPath(int src, int dest, Deque<Integer> shortestPath)
    {
        this.src = src;
        this.dest = dest;
        this.bfsSP = shortestPath;
        this.weight = 0;
    }

    public List<Integer> getShortestPathList()
    {
        return new ArrayList<>(this.bfsSP);
    }

    @Override
    public int compareTo(@NotNull ShortestPath o)
    {
        return Double.compare(this.weight,o.weight);
    }
}
