package jdraw.figures.handles;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;

public class WestHandle extends AbstractFigureHandle {

    public WestHandle(Figure owner) {
        super(owner, Cursor.W_RESIZE_CURSOR);
    }

    @Override
    public Point getLocation() {
        Point p = owner.getBounds().getLocation();
        p.y += owner.getBounds().getHeight() / 2;
        return p;
    }


    @Override
    public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle r = owner.getBounds();
        corner = new Point(r.x + r.width, r.y);
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle r = owner.getBounds();
        owner.setBounds(new Point(x, corner.y), new Point(corner.x, corner.y + r.height));
    }
}