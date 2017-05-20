package tv.rocketbeans.supermafiosi.minigame;

import java.util.Map;

// Contains the result of the played minigame
public class MiniGameResult {

	private final Map<String, Integer> playerResults;
	private final int maximumPoints;
	
	MiniGameResult(int maximumPoints, Map<String, Integer> playerResults) {
		this.maximumPoints = maximumPoints;
		this.playerResults = playerResults;
	}
	
	public int getMaximumPoints() {
		return maximumPoints;
	}
	
	public int getPlayerScore(String playerName) {
		Integer playerPoints = playerResults.get(playerName);
		// Return the percentage (rounded) as points
		return playerPoints != null ? Math.round(10 * (float)playerPoints / (float)getMaximumPoints()) : 0;
	}
	
	@Override
	public String toString() {
		return "Mini Game complete:" + playerResults.toString();
	}
}
