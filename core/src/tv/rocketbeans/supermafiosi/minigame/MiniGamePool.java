package tv.rocketbeans.supermafiosi.minigame;

import java.util.List;

public class MiniGamePool {

	private final List<MiniGame> availableMiniGames;
	
	public MiniGamePool(List<MiniGame> availableMiniGames) {
		this.availableMiniGames = availableMiniGames;
	}
	
	public MiniGame fetchNext() {
		return availableMiniGames.remove(0);
	}
	
	public boolean hasMiniGamesLeft() {
		return !availableMiniGames.isEmpty();
	}
}
