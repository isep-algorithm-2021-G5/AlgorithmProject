package graph;

import com.google.common.base.Objects;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/6/13
 */
@Getter
public class Edge
{

    private final Integer from;

    private final Integer to;

    @Setter
    private double weight;

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
        Edge edge = (Edge) o;
        return this.from.equals(edge.from)
                && this.to.equals(edge.to);
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(getFrom(), getTo());
    }
}
