package jdraw.figures;

import jdraw.framework.FigureEvent;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Oval extends AbstractFigure {

    /**
     * Use the java.awt.geom.Ellipse2D in order to save/reuse code.
     */
    private Ellipse2D oval;

    /**
     * Create a new rectangle of the given dimension.
     *
     * @param x the x-coordinate of the upper left corner of the oval
     * @param y the y-coordinate of the upper left corner of the oval
     * @param w the oval's width
     * @param h the oval's height
     */
    public Oval(int x, int y, int w, int h) {
        oval = getOval(x, y, w, h);
    }

    public Oval(Oval original) {
        oval = getOval(original.oval.getX(), original.oval.getY(), original.oval.getWidth(), original.oval.getHeight());
    }

    private Ellipse2D.Double getOval(double x, double y, double width, double height) {
        return new Ellipse2D.Double(
                x,
                y,
                width,
                height);
    }

    /**
     * Draw the oval to the given graphics context.
     *
     * @param g the graphics context to use for drawing.
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval((int) oval.getX(), (int) oval.getY(), (int) oval.getWidth(), (int) oval.getHeight());
        g.setColor(Color.BLACK);
        g.drawOval((int) oval.getX(), (int) oval.getY(), (int) oval.getWidth(), (int) oval.getHeight());
    }

    @Override
    public void setBounds(Point origin, Point corner) {
        Ellipse2D original = getOval(oval.getX(), oval.getY(), oval.getWidth(), oval.getHeight());
        oval.setFrameFromDiagonal(origin, corner);
        if (!original.equals(oval)) {
            notifyListeners(new FigureEvent(this));
        }
    }

    @Override
    public void move(int dx, int dy) {
        if (dx != 0 || dy != 0) {
            oval.setFrame(oval.getX() + dx, oval.getY() + dy, oval.getWidth(), oval.getHeight());
            notifyListeners(new FigureEvent(this));
        }
    }

    @Override
    public boolean contains(int x, int y) {
        return oval.contains(x, y);
    }

    @Override
    public Rectangle getBounds() {
        return oval.getBounds();
    }

    @Override
    public Oval clone() {
        Oval copy = (Oval) super.clone();
        copy.oval = (Ellipse2D) copy.oval.clone();
        return copy;
    }
}