package jdraw.figures.handles;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class AbstractFigureHandle implements FigureHandle {

    protected final Figure owner;
    protected Point corner;
    private final int cursor;

    public AbstractFigureHandle(Figure owner, int cursor) {
        this.owner = owner;
        this.cursor = cursor;
    }

    @Override
    public Figure getOwner() {
        return owner;
    }

    @Override
    public void draw(Graphics g) {
        Point loc = getLocation();
        g.setColor(Color.WHITE);
        g.fillRect(loc.x - 3, loc.y - 3, 6, 6);
        g.setColor(Color.RED);
        g.drawRect(loc.x - 3, loc.y - 3, 6, 6);
    }


    @Override
    public Point getLocation() {
        return getOwner().getBounds().getLocation();
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(cursor);
    }

    @Override
    public boolean contains(int x, int y) {
        return getLocation().distanceSq(x, y) < 25;
    }


    @Override
    public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
        corner = null;
    }


    public int getWidth() {
        return Double.valueOf(getOwner().getBounds().getWidth()).intValue();
    }

    public int getHeight() {
        return Double.valueOf(getOwner().getBounds().getHeight()).intValue();
    }
}
