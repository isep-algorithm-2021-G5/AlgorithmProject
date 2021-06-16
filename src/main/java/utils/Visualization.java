package utils;

import com.google.common.collect.Multimap;
import graph.Edge;
import graph.Graph;
import graph.Node;
import graph.ShortestPath;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

    public static void show(Graph graph, Set<ShortestPath> sps)
    {
        Map<Integer, Node> nodes = graph.getNodes();
        Multimap<Integer, Edge> adjList = graph.getAdjList();
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
                for (Edge edge : adjList.values())
                {
                    from = nodes.get(edge.getFrom());
                    to = nodes.get(edge.getTo());
                    fromLat = (int) ((-from.getLat() + LAT_DEVIATION) * ZOOM_LEVEL);
                    fromLon = (int) ((from.getLon() + LON_DEVIATION) * ZOOM_LEVEL);
                    toLat = (int) ((-to.getLat() + LAT_DEVIATION) * ZOOM_LEVEL);
                    toLon = (int) ((to.getLon() + LON_DEVIATION) * ZOOM_LEVEL);
                    drawArrow(graphics, fromLon, fromLat, toLon, toLat, ARR_SIZE, Color.GRAY);
                }
                if (sps != null)
                {
                    drawShortestPath(graphics,sps);

                }


            }

            void drawShortestPath(Graphics g, Set<ShortestPath> sps)
            {
                int c = 1;
                Color color;
                for (ShortestPath sp : sps)
                {
                    Integer[] path = sp.getShortestPathList().toArray(new Integer[0]);
                    for (int i = 0; i < path.length - 1; i++)
                    {
                        Node f = nodes.get(path[i]);
                        Node t = nodes.get(path[i + 1]);

                        int fLat = (int) ((-f.getLat() + LAT_DEVIATION) * ZOOM_LEVEL);
                        int fLon = (int) ((f.getLon() + LON_DEVIATION) * ZOOM_LEVEL);
                        int tLat = (int) ((-t.getLat() + LAT_DEVIATION) * ZOOM_LEVEL);
                        int tLon = (int) ((t.getLon() + LON_DEVIATION) * ZOOM_LEVEL);
                        g.setColor(Color.CYAN);
                        if (i == 0)
                        {
                            g.setColor(Color.GREEN);
                            g.fillOval(fLon, fLat, 20, 20);
                        } else
                        {
//                            g.fillOval(fLon, fLat, 5, 5);
                        }
                        switch (c%3){
                            case 1:
                                color = Color.MAGENTA;
                                break;
                            case 2:
                                color = Color.ORANGE;
                                break;
                            case 0:
                                color = Color.CYAN;
                                break;
                            default:
                                color = Color.RED;
                        }
                        drawArrow(g, fLon, fLat, tLon, tLat, 5, color);
                        if (i == path.length - 2)
                        {
                            g.setColor(Color.RED);
                            g.fillOval(tLon, fLat, 20, 20);
                        }
                    }
                    c++;
                }
            }

            void drawArrow(Graphics g1, int x1, int y1, int x2, int y2, int arrasSize, Color color)
            {
                Graphics2D g = (Graphics2D) g1.create();
                double dx = x2 - x1, dy = y2 - y1;
                double angle = Math.atan2(dy, dx);
                int len = (int) Math.sqrt(dx * dx + dy * dy);
                AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
                at.concatenate(AffineTransform.getRotateInstance(angle));
                g.transform(at);
                g.setColor(color);
                g.drawLine(0, 0, len, 0);
                g.fillPolygon(new int[]{len, len - arrasSize, len - arrasSize, len},
                              new int[]{0, -arrasSize, arrasSize, 0}, 4);
            }
        };
        jFrame.add(jpanel);
        jFrame.setSize(X_SIZE, Y_SIZE);
        jFrame.setVisible(true);
    }
}
