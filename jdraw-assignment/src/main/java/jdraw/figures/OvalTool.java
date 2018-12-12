package jdraw.figures;

import jdraw.framework.DrawContext;

import java.awt.*;
import java.awt.event.MouseEvent;

public class OvalTool extends AbstractDrawTool<Oval> {

   /**
    * Create a new Oval tool for the given context.
    * @param context a context to use this tool in.
    */
   public OvalTool(DrawContext context) {
      super(context);
   }

   /**
    * Initializes a new Oval object by setting an anchor
    * point where the mouse was pressed. A new Oval is then
    * added to the model.
    * @param x x-coordinate of mouse
    * @param y y-coordinate of mouse
    * @param e event containing additional information about which keys were pressed.
    *
    * @see jdraw.framework.DrawTool#mouseDown(int, int, MouseEvent)
    */
   @Override
   public void mouseDown(int x, int y, MouseEvent e) {
      if (figure != null) {
         throw new IllegalStateException();
      }
      anchor = new Point(x, y);
      figure = new Oval(x, y, 0, 0);
      view.getModel().addFigure(figure);
   }

   @Override
   public String getName() {
      return "Oval";
   }
}
