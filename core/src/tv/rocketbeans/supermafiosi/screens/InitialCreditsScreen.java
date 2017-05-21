package tv.rocketbeans.supermafiosi.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import de.bitbrain.braingdx.assets.SharedAssetManager;
import de.bitbrain.braingdx.audio.AudioManager;
import de.bitbrain.braingdx.screens.AbstractScreen;
import de.bitbrain.braingdx.tweens.SharedTweenManager;
import tv.rocketbeans.supermafiosi.Colors;
import tv.rocketbeans.supermafiosi.SuperMafiosiGame;
import tv.rocketbeans.supermafiosi.assets.Asset;

public class InitialCreditsScreen extends AbstractScreen<SuperMafiosiGame>{
	
	private boolean isFadingOut = false;

	public InitialCreditsScreen(SuperMafiosiGame game) {
		super(game);
	}

	@Override
	protected void onCreateStage(Stage stage, int width, int height) {
		super.onCreateStage(stage, width, height);
		setBackgroundColor(Colors.BACKGROUND);
		getScreenTransitions().in(0.5f);
		
		getInput().addProcessor(new InputAdapter() {			
			public boolean keyDown(int keycode) {
				isFadingOut = true;
				AudioManager.getInstance().fadeOutMusic(Asset.Music.GAME_WIN_MAIN);
				AudioManager.getInstance().fadeInMusic(Asset.Music.MENU_CHAR_SELECT_INTRO);
				getScreenTransitions().out(new IntroScreen(getGame()), 2f);
				return true;
			};
		});
		
		Tween.call(new TweenCallback() {
			@Override
			public void onEvent(int arg0, BaseTween<?> arg1) {
				getScreenTransitions().out(new IntroScreen(getGame()), 2f);
			}
		}).delay(3f).start(SharedTweenManager.getInstance());
		
		Table table = new Table();
		table.setFillParent(true);
		Image logoA = new Image(new SpriteDrawable(new Sprite(SharedAssetManager.getInstance().get(Asset.Textures.LOGO_BEANSJAM, Texture.class))));
		Image logoB = new Image(new SpriteDrawable(new Sprite(SharedAssetManager.getInstance().get(Asset.Textures.LOGO_WEESEKS, Texture.class))));
		
		table.add(logoA).width(120f * 2f).height(200 * 2f).padRight(250f);
		table.add(logoB).width(200f * 2f).height(200f * 2f);
		
		stage.addActor(table);
	}
	
	@Override
	protected void onUpdate(float delta) {
		if (Gdx.input.isTouched() && !isFadingOut) {
			isFadingOut = true;
			getScreenTransitions().out(new IntroScreen(getGame()), 2f);
		}
	}
}
