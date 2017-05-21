package tv.rocketbeans.supermafiosi.screens;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

import de.bitbrain.braingdx.assets.SharedAssetManager;
import de.bitbrain.braingdx.audio.AudioManager;
import de.bitbrain.braingdx.graphics.renderer.SpriteRenderer;

import de.bitbrain.braingdx.screens.AbstractScreen;
import de.bitbrain.braingdx.tweens.ActorTween;
import de.bitbrain.braingdx.tweens.GameObjectTween;
import de.bitbrain.braingdx.world.GameObject;
import tv.rocketbeans.supermafiosi.SuperMafiosiGame;
import tv.rocketbeans.supermafiosi.assets.Asset;
import tv.rocketbeans.supermafiosi.core.DialogManager;
import tv.rocketbeans.supermafiosi.graphics.BitmapFontBaker;
import tv.rocketbeans.supermafiosi.i18n.Bundle;
import tv.rocketbeans.supermafiosi.i18n.Message;
import tv.rocketbeans.supermafiosi.ui.DialogBox;

public class IntroScreen extends AbstractScreen<SuperMafiosiGame>
{

   public static final int IMAGE_OLD_DON = 1;
   public static final int IMAGE_LOGO = 2;

   public IntroScreen(SuperMafiosiGame game)
   {
      super(game);
   }

   @Override
   protected void onCreateStage(final Stage stage, int width, int height)
   {
      getLightingManager().setAmbientLight(new Color(0.1f, 0.1f, 0.2f, 0.2f));
      getLightingManager().addPointLight("left", new Vector2(350f, Gdx.graphics.getHeight() - 50f), 1000f, new Color(1f, 0.6f, 0.4f, 1f));
      getLightingManager().addPointLight("right", new Vector2(Gdx.graphics.getWidth() - 350f, Gdx.graphics.getHeight() - 50f), 1000f, new Color(1f, 0.6f, 0.4f, 1f));
      Music dying_don_music = SharedAssetManager.getInstance().get(Asset.Music.DYING_DON, Music.class);
      dying_don_music.setLooping(true);
      AudioManager.getInstance().fadeInMusic(dying_don_music, 1f);

      //bounceTitle(stage);
      //showIntoText3(stage);
      //showDonImage(stage);
      Tween.call(new TweenCallback()
      {
         private int tick = 0;
         private Label introlabel1 = null;
         private Label introlabel2 = null;
         private Label introlabel3 = null;
         private Label introlabel4 = null;
         private Label introlabel5 = null;
         private DialogManager dialogManager = new DialogManager();
         private GameObject donImageObject = null;
         private Sound EKG_BEEP = null;
         private Sound EKG_STALL = null;

         @Override
         public void onEvent(int i, BaseTween<?> bt)
         {

            if (tick == 1)
            {
               introlabel1 = showIntroText(stage, Bundle.translations.get(Message.INTRO_SPEAKER_1));
            }

            if (tick == 10)
            {
               textFadeOut(introlabel1);
               introlabel2 = showIntroText(stage, Bundle.translations.get(Message.INTRO_SPEAKER_2));
            }

            if (tick == 20)
            {
               textFadeOut(introlabel2);

               dialogManager.addDialog("Don", Message.INTRO_DON_1, Asset.Textures.OLDDON);
               dialogManager.addDialog("Don", Message.INTRO_DON_2, Asset.Textures.OLDDON);
               dialogManager.addDialog("Don", Message.INTRO_DON_3, Asset.Textures.OLDDON);
               dialogManager.addDialog("Don", Message.INTRO_DON_4, Asset.Textures.OLDDON);
               dialogManager.addDialog("Don", Message.INTRO_DON_5, Asset.Textures.OLDDON);
               dialogManager.addDialog("Don", Message.INTRO_DON_6, Asset.Textures.OLDDON);
               dialogManager.addDialog("Don", Message.INTRO_DON_7, Asset.Textures.OLDDON);
               dialogManager.addDialog("Don", Message.INTRO_DON_8, Asset.Textures.OLDDON);
               dialogManager.addDialog("Don", Message.INTRO_DON_9, Asset.Textures.OLDDON);
               dialogManager.addDialog("Cappo", Message.INTRO_KAPPO_1, Asset.Textures.KAPPO);
               dialogManager.addDialog("Cappo", Message.INTRO_KAPPO_2, Asset.Textures.KAPPO);
               donImageObject = showDonImage(stage, dialogManager);

               EKG_BEEP = Gdx.audio.newSound(Gdx.files.internal(Asset.Sounds.EKG_BEEP));
               EKG_BEEP.loop();

            }

            if (tick == 20)
            {
               dialogManager.nextDialog();
            }

            if (tick == 25)
            {

               dialogManager.nextDialog();
            }

            if (tick == 30)
            {

               dialogManager.nextDialog();
            }

            if (tick == 35)
            {

               dialogManager.nextDialog();
            }

            if (tick == 40)
            {

               dialogManager.nextDialog();
            }

            if (tick == 45)
            {

               dialogManager.nextDialog();
            }

            if (tick == 50)
            {
               dialogManager.nextDialog();
            }

            if (tick == 55)
            {

            }

            if (tick == 60)
            {
               EKG_BEEP.stop();
               EKG_STALL = Gdx.audio.newSound(Gdx.files.internal(Asset.Sounds.EKG_STALL));
               EKG_STALL.loop();
               dialogManager.nextDialog();
               dialogManager.nextDialog();
            }

            if (tick == 65)
            {

               ImageFadeOut(donImageObject);
               dialogManager.nextDialog();
            }

            if (tick == 70)
            {

               dialogManager.nextDialog();
            }

            if (tick == 75)
            {
               EKG_STALL.stop();
               dialogManager.nextDialog();
               introlabel3 = showIntroText(stage, Bundle.translations.get(Message.INTRO_SPEAKER_3));
            }

            if (tick == 85)
            {
               textFadeOut(introlabel3);
               introlabel4 = showIntroText(stage, Bundle.translations.get(Message.INTRO_SPEAKER_4));

            }

            if (tick == 95)
            {
               Music dying_don_music = SharedAssetManager.getInstance().get(Asset.Music.DYING_DON, Music.class);
               AudioManager.getInstance().fadeOutMusic(dying_don_music, 6f);
               Music menu_music_main = SharedAssetManager.getInstance().get(Asset.Music.MENU_CHAR_SELECT_MAIN, Music.class);
               menu_music_main.setLooping(true);
               AudioManager.getInstance().fadeInMusic(Asset.Music.MENU_CHAR_SELECT_MAIN, 6f);
               textFadeOut(introlabel4);
               introlabel5 = showIntroText(stage, Bundle.translations.get(Message.INTRO_SPEAKER_5));
            }

            if (tick == 98)
            {
               changeToMenue();
            }

            tick++;
         }
      }).repeat(Tween.INFINITY, 1f).start(getTweenManager());

      /**
       * Skip Intro by mr. anykey
       */
      stage.addListener(new InputListener()
      {
         public boolean keyDown(InputEvent event, int keycode)
         {
            Music menu_music_main = SharedAssetManager.getInstance().get(Asset.Music.MENU_CHAR_SELECT_MAIN, Music.class);
            menu_music_main.setLooping(true);
            AudioManager.getInstance().stopMusic(Asset.Music.DYING_DON);
            AudioManager.getInstance().playMusic(Asset.Music.MENU_CHAR_SELECT_MAIN);
            getGame().setScreen(new MenuScreen(getGame()));
            return false;
         }
      });

   }

