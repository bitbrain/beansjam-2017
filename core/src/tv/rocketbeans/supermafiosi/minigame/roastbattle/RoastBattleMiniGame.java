package tv.rocketbeans.supermafiosi.minigame.roastbattle;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.badlogic.gdx.graphics.Color;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Vector2;
import de.bitbrain.braingdx.GameContext;
import de.bitbrain.braingdx.assets.SharedAssetManager;
import de.bitbrain.braingdx.audio.AudioManager;
import de.bitbrain.braingdx.graphics.renderer.SpriteRenderer;
import de.bitbrain.braingdx.tweens.GameObjectTween;
import de.bitbrain.braingdx.world.GameObject;
import tv.rocketbeans.supermafiosi.Colors;
import tv.rocketbeans.supermafiosi.Config;
import tv.rocketbeans.supermafiosi.assets.Asset;
import tv.rocketbeans.supermafiosi.assets.AssetUtils;
import tv.rocketbeans.supermafiosi.core.Dialog;
import tv.rocketbeans.supermafiosi.core.DialogManager;
import tv.rocketbeans.supermafiosi.core.DialogManager.DialogManagerListener;
import tv.rocketbeans.supermafiosi.i18n.Bundle;
import tv.rocketbeans.supermafiosi.i18n.Message;
import tv.rocketbeans.supermafiosi.minigame.AbstractMiniGame;
import tv.rocketbeans.supermafiosi.minigame.MiniGameResult;
import tv.rocketbeans.supermafiosi.minigame.roastbattle.Roast.Type;
import tv.rocketbeans.supermafiosi.ui.MultiSelect;
import tv.rocketbeans.supermafiosi.ui.MultiSelect.MultiSelectListener;
import tv.rocketbeans.supermafiosi.core.Mafiosi;
import tv.rocketbeans.supermafiosi.core.MafiosiGameContext;
import static tv.rocketbeans.supermafiosi.screens.IntroScreen.IMAGE_OLD_DON;

public class RoastBattleMiniGame extends AbstractMiniGame
{
   
   private static final Map<Roast.Type, Color> ROAST_COLORS = new HashMap<Roast.Type, Color>();
   
   static
   {
      ROAST_COLORS.put(Roast.Type.PAPER, Colors.PAPER_COLOR);
      ROAST_COLORS.put(Roast.Type.ROCK, Colors.ROCK_COLOR);
      ROAST_COLORS.put(Roast.Type.SCISSORS, Colors.SCISSORS_COLOR);
   }
   
   private final GameContext gameContext;
   private final MafiosiGameContext mafiosiGameContext;
   
   private boolean playersTurn;
   
   private MultiSelect<Roast> select;
   
   private RoastPool roastPool = new RoastPool();
   
   private Map<Mafiosi, Roast> firedRoasts = new HashMap<Mafiosi, Roast>();
   
   private final MultiSelectListener<Roast> multiSelectListener = new MultiSelectListener<Roast>()
   {
      
      @Override
      public void onSelect(Roast roast)
      {
         if (playersTurn)
         {
            System.out.println("Player has selected: " + roast);
            Mafiosi player = mafiosiGameContext.getPlayerMafiosi();
            fireRoast(player, roast);
            mafiosiGameContext.getDialogManager().nextDialog();
            if (select != null)
            {
               gameContext.getStage().getActors().removeValue(select, true);
            }
            Tween.call(new TweenCallback()
            {
               @Override
               public void onEvent(int arg0, BaseTween<?> arg1)
               {
                  mafiosiGameContext.getDialogManager().nextDialog();
                  notifyComplete(evaluateResult());
               }
            }).delay(3).start(gameContext.getTweenManager());
         }
      }
      
   };
   
   private final DialogManagerListener afterAllDialogsListener = new DialogManagerListener()
   {
      
      @Override
      public void afterLastDialog()
      {
         if (!playersTurn)
         {
            System.out.println("Players turn!");
            playersTurn = true;
            // 2. add UI for selection
            Map<Roast, String> selectData = new HashMap<Roast, String>();
            // Scissor
            Roast randomScissor = roastPool.getRandomRoast(Type.SCISSORS);
            selectData.put(randomScissor, Bundle.translations.get(randomScissor.getMessageKey()));
            // Rock
            Roast randomRock = roastPool.getRandomRoast(Type.ROCK);
            selectData.put(randomRock, Bundle.translations.get(randomRock.getMessageKey()));
            // Paper
            Roast randomPaper = roastPool.getRandomRoast(Type.PAPER);
            selectData.put(randomPaper, Bundle.translations.get(randomPaper.getMessageKey()));
            select = new MultiSelect<Roast>(Bundle.translations.get(Message.MINIGAME_ROAST_TITLE), multiSelectListener, selectData, new RoastIconProvider());
            select.setFillParent(true);
            gameContext.getStage().addActor(select);
         }
      }
      
      @Override
      public void onDialog(Dialog dialog)
      {
         
      }
   };
   
