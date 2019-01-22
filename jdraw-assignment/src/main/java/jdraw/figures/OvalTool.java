package jdraw.figures;

import jdraw.framework.AddFigureCommand;
import jdraw.framework.DrawContext;

import java.awt.*;
import java.awt.event.MouseEvent;

public class OvalTool extends AbstractDrawTool<Oval> {


    /**
     * Temporary variable. During Oval creation (during a
     * mouse down - mouse drag - mouse up cycle) this variable refers
     * to the new Oval that is inserted.
     */
    private Oval newOval = null;

    /**
     * Temporary variable.
     * During rectangle creation this variable refers to the point the
     * mouse was first pressed.
     */
    private Point anchor = null;

    /**
     * Create a new Oval tool for the given context.
     *
     * @param context a context to use this tool in.
     */
    public OvalTool(DrawContext context, String name, String icon) {
        super(context, name, icon);
    }

    /**
     * Initializes a new Oval object by setting an anchor
     * point where the mouse was pressed. A new Oval is then
     * added to the model.
     *
     * @param x x-coordinate of mouse
     * @param y y-coordinate of mouse
     * @param e event containing additional information about which keys were pressed.
     * @see jdraw.framework.DrawTool#mouseDown(int, int, MouseEvent)
     */
    @Override
    public void mouseDown(int x, int y, MouseEvent e) {
        if (newOval != null) {
            throw new IllegalStateException();
        }
        anchor = new Point(x, y);
        newOval = new Oval(x, y, 0, 0);
        view.getModel().addFigure(newOval);
    }

    /**
     * During a mouse drag, the Oval will be resized according to the mouse
     * position. The status bar shows the current size.
     *
     * @param x x-coordinate of mouse
     * @param y y-coordinate of mouse
     * @param e event containing additional information about which keys were
     *          pressed.
     * @see jdraw.framework.DrawTool#mouseDrag(int, int, MouseEvent)
     */
    @Override
    public void mouseDrag(int x, int y, MouseEvent e) {
        newOval.setBounds(anchor, new Point(x, y));
        java.awt.Rectangle r = newOval.getBounds();
        context.showStatusText("w: " + r.width + ", h: " + r.height);
    }

    /**
     * When the user releases the mouse, the Oval object is updated
     * according to the color and fill status settings.
     *
     * @param x x-coordinate of mouse
     * @param y y-coordinate of mouse
     * @param e event containing additional information about which keys were
     *          pressed.
     * @see jdraw.framework.DrawTool#mouseUp(int, int, MouseEvent)
     */
    @Override
    public void mouseUp(int x, int y, MouseEvent e) {
        newOval.setBounds(anchor, new Point(x, y));
        this.context.getModel().getDrawCommandHandler().addCommand(new AddFigureCommand(this.context.getModel(), newOval));
        newOval = null;
        anchor = null;
        this.context.showStatusText("Oval Mode");
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
    }

    @Override
    public String getIconName() {
        return "oval.png";
    }

    @Override
    public String getName() {
        return "Oval";
    }
}
