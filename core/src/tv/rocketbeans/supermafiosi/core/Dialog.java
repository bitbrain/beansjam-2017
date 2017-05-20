package tv.rocketbeans.supermafiosi.core;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import de.bitbrain.braingdx.assets.SharedAssetManager;
import tv.rocketbeans.supermafiosi.i18n.Bundle;

public class Dialog {
	
	private final Sprite picture;
	private final String textId;
	private final String title;
	private final Color color;
	
	public Dialog(String title, String textId, String pictureId, Color color) {
		this.textId = textId;
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
	
	public String getTextId() {
		return textId;
	}

	public String getText() {
		return Bundle.translations.get(textId);
	}
}
