/*
 * Copyright (c) 2018 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */
package jdraw.std;

import jdraw.figures.BorderDecorator;
import jdraw.figures.LineTool;
import jdraw.figures.OvalTool;
import jdraw.figures.RectTool;
import jdraw.framework.*;
import jdraw.grids.Grid20;
import jdraw.grids.Grid50;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Standard implementation of interface DrawContext.
 *
 * @author Dominik Gruntz & Christoph Denzler
 * @version 2.6, 24.09.09
 * @see DrawView
 */
@SuppressWarnings("serial")
public class StdContext extends AbstractContext {
    /**
     * Constructs a standard context with a default set of drawing tools.
     *
     * @param view the view that is displaying the actual drawing.
     */
    public StdContext(DrawView view) {
        super(view, null);
    }

    /**
     * Constructs a standard context. The drawing tools available can be
     * parameterized using <code>toolFactories</code>.
     *
     * @param view          the view that is displaying the actual drawing.
     * @param toolFactories a list of DrawToolFactories that are available to the user
     */
    public StdContext(DrawView view, List<DrawToolFactory> toolFactories) {
        super(view, toolFactories);
    }

    private SimpleClipboard clipboard = new SimpleClipboard();

    /**
     * Creates and initializes the "Edit" menu.
     *
     * @return the new "Edit" menu.
     */
    @Override
    protected JMenu createEditMenu() {
        JMenu editMenu = new JMenu("Edit");
        final JMenuItem undo = new JMenuItem("Undo");
        undo.setAccelerator(KeyStroke.getKeyStroke("control Z"));
        editMenu.add(undo);
        undo.addActionListener(e -> {
                    final DrawCommandHandler h = getModel().getDrawCommandHandler();
                    if (h.undoPossible()) {
                        h.undo();
                    }
                }
        );

        final JMenuItem redo = new JMenuItem("Redo");
        redo.setAccelerator(KeyStroke.getKeyStroke("control Y"));
        editMenu.add(redo);
        redo.addActionListener(e -> {
                    final DrawCommandHandler h = getModel().getDrawCommandHandler();
                    if (h.redoPossible()) {
                        h.redo();
                    }
                }
        );
        editMenu.addSeparator();

        JMenuItem sa = new JMenuItem("SelectAll");
        sa.setAccelerator(KeyStroke.getKeyStroke("control A"));
        editMenu.add(sa);
        sa.addActionListener(e -> {
                    for (Figure f : getModel().getFigures()) {
                        getView().addToSelection(f);
                    }
                    getView().repaint();
                }
        );

        editMenu.addSeparator();

        JMenuItem cut = new JMenuItem("Cut");
        cut.addActionListener(e -> {
            clipboard.clear();
            for (Figure f : getView().getSelection()) {
                clipboard.add(f);
                getModel().removeFigure(f);
            }
        });
        editMenu.add(cut);


        JMenuItem paste = new JMenuItem("Paste");
        paste.addActionListener(e -> {
            getView().clearSelection();
            for (Figure f : clipboard.get()) {
                Figure f2 = f.clone();
                getModel().addFigure(f2);
                getView().addToSelection(f2);
            }
        });
        JMenuItem copy = new JMenuItem("Copy");
        copy.addActionListener(e -> {
            clipboard.clear();
            for (Figure f : getView().getSelection()) {
                Figure f2 = f.clone();
                clipboard.add(f2);
            }
        });
        editMenu.add(cut);
        editMenu.add(copy);
        editMenu.add(paste);

        editMenu.addSeparator();
        JMenuItem clear = new JMenuItem("Clear");
        editMenu.add(clear);
        clear.addActionListener(e -> {
            getModel().removeAllFigures();
        });

        editMenu.addSeparator();
        JMenuItem group = new JMenuItem("Group");
        group.setEnabled(false);
        editMenu.add(group);

        JMenuItem ungroup = new JMenuItem("Ungroup");
        ungroup.setEnabled(false);
        editMenu.add(ungroup);

        editMenu.addSeparator();

        JMenu orderMenu = new JMenu("Order...");
        JMenuItem frontItem = new JMenuItem("Bring To Front");
        frontItem.addActionListener(e -> {
            bringToFront(getView().getModel(), getView().getSelection());
        });
        orderMenu.add(frontItem);
        JMenuItem backItem = new JMenuItem("Send To Back");
        backItem.addActionListener(e -> {
            sendToBack(getView().getModel(), getView().getSelection());
        });
        orderMenu.add(backItem);
        editMenu.add(orderMenu);

        JMenu grid = new JMenu("Grid...");
        JMenuItem grid20 = new JMenuItem("20-Grid");
        grid20.addActionListener(e -> {
            getView().setGrid(new Grid20());
        });
        JMenuItem grid50 = new JMenuItem("50-Grid");
        grid50.addActionListener(e -> {
            getView().setGrid(new Grid50());
        });
        JMenuItem noGrid = new JMenuItem("no Grid");
        noGrid.addActionListener(e -> {
            getView().setGrid(null);
        });
        grid.add(grid20);
        grid.add(grid50);
        grid.add(noGrid);
        editMenu.add(grid);


        JMenuItem dec = new JMenuItem("Add Border");
        editMenu.add(dec);
        dec.addActionListener(e -> {
                    List<Figure> s = getView().getSelection();
                    getView().clearSelection();
                    for (Figure f : s) {
                        BorderDecorator decorator = new BorderDecorator(f);
                        getModel().removeFigure(f);
                        getModel().addFigure(decorator);
                        getView().addToSelection(decorator);
                    }
                }
        );


        return editMenu;
    }

