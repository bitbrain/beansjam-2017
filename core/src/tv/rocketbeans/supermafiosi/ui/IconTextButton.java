package tv.rocketbeans.supermafiosi.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import de.bitbrain.braingdx.assets.SharedAssetManager;

public class IconTextButton extends TextButton {
	
	private final Sprite sprite;
	
	private float iconPadding = 0f;

	public IconTextButton(String text, String iconPath, TextButtonStyle style) {
		super(text, style);
		this.sprite = new Sprite(SharedAssetManager.getInstance().get(iconPath, Texture.class));
	}
	
	public void setIconPadding(float padding) {
		this.iconPadding = padding;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		sprite.setPosition(getX() + iconPadding, getY() + iconPadding);
		sprite.setSize(getHeight() - iconPadding * 2f, getHeight() - iconPadding * 2f);
		sprite.draw(batch, parentAlpha);
	}

}
