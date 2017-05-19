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

import de.bitbrain.braingdx.screens.AbstractScreen;
import de.bitbrain.braingdx.tweens.ActorTween;
import tv.rocketbeans.supermafiosi.SuperMafiosiGame;
import tv.rocketbeans.supermafiosi.assets.Asset;
import tv.rocketbeans.supermafiosi.graphics.BitmapFontBaker;

public class IntroScreen extends AbstractScreen<SuperMafiosiGame>
{

   public static final String introText1 = "Die ehrenwerte Milchstrassenfamilie hatte schon immer einen starken Don.";
   public static final String introText2 = "Dies ist sein letzter Tag in seinem ehrwürdigen Leben.";

   public IntroScreen(SuperMafiosiGame game)
   {
      super(game);
   }

   @Override
   protected void onCreateStage(final Stage stage, int width, int height)
   {

      showIntroText1(stage);

      /**
       * Skip Intro by mr. anykey
       */
      stage.addListener(new InputListener()
      {
         public boolean keyDown(InputEvent event, int keycode)
         {
             getScreenTransitions().out(new MenuScreen(getGame()), 0);
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
                    getScreenTransitions().out(new MenuScreen(getGame()), 1);
                 }
              }
      ).delay(
              30).start(getTweenManager());

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
                  showDonImage();

               }
            }).delay(7).start(getTweenManager());
         }
      }
      ).delay(5).start(getTweenManager());
   }

   private void showDonImage()
   {

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
