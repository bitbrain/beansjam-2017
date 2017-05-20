package tv.rocketbeans.supermafiosi.core;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import de.bitbrain.braingdx.assets.SharedAssetManager;

public class Dialog {
	
	private final Sprite picture;
	private final String text;
	private final String title;
	private final Color color;
	
	public Dialog(String title, String text, String pictureId, Color color) {
		this.text = text;
		this.title = title;
		this.picture = new Sprite(SharedAssetManager.getInstance().get(pictureId, Texture.class));
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
	
	public String getTitle() {
		return title;
	}
	
	public Sprite getPicture() {
		return picture;
	}

	public String getText() {
		return text;
	}
}
