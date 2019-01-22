package jdraw.figures.handles;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;


public class NorthWestHandle extends AbstractFigureHandle {

    public NorthWestHandle(Figure owner) {
        super(owner);
    }

    @Override
    public Point getLocation() {
        Point tmp = owner.getBounds().getLocation();
        return tmp;
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR);
    }

    @Override
    public Point getFixedCorner() {
        Rectangle r = owner.getBounds();
        return new Point(r.x + r.width, r.y + r.height);
    }

    @Override
    public Point getVariableCorner(int x, int y) {
        return new Point(x, y);
    }

}
