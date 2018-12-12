package patterns.observer.multimodel;

import java.awt.Color;
import java.util.EnumSet;


public class ColorTest {

	public static void main(String[] args) {
		//final ColorModel model = new ColorModel(Color.BLACK);
		final ColorModel2 model = new ColorModel2(Color.BLACK);

		EnumSet<ColorChannel> all = EnumSet.allOf(ColorChannel.class);

		model.addColorListener(c -> {
			if (Color.RED.equals(c)) {
				model.setColor(Color.GRAY);
			}
		}, all);

		model.addColorListener(c -> {
			System.out.println("Current color: " + c);
		}, all);

		model.setColor(Color.WHITE);
	}

}
