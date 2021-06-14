package graph;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

@Getter
public class ShortestPath {
    private final int src;
    private final int dest;
    private final Deque<Integer> bfsSP;

    public ShortestPath(int src, int dest, Deque<Integer> bfsSP) {
        this.src = src;
        this.dest = dest;
        this.bfsSP = bfsSP;
    }

    public List<Integer> getBfsSPList(){
        return new ArrayList<>(this.bfsSP);
    }

}
