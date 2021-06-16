package graph;

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
public class ShortestPath implements Comparable<ShortestPath>
{

    private int src;
    private int dest;
    /**
     * Shortest path
     */
    private Deque<Integer> sp;
    private double weight;
    private double[] distance;

    public ShortestPath(int src, int dest, Deque<Integer> sp)
    {
        this.src = src;
        this.dest = dest;
        this.sp = sp;
        this.weight = 0;
    }

    public ShortestPath(int src, int dest, Deque<Integer> sp, double weight)
    {
        this.src = src;
        this.dest = dest;
        this.sp = sp;
        this.weight = weight;
    }

    public List<Integer> getShortestPathList()
    {
        return new ArrayList<>(this.sp);
    }

    /**
     * Cut the shortest path from the index and keep only the first half
     * @param index index
     */
    public void split(Integer index)
    {
        Integer[] pathV = this.sp.toArray(new Integer[0]);
        this.dest = pathV[index - 1];
        Deque<Integer> path = new ArrayDeque<>();
        for (int i = 0; i < index; i++)
        {
            path.add(sp.pop());
        }
        this.sp = path;
        this.weight = distance[dest];
    }

    /**
     * Connects a shortest path to the back of this one
     * @param sp shortest path
     */
    public void merge(ShortestPath sp)
    {
        this.sp.removeLast();
        this.sp.addAll(sp.getShortestPathList());
        this.weight += sp.getWeight();
        this.dest = sp.dest;
    }

    @Override
    public int compareTo(ShortestPath o)
    {
        if (Double.compare(this.weight, o.weight) != 0)
        {
            return Double.compare(this.weight, o.weight);
        } else
        {
            return Integer.compare(o.sp.size(), this.sp.size());
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
               path1.sp == null;
    }

    @Override
    public int hashCode()
    {
        return this.getShortestPathList().toString().hashCode();
    }

    public ShortestPath copy()
    {
        Deque<Integer> d = new ArrayDeque<>(this.sp);
        return new ShortestPath(this.src, this.dest, d, this.weight, this.distance);
    }
}
