package tv.rocketbeans.supermafiosi.core;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import de.bitbrain.braingdx.assets.SharedAssetManager;

public class Dialog {
	
	private final Sprite picture;
	private final String text;
	
	public Dialog(String text, String pictureId) {
		this.text = text;
		this.picture = new Sprite(SharedAssetManager.getInstance().get(pictureId, Texture.class));
	}
	
	public Sprite getPicture() {
		return picture;
	}

	public String getText() {
		return text;
	}
}
