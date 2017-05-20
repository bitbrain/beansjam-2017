package tv.rocketbeans.supermafiosi.core;

import java.util.List;

public class MafiosiGameContext {
	
	private final List<Mafiosi> candidates;
	
	private final Mafiosi player;
	
	private final DialogManager dialogManager;
	
	public MafiosiGameContext(List<Mafiosi> candidates, Mafiosi player, DialogManager dialogManager) {
		this.candidates = candidates;
		this.player = player;
		this.dialogManager = dialogManager;
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
}
