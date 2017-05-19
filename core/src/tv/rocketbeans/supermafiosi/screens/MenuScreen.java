package tv.rocketbeans.supermafiosi.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import de.bitbrain.braingdx.assets.SharedAssetManager;
import de.bitbrain.braingdx.screens.AbstractScreen;
import tv.rocketbeans.supermafiosi.SuperMafiosiGame;
import tv.rocketbeans.supermafiosi.assets.Asset;
import tv.rocketbeans.supermafiosi.ui.Styles;

public class MenuScreen extends AbstractScreen<SuperMafiosiGame>{

	public MenuScreen(SuperMafiosiGame game) {
		super(game);
	}

	@Override
	protected void onCreateStage(Stage stage, int width, int height) {
		setupUI(stage);
	}
	
	// Defining general UI
	// Logo, buttons, credits (including animations)
	private void setupUI(Stage stage) {
		// Layout
		Table layout = new Table();
		layout.setFillParent(true);
		// Logo
		Image image = new Image(new SpriteDrawable(new Sprite(SharedAssetManager.getInstance().get(Asset.Textures.LOGO, Texture.class))));
		layout.add(image).row();
		// New game
		TextButton newGameButton = new TextButton("New game", Styles.TEXT_BUTTON_MAIN_MENU);
		layout.add(newGameButton).width(400f).height(100f).padTop(20f).padBottom(10f).row();
		// End game
		TextButton endGameButton = new TextButton("End game", Styles.TEXT_BUTTON_MAIN_MENU);
		layout.add(endGameButton).width(400f).height(100f).padBottom(10f).row();
		stage.addActor(layout);
	}
}
