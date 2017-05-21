package tv.rocketbeans.supermafiosi.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import de.bitbrain.braingdx.graphics.pipeline.RenderPipe;
import de.bitbrain.braingdx.graphics.pipeline.layers.RenderPipeIds;
import de.bitbrain.braingdx.postprocessing.effects.Bloom;
import de.bitbrain.braingdx.postprocessing.effects.Vignette;
import de.bitbrain.braingdx.screens.AbstractScreen;
import de.bitbrain.braingdx.tweens.ActorTween;
import tv.rocketbeans.supermafiosi.Colors;
import tv.rocketbeans.supermafiosi.SuperMafiosiGame;
import tv.rocketbeans.supermafiosi.ui.Styles;

public class CongratulationsScreen extends AbstractScreen<SuperMafiosiGame> {
	
	private boolean isFadingOut = false;

	public CongratulationsScreen(SuperMafiosiGame game) {
		super(game);
	}

	@Override
	protected void onCreateStage(Stage stage, int width, int height) {
		super.onCreateStage(stage, width, height);
		setBackgroundColor(Colors.BACKGROUND);
		getScreenTransitions().in(1f);
		Label congratulations = new Label("CONGRATULATIONS!\nYOU ARE THE NEW DON!", Styles.LABEL_TOAST);
		congratulations.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		congratulations.setAlignment(Align.center);
		stage.addActor(congratulations);
		Tween.to(congratulations, ActorTween.SCALE, 0.66f)
		     .target(1.3f)
		     .ease(TweenEquations.easeInCubic)
		     .repeatYoyo(Tween.INFINITY, 0)
		     .start(getTweenManager());
		
		getInput().addProcessor(new InputAdapter() {			
			public boolean keyDown(int keycode) {
				isFadingOut = true;
				getScreenTransitions().out(new MenuScreen(getGame()), 1f);
				return true;
			};
		});
		setupShaders();
	}
	
	@Override
	protected void onUpdate(float delta) {
		if (Gdx.input.isTouched() && !isFadingOut) {
			isFadingOut = true;
			getScreenTransitions().out(new MenuScreen(getGame()), 1f);
		}
	}
	
	private void setupShaders() {
	      Bloom bloom = new Bloom(Math.round(Gdx.graphics.getWidth() * 0.7f), Math.round(Gdx.graphics.getHeight() * 0.7f));

	      bloom.setBaseIntesity(1f);
	      bloom.setBaseSaturation(1.2f);
	      bloom.setBlurAmount(1.0f);
	      bloom.setBloomSaturation(1f);
	      bloom.setBloomIntesity(1.2f);
	      bloom.setBlurPasses(4);
	      Vignette vignette = new Vignette(Math.round(Gdx.graphics.getWidth() / 2f),
	            Math.round(Gdx.graphics.getHeight() / 2f), false);
	      vignette.setIntensity(0.45f);
	      RenderPipe uiPipe = getRenderPipeline().getPipe(RenderPipeIds.UI);
	      uiPipe.addEffects(vignette, bloom);
	}
}
