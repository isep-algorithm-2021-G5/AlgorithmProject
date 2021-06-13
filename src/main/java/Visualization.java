import com.google.common.collect.Multimap;
import graph.Edge;
import graph.Node;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/6/14
 */
public class Visualization
{

    private static final int ARR_SIZE = 3;
    private static final int X_SIZE = 1200;
    private static final int Y_SIZE = 1200;
    private static final int ZOOM_LEVEL = 1600;
    private static final double LAT_DEVIATION = 33.8;
    private static final double LON_DEVIATION = 112.45;

    public static void show(Map<Integer, Node> nodes, Multimap<Integer, Edge> graph)
    {
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel jpanel = new JPanel()
        {
            @Override
            public void paint(Graphics graphics)
            {
                super.paint(graphics);

                for (Node node : nodes.values())
                {

                    int lat = (int) ((-node.getLat() + LAT_DEVIATION) * ZOOM_LEVEL);
                    int lon = (int) ((node.getLon() + LON_DEVIATION) * ZOOM_LEVEL);

                    graphics.setColor(Color.BLUE);
                    graphics.fillOval(lon, lat, 3, 3);

                }
                Node from, to;
                int fromLat, fromLon, toLat, toLon;
                for (Edge edge : graph.values())
                {
                    from = nodes.get(edge.getFrom());
                    to = nodes.get(edge.getTo());
                    fromLat = (int) ((-from.getLat() + LAT_DEVIATION) * ZOOM_LEVEL);
                    fromLon = (int) ((from.getLon() + LON_DEVIATION) * ZOOM_LEVEL);
                    toLat = (int) ((-to.getLat() + LAT_DEVIATION) * ZOOM_LEVEL);
                    toLon = (int) ((to.getLon() + LON_DEVIATION) * ZOOM_LEVEL);
                    drawArrow(graphics, fromLon, fromLat, toLon, toLat);
                }


            }

            void drawArrow(Graphics g1, int x1, int y1, int x2, int y2)
            {
                Graphics2D g = (Graphics2D) g1.create();
                double dx = x2 - x1, dy = y2 - y1;
                double angle = Math.atan2(dy, dx);
                int len = (int) Math.sqrt(dx * dx + dy * dy);
                AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
                at.concatenate(AffineTransform.getRotateInstance(angle));
                g.transform(at);
                g.setColor(Color.DARK_GRAY);
                g.drawLine(0, 0, len, 0);
                g.fillPolygon(new int[]{len, len - ARR_SIZE, len - ARR_SIZE, len},
                              new int[]{0, -ARR_SIZE, ARR_SIZE, 0}, 4);
            }
        };
        jFrame.add(jpanel);
        jFrame.setSize(X_SIZE, Y_SIZE);
        jFrame.setVisible(true);
    }
}
