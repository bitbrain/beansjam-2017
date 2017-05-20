package tv.rocketbeans.supermafiosi.assets;

import java.util.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import de.bitbrain.braingdx.assets.GameAssetLoader;
import com.badlogic.gdx.audio.Music;

public class MafiosiAssetLoader implements GameAssetLoader {

	@Override
	public void put(Map<String, Class<?>> assets) {
         
            /**
             * Fonts
             */
		assets.put(Asset.Fonts.EIGHT_BIT_WONDER, FreeTypeFontGenerator.class);
            
            /**
             * Textures
             */
		assets.put(Asset.Textures.BUTTON_9PATCH, Texture.class);
		assets.put(Asset.Textures.LOGO, Texture.class);
		assets.put(Asset.Textures.STAGE, Texture.class);
            assets.put(Asset.Textures.AVATAR_01, Texture.class);
            assets.put(Asset.Textures.KRANKENBETT, Texture.class);

            assets.put(Asset.Textures.OLDDON, Texture.class);
            assets.put(Asset.Textures.KAPPO, Texture.class);

            assets.put(Asset.Textures.DUMMY, Texture.class);
            assets.put(Asset.Textures.JURY_TABLE, Texture.class);
            
            /*
            *  Music tralalalaa
            */
            assets.put(Asset.Music.DYING_DON, Music.class);
            assets.put(Asset.Music.MENU_CHAR_SELECT, Music.class);
          
	}
}
