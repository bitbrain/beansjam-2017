package tv.rocketbeans.supermafiosi;

import com.badlogic.gdx.graphics.Color;

public abstract class Colors {

	public static final Color BACKGROUND = Color.valueOf("194d4e");
	public static final Color FOREGROUND = Color.valueOf("33cdc8");
	public static final Color FONT_COLOR = Color.valueOf("ffe3bb");
	
	public static final Color SCISSORS_COLOR = Color.valueOf("ff0096");	
	public static final Color ROCK_COLOR = Color.valueOf("9cff00");
	public static final Color PAPER_COLOR = Color.valueOf("67f2ff");
	
	public static Color lighten(Color color, float factor) {
        Color result = color.cpy();
        result.r *= factor;
        result.g *= factor;
        result.b *= factor;
        return result;
    }
}
