package tv.rocketbeans.supermafiosi.minigame;

// A minigame contains internal logic for different minigames
public interface MiniGame {
	
	interface MiniGameListener {
		void onComplete(MiniGame miniGame, MiniGameResult result);
	}
	
	void initialise();
	
	void cleanup();
	
	void addListener(MiniGameListener listener);
	
	void update(float delta);

}
