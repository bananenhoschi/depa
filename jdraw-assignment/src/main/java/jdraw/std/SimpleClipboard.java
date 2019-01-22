package jdraw.std;

import jdraw.framework.Figure;

import java.util.ArrayList;
import java.util.List;

public class SimpleClipboard {

    private final List<Figure> figures = new ArrayList<>();

    public void add(Figure figure) { figures.add(figure); }
    public List<Figure> get() { return figures; }
    public void clear() { figures.clear(); }

}
