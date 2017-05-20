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
import de.bitbrain.braingdx.assets.SharedAssetManager;
import de.bitbrain.braingdx.graphics.pipeline.RenderLayer;
import de.bitbrain.braingdx.graphics.pipeline.RenderPipe;
import de.bitbrain.braingdx.graphics.pipeline.layers.RenderPipeIds;
import de.bitbrain.braingdx.graphics.renderer.SpriteRenderer;
import de.bitbrain.braingdx.postprocessing.effects.Bloom;
import de.bitbrain.braingdx.postprocessing.effects.Vignette;
import de.bitbrain.braingdx.postprocessing.filters.Blur.BlurType;
import de.bitbrain.braingdx.screens.AbstractScreen;
import de.bitbrain.braingdx.world.GameObject;
import tv.rocketbeans.supermafiosi.Colors;
import tv.rocketbeans.supermafiosi.SuperMafiosiGame;
import tv.rocketbeans.supermafiosi.assets.Asset;
import tv.rocketbeans.supermafiosi.core.DialogManager;
import tv.rocketbeans.supermafiosi.core.Mafiosi;
import tv.rocketbeans.supermafiosi.core.MafiosiGameContext;
import tv.rocketbeans.supermafiosi.core.jury.JuryManager;
import tv.rocketbeans.supermafiosi.i18n.Message;
import tv.rocketbeans.supermafiosi.minigame.MiniGame;
import tv.rocketbeans.supermafiosi.minigame.MiniGameManager;
import tv.rocketbeans.supermafiosi.minigame.roastbattle.RoastBattleMiniGame;
import tv.rocketbeans.supermafiosi.tweens.ConeLightTween;
import tv.rocketbeans.supermafiosi.ui.DialogBox;
import tv.rocketbeans.supermafiosi.ui.Toast;

public class IngameStageScreen extends AbstractScreen<SuperMafiosiGame> {
	
	private DialogManager dialogManager;
	private MafiosiGameContext context;
	private Mafiosi player;
	private Map<String, Mafiosi> mafiosiMap = new HashMap<String, Mafiosi>();
	
	static {
		Tween.registerAccessor(ConeLight.class, new ConeLightTween());
	}

	public IngameStageScreen(SuperMafiosiGame game) {
		super(game);
	}
	
	@Override
	protected void onCreateStage(Stage stage, int width, int height) {
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
		dialogManager.nextDialog();
	}

