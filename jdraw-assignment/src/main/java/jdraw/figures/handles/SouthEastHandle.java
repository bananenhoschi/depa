package jdraw.figures.handles;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;

public class SouthEastHandle extends AbstractFigureHandle {

    public SouthEastHandle(Figure owner) {
        super(owner, Cursor.SE_RESIZE_CURSOR);
    }


    @Override
    public Point getLocation() {
        Point p = owner.getBounds().getLocation();
        p.x += owner.getBounds().getWidth();
        p.y += owner.getBounds().getHeight();
        return p;
    }

    @Override
    public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle r = owner.getBounds();
        corner = new Point(r.x, r.y);
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        owner.setBounds(corner, new Point(x, y));
    }
}