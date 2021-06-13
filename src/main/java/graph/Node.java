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
public class Node
{

    private final double lat;

    private final double lon;

    public Node(String lat, String lon)
    {
        this.lat = Double.parseDouble(lat);
        this.lon = Double.parseDouble(lon);
    }

}
