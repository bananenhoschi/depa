/*
 * Copyright (c) 2018 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */

package jdraw.std;

import jdraw.framework.*;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Provide a standard behavior for the drawing model. This class initially does not implement the methods
 * in a proper way.
 * It is part of the course assignments to do so.
 *
 * @author Carlo Baumann
 */
public class StdDrawModel implements DrawModel, FigureListener {


    private final List<Figure> figures = new CopyOnWriteArrayList<>();
    private final List<DrawModelListener> listeners = new CopyOnWriteArrayList<>();

    @Override
    public void addFigure(Figure f) {

        if (f != null && !figures.contains(f)) {
            figures.add(f);
            f.addFigureListener(this);
            notifyListeners(f, DrawModelEvent.Type.FIGURE_ADDED);
        }
    }

    @Override
    public Iterable<Figure> getFigures() {
        return Collections.unmodifiableList(figures);
    }

    @Override
    public void removeFigure(Figure f) {
        if (figures.remove(f)) {
            f.removeFigureListener(this);
            notifyListeners(f, DrawModelEvent.Type.FIGURE_REMOVED);
        }
    }

    @Override
    public void addModelChangeListener(DrawModelListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeModelChangeListener(DrawModelListener listener) {
        listeners.remove(listener);
    }

    /**
     * The draw command handler. Initialized here with a dummy implementation.
     */
    private DrawCommandHandler handler = new JDrawCommandHandler();

    /**
     * Retrieve the draw command handler in use.
     *
     * @return the draw command handler.
     */
    @Override
    public DrawCommandHandler getDrawCommandHandler() {
        return handler;
    }

    @Override
    public void setFigureIndex(Figure f, int index) {
        int pos = figures.indexOf(f);
        if (pos < 0) {
            throw new IllegalArgumentException("Figure f is not contained in the Model");
        }
        if (index > figures.size() - 1) {
            throw new IndexOutOfBoundsException("Index out of bound");
        }
        if (pos != index) {
            figures.remove(f);
            figures.add(index, f);
            notifyListeners(f, DrawModelEvent.Type.DRAWING_CHANGED);
        }
    }

    @Override
    public void removeAllFigures() {
        figures.forEach(f -> f.removeFigureListener(this));
        figures.clear();
        notifyListeners(null, DrawModelEvent.Type.DRAWING_CLEARED);
    }


    protected void notifyListeners(Figure f, DrawModelEvent.Type type) {
        DrawModelEvent dme = new DrawModelEvent(this, f, type);
        DrawModelListener[] copy = listeners.toArray(new DrawModelListener[listeners.size()]);
        for (DrawModelListener listener : copy) {
            listener.modelChanged(dme);
        }

    }

    @Override
    public void figureChanged(FigureEvent e) {
        notifyListeners(e.getFigure(), DrawModelEvent.Type.FIGURE_CHANGED);
    }
}
