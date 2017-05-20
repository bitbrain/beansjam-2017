package tv.rocketbeans.supermafiosi.assets;

import java.util.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import de.bitbrain.braingdx.assets.GameAssetLoader;

public class MafiosiAssetLoader implements GameAssetLoader {

	@Override
	public void put(Map<String, Class<?>> assets) {
		assets.put(Asset.Fonts.EIGHT_BIT_WONDER, FreeTypeFontGenerator.class);
		assets.put(Asset.Textures.BUTTON_9PATCH, Texture.class);
		assets.put(Asset.Textures.LOGO, Texture.class);
		assets.put(Asset.Textures.AVATAR_01, Texture.class);
	}
}
