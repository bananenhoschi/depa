package jdraw.figures;

import jdraw.figures.handles.LineHandleP1;
import jdraw.figures.handles.LineHandleP2;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

public class Line extends AbstractFigure {

    /**
     * Use the java.awt.geom.Line2D in order to save/reuse code.
     */
    private Line2D line;

    /**
     * Create a new line of the given dimension.
     *
     * @param p1 the Point corner of the line
     * @param p2 the Point corner of the line
     */
    public Line(Point p1, Point p2) {
        line = getLine(p1, p2);
    }

    private Line2D.Double getLine(Point2D p1, Point2D p2) {
        return new Line2D.Double(
                p1,
                p2
        );
    }

    /**
     * Draw the line to the given graphics context.
     *
     * @param g the graphics context to use for drawing.
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine((int) line.getX1(), (int) line.getY1(), (int) line.getX2(), (int) line.getY2());
    }

    @Override
    public void setBounds(Point origin, Point corner) {
        Line2D original = getLine(line.getP1(), line.getP2());
        line.setLine(origin, corner);
        if (!original.equals(line)) {
            line.setLine(origin, corner);
            notifyListeners(new FigureEvent(this));
        }
    }

    @Override
    public void move(int dx, int dy) {
        if (dx != 0 || dy != 0) {
            line.setLine(line.getX1() + dx, line.getY1() + dy, line.getX2() + dx, line.getY2() + dy);
            notifyListeners(new FigureEvent(this));
        }
    }

    @Override
    public boolean contains(int x, int y) {
        return line.ptLineDist(x, y) < 25;
    }

    @Override
    public Rectangle getBounds() {
        return line.getBounds();
    }

    /**
     * Returns a list of 2 handles for this Line.
     *
     * @return all handles that are attached to the targeted figure.
     * @see jdraw.framework.Figure#getHandles()
     */
    @Override
    public List<FigureHandle> getHandles() {
        List<FigureHandle> handles = new LinkedList<>();
        handles.add(new LineHandleP1(this, line));
        handles.add(new LineHandleP2(this, line));
        return handles;
    }

    @Override
    public Line clone() {
        Line copy = (Line) super.clone();
        copy.line = (Line2D) copy.line.clone();
        return copy;
    }
}