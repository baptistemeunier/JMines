package App;

import javax.swing.JPanel;

import App.Task.GamePanelTask;
import State.StartState;

import java.awt.Graphics;
import java.util.Timer;

public class GameManagerPanel extends JPanel {

	private static GameManagerPanel instance  = null;
	
	private Timer routine;
	public static int frameRate = 60;
	public static GameManagerPanel getInstance() {
		if(GameManagerPanel.instance == null) {
			GameManagerPanel.instance = new GameManagerPanel();
		}
		return GameManagerPanel.instance;
	}

	private GameManagerPanel() {
		Log.debug("GameManagerPanel");
	}
	
	public void init() {
		GameManager.getInstance().pushState(new StartState());
		routine = new Timer();
		routine.schedule(new GamePanelTask(), 1000, 1000/GameManagerPanel.frameRate);
	}
	
	public void paintComponent(Graphics g){
		if(GameManager.getInstance().getState() != null) {
			GameManager.getInstance().getState().draw(g);
		}
	}

}