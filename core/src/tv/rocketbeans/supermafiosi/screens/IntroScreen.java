package tv.rocketbeans.supermafiosi.screens;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import de.bitbrain.braingdx.assets.SharedAssetManager;
import de.bitbrain.braingdx.audio.AudioManager;
import de.bitbrain.braingdx.graphics.renderer.SpriteRenderer;

import de.bitbrain.braingdx.screens.AbstractScreen;
import de.bitbrain.braingdx.tweens.ActorTween;
import de.bitbrain.braingdx.tweens.GameObjectTween;
import de.bitbrain.braingdx.world.GameObject;
import tv.rocketbeans.supermafiosi.SuperMafiosiGame;
import tv.rocketbeans.supermafiosi.assets.Asset;
import tv.rocketbeans.supermafiosi.core.Dialog;
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

         @Override
         public void onEvent(int i, BaseTween<?> bt)
         {
           
            
            if (tick == 1)
            {
                introlabel1 = showIntroText1(stage);
            }
            
            if(tick == 10)
            {
               textFadeOut(introlabel1);
              introlabel2 = showIntoText2(stage);
            }
            
            if(tick == 15)
            {
               textFadeOut(introlabel2);
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
            changeToMenue();
            return false;
         }
      });

   }

   private void changeToMenue()
   {

      Music menu_music_main = SharedAssetManager.getInstance().get(Asset.Music.MENU_CHAR_SELECT_MAIN, Music.class);
      menu_music_main.setLooping(true);
      if (!menu_music_main.isPlaying())
      {
         AudioManager.getInstance().stopMusic(Asset.Music.DYING_DON);
         AudioManager.getInstance().fadeInMusic(Asset.Music.MENU_CHAR_SELECT_MAIN);
      }
      getScreenTransitions().out(new MenuScreen(getGame()), 1);
   }

   private Label showIntroText1(final Stage stage)
   {
      final Label introLabel1 = createLabel(Bundle.translations.get(Message.INTRO_SPEAKER_1));
      setLabelToCenter(introLabel1);

      stage.addActor(introLabel1);

      textFadeIn(introLabel1);

  
      return introLabel1;
   }

   private Label showIntoText2(final Stage stage)
   {
    
            final Label introLabel2 = createLabel(Bundle.translations.get(Message.INTRO_SPEAKER_2));
            setLabelToCenter(introLabel2);
            textFadeIn(introLabel2);
            stage.addActor(introLabel2);

            Tween.call(new TweenCallback()
            {

               @Override
               public void onEvent(int i, BaseTween<?> bt)
               {
                  textFadeOut(introLabel2);
                  showDonImage(stage);

               }
            }).delay(7).start(getTweenManager());
            return introLabel2;
   }

   private void setLabelToCenter(Label label)
   {
      label.setPosition(Gdx.graphics.getWidth() / 2 - label.getWidth() / 2, Gdx.graphics.getHeight() / 2);
   }

   private void showIntoText3(final Stage stage)
   {

      Tween.call(new TweenCallback()
      {

         @Override
         public void onEvent(int i, BaseTween<?> bt)
         {
            final Label introLabel3 = createLabel(Bundle.translations.get(Message.INTRO_SPEAKER_3));
            setLabelToCenter(introLabel3);
            textFadeIn(introLabel3);
            stage.addActor(introLabel3);

            Tween.call(new TweenCallback()
            {

               @Override
               public void onEvent(int i, BaseTween<?> bt)
               {
                  textFadeOut(introLabel3);
                  showIntoText4(stage);

                  Music dying_don_music = SharedAssetManager.getInstance().get(Asset.Music.DYING_DON, Music.class);
                  AudioManager.getInstance().fadeOutMusic(dying_don_music, 5f);

               }
            }).delay(7).start(getTweenManager());
         }
      }
      ).delay(5).start(getTweenManager());
   }

   private void showIntoText4(final Stage stage)
   {
      final Label introLabel4 = createLabel(Bundle.translations.get(Message.INTRO_SPEAKER_4));
      setLabelToCenter(introLabel4);
      textFadeIn(introLabel4);
      stage.addActor(introLabel4);

      Tween.call(new TweenCallback()
      {

         @Override
         public void onEvent(int i, BaseTween<?> bt)
         {
            textFadeOut(introLabel4);
            showIntoText5(stage);

            Music menu_music = SharedAssetManager.getInstance().get(Asset.Music.MENU_CHAR_SELECT_INTRO, Music.class);
            menu_music.setLooping(true);
            AudioManager.getInstance().fadeInMusic(menu_music, 1f);

         }
      }).delay(7).start(getTweenManager());
   }

   private void showIntoText5(final Stage stage)
   {
      final Label introLabel5 = createLabel(Bundle.translations.get(Message.INTRO_SPEAKER_5));
      setLabelToCenter(introLabel5);
      textFadeIn(introLabel5);
      stage.addActor(introLabel5);
      Tween.call(new TweenCallback()
      {

         @Override
         public void onEvent(int i, BaseTween<?> bt)
         {
            textFadeOut(introLabel5);
            bounceTitle(stage);
         }
      }).delay(7).start(getTweenManager());
   }

   private void bounceTitle(final Stage stage)
   {
      final GameObject logoGameObject = getGameWorld().addObject();
      logoGameObject.setType(IMAGE_LOGO);
      Texture logotexture = SharedAssetManager.getInstance().get(Asset.Textures.LOGO);
      logoGameObject.setDimensions(logotexture.getWidth(), logotexture.getHeight());
      logoGameObject.setPosition(Gdx.graphics.getWidth() / 2 - logoGameObject.getWidth() / 2, Gdx.graphics.getWidth());

      getRenderManager().register(IMAGE_LOGO, new SpriteRenderer(Asset.Textures.LOGO));
      Tween.to(logoGameObject, GameObjectTween.POS_Y, 5f).target(Gdx.graphics.getHeight() / 2 - logotexture.getHeight() / 2).ease(TweenEquations.easeOutBounce).start(getTweenManager());

   }

   private void showDonImage(final Stage stage)
   {

      final GameObject donImageObject = getGameWorld().addObject();
      donImageObject.setType(IMAGE_OLD_DON);
      donImageObject.setDimensions(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
      donImageObject.setPosition(0, 0);
      donImageObject.getColor().a = 0f;
      getRenderManager().register(IMAGE_OLD_DON, new SpriteRenderer(Asset.Textures.KRANKENBETT));

      ImageFadeIn(donImageObject);

      final DialogManager dialogManager = new DialogManager();

      DialogBox dialogBox = new DialogBox(dialogManager);
      dialogBox.setHeight(150f);
      dialogBox.setWidth(Gdx.graphics.getWidth());
      stage.addActor(dialogBox);
      dialogManager.addDialog("Title", Message.INTRO_DON_1, Asset.Textures.OLDDON);
      dialogManager.addDialog("Title", Message.INTRO_DON_2, Asset.Textures.OLDDON);
      dialogManager.addDialog("Title", Message.INTRO_DON_3, Asset.Textures.OLDDON);
      dialogManager.addDialog("Title", Message.INTRO_DON_4, Asset.Textures.OLDDON);
      dialogManager.addDialog("Title", Message.INTRO_DON_5, Asset.Textures.OLDDON);
      dialogManager.addDialog("Title", Message.INTRO_DON_6, Asset.Textures.OLDDON);
      dialogManager.addDialog("Title", Message.INTRO_DON_7, Asset.Textures.OLDDON);
      dialogManager.addDialog("Title", Message.INTRO_DON_8, Asset.Textures.OLDDON);
      dialogManager.addDialog("Title", Message.INTRO_DON_9, Asset.Textures.OLDDON);
      dialogManager.addDialog("Title", Message.INTRO_KAPPO_1, Asset.Textures.KAPPO);
      dialogManager.addDialog("Title", Message.INTRO_KAPPO_2, Asset.Textures.KAPPO);

      Tween.call(new TweenCallback()
      {
         private int counter = 1;

         @Override
         public void onEvent(int i, BaseTween<?> bt)
         {
            dialogManager.nextDialog();

            counter++;

            if (counter == 10)
            {
               ImageFadeOut(donImageObject);
            }

            if (counter == 12)
            {
               showIntoText3(stage);
            }

            if (counter == 24)
            {
               changeToMenue();
            }

         }
      }).repeat(13, 4).start(getTweenManager());

   }

   private Label createLabel(String text)
   {
      Label.LabelStyle defaultlabelstyle = new Label.LabelStyle();
      defaultlabelstyle.font = BitmapFontBaker.bake(Asset.Fonts.UPHEAVTT, 18);
      defaultlabelstyle.fontColor = Color.WHITE;
      defaultlabelstyle.fontColor.a = 1f;

      final Label introLabel1 = new Label(text, defaultlabelstyle);
      introLabel1.setPosition(20, Gdx.graphics.getHeight() / 2);
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
