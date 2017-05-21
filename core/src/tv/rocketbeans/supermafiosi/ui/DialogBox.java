package tv.rocketbeans.supermafiosi.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;
import de.bitbrain.braingdx.assets.SharedAssetManager;
import de.bitbrain.braingdx.graphics.GraphicsFactory;
import de.bitbrain.braingdx.tweens.ActorTween;
import de.bitbrain.braingdx.tweens.SharedTweenManager;
import de.bitbrain.braingdx.tweens.ValueTween;
import de.bitbrain.braingdx.util.ValueProvider;
import tv.rocketbeans.supermafiosi.Colors;
import tv.rocketbeans.supermafiosi.assets.Asset;
import tv.rocketbeans.supermafiosi.core.Dialog;
import tv.rocketbeans.supermafiosi.core.DialogManager;

public class DialogBox extends Actor {
	
	private static final float INNER_PADDING_Y = 40f;
	private static final float MARGIN = 10f;
	private static final float AVATAR_PADDING = -10f;
	private static final float TITLE_PADDING = 20f;
	
	private Dialog dialog;
	
	private final DialogManager dialogManager;
	private final TweenManager tweenManager = SharedTweenManager.getInstance();
	
	private Label text;
	private Label title;
	private ValueProvider offsetProvider = new ValueProvider();
	private ValueProvider avatarBouncing = new ValueProvider();
	private boolean currentlyClosing;
	private final NinePatch dialogBackground;
	private final NinePatch titleBackground;
	
	static {
		Tween.registerAccessor(ValueProvider.class, new ValueTween());
	}
	
	public DialogBox(DialogManager dialogManager) {
		this.dialogManager = dialogManager;
		// Create a nice background so font is readable
		Texture buttonNinePatchTexture = SharedAssetManager.getInstance().get(Asset.Textures.PANEL_9PATCH, Texture.class);
		Texture labelNinePatchTexture = SharedAssetManager.getInstance().get(Asset.Textures.LABEL_9PATCH, Texture.class);
		dialogBackground = GraphicsFactory.createNinePatch(buttonNinePatchTexture, 20, Colors.FOREGROUND);
		titleBackground =  GraphicsFactory.createNinePatch(labelNinePatchTexture, 15, Colors.FOREGROUND);
	}
	
	@Override
	public void act(float delta) {
		if (!currentlyClosing && (dialog == null || dialog != dialogManager.getCurrentDialog())) {
			unsetDialog(dialog, new TweenCallback() {
				@Override
				public void onEvent(int arg0, BaseTween<?> arg1) {
					setDialog(dialogManager.getCurrentDialog());
				}
			});
		}
	}
	
	@Override
	public float getX() {
		return MARGIN;
	}
	
	@Override
	public float getY() {
		return MARGIN + offsetProvider.getValue();
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		parentAlpha *= getColor().a;
		if (title != null) {
			title.setX(getTitleX());
			title.setY(getTitleY());
			titleBackground.getColor().a = title.getColor().a;
			titleBackground.draw(batch, getTitleBackgroundX(), getTitleBackgroundY(), getTitleBackgroundWidth(), getTitleBackgroundHeight());
			title.draw(batch, 1f);
		}
		if (dialog != null) {
			dialogBackground.draw(batch, getX(), getY(), getWidth() - MARGIN * 2f, getHeight());
			Sprite avatar = dialog.getPicture();
			avatar.setPosition(getX() + AVATAR_PADDING + 20f, getY() + AVATAR_PADDING + avatarBouncing.getValue());
			avatar.setSize(getHeight() - AVATAR_PADDING * 2f, getHeight() - AVATAR_PADDING * 2f);
			dialog.getPicture().draw(batch, parentAlpha);
		}
		if (text != null) {
			text.setPosition(getX() + getHeight() + 50f, getY() + getHeight() - text.getHeight() + - INNER_PADDING_Y);
			text.draw(batch, parentAlpha);
		}
	}
	
	private void unsetDialog(Dialog dialog, TweenCallback finishCallback) {
		if (dialog != null) {
			currentlyClosing = true;
			Tween.to(text, ActorTween.ALPHA, 0.5f)
		     .target(0f)
		     .ease(TweenEquations.easeInCubic)
		     .start(tweenManager);
			Tween.to(title, ActorTween.ALPHA, 0.5f)
		     .target(0f)
		     .ease(TweenEquations.easeInCubic)
		     .start(tweenManager);
			Tween.to(this, ActorTween.ALPHA, 0.5f)
			 .delay(0.3f)
		     .target(0f)
		     .ease(TweenEquations.easeInCubic)
		     .start(tweenManager);
			Tween.to(offsetProvider, ValueTween.VALUE, 0.5f)
		     .target(getFadeOutYPosition())
		     .ease(TweenEquations.easeInCubic)
		     .setCallbackTriggers(TweenCallback.COMPLETE)
		     .setCallback(finishCallback)
		     .start(tweenManager);
			tweenManager.killTarget(avatarBouncing);
		} else {
			finishCallback.onEvent(0, null);
		}
	}
	
	private void setDialog(Dialog dialog) {
		currentlyClosing = false;
		if (dialog != null) {
			
			this.dialog = dialog;
			this.text = new Label(dialog.getText(), Styles.LABEL_DIALOG);
			this.title = new Label(dialog.getTitle(), Styles.LABEL_DIALOG_TITLE);
			text.setColor(dialog.getColor());
			text.setWrap(true);
			text.setWidth(getWidth() - getHeight() -  MARGIN * 2f - 50f);
			text.setAlignment(Align.top | Align.left);
			text.setHeight(getHeight() -  MARGIN);
			getColor().a = 0f;
			Tween.to(this, ActorTween.ALPHA, 0.8f)
				 .delay(0.3f)
			     .target(1f)
			     .ease(TweenEquations.easeInCubic)
			     .start(tweenManager);
			text.getColor().a = 0f;
			Tween.to(text, ActorTween.ALPHA, 0.4f)
			 .delay(0.6f)
		     .target(1f)
		     .ease(TweenEquations.easeInCubic)
		     .start(tweenManager);
			Tween.to(title, ActorTween.ALPHA, 0.6f)
		     .target(1f)
		     .ease(TweenEquations.easeInCubic)
		     .start(tweenManager);
			offsetProvider.setValue(getFadeOutYPosition());
			Tween.to(offsetProvider, ValueTween.VALUE, 0.5f)
		     .target(0f)
		     .ease(TweenEquations.easeInCubic)
		     .start(tweenManager);
			avatarBouncing.setValue(0f);
			Tween.to(avatarBouncing, ValueTween.VALUE, 0.5f)
		     .target(15f)
		     .ease(TweenEquations.easeInCubic)
		     .repeatYoyo(Tween.INFINITY, 0f)
		     .start(tweenManager);
		}
	}
	
	private float getTitleBackgroundX() {
		return title.getX() - TITLE_PADDING;
	}
	
	private float getTitleBackgroundY() {
		return title.getY() - (TITLE_PADDING / 2f);
	}
	
	private float getTitleBackgroundWidth() {
		return title.getPrefWidth() + TITLE_PADDING * 2f;
	}
	
	private float getTitleBackgroundHeight() {
		return title.getPrefHeight() + (TITLE_PADDING / 2f) * 2f;
	}
	
	private float getTitleY() {
		return  getY() + getHeight() + (TITLE_PADDING / 2f) - 2f;
	}
	
	private float getTitleX() {
		return getX() + TITLE_PADDING;
	}
	
	private float getFadeOutYPosition() {
		return -getHeight() - MARGIN - TITLE_PADDING * 4f;
	}
	
}
