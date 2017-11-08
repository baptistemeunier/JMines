package State;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Button;
import java.awt.Graphics;

import App.Log;
import App.GameManager;
import App.GameManagerPanel;

public class StartState implements GameState, ActionListener {
	
	private Button startBtn = new Button("Commencer une partie");
	
	public StartState() {

	}
	
	@Override
	public void init() {
    	Log.debug("State \"StartState\" init");
    	this.showButton();
	}

	@Override
	public void update(double deltatime) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleEvent() {
		// TODO Auto-generated method stub
		
	}

	@Override 
	public void pause() {
    	Log.debug("State \"StartState\" pause");
    	this.hideButton();
	}

	@Override
	public void resume() {
    	Log.debug("State \"StartState\" resume");
    	this.showButton();
	}

	@Override
	public void release() {
    	Log.debug("State \"StartState\" release");
    	this.hideButton();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == this.startBtn) {
			Log.debug("User click on start new game");
			GameManager.getInstance().setState(new PlayingState());
		}
	}

	private void showButton() {
	    this.startBtn.setEnabled(true);
	    this.startBtn.addActionListener(this);			
		GameManagerPanel.getInstance().add(this.startBtn);
	}

	private void hideButton() {
	    this.startBtn.setEnabled(false);
		GameManagerPanel.getInstance().remove(this.startBtn);
		this.startBtn.removeActionListener(this);
	}
}