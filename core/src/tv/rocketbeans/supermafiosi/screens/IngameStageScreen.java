package tv.rocketbeans.supermafiosi.screens;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import box2dLight.ConeLight;
import com.badlogic.gdx.audio.Music;
import de.bitbrain.braingdx.assets.SharedAssetManager;
import de.bitbrain.braingdx.audio.AudioManager;
import de.bitbrain.braingdx.graphics.pipeline.RenderLayer;
import de.bitbrain.braingdx.graphics.pipeline.layers.RenderPipeIds;
import de.bitbrain.braingdx.graphics.renderer.SpriteRenderer;
import de.bitbrain.braingdx.postprocessing.effects.Vignette;
import de.bitbrain.braingdx.screens.AbstractScreen;
import de.bitbrain.braingdx.world.GameObject;
import java.awt.geom.Point2D;
import tv.rocketbeans.supermafiosi.Colors;
import tv.rocketbeans.supermafiosi.SuperMafiosiGame;
import tv.rocketbeans.supermafiosi.assets.Asset;
import tv.rocketbeans.supermafiosi.assets.AssetUtils;
import tv.rocketbeans.supermafiosi.core.Dialog;
import tv.rocketbeans.supermafiosi.core.DialogManager;
import tv.rocketbeans.supermafiosi.core.Mafiosi;
import tv.rocketbeans.supermafiosi.core.MafiosiGameContext;
import tv.rocketbeans.supermafiosi.core.jury.JuryManager;
import tv.rocketbeans.supermafiosi.core.jury.UtilIsometric;
import tv.rocketbeans.supermafiosi.i18n.Message;
import tv.rocketbeans.supermafiosi.minigame.MiniGame;
import tv.rocketbeans.supermafiosi.minigame.MiniGameManager;
import tv.rocketbeans.supermafiosi.minigame.roastbattle.RoastBattleMiniGame;
import tv.rocketbeans.supermafiosi.minigame.roulette.RouletteMiniGame;
import tv.rocketbeans.supermafiosi.tweens.ConeLightTween;
import tv.rocketbeans.supermafiosi.ui.DialogBox;
import tv.rocketbeans.supermafiosi.ui.Toast;

public class IngameStageScreen extends AbstractScreen<SuperMafiosiGame>
{

   private DialogManager dialogManager;
   private MafiosiGameContext context;
   private Mafiosi player;
   private Map<String, Mafiosi> mafiosiMap = new HashMap<String, Mafiosi>();
   private MiniGameManager miniGameManager = null;

   static
   {
      Tween.registerAccessor(ConeLight.class, new ConeLightTween());
   }

   public IngameStageScreen(SuperMafiosiGame game)
   {
      super(game);
   }

