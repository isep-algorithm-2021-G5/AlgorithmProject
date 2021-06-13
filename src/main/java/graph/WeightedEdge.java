package graph;

import com.google.common.base.Objects;
import lombok.Getter;

/**
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/6/13
 */
@Getter
public class WeightedEdge extends Edge
{

    private final double weight;

    public WeightedEdge(Integer from, Integer to, double weight)
    {
        super(from, to);
        this.weight = weight;
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
        WeightedEdge edge = (WeightedEdge) o;
        return Objects.equal(getFrom(), edge.getFrom())
                && Objects.equal(getTo(), edge.getTo());
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(getFrom(), getTo());
    }
}
