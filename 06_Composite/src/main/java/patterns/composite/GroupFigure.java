package patterns.composite;

import java.util.LinkedList;
import java.util.List;

public class GroupFigure extends Figure {
    private String name;
    private List<Figure> figures = new LinkedList<>();

    public GroupFigure(String name, Figure... figures) {
        this.name = name;
        for (Figure f : figures) {
            addFigure(f);
        }
    }

    public void addFigure(Figure f) {
        if (f.parent != null) {
            throw new IllegalArgumentException();
        } else
            f.parent = this;

        figures.add(f);
    }

    @Override
    public void draw(String prefix) {
        System.out.println(prefix + name);
        for (Figure f : figures) {
            f.draw(prefix + ">>");
        }
    }
}
