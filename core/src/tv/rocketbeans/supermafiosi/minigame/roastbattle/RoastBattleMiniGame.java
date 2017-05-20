package tv.rocketbeans.supermafiosi.minigame.roastbattle;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.badlogic.gdx.graphics.Color;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import de.bitbrain.braingdx.GameContext;
import tv.rocketbeans.supermafiosi.Colors;
import tv.rocketbeans.supermafiosi.Config;
import tv.rocketbeans.supermafiosi.core.Dialog;
import tv.rocketbeans.supermafiosi.core.DialogManager;
import tv.rocketbeans.supermafiosi.core.DialogManager.DialogManagerListener;
import tv.rocketbeans.supermafiosi.i18n.Bundle;
import tv.rocketbeans.supermafiosi.i18n.Message;
import tv.rocketbeans.supermafiosi.minigame.AbstractMiniGame;
import tv.rocketbeans.supermafiosi.minigame.MiniGameResult;
import tv.rocketbeans.supermafiosi.minigame.roastbattle.Roast.Type;
import tv.rocketbeans.supermafiosi.ui.MultiSelect;
import tv.rocketbeans.supermafiosi.ui.MultiSelect.MultiSelectListener;
import tv.rocketbeans.supermafiosi.core.Mafiosi;
import tv.rocketbeans.supermafiosi.core.MafiosiGameContext;

public class RoastBattleMiniGame extends AbstractMiniGame {
	
	private static final Map<Roast.Type, Color> ROAST_COLORS = new HashMap<Roast.Type, Color>();
	
	static {
		ROAST_COLORS.put(Roast.Type.PAPER, Colors.PAPER_COLOR);
		ROAST_COLORS.put(Roast.Type.ROCK, Colors.ROCK_COLOR);
		ROAST_COLORS.put(Roast.Type.SCISSORS, Colors.SCISSORS_COLOR);
	}
	
	private final GameContext gameContext;
	private final MafiosiGameContext mafiosiGameContext;
	
	private boolean playersTurn;
	
	private MultiSelect<Roast> select;
	
	private RoastPool roastPool = new RoastPool();
	
	private Map<Mafiosi, Roast> firedRoasts = new HashMap<Mafiosi, Roast>();
	
	private final MultiSelectListener<Roast> multiSelectListener = new MultiSelectListener<Roast>() {

		@Override
		public void onSelect(Roast roast) {			
			if (playersTurn) {
				System.out.println("Player has selected: " + roast);
				Mafiosi player = mafiosiGameContext.getPlayerMafiosi();
				fireRoast(player, roast);
				mafiosiGameContext.getDialogManager().nextDialog();
				if (select != null) {
					gameContext.getStage().getActors().removeValue(select, true);
				}
				Tween.call(new TweenCallback() {
					@Override
					public void onEvent(int arg0, BaseTween<?> arg1) {
						mafiosiGameContext.getDialogManager().nextDialog();
						notifyComplete(evaluateResult());
					}
				}).delay(3).start(gameContext.getTweenManager());
			}
		}
		
	};
	
	private final DialogManagerListener afterAllDialogsListener = new DialogManagerListener() {
		
		@Override
		public void afterLastDialog() {
			if (!playersTurn) {
				System.out.println("Players turn!");
				playersTurn = true;
				// 2. add UI for selection
				Map<Roast, String> selectData = new HashMap<Roast, String>();
				// Scissor
				Roast randomScissor = roastPool.getRandomRoast(Type.SCISSORS);
				selectData.put(randomScissor, Bundle.translations.get(randomScissor.getMessageKey()));
				// Rock
				Roast randomRock = roastPool.getRandomRoast(Type.ROCK);
				selectData.put(randomRock, Bundle.translations.get(randomRock.getMessageKey()));
				// Paper
				Roast randomPaper = roastPool.getRandomRoast(Type.PAPER);
				selectData.put(randomPaper, Bundle.translations.get(randomPaper.getMessageKey()));
				select = new MultiSelect<Roast>(Bundle.translations.get(Message.MINIGAME_ROAST_TITLE), multiSelectListener, selectData, new RoastIconProvider());
				select.setFillParent(true);
				gameContext.getStage().addActor(select);
			}
		}
		@Override
		public void onDialog(Dialog dialog) {
			
		}
	};
	
	public RoastBattleMiniGame(GameContext gameContext, MafiosiGameContext mafiosiGameContext) {
		this.gameContext = gameContext;
		this.mafiosiGameContext = mafiosiGameContext;
	}

	@Override
	public void initialise() {
		mafiosiGameContext.getDialogManager().addListener(afterAllDialogsListener);
		// 1. for each player, add a dialog with a special roast punch line
		initialiseOtherMafiosis();
	}

	@Override
	public void cleanup() {
		mafiosiGameContext.getDialogManager().removeListener(afterAllDialogsListener);
		firedRoasts.clear();
	}

	@Override
	public void update(float delta) {
		
	}
	
	// Evaluate the game result by aggregating a mafiosis choice against other choices
	private MiniGameResult evaluateResult() {
		Map<String, Integer> playerResults = new HashMap<String, Integer>();
		// If you beat all others you get full points!
		final int MULTIPLIER = Config.MAXIMUM_POINTS / (firedRoasts.size() - 1);
		for (Entry<Mafiosi, Roast> roastEntry : firedRoasts.entrySet()) {
			int score = 0;
			Mafiosi mafiosi = roastEntry.getKey();
			Roast roast = roastEntry.getValue();
			for (Entry<Mafiosi, Roast> otherRoasts : firedRoasts.entrySet()) {
				if ( otherRoasts.getKey() != mafiosi) {
					// Compare a players roast against all others to calculate a score
					score += roast.compareTo(otherRoasts.getValue()) * Math.max(MULTIPLIER, 1);
					score = Math.max(score, 0);
				}
			}
			playerResults.put(mafiosi.getName(), score);
		}
		System.out.println(firedRoasts);
		return new MiniGameResult(firedRoasts.size(), playerResults);
	}
	
	private void initialiseOtherMafiosis() {
		for (Mafiosi mafiosi : mafiosiGameContext.getCandidates()) {
			if (mafiosi != mafiosiGameContext.getPlayerMafiosi()) {
				Roast roast = roastPool.getRandomRoast();
				fireRoast(mafiosi, roast);
			}
		}
	}
	
	private void fireRoast(Mafiosi player, Roast roast) {
		firedRoasts.put(player, roast);
		mafiosiGameContext.getDialogManager().addDialog(player.getName(), roast.getMessageKey(), player.getAvatarId());
	}

}
