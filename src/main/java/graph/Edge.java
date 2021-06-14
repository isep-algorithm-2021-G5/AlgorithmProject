package graph;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/6/13
 */
@Getter
@EqualsAndHashCode
public class Edge
{

    private final Integer from;

    private final Integer to;

    private final double weight;

    private final Integer capacity;

    public Edge(Integer from, Integer to)
    {
        this.from = from;
        this.to = to;
        this.weight = 1;
        this.capacity = null;
    }

    public Edge(Integer from, Integer to, double weight)
    {
        this.from = from;
        this.to = to;
        this.weight = weight;
        this.capacity = null;
    }

    public Edge(Integer from, Integer to, double weight, Integer capacity)
    {
        this.from = from;
        this.to = to;
        this.weight = weight;
        this.capacity = capacity;
    }
}
