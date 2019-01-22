package jdraw.framework;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

public class JDrawCommandHandler implements DrawCommandHandler {

    private Stack<DrawCommand> undoStack = new Stack<>();
    private Stack<DrawCommand> redoStack = new Stack<>();



    private Script script = null;

    @Override
    public void clearHistory() {
        undoStack.clear();
        redoStack.clear();
    }

    @Override
    public void addCommand(DrawCommand cmd) {
        System.out.println(undoStack);
        redoStack.clear();
        if (script == null) {
            undoStack.push(cmd);

        } else {
            script.commands.add(cmd);
        }
    }

    public void beginScript() {
        if (script != null) throw new IllegalStateException();
        script = new Script();
    }

    public void endScript() {
        if (script == null) throw new IllegalStateException();
        Script s = script;
        script = null;
        if (s.commands.size() > 0) {
            if (s.commands.size() == 1) {
                addCommand(s.commands.get(0));
            } else {
                addCommand(s);
            }
        }
    }

    public void undo() {
        if (undoPossible()) {
            DrawCommand d = undoStack.pop();
            redoStack.push(d);
            d.undo();
        }
    }

    public void redo() {
        if (redoPossible()) {
            DrawCommand d = redoStack.pop();
            undoStack.push(d);
            d.redo();
        }
    }

    public boolean undoPossible() {
        return undoStack.size() > 0;
    }

    public boolean redoPossible() {
        return redoStack.size() > 0;
    }

    private static final class Script implements DrawCommand {
        private List<DrawCommand> commands = new LinkedList<>();

        public void redo() {
            ListIterator<DrawCommand> it = commands.listIterator();
            while (it.hasNext()) {
                it.next().redo();
            }
        }

        public void undo() {
            int size = commands.size();
            ListIterator<DrawCommand> it = commands.listIterator(size);
            while (it.hasPrevious()) {
                it.previous().undo();
            }
        }
    }
}
