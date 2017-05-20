package tv.rocketbeans.supermafiosi.minigame;

import de.bitbrain.braingdx.GameContext;
import tv.rocketbeans.supermafiosi.core.Dialog;
import tv.rocketbeans.supermafiosi.core.DialogManager.DialogManagerListener;
import tv.rocketbeans.supermafiosi.core.Mafiosi;
import tv.rocketbeans.supermafiosi.core.MafiosiGameContext;

public class RoastBattleMiniGame extends AbstractMiniGame {
	
	private final GameContext gameContext;
	private final MafiosiGameContext mafiosiGameContext;
	
	private boolean playersTurn;
	
	private final DialogManagerListener afterAllDialogsListener = new DialogManagerListener() {
		@Override
		public void afterLastDialog() {
			if (!playersTurn) {
				playersTurn = true;
				// 2. add UI for selection
				// 3. after all punch lines have been said the player can choose an own punchline
				// 4. player tells the punchline
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
		for (Mafiosi mafiosi : mafiosiGameContext.getCandidates()) {
			if (mafiosi != mafiosiGameContext.getPlayerMafiosi()) {
				// Not the player, let's add punch lines!
			}
		}
	}

	@Override
	public void cleanup() {
		mafiosiGameContext.getDialogManager().removeListener(afterAllDialogsListener);
	}

	@Override
	public void update(float delta) {
		
		// 5. evaluate result and notify jury when all finished
	}

}
