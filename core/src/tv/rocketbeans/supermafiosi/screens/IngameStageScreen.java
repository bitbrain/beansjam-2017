package tv.rocketbeans.supermafiosi.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

import aurelienribon.tweenengine.Tween;
import de.bitbrain.braingdx.graphics.pipeline.RenderPipe;
import de.bitbrain.braingdx.graphics.pipeline.layers.RenderPipeIds;
import de.bitbrain.braingdx.graphics.renderer.SpriteRenderer;
import de.bitbrain.braingdx.postprocessing.effects.Bloom;
import de.bitbrain.braingdx.postprocessing.effects.Vignette;
import de.bitbrain.braingdx.postprocessing.filters.Blur.BlurType;
import de.bitbrain.braingdx.screens.AbstractScreen;
import de.bitbrain.braingdx.tweens.GameObjectTween;
import de.bitbrain.braingdx.world.GameObject;
import tv.rocketbeans.supermafiosi.Colors;
import tv.rocketbeans.supermafiosi.SuperMafiosiGame;
import tv.rocketbeans.supermafiosi.assets.Asset;
import tv.rocketbeans.supermafiosi.core.Dialog;
import tv.rocketbeans.supermafiosi.ui.DialogBox;

public class IngameStageScreen extends AbstractScreen<SuperMafiosiGame> {

	public IngameStageScreen(SuperMafiosiGame game) {
		super(game);
	}
	
	@Override
	protected void onCreateStage(Stage stage, int width, int height) {
		setupShaders();
		Dialog dialog = new Dialog("Hello, my friend how are you tonight? I hope you are alright. Beansjam is amazing, isn't it?!", Asset.Textures.AVATAR_01);
		DialogBox dialogBox = new DialogBox();
		dialogBox.setHeight(150f);
		dialogBox.setWidth(Gdx.graphics.getWidth());
		dialogBox.setDialog(dialog);
		stage.addActor(dialogBox);
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
