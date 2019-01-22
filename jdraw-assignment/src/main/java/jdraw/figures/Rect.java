/*
 * Copyright (c) 2018 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */

package jdraw.figures;

import jdraw.framework.FigureEvent;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Represents shapes in JDraw.
 *
 * @author Christoph Denzler
 */
public class Rect extends AbstractFigure {

    /**
     * Use the java.awt.Rectangle in order to save/reuse code.
     */
    private Rectangle2D rectangle;

    /**
     * Create a new rectangle of the given dimension.
     *
     * @param x the x-coordinate of the upper left corner of the rectangle
     * @param y the y-coordinate of the upper left corner of the rectangle
     * @param w the rectangle's width
     * @param h the rectangle's height
     */
    public Rect(int x, int y, int w, int h) {
        rectangle = getRectangle(x, y, w, h);
    }


    public Rect(Rect original) {
        rectangle = getRectangle(original.rectangle.getX(), original.rectangle.getY(), original.rectangle.getWidth(), original.rectangle.getHeight());

    }

    private Rectangle2D.Double getRectangle(double x, double y, double width, double height) {
        return new Rectangle2D.Double(
                x,
                y,
                width,
                height
        );
    }

    /**
     * Draw the rectangle to the given graphics context.
     *
     * @param g the graphics context to use for drawing.
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect((int) rectangle.getX(), (int) rectangle.getY(), (int) rectangle.getWidth(), (int) rectangle.getHeight());
        g.setColor(Color.BLACK);
        g.drawRect((int) rectangle.getX(), (int) rectangle.getY(), (int) rectangle.getWidth(), (int) rectangle.getHeight());
    }

    @Override
    public void setBounds(Point origin, Point corner) {
        Rectangle2D.Double original = getRectangle(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
        rectangle.setFrameFromDiagonal(origin, corner);
        if (!original.equals(rectangle)) {
            notifyListeners(new FigureEvent(this));
        }
    }

    @Override
    public void move(int dx, int dy) {
        if (dx != 0 || dy != 0) {
            rectangle.setFrame(rectangle.getX() + dx, rectangle.getY() + dy, rectangle.getWidth(), rectangle.getHeight());
            notifyListeners(new FigureEvent(this));
        }
    }

    @Override
    public boolean contains(int x, int y) {
        return rectangle.contains(x, y);
    }

    @Override
    public Rectangle getBounds() {
        return rectangle.getBounds();
    }

    @Override
    public Rect clone() {
        Rect copy = (Rect) super.clone();
        copy.rectangle = (Rectangle2D) copy.rectangle.clone();
        return copy;
    }
}