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
            public void paint(Graphics g)
            {
                super.paint(g);

                for (Node node : nodes.values())
                {
                    drawNode(g, node, Color.BLUE, 3);
                }
                for (Edge edge : adjList.values())
                {
                    drawArrow(g, nodes.get(edge.getFrom()), nodes.get(edge.getTo()),
                              Color.GRAY);
                }
                if (sps != null)
                {
                    drawShortestPath(g, sps, nodes);
                }
            }

        };
        jFrame.add(jpanel);
        jFrame.setSize(X_SIZE, Y_SIZE);
        jFrame.setVisible(true);
    }

    private static void drawShortestPath(Graphics g, Set<ShortestPath> sps,
                                         Map<Integer, Node> nodes)
    {
        int c = 1;
        Color color;
        for (ShortestPath sp : sps)
        {
            Integer[] path = sp.getShortestPathList().toArray(new Integer[0]);
            for (int i = 0; i < path.length - 1; i++)
            {
                Node from = nodes.get(path[i]);
                Node to = nodes.get(path[i + 1]);

                if (i == 0)
                {
                    drawNode(g, from, Color.GREEN, 20);
                }
                switch (c % 3)
                {
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
                drawArrow(g, from, to, color);
                if (i == path.length - 2)
                {
                    drawNode(g, to, Color.RED, 20);
                }
            }
            c++;
        }
    }

    private static void drawNode(Graphics g, Node node, Color color, int diameter)
    {
        int lat = normalizeLat(node);
        int lon = normalizeLon(node);
        g.setColor(color);
        g.fillOval(lon, lat, diameter, diameter);
    }

    static void drawArrow(Graphics g, Node from, Node to, Color color)
    {
        int fromLat = normalizeLat(from);
        int fromLon = normalizeLon(from);
        int toLat = normalizeLat(to);
        int toLon = normalizeLon(to);
        drawArrow(g, fromLon, fromLat, toLon, toLat, color);

    }

    private static void drawArrow(Graphics g, int x1, int y1, int x2, int y2,
                                  Color color)
    {

        Graphics2D g2D = (Graphics2D) g.create();
        double dx = x2 - x1, dy = y2 - y1;
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx * dx + dy * dy);
        AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        g2D.transform(at);
        g2D.setColor(color);
        g2D.drawLine(0, 0, len, 0);
        g2D.fillPolygon(new int[]{len, len - ARR_SIZE, len - ARR_SIZE, len},
                        new int[]{0, -ARR_SIZE, ARR_SIZE, 0}, 4);
    }


    private static int normalizeLat(Node node)
    {
        return (int) ((-node.getLat() + LAT_DEVIATION) * ZOOM_LEVEL);
    }

    private static int normalizeLon(Node node)
    {
        return (int) ((node.getLon() + LON_DEVIATION) * ZOOM_LEVEL);
    }
}
