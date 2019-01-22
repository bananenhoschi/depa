package jdraw.figures.handles;

import jdraw.framework.Figure;

import java.awt.*;

public class NorthHandle extends AbstractFigureHandle {

    public NorthHandle(Figure owner) {
        super(owner);
    }

    @Override
    public Point getLocation() {
        Point tmp = owner.getBounds().getLocation();
        tmp.x += owner.getBounds().getWidth() / 2;
        return tmp;
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR);
    }

    @Override
    public Point getFixedCorner() {
        Rectangle r = owner.getBounds();
        return new Point(r.x + r.width, r.y + r.height);
    }

    @Override
    public Point getVariableCorner(int x, int y) {
        Rectangle r = owner.getBounds();
        return new Point(r.x, y);
    }
}
