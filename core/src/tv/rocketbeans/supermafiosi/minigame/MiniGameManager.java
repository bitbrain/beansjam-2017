package tv.rocketbeans.supermafiosi.minigame;

import java.util.List;

import com.badlogic.gdx.Gdx;

import de.bitbrain.braingdx.behavior.BehaviorAdapter;
import de.bitbrain.braingdx.world.GameObject;

/**
 * Manages mini games by accessing a minigame pool.
 */
public class MiniGameManager extends BehaviorAdapter {

	private final MiniGamePool pool;
	
	private MiniGame currentMiniGame;
	
	private boolean isMinigameActive;
	
	private MiniGame.MiniGameListener listener = new MiniGame.MiniGameListener() {
		@Override
		public void onComplete(MiniGame miniGame, MiniGameResult result) {
			isMinigameActive = false;
			miniGame.cleanup();
			// TODO tell Jury about the results
			System.out.println(result);
		}
	};
	
	public MiniGameManager(List<MiniGame> miniGames) {
		pool = new MiniGamePool(miniGames);
	}
	
	public void triggerNextMiniGame() {
		Gdx.app.log("INFO", "Next minigame triggered!");
		if (pool.hasMiniGamesLeft()) {
			isMinigameActive = true;
			currentMiniGame = pool.fetchNext();
			currentMiniGame.addListener(listener);
			currentMiniGame.initialise();
		}
	}
	
	@Override
	public void update(GameObject source, float delta) {
		if (isMinigameActive) {
			currentMiniGame.update(delta);
		}
	}
}
