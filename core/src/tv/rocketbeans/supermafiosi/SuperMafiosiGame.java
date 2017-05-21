package tv.rocketbeans.supermafiosi;

import java.util.Locale;

import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;

import de.bitbrain.braingdx.BrainGdxGame;
import de.bitbrain.braingdx.assets.GameAssetLoader;
import de.bitbrain.braingdx.assets.SharedAssetManager;
import de.bitbrain.braingdx.audio.AudioManager;
import de.bitbrain.braingdx.screens.AbstractScreen;
import tv.rocketbeans.supermafiosi.assets.Asset;
import tv.rocketbeans.supermafiosi.assets.MafiosiAssetLoader;
import tv.rocketbeans.supermafiosi.i18n.Bundle;
import tv.rocketbeans.supermafiosi.screens.IngameStageScreen;
import tv.rocketbeans.supermafiosi.screens.IntroScreen;
import tv.rocketbeans.supermafiosi.ui.Styles;

public class SuperMafiosiGame extends BrainGdxGame
{

   @Override
   protected GameAssetLoader getAssetLoader()
   {
      SharedAssetManager.getInstance().setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(new InternalFileHandleResolver()));
      return new MafiosiAssetLoader();
   }

   @Override
   protected AbstractScreen<?> getInitialScreen()
   {
      Styles.init();
      Bundle.load();
      AudioManager.getInstance().setVolume(Config.MUSIC_VOLUME);
      // Temporarily set to German for now
      Bundle.setLocale(Locale.GERMAN);
      if (Config.DEBUG)
      {

         Music menu_music_main = SharedAssetManager.getInstance().get(Asset.Music.MENU_CHAR_SELECT_MAIN, Music.class);
         menu_music_main.setLooping(true);
         if (!menu_music_main.isPlaying())
         {
            AudioManager.getInstance().stopMusic(Asset.Music.DYING_DON);
            AudioManager.getInstance().fadeInMusic(Asset.Music.MENU_CHAR_SELECT_MAIN);
         }
         return new IngameStageScreen(this);
      }
      return new IntroScreen(this);
   }

}