   @Override
   protected void onCreateStage(Stage stage, int width, int height)
   {
      Toast.getInstance().init(stage);

      dialogManager = new DialogManager();
      setBackgroundColor(Colors.BACKGROUND);
      getInput().addProcessor(dialogManager);
      setupBackground();
      setupAllMafiosis();
      setupUI(stage);
      setupLighting();
      setupShaders();
      setupMiniGameFramework();
      JuryManager.getInstance().initRender(this);
      JuryManager.getInstance().setMinigameManager(miniGameManager);
      JuryManager.getInstance().setJurySceneVisible(false);

      final Music audiance_happy = SharedAssetManager.getInstance().get(Asset.Music.AUDIANCE_HAPPY, Music.class);
      audiance_happy.setLooping(true);
      AudioManager.getInstance().playMusic(Asset.Music.AUDIANCE_HAPPY);

      getBehaviorManager().apply(miniGameManager);
      
      //miniGameManager.triggerNextMiniGame();

      dialogManager.addDialog("Moderator", "dialog.moderator.start1", Asset.Textures.AVATAR_MODERATOR);
      dialogManager.addDialog("Moderator", "dialog.moderator.start2", Asset.Textures.AVATAR_MODERATOR);
      dialogManager.addDialog("Moderator", "dialog.moderator.start3", Asset.Textures.AVATAR_MODERATOR);
      dialogManager.addDialog("Moderator", "dialog.moderator.start4", Asset.Textures.AVATAR_MODERATOR);
      dialogManager.addDialog("Lerry Sanchez", "dialog.lerry.greeting", Asset.Textures.KAPPO);

      dialogManager.addDialog("Moderator", "dialog.moderator.start5", Asset.Textures.AVATAR_MODERATOR);
      dialogManager.addDialog("Moderator", "dialog.moderator.start6", Asset.Textures.AVATAR_MODERATOR);
      dialogManager.addDialog("Tron Jawolta", "dialog.tron.greeting", Asset.Textures.AVATAR_TRON_JAWOLTA);
      dialogManager.addDialog("Moderator", "dialog.moderator.start7", Asset.Textures.AVATAR_MODERATOR);
      dialogManager.addDialog("Moderator", "dialog.moderator.start8", Asset.Textures.AVATAR_MODERATOR);
      dialogManager.addDialog("Ronald Trumpf", "dialog.ronald.greeting", Asset.Textures.AVATAR_RONALD_TRUMPF);

      dialogManager.addDialog("Moderator", "dialog.moderator.introgame1", Asset.Textures.AVATAR_MODERATOR);
      dialogManager.addListener(new DialogManager.DialogManagerListener()
      {

         @Override
         public void afterLastDialog()
         {
         }

         @Override
         public void onDialog(Dialog dialog)
         {
            if (dialog.getTextId().equals("dialog.moderator.introgame2"))
            {
               JuryManager.getInstance().setJurySceneVisible(true);
            }

         }
      });

      dialogManager.addDialog("Moderator", "dialog.moderator.introgame2", Asset.Textures.AVATAR_MODERATOR);
      dialogManager.addDialog("Eduard Laser", "dialog.eduardlaser.introgame", Asset.Textures.AVATAR_EDUARD_LASER);

      dialogManager.addListener(new DialogManager.DialogManagerListener()
      {

         @Override
         public void afterLastDialog()
         {
         }

         @Override
         public void onDialog(Dialog dialog)
         {
            if (dialog.getTextId().equals("dialog.moderator.introgame4"))
            {
               JuryManager.getInstance().setJurySceneVisible(false);
            }

         }
      });

      dialogManager.addDialog("Moderator", "dialog.moderator.introgame4", Asset.Textures.AVATAR_MODERATOR);
      dialogManager.addDialog("Moderator", "dialog.moderator.introgame5", Asset.Textures.AVATAR_MODERATOR);

      dialogManager.addDialog("Moderator", "dialog.moderator.introgame6", Asset.Textures.AVATAR_MODERATOR);

      dialogManager.addListener(new DialogManager.DialogManagerListener()
      {

         @Override
         public void afterLastDialog()
         {
            AudioManager.getInstance().fadeOutMusic(audiance_happy, 4f);
            System.out.println("stop executed");
            audiance_happy.stop();
            miniGameManager.triggerNextMiniGame();
         }

         @Override
         public void onDialog(Dialog dialog)
         {
         }
      });

      dialogManager.nextDialog();
      
  //    miniGameManager.triggerNextMiniGame();
   }

