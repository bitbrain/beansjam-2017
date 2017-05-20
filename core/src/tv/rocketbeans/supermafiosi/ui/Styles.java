package tv.rocketbeans.supermafiosi.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

import de.bitbrain.braingdx.assets.SharedAssetManager;
import de.bitbrain.braingdx.graphics.GraphicsFactory;
import tv.rocketbeans.supermafiosi.Colors;
import tv.rocketbeans.supermafiosi.assets.Asset;
import tv.rocketbeans.supermafiosi.graphics.BitmapFontBaker;

public final class Styles {
	
	public static final TextButtonStyle TEXT_BUTTON_MAIN_MENU = new TextButtonStyle();
	
	public static final LabelStyle LABEL_CREDITS = new LabelStyle();
	public static final LabelStyle LABEL_DIALOG = new LabelStyle();
	public static final LabelStyle LABEL_DIALOG_TITLE = new LabelStyle();

	public static void init() {
		TEXT_BUTTON_MAIN_MENU.font = BitmapFontBaker.bake(Asset.Fonts.EIGHT_BIT_WONDER, 30);
		TEXT_BUTTON_MAIN_MENU.fontColor = Colors.FOREGROUND;
		TEXT_BUTTON_MAIN_MENU.up = createNinePatchDrawable(Asset.Textures.BUTTON_9PATCH, 20, Colors.FOREGROUND);
		TEXT_BUTTON_MAIN_MENU.overFontColor = Colors.lighten(Colors.FOREGROUND, 5.5f);
		TEXT_BUTTON_MAIN_MENU.over = createNinePatchDrawable(Asset.Textures.BUTTON_9PATCH, 20, Colors.lighten(Colors.FOREGROUND, 5.5f));
		TEXT_BUTTON_MAIN_MENU.downFontColor = Colors.lighten(Colors.FOREGROUND, 0.6f);
		TEXT_BUTTON_MAIN_MENU.down = createNinePatchDrawable(Asset.Textures.BUTTON_9PATCH, 20, Colors.lighten(Colors.FOREGROUND, 0.6f));
		
		LABEL_CREDITS.fontColor = Colors.FOREGROUND.cpy();
		LABEL_CREDITS.fontColor.a = 0.5f;
		LABEL_CREDITS.font = BitmapFontBaker.bake(Asset.Fonts.EIGHT_BIT_WONDER, 16);
		
		LABEL_DIALOG.fontColor = Colors.FOREGROUND;
		LABEL_DIALOG.font = BitmapFontBaker.bake(Asset.Fonts.EIGHT_BIT_WONDER, 22);
		LABEL_DIALOG_TITLE.fontColor =  Colors.BACKGROUND;
		LABEL_DIALOG_TITLE.font = BitmapFontBaker.bake(Asset.Fonts.EIGHT_BIT_WONDER, 28);
	}
	
	private static Drawable createNinePatchDrawable(String assetId, int borderRadius, Color color) {
		return new NinePatchDrawable(GraphicsFactory.createNinePatch(SharedAssetManager.getInstance().get(assetId, Texture.class), borderRadius, color));
	}
}
