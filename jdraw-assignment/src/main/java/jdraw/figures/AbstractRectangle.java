package jdraw.figures;

import jdraw.framework.FigureEvent;

import java.awt.*;
import java.awt.geom.RectangularShape;

public abstract class AbstractRectangle<T extends RectangularShape> extends AbstractFigure<T> {


    @Override
    public void move(int dx, int dy) {
        if (dx != 0 || dy != 0) {
            shape.setFrame(shape.getX() + dx, shape.getY() + dy, shape.getWidth(), shape.getHeight());
            notifyListeners(new FigureEvent(this));
        }
    }

    @Override
    public void setBounds(Point origin, Point corner) {
        shape.setFrameFromDiagonal(origin, corner);
        notifyListeners(new FigureEvent(this));
    }


}
