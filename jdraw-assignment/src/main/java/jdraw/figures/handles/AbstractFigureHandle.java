package jdraw.figures.handles;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class AbstractFigureHandle implements FigureHandle {


    protected Figure owner;
    protected Point anchorPoint, oldCorner;

    public AbstractFigureHandle(Figure owner) {
        this.owner = owner;
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
        g.setColor(Color.BLACK);
        g.drawRect(loc.x - 3, loc.y - 3, 6, 6);
    }

    @Override
    public boolean contains(int x, int y) {
        Point loc = getLocation();
        Rectangle handleBounds = new Rectangle(loc.x - 3, loc.y - 3, 6, 6);
        return handleBounds.contains(x, y);
    }

    @Override
    public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
        anchorPoint = getFixedCorner();
        oldCorner = new Point(x, y);
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        owner.setBounds(getVariableCorner(x, y), anchorPoint);
        Point newCorner = new Point(x, y);
        owner.setBounds(anchorPoint, newCorner);
        //v.getModel().getDrawCommandHandler().addCommand(new SetBoundsCommand(owner, anchorPoint, oldCorner, anchorPoint, newCorner));
    }

    @Override
    public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
        anchorPoint = null;
    }

    abstract public Point getFixedCorner();

    abstract public Point getVariableCorner(int x, int y);
}