    /**
     * Creates and initializes items in the file menu.
     *
     * @return the new "File" menu.
     */
    @Override
    protected JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("File");
        JMenuItem open = new JMenuItem("Open");
        fileMenu.add(open);
        open.setAccelerator(KeyStroke.getKeyStroke("control O"));
        open.addActionListener(e -> doOpen());

        JMenuItem save = new JMenuItem("Save");
        save.setAccelerator(KeyStroke.getKeyStroke("control S"));
        fileMenu.add(save);
        save.addActionListener(e -> doSave());

        JMenuItem exit = new JMenuItem("Exit");
        fileMenu.add(exit);
        exit.addActionListener(e -> System.exit(0));

        return fileMenu;
    }

    @Override
    protected void doRegisterDrawTools() {
        DrawTool rectangleTool = new RectTool(this, "Rectangle", "rectangle.png");
        addTool(rectangleTool);
        DrawTool ovalTool = new OvalTool(this, "Oval", "oval.png");
        addTool(ovalTool);
        DrawTool lineTool = new LineTool(this, "Line", "line.png");
        addTool(lineTool);
    }

    /**
     * Changes the order of figures and moves the figures in the selection
     * to the front, i.e. moves them to the end of the list of figures.
     *
     * @param model     model in which the order has to be changed
     * @param selection selection which is moved to front
     */
    public void bringToFront(DrawModel model, List<Figure> selection) {
        // the figures in the selection are ordered according to the order in
        // the model
        List<Figure> orderedSelection = new LinkedList<Figure>();
        int pos = 0;
        for (Figure f : model.getFigures()) {
            pos++;
            if (selection.contains(f)) {
                orderedSelection.add(0, f);
            }
        }
        for (Figure f : orderedSelection) {
            model.setFigureIndex(f, --pos);
        }
    }

    /**
     * Changes the order of figures and moves the figures in the selection
     * to the back, i.e. moves them to the front of the list of figures.
     *
     * @param model     model in which the order has to be changed
     * @param selection selection which is moved to the back
     */
    public void sendToBack(DrawModel model, List<Figure> selection) {
        // the figures in the selection are ordered according to the order in
        // the model
        List<Figure> orderedSelection = new LinkedList<Figure>();
        for (Figure f : model.getFigures()) {
            if (selection.contains(f)) {
                orderedSelection.add(f);
            }
        }
        int pos = 0;
        for (Figure f : orderedSelection) {
            model.setFigureIndex(f, pos++);
        }
    }

    /**
     * Handles the saving of a drawing to a file.
     */
    private void doSave() {
        JFileChooser chooser = new JFileChooser(getClass().getResource("").getFile());
        chooser.setDialogTitle("Save Graphic");
        chooser.setDialogType(JFileChooser.SAVE_DIALOG);

        chooser.setFileFilter(new FileNameExtensionFilter("JDraw Graphics (*.draw)", "draw"));
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("JDraw Graphics (*.xml)", "xml"));
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("JDraw Graphics (*.json)", "json"));

        int res = chooser.showSaveDialog(this);

        if (res == JFileChooser.APPROVE_OPTION) {
            // save graphic
            File file = chooser.getSelectedFile();
            FileFilter filter = chooser.getFileFilter();
            if (filter instanceof FileNameExtensionFilter && !filter.accept(file)) {
                file = new File(chooser.getCurrentDirectory(), file.getName() + "." + ((FileNameExtensionFilter) filter).getExtensions()[0]);
            }
            System.out.println("save current graphic to file " + file.getName() + " using format "
                    + ((FileNameExtensionFilter) filter).getExtensions()[0]);
        }
    }

    /**
     * Handles the opening of a new drawing from a file.
     */
    private void doOpen() {
        JFileChooser chooser = new JFileChooser(getClass().getResource("")
                .getFile());
        chooser.setDialogTitle("Open Graphic");
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public String getDescription() {
                return "JDraw Graphic (*.draw)";
            }

            @Override
            public boolean accept(File f) {
                return f.isDirectory() || f.getName().endsWith(".draw");
            }
        });
        int res = chooser.showOpenDialog(this);

        if (res == JFileChooser.APPROVE_OPTION) {
            // read jdraw graphic
            System.out.println("read file "
                    + chooser.getSelectedFile().getName());
        }
    }
}
