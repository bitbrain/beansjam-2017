package tv.rocketbeans.supermafiosi.minigame.roulette;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import de.bitbrain.braingdx.GameContext;
import de.bitbrain.braingdx.assets.SharedAssetManager;
import de.bitbrain.braingdx.audio.AudioManager;
import de.bitbrain.braingdx.graphics.renderer.SpriteRenderer;
import de.bitbrain.braingdx.tweens.ActorTween;
import de.bitbrain.braingdx.tweens.GameObjectTween;
import de.bitbrain.braingdx.tweens.SharedTweenManager;
import de.bitbrain.braingdx.world.GameObject;
import tv.rocketbeans.supermafiosi.SuperMafiosiGame;
import tv.rocketbeans.supermafiosi.assets.Asset;
import tv.rocketbeans.supermafiosi.core.Dialog;
import tv.rocketbeans.supermafiosi.core.DialogManager.DialogManagerListener;
import tv.rocketbeans.supermafiosi.core.Mafiosi;
import tv.rocketbeans.supermafiosi.core.MafiosiGameContext;
import tv.rocketbeans.supermafiosi.i18n.Message;
import tv.rocketbeans.supermafiosi.minigame.AbstractMiniGame;
import tv.rocketbeans.supermafiosi.screens.CongratulationsScreen;
import tv.rocketbeans.supermafiosi.screens.GameOverScreen;
import tv.rocketbeans.supermafiosi.ui.Styles;
import tv.rocketbeans.supermafiosi.ui.Toast;

/**
 * In this final mini game the players need to pull the trigger in round robin. The chances are
 * higher for a shot if more bullets are loaded.
 */
public class RouletteMiniGame extends AbstractMiniGame
{

   private final MafiosiGameContext context;

   private final GameContext gameContext;

   private final List<Mafiosi> deadMafiosis = new ArrayList<Mafiosi>();

   private List<Mafiosi> remainingCandidates;

   private Mafiosi mafiosi;

   private boolean waitingForPlayerConfirmation;

   private GameObject rouletteBackground;

   private GameObject ronaldtrumpfroulette;
   private GameObject tronroulette;
   private GameObject sanchezroulette;

   private GameObject ronaldtrumpfwafferoulette;
   private GameObject tronwafferoulette;
   private GameObject sanchezwafferoulette;

   private DialogManagerListener dialogListener = new DialogManagerListener()
   {

      @Override
      public void afterLastDialog()
      {
         System.out.println("After last dialog!");
         if (waitingForPlayerConfirmation)
         {
            return;
         }
         Tween.call(new TweenCallback()
         {
            @Override
            public void onEvent(int arg0, BaseTween<?> arg1)
            {
               pullTrigger();
            }
         }).delay(2f).start(SharedTweenManager.getInstance());
      }

      @Override
      public void onDialog(Dialog dialog)
      {

      }
   };

   private Label confirmationLabel;
   private final SuperMafiosiGame game;

   public RouletteMiniGame(SuperMafiosiGame game, MafiosiGameContext context, GameContext gameContext)
   {
      this.context = context;
      this.gameContext = gameContext;
      this.game = game;
   }

   @Override
   public void initialise()
   {
      System.out.println("Initialise...");
      context.getDialogManager().addListener(dialogListener);
      remainingCandidates = new ArrayList<Mafiosi>(context.getCandidates());
      AudioManager.getInstance().fadeOutMusic(Asset.Music.MENU_CHAR_SELECT_MAIN, 4f);
      AudioManager.getInstance().fadeOutMusic(Asset.Music.AUDIANCE_HAPPY, 8f);
      AudioManager.getInstance().fadeInMusic(Asset.Music.MENU_MINIGAME_ROULETTE_MUSIC_MAIN);
      nextPlayer();

   }

