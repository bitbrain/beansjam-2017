package tv.rocketbeans.supermafiosi;

import com.badlogic.gdx.graphics.Color;

public abstract class Colors {

	public static final Color BACKGROUND = Color.valueOf("0e081d");
	public static final Color FOREGROUND = Color.valueOf("00d8ff");
	
	public static Color lighten(Color color, float factor) {
        Color result = color.cpy();
        result.r *= factor;
        result.g *= factor;
        result.b *= factor;
        return result;
    }
}