	private void setupBackground() {
		getRenderPipeline().set(RenderPipeIds.BACKGROUND, new RenderLayer() {
			
			@Override
			public void render(Batch batch, float delta) {
				Texture texture = SharedAssetManager.getInstance().get(Asset.Textures.STAGE, Texture.class);
				batch.begin();
				batch.draw(texture, 0f, 0f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
				batch.end();
			}
			
			@Override
			public void beforeRender() {
				// XXX Auto-generated method stub
				
			}
		});
	}
	
	private void setupLighting() {
		getLightingManager().setConfig(getLightingManager().new LightingConfig().blur(true).diffuseLighting(false));
		getLightingManager().setAmbientLight(new Color(0.3f, 0.3f, 0.4f, 0.4f));
		float lightingOffset = 50f;
		ConeLight left = getLightingManager().addConeLight("leftLight", -lightingOffset, Gdx.graphics.getHeight() + lightingOffset, 1600f, -45f, 20f, Color.RED);
		ConeLight right = getLightingManager().addConeLight("rightLight", Gdx.graphics.getWidth() + lightingOffset, Gdx.graphics.getHeight() + lightingOffset, 1600f, -135f, 20f, Color.BLUE);
		ConeLight top = getLightingManager().addConeLight("topLight", Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() + lightingOffset, 900f, -110f, 30f, Color.LIME);
		Tween.to(left, ConeLightTween.DIRECTION, 1.5f + (float)Math.random())
		     .target(10f)
		     .ease(TweenEquations.easeInOutQuad)
		     .repeatYoyo(Tween.INFINITY, 0f)
		     .start(getTweenManager());
		Tween.to(right, ConeLightTween.DIRECTION, 1.5f + (float)Math.random())
	     .target(-170f)
	     .ease(TweenEquations.easeInOutQuad)
	     .repeatYoyo(Tween.INFINITY, 0f)
	     .start(getTweenManager());
		Tween.to(top, ConeLightTween.DIRECTION, 1.5f + (float)Math.random())
	     .target(-80f)
	     .ease(TweenEquations.easeInOutQuad)
	     .repeatYoyo(Tween.INFINITY, 0f)
	     .start(getTweenManager());
	}
	
	private void setupAllMafiosis() {
		// Contestants
//		setupMafiosis(300f, 300f, 
//				new Mafiosi("Lerry Sanchez", Message.DIALOG_LERRY_GREETING, 47, Asset.Textures.DUMMY, Asset.Textures.AVATAR_01),
//				new Mafiosi("Eduard Laser", Message.DIALOG_EDUARDLASER_GREETING, 38, Asset.Textures.DUMMY, Asset.Textures.AVATAR_01),
//				new Mafiosi("Stephano Caprese", Message.DIALOG_STEPHANO_GREETING, 28, Asset.Textures.DUMMY, Asset.Textures.AVATAR_01)
//		);
		
		JuryManager.getInstance().initRender(this);
            JuryManager.getInstance().startRateJury(this);
            
		
		// Moderator
//		setupMafiosis(625f, 250f, 
//				new Mafiosi("Heinrich Walters", Message.DIALOG_HEINRICH_GREETING, 35, Asset.Textures.DUMMY, Asset.Textures.AVATAR_01));
	}
	
	private void setupMafiosis(float startX, float startY, Mafiosi ... mafiosis) {
		final Vector2 startPosition = new Vector2(startX, startY);
		final float mafiosiWidth = 64f;
		final float mafiosiGap = 20f;
	
		for (int i = 0; i < mafiosis.length; ++i) {
			Mafiosi m = mafiosis[i];
			mafiosiMap.put(m.getName(), m);
			GameObject o = getGameWorld().addObject();
			o.setType(m.getName());
			o.setDimensions(mafiosiWidth, mafiosiWidth * 2f);
			o.setPosition(startPosition.x + i * (mafiosiWidth + mafiosiGap), startPosition.y);
			getRenderManager().register(m.getName(), new SpriteRenderer(m.getSpriteId()));
			//dialogManager.addDialog(m.getName(), m.getBioId(), m.getAvatarId());
		}
	}
	
	private void setupUI(Stage stage) {
		DialogBox dialogBox = new DialogBox(dialogManager);
		dialogBox.setHeight(150f);
		dialogBox.setWidth(Gdx.graphics.getWidth());
		stage.addActor(dialogBox);
	}
	
	private void setupShaders() {
		Vignette vignette = new Vignette(Math.round(Gdx.graphics.getWidth() / 2f),
				Math.round(Gdx.graphics.getHeight() / 2f), false);
		vignette.setIntensity(0.45f);
		getRenderPipeline().getPipe(RenderPipeIds.WORLD).addEffects(vignette);
	}
	
	private void setupMiniGameFramework() {
		player = new Mafiosi("Eduard Laser", Message.DIALOG_EDUARDLASER_GREETING, 38, Asset.Textures.DUMMY, Asset.Textures.AVATAR_01);
		setupMafiosis(300f, 300f, 
			new Mafiosi("Lerry Sanchez", Message.DIALOG_LERRY_GREETING, 47, Asset.Textures.DUMMY, Asset.Textures.AVATAR_01),
			player,
			new Mafiosi("Stephano Caprese", Message.DIALOG_STEPHANO_GREETING, 28, Asset.Textures.DUMMY, Asset.Textures.AVATAR_01)
		);
		context = new MafiosiGameContext(new ArrayList<Mafiosi>(mafiosiMap.values()), player, dialogManager);
		
		List<MiniGame> games = new ArrayList<MiniGame>();
		games.add(new RoastBattleMiniGame(this, context));
		MiniGameManager miniGameManager = new MiniGameManager(games);
		miniGameManager.triggerNextMiniGame();
	}

}
