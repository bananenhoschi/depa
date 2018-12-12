package jdraw.figures;

import jdraw.figures.handles.AbstractFigureHandle;
import jdraw.framework.DrawView;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

public class Line extends AbstractFigure<Line2D.Double> {

    /**
     * Create a new rectangle of the given dimension.
     *
     * @param x the x-coordinate of the upper left corner of the rectangle
     * @param y the y-coordinate of the upper left corner of the rectangle
     * @param w the rectangle's width
     * @param h the rectangle's height
     */
    public Line(int x, int y, int w, int h) {
        shape = new Line2D.Double(x, y, w, h);
    }

    /**
     * Draw the rectangle to the given graphics context.
     *
     * @param g the graphics context to use for drawing.
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine((int) shape.getX1(), (int) shape.getY1(), (int) shape.getX2(), (int) shape.getY2());
    }

    @Override
    public void setBounds(Point origin, Point corner) {
        shape.setLine(origin, corner);
        notifyListeners(new FigureEvent(this));
    }

    @Override
    public void move(int dx, int dy) {
        if (dx != 0 || dy != 0) {
            shape.setLine(shape.getX1() + dx, shape.getY1() + dy, shape.getX2() + dx, shape.getY2() + dy);
            notifyListeners(new FigureEvent(this));
        }
    }

    @Override
    public boolean contains(int x, int y) {
        return shape.ptLineDistSq(x, y) < 25;
    }

    @Override
    public List<FigureHandle> getHandles() {
        List<FigureHandle> handles = new LinkedList<>();
        handles.add(new LineFirstHandle(this));
        return handles;
    }

    class LineFirstHandle extends AbstractFigureHandle {

        private Point2D linePoint;

        public LineFirstHandle(Line owner) {
            super(owner, Cursor.CROSSHAIR_CURSOR);
        }

        @Override
        public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
            Point p = getLocation();
               linePoint = new Point2D.Double(p.getX(), p.getY());
        }

        @Override
        public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
            shape.setLine(linePoint.getX() + x, linePoint.getY() + y, shape.getX2(), shape.getY2());
        }

    }


}