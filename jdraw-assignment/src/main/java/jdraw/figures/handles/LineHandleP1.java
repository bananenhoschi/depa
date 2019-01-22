package jdraw.figures.handles;


import jdraw.framework.Figure;

import java.awt.*;
import java.awt.geom.Line2D;

public class LineHandleP1 extends AbstractFigureHandle {

    private Line2D line;

    public LineHandleP1(Figure owner, Line2D line) {
        super(owner);
        this.line = line;
    }

    @Override
    public Point getLocation() {
        Point tmp = new Point((int) line.getX1(), (int) line.getY1());
        return tmp;
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR);
    }

    @Override
    public Point getFixedCorner() {
        anchorPoint = new Point((int) line.getX2(), (int) line.getY2());
        return anchorPoint;
    }

    @Override
    public Point getVariableCorner(int x, int y) {
        return new Point(x, y);
    }
}