   private void setupBackground()
   {
      getRenderPipeline().set(RenderPipeIds.BACKGROUND, new RenderLayer()
      {

         @Override
         public void render(Batch batch, float delta)
         {
            Texture texture = SharedAssetManager.getInstance().get(Asset.Textures.STAGE, Texture.class);
            batch.begin();
            batch.draw(texture, 0f, 0f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            batch.end();
         }

         @Override
         public void beforeRender()
         {
            // XXX Auto-generated method stub

         }
      });
   }

   private void setupLighting()
   {
      getLightingManager().setConfig(getLightingManager().new LightingConfig().blur(true).diffuseLighting(false));
      getLightingManager().setAmbientLight(new Color(0.3f, 0.3f, 0.4f, 0.4f));
      float lightingOffset = 50f;
      ConeLight left = getLightingManager().addConeLight("leftLight", -lightingOffset, Gdx.graphics.getHeight() + lightingOffset, 1000f, -45f, 20f, Color.RED);
      ConeLight right = getLightingManager().addConeLight("rightLight", Gdx.graphics.getWidth() + lightingOffset, Gdx.graphics.getHeight() + lightingOffset, 1000f, -135f, 20f, Color.BLUE);
      ConeLight top = getLightingManager().addConeLight("topLight", Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() + lightingOffset, 500f, -110f, 30f, Color.LIME);
      Tween.to(left, ConeLightTween.DIRECTION, 1.5f + (float) Math.random())
              .target(10f)
              .ease(TweenEquations.easeInOutCubic)
              .repeatYoyo(Tween.INFINITY, 0f)
              .start(getTweenManager());
      Tween.to(right, ConeLightTween.DIRECTION, 1.5f + (float) Math.random())
              .target(-170f)
              .ease(TweenEquations.easeInOutCubic)
              .repeatYoyo(Tween.INFINITY, 0f)
              .start(getTweenManager());
      Tween.to(top, ConeLightTween.DIRECTION, 1.5f + (float) Math.random())
              .target(-80f)
              .ease(TweenEquations.easeInOutCubic)
              .repeatYoyo(Tween.INFINITY, 0f)
              .start(getTweenManager());
   }

   private void setupAllMafiosis()
   {

      // Contestants
//		setupMafiosis(300f, 300f, 
//				new Mafiosi("Lerry Sanchez", Message.DIALOG_LERRY_GREETING, 47, Asset.Textures.DUMMY, Asset.Textures.AVATAR_01),
//				new Mafiosi("Eduard Laser", Message.DIALOG_EDUARDLASER_GREETING, 38, Asset.Textures.DUMMY, Asset.Textures.AVATAR_01),
//				new Mafiosi("Stephano Caprese", Message.DIALOG_STEPHANO_GREETING, 28, Asset.Textures.DUMMY, Asset.Textures.AVATAR_01)
//		);
//		JuryManager.getInstance().initRender(this);
//            JuryManager.getInstance().startRateJury(this);
      // Moderator
//		setupMafiosis(625f, 250f, 
//				new Mafiosi("Heinrich Walters", Message.DIALOG_HEINRICH_GREETING, 35, Asset.Textures.DUMMY, Asset.Textures.AVATAR_01));
   }

   private void setupMafiosis(float startX, float startY, Mafiosi... mafiosis)
   {
      final Vector2 startPosition = new Vector2(startX, startY);
      final float mafiosiWidth = 64f;
      final float mafiosiGap = 20f;

      for (int i = 0; i < mafiosis.length; ++i)
      {
         Mafiosi m = mafiosis[i];
         mafiosiMap.put(m.getName(), m);
         GameObject o = getGameWorld().addObject();
         o.setType(m.getName());
         o.setDimensions(mafiosiWidth, mafiosiWidth * 2f);

         Point2D.Double position = new Point2D.Double(startPosition.x + i * (mafiosiWidth + mafiosiGap) , startPosition.y - 200);
         Point2D.Double isometric_point = UtilIsometric.convertCartesisToIsoMorph(position);

         o.setPosition((int) isometric_point.x, (int) isometric_point.y);

         getRenderManager().register(m.getName(), new SpriteRenderer(m.getSpriteId()));
         //dialogManager.addDialog(m.getName(), m.getBioId(), m.getAvatarId());
      }
   }

   private void setupUI(Stage stage)
   {
      DialogBox dialogBox = new DialogBox(dialogManager);
      dialogBox.setHeight(150f);
      dialogBox.setWidth(Gdx.graphics.getWidth());
      stage.addActor(dialogBox);
   }

   private void setupShaders()
   {
      Vignette vignette = new Vignette(Math.round(Gdx.graphics.getWidth() / 2f),
              Math.round(Gdx.graphics.getHeight() / 2f), false);
      vignette.setIntensity(0.45f);
      getRenderPipeline().getPipe(RenderPipeIds.WORLD).addEffects(vignette);
   }

   private void setupMiniGameFramework()
   {
      player = new Mafiosi("Lerry Sanchez", Message.DIALOG_EDUARDLASER_GREETING, 38, Asset.Textures.LERRY_SANCHEZ_STAGE, Asset.Textures.KAPPO);
      setupMafiosis(300f, 300f,
              new Mafiosi("Tron Jawolta", Message.DIALOG_LERRY_GREETING, 47, Asset.Textures.TRON_JAWOLTA_STAGE, Asset.Textures.AVATAR_TRON_JAWOLTA),
              player,
              new Mafiosi("Ronald Trumpf", Message.DIALOG_STEPHANO_GREETING, 28, Asset.Textures.RONALD_TRUMPF_STAGE, Asset.Textures.AVATAR_RONALD_TRUMPF)
      );
      List<MiniGame> games = new ArrayList<MiniGame>();
      context = new MafiosiGameContext(new ArrayList<Mafiosi>(mafiosiMap.values()), games, player, dialogManager);


      games.add(new RoastBattleMiniGame(this, context));
      games.add(new RouletteMiniGame(getGame(), context, this));
      miniGameManager = new MiniGameManager(games);
   }

}
