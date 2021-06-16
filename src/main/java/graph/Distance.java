package graph;

import lombok.Getter;
import lombok.NonNull;

/**
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/6/13
 */
@Getter
public class Distance extends Edge implements Comparable<Distance>
{

    public Distance(Integer from, Integer to, double weight)
    {
        super(from, to, weight);
    }

    @Override
    public int compareTo(@NonNull Distance o)
    {
        return Double.compare(this.getWeight(), o.getWeight());
    }
}