   public void setRouletteAnimation(Mafiosi mafiosi)
   {
      if (rouletteBackground == null)
      {
         rouletteBackground = gameContext.getGameWorld().addObject();
         rouletteBackground.setType("RouletteBG");
         rouletteBackground.setPosition(0, 0);
         rouletteBackground.setDimensions(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
         rouletteBackground.getColor().a = 0f;
         gameContext.getRenderManager().register("RouletteBG", new SpriteRenderer(Asset.Textures.ROULETTE_BG));

         ronaldtrumpfroulette = createRouletteHeadAnimation(Asset.Textures.RONALD_ROULETTE, "RonaldTrumpfRoulette");
         tronroulette = createRouletteHeadAnimation(Asset.Textures.TRON_ROULETTE, "LerryRoulette");
         sanchezroulette = createRouletteHeadAnimation(Asset.Textures.SANCHEZ_ROULETTE, "TronRoulette");

         ronaldtrumpfwafferoulette = createRouletteWaffeAnimation(Asset.Textures.RONALD_ROULETTE_WEAPON, "Ronaldtrumpfwafferoulette");
         tronwafferoulette = createRouletteWaffeAnimation(Asset.Textures.TRON_ROULETTE_WEAPON, "tronwafferoulette");
         sanchezwafferoulette = createRouletteWaffeAnimation(Asset.Textures.SANCHEZ_ROULETTE_WEAPON, "sanchezwafferoulette");
      }

      if (mafiosi == null)
      {
         rouletteBackground.getColor().a = 0;

         ronaldtrumpfroulette.getColor().a = 0;
         tronroulette.getColor().a = 0;
         sanchezroulette.getColor().a = 0;

         ronaldtrumpfwafferoulette.getColor().a = 0;
         tronwafferoulette.getColor().a = 0;
         sanchezwafferoulette.getColor().a = 0;
         return;
      }

      if (mafiosi.getName().contains("Trumpf"))
      {
         rouletteBackground.getColor().a = 1f;

         ronaldtrumpfroulette.getColor().a = 1f;
         tronroulette.getColor().a = 0;
         sanchezroulette.getColor().a = 0;

         ronaldtrumpfwafferoulette.getColor().a = 1f;
         tronwafferoulette.getColor().a = 0;
         sanchezwafferoulette.getColor().a = 0;
      }

      if (mafiosi.getName().contains("Jawolta"))
      {
         rouletteBackground.getColor().a = 1f;

         ronaldtrumpfroulette.getColor().a = 0;
         tronroulette.getColor().a = 1f;
         sanchezroulette.getColor().a = 0;

         ronaldtrumpfwafferoulette.getColor().a = 0;
         tronwafferoulette.getColor().a = 1f;
         sanchezwafferoulette.getColor().a = 0;
      }

      if (mafiosi.getName().contains("Sanchez"))
      {
         rouletteBackground.getColor().a = 1f;

         ronaldtrumpfroulette.getColor().a = 0;
         tronroulette.getColor().a = 0;
         sanchezroulette.getColor().a = 1f;

         ronaldtrumpfwafferoulette.getColor().a = 0;
         tronwafferoulette.getColor().a = 0;
         sanchezwafferoulette.getColor().a = 1f;
      }

   }

   public GameObject createRouletteHeadAnimation(String path, String type)
   {
      GameObject o = gameContext.getGameWorld().addObject();
      o.setType(type);
      o.setPosition(600, 250);
      o.setDimensions(Gdx.graphics.getWidth() / 1.5f, Gdx.graphics.getHeight());
      o.getColor().a = 0f;
      gameContext.getRenderManager().register(type, new SpriteRenderer(path));
      return o;
   }

   public GameObject createRouletteWaffeAnimation(String path, String type)
   {
      GameObject o = gameContext.getGameWorld().addObject();
      o.setType(type);
      o.setPosition(200, 250);
      o.setDimensions(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);
      o.getColor().a = 0f;
      gameContext.getRenderManager().register(type, new SpriteRenderer(path));

      Tween.to(o, GameObjectTween.POS_Y, 0.75f).target(220).repeatYoyo(Tween.INFINITY, 0.75f).ease(TweenEquations.easeNone).start(gameContext.getTweenManager());

      return o;
   }

   @Override
   public void cleanup()
   {
      context.getDialogManager().removeListener(dialogListener);
   }

   @Override
   public void update(float delta)
   {
      if (waitingForPlayerConfirmation)
      {
         if (Gdx.input.isTouched() || Gdx.input.isKeyPressed(Keys.ANY_KEY))
         {
            System.out.println("Player decided to pull the trigger!");
            waitingForPlayerConfirmation = false;
            gameContext.getTweenManager().killTarget(confirmationLabel);
            gameContext.getStage().getActors().removeValue(confirmationLabel, true);
            context.getDialogManager().addDialog(mafiosi.getName(), Message.MAINMENU_BUTTON_NEWGAME, mafiosi.getAvatarId());
            context.getDialogManager().nextDialog();
         }
      }
   }

   private void nextPlayer()
   {
      System.out.println("=== NEW ROUND!!! ===");
      // Initialise first candidate to pull the trigger
      mafiosi = remainingCandidates.remove(0);

      setRouletteAnimation(mafiosi);

      if (mafiosi.equals(context.getPlayerMafiosi()))
      {
         Tween.call(new TweenCallback()
         {
            @Override
            public void onEvent(int arg0, BaseTween<?> arg1)
            {
               System.out.println(mafiosi.getName() + " turn (PLAYER)");

               confirmationLabel = new Label("PULL THE TRIGGER!!", Styles.LABEL_PULL_THE_TRIGGER);
               confirmationLabel.setWidth(Gdx.graphics.getWidth());
               confirmationLabel.setHeight(Gdx.graphics.getHeight());
               confirmationLabel.setAlignment(Align.center);
               Tween.to(confirmationLabel, ActorTween.SCALE, 0.5f)
                       .target(1.3f)
                       .ease(TweenEquations.easeInCubic)
                       .repeatYoyo(Tween.INFINITY, 0f)
                       .start(gameContext.getTweenManager());
               gameContext.getStage().addActor(confirmationLabel);
               waitingForPlayerConfirmation = true;
            }
         }).delay(4f).start(SharedTweenManager.getInstance());

      }
      else
      {
         System.out.println(mafiosi.getName() + " turn");
         context.getDialogManager().addDialog(mafiosi.getName(), Message.MAINMENU_BUTTON_NEWGAME, mafiosi.getAvatarId());
         context.getDialogManager().nextDialog();
      }
   }

   private void pullTrigger()
   {
      System.out.println("Pull the trigger!");
      int numberOfBullets = 1; //context.getNumberOfBullets(mafiosi.getName());
      boolean mafiosiWillBeDeadForSure = Math.random() > (float) numberOfBullets / (float) context.getNumberOfBulletSlots();
      if (mafiosiWillBeDeadForSure)
      {
         System.out.println("SHOOT!");
         Toast.getInstance().doToast("SHOT!");
         shootCurrentPlayer();
         SharedAssetManager.getInstance().get(Asset.Sounds.TRIGGER_BULLET, Sound.class).play(1f, (float) (0.7f + Math.random() * 0.5f), 0f);
      }
      else
      {
         SharedAssetManager.getInstance().get(Asset.Sounds.TRIGGER_NO_BULLET, Sound.class).play(1f, (float) (0.7f + Math.random() * 0.5f), 0f);
         Toast.getInstance().doToast("MISS!");
         System.out.println("MISS!");
         remainingCandidates.add(mafiosi);
         context.getDialogManager().addDialog(mafiosi.getName(), Message.MAINMENU_BUTTON_EXITGAME, mafiosi.getAvatarId());
         nextPlayer();
      }
   }

   private void shootCurrentPlayer()
   {
      if (mafiosi.equals(context.getPlayerMafiosi()))
      {
         System.out.println("Game over!");
         AudioManager.getInstance().fadeOutMusic(Asset.Music.MENU_MINIGAME_ROULETTE_MUSIC_MAIN);
         gameContext.getScreenTransitions().out(new GameOverScreen(game), 2.5f);
      }
      else
      {
         deadMafiosis.add(mafiosi);
         System.out.println(mafiosi + " shot.");
         if (deadMafiosis.size() >= context.getCandidates().size() - 1)
         {
            AudioManager.getInstance().fadeOutMusic(Asset.Music.MENU_MINIGAME_ROULETTE_MUSIC_MAIN);
            System.out.println("You won!");
            gameContext.getScreenTransitions().out(new CongratulationsScreen(game), 1.0f);
         }
         else
         {
            nextPlayer();
         }
      }
   }

}
