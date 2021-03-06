package tv.rocketbeans.supermafiosi.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

import de.bitbrain.braingdx.assets.SharedAssetManager;

public class BitmapFontBaker {

	   public static BitmapFont bake(String fontPath, int fontSize) {
	      FreeTypeFontGenerator generator = SharedAssetManager.getInstance().get(fontPath, FreeTypeFontGenerator.class);
	      FreeTypeFontParameter parameter = new FreeTypeFontParameter();
	      parameter.size = fontSize;
	      parameter.mono = true;
	      parameter.color = Color.WHITE.cpy();
	      BitmapFont font = generator.generateFont(parameter);
	      return font;
	   }
	}