   public void changeToMenue()
   {
      getScreenTransitions().out(new MenuScreen(getGame()), 8f);
   }

   private Label showIntroText(final Stage stage, String text)
   {
      final Label introLabel1 = createLabel(text);
      stage.addActor(introLabel1);
      textFadeIn(introLabel1);
      return introLabel1;
   }

   private GameObject showDonImage(final Stage stage, DialogManager dialogManager)
   {

      final GameObject donImageObject = getGameWorld().addObject();
      donImageObject.setType(IMAGE_OLD_DON);
      donImageObject.setDimensions(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
      donImageObject.setPosition(0, 0);
      donImageObject.getColor().a = 0f;
      getRenderManager().register(IMAGE_OLD_DON, new SpriteRenderer(Asset.Textures.KRANKENBETT));

      ImageFadeIn(donImageObject);

      DialogBox dialogBox = new DialogBox(dialogManager);
      dialogBox.setHeight(150f);
      dialogBox.setWidth(Gdx.graphics.getWidth());
      stage.addActor(dialogBox);

      return donImageObject;
   }

   private Label createLabel(String text)
   {
      Label.LabelStyle defaultlabelstyle = new Label.LabelStyle();
      defaultlabelstyle.font = BitmapFontBaker.bake(Asset.Fonts.UPHEAVTT, 42);
      defaultlabelstyle.fontColor = Color.WHITE;
      defaultlabelstyle.fontColor.a = 1f;

      final Label introLabel1 = new Label(text, defaultlabelstyle);
      introLabel1.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
      introLabel1.setWrap(true);
      introLabel1.setAlignment(Align.center);
      return introLabel1;
   }

   private void ImageFadeIn(GameObject gameobject)
   {
      Tween.to(gameobject, GameObjectTween.ALPHA, 5f).target(1f).ease(TweenEquations.easeNone)
              .start(getTweenManager());
   }

   private void ImageFadeOut(GameObject gameobject)
   {
      Tween.to(gameobject, GameObjectTween.ALPHA, 5f).target(0f).ease(TweenEquations.easeNone)
              .start(getTweenManager());
   }

   private void textFadeIn(Label label)
   {
      label.getColor().a = 0f;
      Tween.to(label, ActorTween.ALPHA, 5f).target(1f).ease(TweenEquations.easeNone)
              .start(getTweenManager());
   }

   private void textFadeOut(Label label)
   {
      Tween.to(label, ActorTween.ALPHA, 5f).target(0f).ease(TweenEquations.easeNone)
              .start(getTweenManager());
   }

}
