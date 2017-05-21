package tv.rocketbeans.supermafiosi.minigame.roulette;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import de.bitbrain.braingdx.GameContext;
import de.bitbrain.braingdx.tweens.ActorTween;
import de.bitbrain.braingdx.tweens.SharedTweenManager;
import tv.rocketbeans.supermafiosi.core.Dialog;
import tv.rocketbeans.supermafiosi.core.DialogManager.DialogManagerListener;
import tv.rocketbeans.supermafiosi.core.Mafiosi;
import tv.rocketbeans.supermafiosi.core.MafiosiGameContext;
import tv.rocketbeans.supermafiosi.i18n.Message;
import tv.rocketbeans.supermafiosi.minigame.AbstractMiniGame;
import tv.rocketbeans.supermafiosi.ui.Styles;

/**
 * In this final mini game the players need to pull the trigger in round
 * robin. The chances are higher for a shot if more bullets are loaded.
 */
public class RouletteMiniGame extends AbstractMiniGame {
	
	private final MafiosiGameContext context;
	
	private final GameContext gameContext;
	
	private final List<Mafiosi> deadMafiosis = new ArrayList<Mafiosi>();
	
	private List<Mafiosi> remainingCandidates;
	
	private Mafiosi mafiosi;
	
	private boolean waitingForPlayerConfirmation;
	
	private DialogManagerListener dialogListener = new DialogManagerListener() {

		@Override
		public void afterLastDialog() {
			Tween.call(new TweenCallback() {
				@Override
				public void onEvent(int arg0, BaseTween<?> arg1) {
					pullTrigger();
				}
			}).delay(1f).start(SharedTweenManager.getInstance());
		}

		@Override
		public void onDialog(Dialog dialog) {
			
		}
	};

	private Label confirmationLabel;

	public RouletteMiniGame(MafiosiGameContext context, GameContext gameContext) {
		this.context = context;
		this.gameContext = gameContext;
	}

	@Override
	public void initialise() {
		context.getDialogManager().addListener(dialogListener);
		remainingCandidates = new ArrayList<Mafiosi>(context.getCandidates());
		nextPlayer();
	}

	@Override
	public void cleanup() {
		context.getDialogManager().removeListener(dialogListener);
	}

	@Override
	public void update(float delta) {
		if (waitingForPlayerConfirmation) {
			if (Gdx.input.isTouched() || Gdx.input.isKeyPressed(Keys.ANY_KEY)) {
				waitingForPlayerConfirmation = false;
				gameContext.getTweenManager().killTarget(confirmationLabel);
				gameContext.getStage().getActors().removeValue(confirmationLabel, true);
				context.getDialogManager().addDialog(mafiosi.getName(), Message.MAINMENU_BUTTON_NEWGAME, mafiosi.getAvatarId());
			}
		}
	}
	
	private void nextPlayer() {
		// Initialise first candidate to pull the trigger
		mafiosi = remainingCandidates.remove(0);
		if (mafiosi.equals(context.getPlayerMafiosi())) {
			waitingForPlayerConfirmation = true;
			confirmationLabel = new Label("PULL THE TRIGGER!!", Styles.LABEL_PULL_THE_TRIGGER);
			confirmationLabel.setWidth(Gdx.graphics.getWidth());
			confirmationLabel.setHeight(Gdx.graphics.getHeight());
			confirmationLabel.setAlignment(Align.center);
			Tween.to(confirmationLabel, ActorTween.SCALE, 0.5f)
			     .target(1.3f)
			     .ease(TweenEquations.easeInCubic)
			     .repeatYoyo(Tween.INFINITY, 0f)
			     .start(gameContext.getTweenManager());
			gameContext.getStage().addActor(confirmationLabel);
		} else {
			context.getDialogManager().addDialog(mafiosi.getName(), Message.MAINMENU_BUTTON_NEWGAME, mafiosi.getAvatarId());
		}
		
	}
	
	private void pullTrigger() {
		int numberOfBullets = context.getNumberOfBullets(mafiosi.getName());
		boolean mafiosiWillBeDeadForSure = false;//Math.random() > (float)numberOfBullets / (float)context.getNumberOfBulletSlots();
		if (mafiosiWillBeDeadForSure) {
			shootCurrentPlayer();
		} else {
			remainingCandidates.add(mafiosi);
			context.getDialogManager().addDialog(mafiosi.getName(), Message.MAINMENU_BUTTON_EXITGAME, mafiosi.getAvatarId());
			nextPlayer();
		}
	}
	
	private void shootCurrentPlayer() {
		if (mafiosi.equals(context.getPlayerMafiosi())) {
			// GAME OVER!!
			// TODO
			System.out.println("Game Over!");
		} else {
			deadMafiosis.add(mafiosi);
			System.out.println(mafiosi + " shot.");
			if (deadMafiosis.size() >= context.getCandidates().size() - 1) {
				// All other mafiosis are dead -> CONGRATULATIONS!
				// YOU ARE THE NEW DON!!
				// TODO
				System.out.println("WON THE GAME!");
			} else {
				nextPlayer();
			}
		}
	}

}
