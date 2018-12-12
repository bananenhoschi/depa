package jdraw.figures;

import jdraw.figures.handles.EastHandle;
import jdraw.figures.handles.NorthHandle;
import jdraw.figures.handles.SouthHandle;
import jdraw.figures.handles.WestHandle;
import jdraw.framework.FigureHandle;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.LinkedList;
import java.util.List;

public class Oval extends AbstractRectangle<Ellipse2D.Double> {


    /**
     * Create a new rectangle of the given dimension.
     *
     * @param x the x-coordinate of the upper left corner of the rectangle
     * @param y the y-coordinate of the upper left corner of the rectangle
     * @param w the rectangle's width
     * @param h the rectangle's height
     */
    public Oval(int x, int y, int w, int h) {
        shape = new Ellipse2D.Double(x, y, w, h);
    }

    /**
     * Draw the rectangle to the given graphics context.
     *
     * @param g the graphics context to use for drawing.
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, 0));
        g.fillOval((int) shape.x, (int) shape.y, (int) shape.width, (int) shape.height);
        g.setColor(Color.BLACK);
        g.drawOval((int) shape.x, (int) shape.y, (int) shape.width, (int) shape.height);
    }

    @Override
    public List<FigureHandle> getHandles() {
        List<FigureHandle> handles = new LinkedList<>();
        handles.add(new NorthHandle(this));
        handles.add(new WestHandle(this));
        handles.add(new EastHandle(this));
        handles.add(new SouthHandle(this));
        return handles;
    }

}