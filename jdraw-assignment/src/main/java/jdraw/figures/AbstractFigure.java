package jdraw.figures;

import jdraw.figures.handles.*;
import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

abstract public class AbstractFigure implements Figure {

    private List<FigureListener> listeners = new ArrayList<>();

    @Override
    public void addFigureListener(FigureListener listener) {
        if (listener != null && !listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    @Override
    public AbstractFigure clone() {
        try {
            AbstractFigure copy = (AbstractFigure) super.clone();
            copy.listeners = new LinkedList<>();
            return copy;
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }

    @Override
    public void removeFigureListener(FigureListener listener) {
        listeners.remove(listener);
    }

    protected void notifyListeners(FigureEvent evt) {
        FigureListener[] copy = listeners.toArray(new FigureListener[listeners.size()]);
        for (FigureListener listener : copy) {
            listener.figureChanged(evt);
        }
    }

    @Override
    public List<FigureHandle> getHandles() {
        List<FigureHandle> handles = new LinkedList<>();
        handles.add(new NorthHandle(this));
        handles.add(new NorthEastHandle(this));
        handles.add(new EastHandle(this));
        handles.add(new SouthEastHandle(this));
        handles.add(new SouthHandle(this));
        handles.add(new SouthWestHandle(this));
        handles.add(new WestHandle(this));
        handles.add(new NorthWestHandle(this));
        return handles;
    }
}
