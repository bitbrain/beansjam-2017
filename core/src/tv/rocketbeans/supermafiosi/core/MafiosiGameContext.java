package tv.rocketbeans.supermafiosi.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tv.rocketbeans.supermafiosi.minigame.MiniGame;

public class MafiosiGameContext {
	
	private final List<Mafiosi> candidates;
	
	private final Mafiosi player;
	
	private final DialogManager dialogManager;
	
	private final Map<String, Integer> bullets;
	
	private final List<MiniGame> miniGames;
	
	public MafiosiGameContext(List<Mafiosi> candidates, List<MiniGame> miniGames, Mafiosi player, DialogManager dialogManager) {
		this.candidates = candidates;
		this.player = player;
		this.dialogManager = dialogManager;
		this.bullets = new HashMap<String, Integer>();
		this.miniGames = miniGames;
	}

	public Mafiosi getPlayerMafiosi() {
		return player;
	}

	public List<Mafiosi> getCandidates() {
		return candidates;
	}
	
	public DialogManager getDialogManager() {
		return dialogManager;
	}
	
	public int getNumberOfBullets(String candidateName) {
		if (bullets.containsKey(candidateName)) {
			return bullets.get(candidateName);
		} else {
			return 0;
		}
	}
	
	public int getNumberOfBulletSlots() {
		return Math.max(miniGames.size(), 2); // do not count the last game = roulette
	}
	
	public void addBullet(String candidateName) {
		Integer value = bullets.get(candidateName);
		if (bullets.get(candidateName) == null) {
			bullets.put(candidateName, 1);
		} else {
			bullets.put(candidateName, ++value);
		}
	}
}
