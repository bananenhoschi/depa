package jdraw.figures.handles;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;

public class SouthHandle extends AbstractFigureHandle {

    public SouthHandle(Figure owner) {
        super(owner);
    }

    @Override
    public Point getLocation() {
        Point tmp = owner.getBounds().getLocation();
        tmp.x += owner.getBounds().getWidth() / 2;
        tmp.y += owner.getBounds().getHeight();
        return tmp;
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR);
    }

    @Override
    public Point getFixedCorner() {
        Rectangle r = owner.getBounds();
        return new Point(r.x, r.y);
    }

    @Override
    public Point getVariableCorner(int x, int y) {
        Rectangle r = owner.getBounds();
        return new Point(r.x + r.width, y);
    }
}