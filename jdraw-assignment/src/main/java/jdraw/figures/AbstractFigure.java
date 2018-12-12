package jdraw.figures;

import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

import java.awt.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

abstract public class AbstractFigure<T extends Shape> implements Figure {

    /**
     * Use the java.awt.Rectangle in order to save/reuse code.
     */
    protected T shape;


    private List<FigureListener> listeners = new CopyOnWriteArrayList<>();

    @Override
    public void addFigureListener(FigureListener listener) {
        if (listener != null)
            listeners.add(listener);
    }

    @Override
    public void removeFigureListener(FigureListener listener) {
        listeners.remove(listener);
    }

    @Override
    public Figure clone() {
        return null;
    }

    protected void notifyListeners(FigureEvent evt) {
        if (evt != null) {
            FigureListener[] copy = listeners.toArray(new FigureListener[listeners.size()]);
            for (FigureListener listener : copy) {
                listener.figureChanged(evt);
            }
        }

    }

    @Override
    public boolean contains(int x, int y) {
        return shape.contains(x, y);
    }

    @Override
    public Rectangle getBounds() {
        return shape.getBounds();
    }


    /**
     * Returns a list of 8 handles for this Rectangle.
     *
     * @return all handles that are attached to the targeted figure.
     * @see jdraw.framework.Figure#getHandles()
     */
    @Override
    public List<FigureHandle> getHandles() {
        return null;
    }


}
