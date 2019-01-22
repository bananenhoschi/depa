package jdraw.grids;

import jdraw.framework.DrawGrid;

import java.awt.*;

public abstract class AbstractGrid implements DrawGrid {

    private final int gridValue;

    public AbstractGrid(int gridValue) {
        this.gridValue = gridValue;
    }

    @Override
    public Point constrainPoint(Point p) {
        double x = ((p.x + gridValue / 2) / gridValue) * gridValue;
        double y = ((p.y + gridValue / 2) / gridValue) * gridValue;
        p = new Point(Double.valueOf(x).intValue(), Double.valueOf(y).intValue());
        return p;
    }

    @Override
    public int getStepX(boolean right) {
        return gridValue;
    }

    @Override
    public int getStepY(boolean down) {
        return gridValue;
    }

    @Override
    public void activate() {
    }

    @Override
    public void deactivate() {
    }

    @Override
    public void mouseDown() {
    }

    @Override
    public void mouseUp() {
    }

}
