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
@AllArgsConstructor
@EqualsAndHashCode
public class Edge
{

    private final Integer from;

    private final Integer to;

    private final double weight;

}
