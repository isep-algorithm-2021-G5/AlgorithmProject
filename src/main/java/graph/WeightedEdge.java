package graph;

import com.google.common.base.Objects;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/6/13
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class WeightedEdge extends Edge
{

    private final double weight;

    public WeightedEdge(Integer from, Integer to, double weight)
    {
        super(from, to);
        this.weight = weight;
    }
}
