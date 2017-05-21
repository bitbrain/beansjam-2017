package tv.rocketbeans.supermafiosi.ui;

import java.util.Map;
import java.util.Map.Entry;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import de.bitbrain.braingdx.assets.SharedAssetManager;
import de.bitbrain.braingdx.tweens.SharedTweenManager;
import de.bitbrain.braingdx.tweens.ValueTween;
import de.bitbrain.braingdx.util.ValueProvider;
import de.bitbrain.braingdx.world.GameObject;
import tv.rocketbeans.supermafiosi.assets.Asset;
import tv.rocketbeans.supermafiosi.core.Mafiosi;

public class ActiveMafiosiArrow extends Actor {

	private final Sprite sprite;
	
	private final Map<Mafiosi, GameObject> mafiosiObjects;
	
	private final ValueProvider arrowOffset = new ValueProvider();
	
	public ActiveMafiosiArrow(Map<Mafiosi, GameObject> mafiosiObjects) {
		setZIndex(0);
		this.mafiosiObjects = mafiosiObjects;
		sprite = new Sprite(SharedAssetManager.getInstance().get(Asset.Textures.ARROW, Texture.class));
		Tween.to(arrowOffset, ValueTween.VALUE, 1f)
		     .target(20f)
		     .ease(TweenEquations.easeInCubic)
		     .repeatYoyo(Tween.INFINITY, 0f)
		     .start(SharedTweenManager.getInstance());
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		for (Entry<Mafiosi, GameObject> entry : mafiosiObjects.entrySet()) {
			GameObject gameObject = entry.getValue();
			Mafiosi mafiosi = entry.getKey();
			
			if (mafiosi.isActive()) {
				// Draw mafiosi active arrow
				sprite.setSize(64, 64);
				sprite.setPosition(gameObject.getLeft() + gameObject.getWidth() / 2f - sprite.getWidth() / 2f, gameObject.getTop() + gameObject.getHeight() + 20f + arrowOffset.getValue());
				sprite.draw(batch, parentAlpha);
			}
		}
	}
}
