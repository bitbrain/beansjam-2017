package tv.rocketbeans.supermafiosi.assets;

import java.util.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import de.bitbrain.braingdx.assets.GameAssetLoader;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class MafiosiAssetLoader implements GameAssetLoader {

	@Override
	public void put(Map<String, Class<?>> assets) {
         
            /**
             * Fonts
             */
		assets.put(Asset.Fonts.UPHEAVTT, FreeTypeFontGenerator.class);
            
            /**
             * Textures
             */
		assets.put(Asset.Textures.BUTTON_9PATCH, Texture.class);
		assets.put(Asset.Textures.LABEL_9PATCH, Texture.class);
		assets.put(Asset.Textures.PANEL_9PATCH, Texture.class);
		assets.put(Asset.Textures.LOGO, Texture.class);
		assets.put(Asset.Textures.STAGE, Texture.class);
            assets.put(Asset.Textures.AVATAR_01, Texture.class);
            assets.put(Asset.Textures.KRANKENBETT, Texture.class);

            
            
            assets.put(Asset.Textures.OLDDON, Texture.class);
            assets.put(Asset.Textures.KAPPO, Texture.class);

            assets.put(Asset.Textures.DUMMY, Texture.class);
            assets.put(Asset.Textures.AVATAR_MODERATOR, Texture.class);
            
            assets.put(Asset.Textures.JURY_BACKGROUND, Texture.class);
            assets.put(Asset.Textures.JURY_TABLE, Texture.class);
            assets.put(Asset.Textures.JURY_UNDECIDED, Texture.class);
            assets.put(Asset.Textures.JURY_NONBULLET, Texture.class);
            assets.put(Asset.Textures.JURY_BULLET, Texture.class);
            
            assets.put(Asset.Textures.JURY_RICK, Texture.class);
            assets.put(Asset.Textures.JURY_RICK_ARM, Texture.class);
            
            
            assets.put(Asset.Textures.JURY_BLOB, Texture.class);
            assets.put(Asset.Textures.JURY_BLOB_ARM, Texture.class);
            
            assets.put(Asset.Textures.JURY_LASER, Texture.class);
            assets.put(Asset.Textures.JURY_LASER_ARM, Texture.class);
            
            assets.put(Asset.Textures.MINIGAME_ROASTBATTLE_ICON_RAINDROP, Texture.class);
            assets.put(Asset.Textures.MINIGAME_ROASTBATTLE_ICON_HEART, Texture.class);
            assets.put(Asset.Textures.MINIGAME_ROASTBATTLE_ICON_SKULL, Texture.class);
            
            
            assets.put(Asset.Textures.AVATAR_EDUARD_LASER, Texture.class);
            assets.put(Asset.Textures.AVATAR_RICK, Texture.class);
            assets.put(Asset.Textures.AVATAR_BLOBBY, Texture.class);
              
            
            assets.put(Asset.Textures.AVATAR_RONALD_TRUMPF, Texture.class);
            assets.put(Asset.Textures.AVATAR_TRON_JAWOLTA, Texture.class);
            assets.put(Asset.Textures.RONALD_TRUMPF_STAGE, Texture.class);
            assets.put(Asset.Textures.TRON_JAWOLTA_STAGE, Texture.class);
            assets.put(Asset.Textures.LERRY_SANCHEZ_STAGE, Texture.class);
            assets.put(Asset.Textures.MODERATOR_STAGE, Texture.class);
            assets.put(Asset.Textures.ROAST_BATTLE_LOGO, Texture.class);
            
            
            
            assets.put(Asset.Textures.ROULETTE_BG, Texture.class);
 
            
            assets.put(Asset.Textures.SANCHEZ_ROULETTE, Texture.class);
            assets.put(Asset.Textures.RONALD_ROULETTE, Texture.class);
            assets.put(Asset.Textures.TRON_ROULETTE, Texture.class);
            
            assets.put(Asset.Textures.SANCHEZ_ROULETTE_WEAPON, Texture.class);
            assets.put(Asset.Textures.RONALD_ROULETTE_WEAPON, Texture.class);
            assets.put(Asset.Textures.TRON_ROULETTE_WEAPON, Texture.class);
            
            assets.put(Asset.Textures.BOOM, Texture.class);
            
            /*
            *  Music tralalalaa
            */
            assets.put(Asset.Music.DYING_DON, Music.class);
            assets.put(Asset.Music.MENU_CHAR_SELECT_INTRO, Music.class);
            assets.put(Asset.Music.MENU_CHAR_SELECT_MAIN, Music.class);
            assets.put(Asset.Music.TENSION, Music.class);
            assets.put(Asset.Music.AUDIANCE_HAPPY, Music.class);
            
            assets.put(Asset.Music.MENU_MINIGAME_ROASTME_MUSIC_INTRO, Music.class);
            assets.put(Asset.Music.MENU_MINIGAME_ROASTME_MUSIC_MAIN, Music.class);
            
            assets.put(Asset.Music.MENU_MINIGAME_ROULETTE_MUSIC_MAIN, Music.class);
            
            assets.put(Asset.Music.GAME_OVER_MAIN, Music.class);
            assets.put(Asset.Music.GAME_OVER_INTRO, Music.class);
            assets.put(Asset.Music.GAME_WIN_MAIN, Music.class);
            assets.put(Asset.Music.GAME_WIN_INTRO, Music.class);
            assets.put(Asset.Music.STINGER, Music.class);
            
            /*
            * Sounds
            */
       
            assets.put(Asset.Sounds.AUDIANCE_BOO, Sound.class);
            assets.put(Asset.Sounds.AUDIANCE_CLAPPING1, Sound.class);
            assets.put(Asset.Sounds.AUDIANCE_CLAPPING2, Sound.class);
            assets.put(Asset.Sounds.AUDIANCE_CLAPPING3, Sound.class);
            assets.put(Asset.Sounds.AUDIANCE_DRUMROLL, Sound.class);
            assets.put(Asset.Sounds.AUDIANCE_HAPPY, Sound.class);
            assets.put(Asset.Sounds.TRIGGER_BULLET, Sound.class);
            assets.put(Asset.Sounds.TRIGGER_NO_BULLET, Sound.class);
            
            assets.put(Asset.Sounds.SQUEEKY_1, Sound.class);
            assets.put(Asset.Sounds.SQUEEKY_2, Sound.class);
            
            assets.put(Asset.Sounds.EKG_BEEP, Sound.class);
            assets.put(Asset.Sounds.EKG_STALL, Sound.class);
          
            
	}
}
