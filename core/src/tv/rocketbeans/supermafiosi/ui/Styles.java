package tv.rocketbeans.supermafiosi.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

import de.bitbrain.braingdx.assets.SharedAssetManager;
import de.bitbrain.braingdx.graphics.GraphicsFactory;
import tv.rocketbeans.supermafiosi.assets.Asset;
import tv.rocketbeans.supermafiosi.graphics.BitmapFontBaker;

public final class Styles {
	
	public static final TextButtonStyle TEXT_BUTTON_MAIN_MENU = new TextButtonStyle();

	public static void init() {
		TEXT_BUTTON_MAIN_MENU.font = BitmapFontBaker.bake(Asset.Fonts.EIGHT_BIT_WONDER, 30);
		TEXT_BUTTON_MAIN_MENU.fontColor = Color.WHITE;
		TEXT_BUTTON_MAIN_MENU.up = createNinePatchDrawable(Asset.Textures.BUTTON_9PATCH, 20, Color.WHITE);
		TEXT_BUTTON_MAIN_MENU.overFontColor = Color.BLUE;
		TEXT_BUTTON_MAIN_MENU.over = createNinePatchDrawable(Asset.Textures.BUTTON_9PATCH, 20, Color.BLUE);
		TEXT_BUTTON_MAIN_MENU.downFontColor = Color.CYAN;
		TEXT_BUTTON_MAIN_MENU.down = createNinePatchDrawable(Asset.Textures.BUTTON_9PATCH, 20, Color.CYAN);
	}
	
	private static Drawable createNinePatchDrawable(String assetId, int borderRadius, Color color) {
		return new NinePatchDrawable(GraphicsFactory.createNinePatch(SharedAssetManager.getInstance().get(assetId, Texture.class), borderRadius, color));
	}
}
