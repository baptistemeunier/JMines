package App.Task;

import java.util.TimerTask;

import App.GameManager;
import App.GameManagerPanel;

public class GamePanelTask extends TimerTask {
	public GamePanelTask() {	}
	
	public void run() {
		GameManager.getInstance().getState().update(1000/GameManagerPanel.frameRate);
		GameManagerPanel.getInstance().repaint();
	}
}
