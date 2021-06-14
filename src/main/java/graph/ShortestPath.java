package graph;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import lombok.Getter;

/**
 * @author : Xianqi LIU
 * @version : 1.0.0
 * @date : 2021/6/14
 */
@Getter
public class ShortestPath
{

    private final int src;
    private final int dest;
    private final Deque<Integer> bfsSP;

    public ShortestPath(int src, int dest, Deque<Integer> bfsShortestPath)
    {
        this.src = src;
        this.dest = dest;
        this.bfsSP = bfsShortestPath;
    }

    public List<Integer> getBfsShortestPathList()
    {
        return new ArrayList<>(this.bfsSP);
    }

}
