/*
 * Copyright (c) 2018 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */

package jdraw.figures;

import jdraw.figures.handles.*;
import jdraw.framework.FigureHandle;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents shapes in JDraw.
 *
 * @author Christoph Denzler
 */
public class Rect extends AbstractRectangle<Rectangle> {


    /**
     * Create a new shape of the given dimension.
     *
     * @param x the x-coordinate of the upper left corner of the shape
     * @param y the y-coordinate of the upper left corner of the shape
     * @param w the shape's width
     * @param h the shape's height
     */
    public Rect(int x, int y, int w, int h) {
        shape = new Rectangle(x, y, w, h);
    }

    /**
     * Draw the shape to the given graphics context.
     *
     * @param g the graphics context to use for drawing.
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, 0));
        g.fillRect(shape.x, shape.y, shape.width, shape.height);
        g.setColor(Color.BLACK);
        g.drawRect(shape.x, shape.y, shape.width, shape.height);
    }

    @Override
    public List<FigureHandle> getHandles() {
        List<FigureHandle> handles = new LinkedList<>();
        handles.add(new NorthWestHandle(this));
        handles.add(new NorthHandle(this));
        handles.add(new NorthEastHandle(this));
        handles.add(new WestHandle(this));
        handles.add(new EastHandle(this));
        handles.add(new SouthWestHandle(this));
        handles.add(new SouthEastHandle(this));
        handles.add(new SouthHandle(this));
        return handles;
    }
}
