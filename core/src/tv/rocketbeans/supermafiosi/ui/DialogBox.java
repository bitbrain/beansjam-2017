package tv.rocketbeans.supermafiosi.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;
import de.bitbrain.braingdx.tweens.ActorTween;
import de.bitbrain.braingdx.tweens.SharedTweenManager;
import de.bitbrain.braingdx.tweens.ValueTween;
import de.bitbrain.braingdx.util.ValueProvider;
import tv.rocketbeans.supermafiosi.core.Dialog;

public class DialogBox extends Actor {
	
	private static final float INNER_PADDING_X = 30f;
	private static final float INNER_PADDING_Y = 10f;
	private static final float MARGIN = 30f;
	
	private Dialog dialog;
	
	private final TweenManager tweenManager = SharedTweenManager.getInstance();
	
	private Label label;
	
	private ValueProvider offsetProvider = new ValueProvider();
	
	static {
		Tween.registerAccessor(ValueProvider.class, new ValueTween());
	}

	public void setDialog(Dialog dialog) {
		this.dialog = dialog;
		this.label = new Label(dialog.getText(), Styles.LABEL_DIALOG);
		label.setWrap(true);
		label.setWidth(getWidth() - getHeight() -  MARGIN * 2f);
		label.setAlignment(Align.top | Align.left);
		label.setHeight(getHeight() -  MARGIN);
		getColor().a = 0f;
		Tween.to(this, ActorTween.ALPHA, 0.8f)
		     .target(1f)
		     .ease(TweenEquations.easeInCubic)
		     .start(tweenManager);
		label.getColor().a = 0f;
		Tween.to(label, ActorTween.ALPHA, 0.4f)
		 .delay(0.3f)
	     .target(1f)
	     .ease(TweenEquations.easeInCubic)
	     .start(tweenManager);
		offsetProvider.setValue(-getHeight() - MARGIN);
		Tween.to(offsetProvider, ValueTween.VALUE, 0.5f)
	     .target(0f)
	     .ease(TweenEquations.easeInCubic)
	     .start(tweenManager);
	}
	
	@Override
	public void act(float delta) {
		if (Gdx.input.isTouched()) {
			Tween.to(label, ActorTween.ALPHA, 0.5f)
		     .target(0f)
		     .ease(TweenEquations.easeInCubic)
		     .start(tweenManager);
			Tween.to(this, ActorTween.ALPHA, 0.5f)
			 .delay(0.3f)
		     .target(0f)
		     .ease(TweenEquations.easeInCubic)
		     .start(tweenManager);
			Tween.to(offsetProvider, ValueTween.VALUE, 0.5f)
		     .target(-getHeight() - MARGIN)
		     .ease(TweenEquations.easeInCubic)
		     .start(tweenManager);
		}
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		parentAlpha *= getColor().a;
		if (dialog != null) {
			Sprite avatar = dialog.getPicture();
			avatar.setPosition(MARGIN, MARGIN + offsetProvider.getValue());
			avatar.setSize(getHeight(), getHeight());
			dialog.getPicture().draw(batch, parentAlpha);
		}
		if (label != null) {
			label.setPosition(MARGIN + getHeight() + INNER_PADDING_X, MARGIN + getHeight() - label.getHeight() + offsetProvider.getValue() - INNER_PADDING_Y);
			label.draw(batch, parentAlpha);
		}
	}
	
	
}
