package tv.rocketbeans.supermafiosi.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import de.bitbrain.braingdx.assets.SharedAssetManager;
import de.bitbrain.braingdx.graphics.pipeline.RenderPipe;
import de.bitbrain.braingdx.graphics.pipeline.layers.RenderPipeIds;
import de.bitbrain.braingdx.postprocessing.effects.Bloom;
import de.bitbrain.braingdx.postprocessing.effects.Vignette;
import de.bitbrain.braingdx.postprocessing.filters.Blur.BlurType;
import de.bitbrain.braingdx.screens.AbstractScreen;
import de.bitbrain.braingdx.screens.TransitionCallback;
import de.bitbrain.braingdx.tweens.ActorTween;
import tv.rocketbeans.supermafiosi.Colors;
import tv.rocketbeans.supermafiosi.SuperMafiosiGame;
import tv.rocketbeans.supermafiosi.assets.Asset;
import tv.rocketbeans.supermafiosi.i18n.Bundle;
import tv.rocketbeans.supermafiosi.i18n.Message;
import tv.rocketbeans.supermafiosi.ui.Styles;

public class MenuScreen extends AbstractScreen<SuperMafiosiGame>{

	public MenuScreen(SuperMafiosiGame game) {
		super(game);
	}

	@Override
	protected void onCreateStage(Stage stage, int width, int height) {
		setBackgroundColor(Colors.BACKGROUND);
		setupUI(stage);
		setupShaders();
	}
	
	// Defining general UI
	// Logo, buttons, credits (including animations)
	private void setupUI(Stage stage) {
		// Layout
		Table layout = new Table();
		layout.setFillParent(true);
		// Logo
		Image image = new Image(new SpriteDrawable(new Sprite(SharedAssetManager.getInstance().get(Asset.Textures.LOGO, Texture.class))));
		layout.add(image).padBottom(50f).row();
		Tween.to(image, ActorTween.ALPHA, 1.5f)
		     .target(0.9f)
		     .repeatYoyo(Tween.INFINITY, 0f)
		     .ease(TweenEquations.easeOutQuad)
		     .start(getTweenManager());
		
		// New game
		TextButton newGameButton = new TextButton(Bundle.translations.get(Message.MAINMENU_BUTTON_NEWGAME), Styles.TEXT_BUTTON_MAIN_MENU);
		newGameButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				getScreenTransitions().out(new IngameStageScreen(getGame()), 1f);
			}
		});
		layout.add(newGameButton).width(400f).height(100f).padTop(20f).padBottom(20f).row();
		
		// End game
		TextButton endGameButton = new TextButton(Bundle.translations.get(Message.MAINMENU_BUTTON_EXITGAME), Styles.TEXT_BUTTON_MAIN_MENU);		
		// Register listener to quit the game with a slight fade-out
		endGameButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.input.setInputProcessor(null);
				getScreenTransitions().out(new TransitionCallback() {
					@Override
					public void beforeTransition() {
						// noOp
					}

					@Override
					public void afterTransition() {
						Gdx.app.exit();
					}
					
				}, 0.5f);
			}
		});
		layout.add(endGameButton).width(400f).height(100f).padBottom(10f).row();
		
		// Credits
		Label credits = new Label(Bundle.translations.get(Message.MAINMENU_CREDITS), Styles.LABEL_CREDITS);
		layout.add(credits).padTop(50f);
		
		stage.addActor(layout);
	}

	private void setupShaders() {
		Bloom bloom = new Bloom(Math.round(Gdx.graphics.getWidth() * 0.9f),
				Math.round(Gdx.graphics.getHeight() * 0.9f));
		bloom.setBaseIntesity(1.1f);
		bloom.setBaseSaturation(1.0f);
		bloom.setBlurType(BlurType.Gaussian5x5b);
		bloom.setBlurAmount(0.5f);
		bloom.setBloomSaturation(0.9f);
		bloom.setBloomIntesity(0.9f);
		bloom.setBlurPasses(5);
		Vignette vignette = new Vignette(Math.round(Gdx.graphics.getWidth() / 2f),
				Math.round(Gdx.graphics.getHeight() / 2f), false);
		vignette.setIntensity(0.35f);
		RenderPipe uiPipe = getRenderPipeline().getPipe(RenderPipeIds.UI);
		uiPipe.addEffects(vignette, bloom);
	}
}
