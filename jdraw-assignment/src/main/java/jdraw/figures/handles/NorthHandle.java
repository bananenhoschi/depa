package jdraw.figures.handles;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.awt.*;
import java.awt.event.MouseEvent;

public class NorthHandle extends AbstractFigureHandle {


    public NorthHandle(Figure owner) {
        super(owner, Cursor.N_RESIZE_CURSOR);
    }

    @Override
    public Point getLocation() {
        Point tmp = owner.getBounds().getLocation();
        tmp.x += owner.getBounds().getWidth() / 2;
        return tmp;
    }

    @Override
    public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle r = owner.getBounds();
        corner = new Point(r.x + r.width, r.y + r.height);
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        Rectangle r = owner.getBounds();
        owner.setBounds(new Point(corner.x - r.width, y), corner);
    }
}
