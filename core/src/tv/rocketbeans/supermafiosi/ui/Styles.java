package tv.rocketbeans.supermafiosi.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import de.bitbrain.braingdx.assets.SharedAssetManager;
import de.bitbrain.braingdx.graphics.GraphicsFactory;
import tv.rocketbeans.supermafiosi.Colors;
import tv.rocketbeans.supermafiosi.assets.Asset;
import tv.rocketbeans.supermafiosi.graphics.BitmapFontBaker;

public final class Styles {
	
	public static final TextButtonStyle TEXT_BUTTON_MAIN_MENU = new TextButtonStyle();
	public static final TextButtonStyle TEXT_BUTTON_MULTIPLE_CHOICE_OPTION = new TextButtonStyle();
	
	public static final LabelStyle LABEL_CREDITS = new LabelStyle();
	public static final LabelStyle LABEL_DIALOG = new LabelStyle();
	public static final LabelStyle LABEL_DIALOG_TITLE = new LabelStyle();
	public static final LabelStyle LABEL_MULTISELECT_TITLE = new LabelStyle();
	public static final LabelStyle LABEL_TOAST = new LabelStyle();

	public static void init() {
		TEXT_BUTTON_MAIN_MENU.font = BitmapFontBaker.bake(Asset.Fonts.UPHEAVTT, 30);
		TEXT_BUTTON_MAIN_MENU.fontColor = Colors.FONT_COLOR.cpy();
		TEXT_BUTTON_MAIN_MENU.up = createNinePatchDrawable(Asset.Textures.BUTTON_9PATCH, 20, Colors.FOREGROUND.cpy());
		TEXT_BUTTON_MAIN_MENU.overFontColor = Colors.lighten(Colors.FONT_COLOR, 1.4f);
		TEXT_BUTTON_MAIN_MENU.over = createNinePatchDrawable(Asset.Textures.BUTTON_9PATCH, 20, Colors.lighten(Colors.FOREGROUND.cpy(), 1.1f));
		TEXT_BUTTON_MAIN_MENU.downFontColor = Colors.lighten(Colors.FONT_COLOR, 0.8f);
		TEXT_BUTTON_MAIN_MENU.down = createNinePatchDrawable(Asset.Textures.BUTTON_9PATCH, 20, Colors.lighten(Colors.FOREGROUND.cpy(), 0.9f));
		
		TEXT_BUTTON_MULTIPLE_CHOICE_OPTION.font = BitmapFontBaker.bake(Asset.Fonts.UPHEAVTT, 26);
		TEXT_BUTTON_MULTIPLE_CHOICE_OPTION.up = createNinePatchDrawable(Asset.Textures.BUTTON_9PATCH, 20, Colors.FOREGROUND.cpy());
		TEXT_BUTTON_MULTIPLE_CHOICE_OPTION.over = createNinePatchDrawable(Asset.Textures.BUTTON_9PATCH, 20, Colors.lighten(Colors.FOREGROUND.cpy(), 1.5f));
		TEXT_BUTTON_MULTIPLE_CHOICE_OPTION.fontColor = Colors.FONT_COLOR.cpy();
		TEXT_BUTTON_MULTIPLE_CHOICE_OPTION.overFontColor = Colors.lighten(Colors.FONT_COLOR, 1.5f);
		TEXT_BUTTON_MAIN_MENU.fontColor = Colors.FONT_COLOR.cpy();
		
		LABEL_CREDITS.fontColor = Colors.FONT_COLOR.cpy();
		LABEL_CREDITS.fontColor.a = 0.5f;
		LABEL_CREDITS.font = BitmapFontBaker.bake(Asset.Fonts.UPHEAVTT, 16);
		
		LABEL_DIALOG.fontColor = Colors.FONT_COLOR.cpy();
		LABEL_DIALOG.font = BitmapFontBaker.bake(Asset.Fonts.UPHEAVTT, 22);
		LABEL_DIALOG_TITLE.fontColor =  Colors.FONT_COLOR.cpy();
		LABEL_DIALOG_TITLE.font = BitmapFontBaker.bake(Asset.Fonts.UPHEAVTT, 28);
		
		LABEL_MULTISELECT_TITLE.fontColor = Colors.lighten(Colors.FONT_COLOR, 1.5f);
		LABEL_MULTISELECT_TITLE.font = BitmapFontBaker.bake(Asset.Fonts.UPHEAVTT, 48);
		
		LABEL_TOAST.font = BitmapFontBaker.bake(Asset.Fonts.UPHEAVTT, 92);
		LABEL_TOAST.fontColor = Colors.FONT_SPECIAL.cpy();
	}
	
	private static Drawable createNinePatchDrawable(String assetId, int borderRadius, Color color) {
		return new NinePatchDrawable(GraphicsFactory.createNinePatch(SharedAssetManager.getInstance().get(assetId, Texture.class), borderRadius, color));
	}
}
