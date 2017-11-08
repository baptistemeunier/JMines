package App.Task;

import java.util.TimerTask;

import App.Game;

public class GameTask extends TimerTask {
	private Game game;
	public GameTask(Game game) {
		this.game = game;
	}
	public void run() {
		game.addTime(1000);
	}
}
