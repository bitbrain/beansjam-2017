package tv.rocketbeans.supermafiosi.minigame.roulette;

import tv.rocketbeans.supermafiosi.core.MafiosiGameContext;
import tv.rocketbeans.supermafiosi.minigame.AbstractMiniGame;

/**
 * In this final mini game the players need to pull the trigger in round
 * robin. The chances are higher for a shot if more bullets are loaded.
 */
public class RouletteMiniGame extends AbstractMiniGame {
	
	private final MafiosiGameContext context;
	
	public RouletteMiniGame(MafiosiGameContext context) {
		this.context = context;
	}

	@Override
	public void initialise() {
		
	}

	@Override
	public void cleanup() {
		
	}

	@Override
	public void update(float delta) {
		
	}

}
