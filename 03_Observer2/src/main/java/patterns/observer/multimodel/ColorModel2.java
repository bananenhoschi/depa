package patterns.observer.multimodel;

import java.awt.*;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class ColorModel2 {
    private int red, green, blue;

    public ColorModel2(Color c) {
        this.red = c.getRed();
        this.green = c.getGreen();
        this.blue = c.getBlue();
    }

    private final Map<ColorListener, EnumSet<ColorChannel>> listeners = new HashMap<>();

    // register a color listener for a set of channels, i.e. a subset of {RED,GREEN,BLUE}
    public void addColorListener(ColorListener l, EnumSet<ColorChannel> set) {
        listeners.put(l, set);
    }

    public void removeColorListener(ColorListener l) {
        listeners.remove(l);
    }

    public void setColor(Color c) {
        Color old = getColor();

        this.red = c.getRed();
        this.blue = c.getBlue();
        this.green = c.getGreen();

        // Variante 1
        // if (old.getBlue() != c.getBlue()) notifyListeners(ColorChannel.BLUE);
        // if (old.getGreen() != c.getGreen()) notifyListeners(ColorChannel.GREEN);
        // if (old.getRed() != c.getRed()) notifyListeners(ColorChannel.RED);

        // Variante 2
        //notifyAllListener();

        // Variante 3

    }


    public Color getColor() {
        return new Color(red, green, blue);
    }

    public void setRed(int red) {
        this.red = red;
        notifyListeners(ColorChannel.RED);
    }

    public void setGreen(int green) {
        this.green = green;
        notifyListeners(ColorChannel.GREEN);
    }

    public void setBlue(int blue) {
        this.blue = blue;
        notifyListeners(ColorChannel.BLUE);
    }

    private void notifyAllListener() {
        Color color = getColor();
        for (ColorListener l : new ArrayList<>(listeners.keySet())) {
            l.colorValueChanged(color);
        }
    }

    private void notifyListener(boolean rChanged, boolean bChanged, boolean gChanged){
        Color color = getColor();
        for (ColorListener l : new ArrayList<>(listeners.keySet())) {
            if()
            if (listeners.get(l).contains(channel))
                l.colorValueChanged(color);
        }
    }

    private void notifyListeners(ColorChannel channel) {
        Color color = getColor();
        for (ColorListener l : new ArrayList<>(listeners.keySet())) {
            if (listeners.get(l).contains(channel))
                l.colorValueChanged(color);
        }
    }

}
