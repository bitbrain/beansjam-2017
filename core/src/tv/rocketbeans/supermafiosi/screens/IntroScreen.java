package tv.rocketbeans.supermafiosi.screens;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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
import tv.rocketbeans.supermafiosi.i18n.Message;
import tv.rocketbeans.supermafiosi.ui.DialogBox;

public class IntroScreen extends AbstractScreen<SuperMafiosiGame>
{

   public static final String introText1 = "Die ehrenwerte Milchstrassenfamilie hatte schon immer einen starken Don.";
   public static final String introText2 = "Dies ist sein letzter Tag in seinem ehrwürdigen Leben.";

   public static final int IMAGE = 1;

   public IntroScreen(SuperMafiosiGame game)
   {
      super(game);
   }

   @Override
   protected void onCreateStage(final Stage stage, int width, int height)
   {

      AudioManager.getInstance().fadeInMusic(Asset.Music.DYING_DON);

      showDonImage(stage);
      //showIntroText1(stage);

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

      /**
       * goto mainscreen after intro is done.
       */
      Tween.call(
              new TweenCallback()
              {
                 @Override
                 public void onEvent(int i, BaseTween<?> bt
                 )
                 {
                    changeToMenue();
                 }
              }
      ).delay(
              60).start(getTweenManager());

   }

   private void changeToMenue()
   {
      AudioManager.getInstance().stopMusic(Asset.Music.DYING_DON);
      AudioManager.getInstance().fadeInMusic(Asset.Music.MENU_CHAR_SELECT);
      getScreenTransitions().out(new MenuScreen(getGame()), 1);
   }

   private void showIntroText1(final Stage stage)
   {
      final Label introLabel1 = createLabel(introText1);
      introLabel1.setPosition(20, Gdx.graphics.getHeight() / 2);

      stage.addActor(introLabel1);

      textFadeIn(introLabel1);

      Tween.call(new TweenCallback()
      {
         @Override
         public void onEvent(int i, BaseTween<?> bt)
         {
            textFadeOut(introLabel1);
            showIntoText2(stage);
         }
      }).delay(10).start(getTweenManager());
   }

   private void showIntoText2(final Stage stage)
   {
      Tween.call(new TweenCallback()
      {

         @Override
         public void onEvent(int i, BaseTween<?> bt)
         {
            final Label introLabel2 = createLabel(introText2);
            introLabel2.setPosition(20, Gdx.graphics.getHeight() / 2);
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
         }
      }
      ).delay(5).start(getTweenManager());
   }

   private void showDonImage(final Stage stage)
   {

      final GameObject donImageObject = getGameWorld().addObject();
      donImageObject.setType(IMAGE);
      donImageObject.setDimensions(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
      donImageObject.setPosition(0, 0);
      donImageObject.getColor().a = 0f;
      getRenderManager().register(IMAGE, new SpriteRenderer(Asset.Textures.KRANKENBETT));

      ImageFadeIn(donImageObject);

      final DialogManager dialogManager = new DialogManager();

      DialogBox dialogBox = new DialogBox(dialogManager);
      dialogBox.setHeight(150f);
      dialogBox.setWidth(Gdx.graphics.getWidth());
      stage.addActor(dialogBox);
      dialogManager.addDialog(Message.INTRO_DON_1, Asset.Textures.OLDDON);
      dialogManager.addDialog(Message.INTRO_DON_2, Asset.Textures.OLDDON);
      dialogManager.addDialog(Message.INTRO_DON_3, Asset.Textures.OLDDON);
      dialogManager.addDialog(Message.INTRO_DON_4, Asset.Textures.OLDDON);
      dialogManager.addDialog(Message.INTRO_DON_5, Asset.Textures.OLDDON);
      dialogManager.addDialog(Message.INTRO_DON_6, Asset.Textures.OLDDON);
      dialogManager.addDialog(Message.INTRO_DON_7, Asset.Textures.OLDDON);
      dialogManager.addDialog(Message.INTRO_DON_8, Asset.Textures.OLDDON);
      dialogManager.addDialog(Message.INTRO_DON_9, Asset.Textures.OLDDON);
      dialogManager.addDialog(Message.INTRO_KAPPO_1, Asset.Textures.KAPPO);
      dialogManager.addDialog(Message.INTRO_KAPPO_2, Asset.Textures.KAPPO);
      
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
      defaultlabelstyle.font = BitmapFontBaker.bake(Asset.Fonts.EIGHT_BIT_WONDER, 18);
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
