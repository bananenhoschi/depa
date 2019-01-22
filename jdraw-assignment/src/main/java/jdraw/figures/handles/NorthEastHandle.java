package jdraw.figures.handles;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;

public class NorthEastHandle extends AbstractFigureHandle {

    public NorthEastHandle(Figure owner) {
        super(owner);
    }

    @Override
    public Point getLocation() {
        Point tmp = owner.getBounds().getLocation();
        tmp.x += owner.getBounds().getWidth();
        return tmp;
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR);
    }

    @Override
    public Point getFixedCorner() {
        Rectangle r = owner.getBounds();
        return new Point(r.x, r.y + r.height);
    }

    @Override
    public Point getVariableCorner(int x, int y) {
        return new Point(x, y);
    }
}