package graph;

import com.sun.istack.internal.NotNull;
import java.util.ArrayDeque;
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
@Setter
@AllArgsConstructor
public class ShortestPath implements Comparable<ShortestPath>, Cloneable
{

    private int src;
    private int dest;
    private Deque<Integer> shortestPath;
    private double weight;
    private double[] distance;

    public ShortestPath(int src, int dest, Deque<Integer> shortestPath)
    {
        this.src = src;
        this.dest = dest;
        this.shortestPath = shortestPath;
        this.weight = 0;
    }

    public ShortestPath(int src, int dest, Deque<Integer> shortestPath, double weight)
    {
        this.src = src;
        this.dest = dest;
        this.shortestPath = shortestPath;
        this.weight = weight;
    }

    public List<Integer> getShortestPathList()
    {
        return new ArrayList<>(this.shortestPath);
    }

    public void split(Integer index)
    {
        Integer[] pathV = this.shortestPath.toArray(new Integer[0]);
        this.dest = pathV[index - 1];
        Deque<Integer> path = new ArrayDeque<>();
        for (int i = 0; i < index; i++)
        {
            path.add(shortestPath.pop());
        }
        this.shortestPath = path;
        this.weight = distance[index - 1];
    }

    public void merge(ShortestPath sp)
    {
        assert sp.src == this.dest;
        this.shortestPath.removeLast();
        this.shortestPath.addAll(sp.getShortestPathList());
        this.weight += sp.getWeight();
        this.dest = sp.dest;
    }

    @Override
    public int compareTo(@NotNull ShortestPath o)
    {
        if (Double.compare(this.weight, o.weight) != 0)
        {
            return Double.compare(this.weight, o.weight);
        } else
        {
            return -Integer.compare(this.shortestPath.size(), o.shortestPath.size());
        }

    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }

        ShortestPath path1 = (ShortestPath) o;
        return this.getShortestPathList() != null ?
               this.getShortestPathList().toString()
                       .equals(path1.getShortestPathList().toString()) :
               path1.shortestPath == null;
    }

    @Override
    public int hashCode()
    {
        return this.getShortestPathList().toString().hashCode();
    }

    @Override
    public Object clone()
    {
        try
        {
            Object clone = super.clone();
        } catch (CloneNotSupportedException e)
        {
            e.printStackTrace();
        }
        Deque<Integer> d = new ArrayDeque<Integer>(this.shortestPath);
        return new ShortestPath(this.src, this.dest, d, this.weight, this.distance);
    }
}
