package jdraw.figures;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractDecorator implements Figure {


    private Figure figure;

    private List<FigureHandle> handles;

    public AbstractDecorator(Figure figure) {
        this.figure = figure;
    }

    public Figure getInner() {
        return figure;
    }

    @Override
    public void addFigureListener(FigureListener listener) {
        figure.addFigureListener(listener);
    }

    @Override
    public AbstractDecorator clone() {
        try {
            AbstractDecorator f = (AbstractDecorator) super.clone();
            f.figure = (Figure) figure.clone();
            f.handles = null;
            return f;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    @Override
    public void removeFigureListener(FigureListener listener) {
        figure.removeFigureListener(listener);
    }

    @Override
    public void draw(Graphics g) {
        figure.draw(g);
    }

    @Override
    public void move(int dx, int dy) {
        figure.move(dx, dy);
    }

    @Override
    public boolean contains(int x, int y) {
        return figure.contains(x, y);
    }

    @Override
    public void setBounds(Point origin, Point corner) {
        figure.setBounds(origin, corner);
    }

    @Override
    public Rectangle getBounds() {
        return figure.getBounds();
    }

    @Override
    public List<FigureHandle> getHandles() {
        List<FigureHandle> handles = new LinkedList<>();
        for (FigureHandle h : figure.getHandles()) {
            handles.add(new HandleDecorator(h));
        }
        return Collections.unmodifiableList(handles);
    }

    class HandleDecorator implements FigureHandle {

        private final FigureHandle inner;

        public HandleDecorator(FigureHandle handle) {
            this.inner = handle;
        }

        @Override
        public Figure getOwner() {
            return AbstractDecorator.this;
        }

        @Override
        public boolean contains(int x, int y) {
            return inner.contains(x, y);
        }

        @Override
        public Point getLocation() {
            return inner.getLocation();
        }

        @Override
        public void draw(Graphics g) {
            inner.draw(g);
        }

        @Override
        public Cursor getCursor() {
            return inner.getCursor();
        }

        @Override
        public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
            inner.startInteraction(x, y, e, v);
        }

        @Override
        public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
            inner.dragInteraction(x, y, e, v);
        }

        @Override
        public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
            inner.stopInteraction(x, y, e, v);
        }
    }
}