   public RoastBattleMiniGame(GameContext gameContext, MafiosiGameContext mafiosiGameContext)
   {
      this.gameContext = gameContext;
      this.mafiosiGameContext = mafiosiGameContext;
   }
   
   @Override
   public void initialise()
   {
      final Music select_main = SharedAssetManager.getInstance().get(Asset.Music.MENU_CHAR_SELECT_MAIN, Music.class);
      AudioManager.getInstance().fadeOutMusic(select_main, 4f);
      
      final Music minigamemusic_intro = SharedAssetManager.getInstance().get(Asset.Music.MENU_MINIGAME_ROASTME_MUSIC_INTRO, Music.class);
      
      minigamemusic_intro.setOnCompletionListener(new Music.OnCompletionListener()
      {
         
         @Override
         public void onCompletion(Music music)
         {
            final Music minigamemusic_main = SharedAssetManager.getInstance().get(Asset.Music.MENU_MINIGAME_ROASTME_MUSIC_MAIN, Music.class);
            minigamemusic_main.setLooping(true);
            minigamemusic_main.play();
         }
      });
      minigamemusic_intro.play();
      
      GameObject minigameLogoObject = this.gameContext.getGameWorld().addObject();
      minigameLogoObject.setType("RoastMeTitle");
      Vector2 logodimension = AssetUtils.getDimensionOfTexture(Asset.Textures.LOGO);
      minigameLogoObject.setDimensions(700, 500);
      minigameLogoObject.setPosition(Gdx.graphics.getWidth() / 2 - minigameLogoObject.getWidth() / 2, Gdx.graphics.getHeight());
      
      this.gameContext.getRenderManager().register("RoastMeTitle", new SpriteRenderer(Asset.Textures.LOGO));
      
      Tween.to(minigameLogoObject, GameObjectTween.POS_Y, 10f).target(Gdx.graphics.getHeight() / 2 - minigameLogoObject.getHeight() / 2).ease(TweenEquations.easeNone).start(this.gameContext.getTweenManager());
      
      
      Tween.to(minigameLogoObject, GameObjectTween.POS_Y, 12f).delay(10f).target(Gdx.graphics.getHeight()).ease(TweenEquations.easeNone).start(this.gameContext.getTweenManager());
      
      
      mafiosiGameContext.getDialogManager().addListener(afterAllDialogsListener);
      // 1. for each player, add a dialog with a special roast punch line
      initialiseOtherMafiosis();
   }
   
   @Override
   public void cleanup()
   {
      mafiosiGameContext.getDialogManager().removeListener(afterAllDialogsListener);
      firedRoasts.clear();
   }
   
   @Override
   public void update(float delta)
   {
      
   }

   // Evaluate the game result by aggregating a mafiosis choice against other choices
   private MiniGameResult evaluateResult()
   {
      Map<String, Integer> playerResults = new HashMap<String, Integer>();
      // If you beat all others you get full points!
      final int MULTIPLIER = Config.MAXIMUM_POINTS / (firedRoasts.size() - 1);
      for (Entry<Mafiosi, Roast> roastEntry : firedRoasts.entrySet())
      {
         int score = 0;
         Mafiosi mafiosi = roastEntry.getKey();
         Roast roast = roastEntry.getValue();
         for (Entry<Mafiosi, Roast> otherRoasts : firedRoasts.entrySet())
         {
            if (otherRoasts.getKey() != mafiosi)
            {
               // Compare a players roast against all others to calculate a score
               score += roast.compareTo(otherRoasts.getValue()) * Math.max(MULTIPLIER, 1);
               score = Math.max(score, 0);
            }
         }
         playerResults.put(mafiosi.getName(), score);
      }
      System.out.println(firedRoasts);
      return new MiniGameResult(firedRoasts.size(), playerResults);
   }
   
   private void initialiseOtherMafiosis()
   {
      for (Mafiosi mafiosi : mafiosiGameContext.getCandidates())
      {
         if (mafiosi != mafiosiGameContext.getPlayerMafiosi())
         {
            Roast roast = roastPool.getRandomRoast();
            fireRoast(mafiosi, roast);
         }
      }
   }
   
   private void fireRoast(Mafiosi player, Roast roast)
   {
      firedRoasts.put(player, roast);
      mafiosiGameContext.getDialogManager().addDialog(player.getName(), roast.getMessageKey(), player.getAvatarId());
   }
   
}
