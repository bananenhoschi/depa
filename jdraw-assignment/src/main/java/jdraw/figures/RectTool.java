/*
 * Copyright (c) 2018 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */

package jdraw.figures;

import java.awt.Point;
import java.awt.event.MouseEvent;

import jdraw.framework.DrawContext;

/**
 * This tool defines a mode for drawing rectangles.
 *
 * @author Christoph Denzler
 * @see jdraw.framework.Figure
 */
public class RectTool extends AbstractDrawTool<Rect> {


   /**
    * Create a new rectangle tool for the given context.
    *
    * @param context a context to use this tool in.
    */
   public RectTool(DrawContext context) {
      super(context);
   }

   /**
    * Initializes a new Rectangle object by setting an anchor
    * point where the mouse was pressed. A new Rectangle is then
    * added to the model.
    *
    * @param x x-coordinate of mouse
    * @param y y-coordinate of mouse
    * @param e event containing additional information about which keys were pressed.
    * @see jdraw.framework.DrawTool#mouseDown(int, int, MouseEvent)
    */
   @Override
   public void mouseDown(int x, int y, MouseEvent e) {
      if (figure != null) {
         throw new IllegalStateException();
      }
      anchor = new Point(x, y);
      figure = new Rect(x, y, 0, 0);
      view.getModel().addFigure(figure);
   }

   @Override
   public String getName() {
      return "Rectangle";
   }

}
