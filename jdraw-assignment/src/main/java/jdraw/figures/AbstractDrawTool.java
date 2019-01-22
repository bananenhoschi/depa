package jdraw.figures;

import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

abstract public class AbstractDrawTool<T extends Figure> implements DrawTool {


    private String name;
    private String icon;
    /**
     * the image resource path.
     */
    private static final String IMAGES = "/images/";

    /**
     * The context we use for drawing.
     */
    protected final DrawContext context;

    /**
     * The context's view. This variable can be used as a shortcut, i.e.
     * instead of calling context.getView().
     */
    protected final DrawView view;


    public AbstractDrawTool(DrawContext context, String name, String icon) {
        this.context = context;
        this.view = context.getView();
        this.name = name;
        this.icon = icon;
    }
    /**
     * Deactivates the current mode by resetting the cursor
     * and clearing the status bar.
     *
     * @see DrawTool#deactivate()
     */
    @Override
    public void deactivate() {
        this.context.showStatusText("");
    }

    /**
     * Activates the Rectangle Mode. There will be a
     * specific menu added to the menu bar that provides settings for
     * Rectangle attributes
     */
    @Override
    public void activate() {
        this.context.showStatusText(getName() + " Mode");
    }

    @Override
    public Icon getIcon() {
        return new ImageIcon(getClass().getResource(IMAGES + getIconName()));
    }

    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
    }

    @Override
    public String getName() {
        return name;
    }

    public String getIconName() {
        return icon;
    }
}
