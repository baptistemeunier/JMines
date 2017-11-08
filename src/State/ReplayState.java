package State;

import java.awt.Button;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import App.Log;
import Display.BoardDisplay;
import App.Game;
import App.GameManager;
import App.GameManagerPanel;

public class ReplayState implements GameState, ActionListener {

	private Button replayBtn = new Button("Rejouer");
	private Button viewScoreBtn = new Button("Voir les scores");

	public ReplayState() {
	}
	
	@Override
	public void init() {
    	Log.debug("State \"ReplayState\" init");
	    GameManagerPanel.getInstance().repaint();
	    this.viewScoreBtn.setEnabled(false);
	    this.showButton();
	}

	@Override
	public void update(double deltatime) {
	}

	@Override
	public void draw(Graphics g) {
		this.drawBase(g);
	}

	protected void drawBase(Graphics g) {
		BoardDisplay.getInstance().draw(g);
		if(Game.getInstance().isWin()) {
			g.drawString("Vous avez gagné en " + Game.getInstance().getTimeFormat() + " ;)", 50, 30);
		} else {
			g.drawString("Vous avez perdu en " + Game.getInstance().getTimeFormat() + " ! :(", 50, 30);			    
		}
	}
	@Override
	public void handleEvent() {
		// TODO Auto-generated method stub
		
	}

	@Override 
	public void pause() {
    	Log.debug("State \"ReplayState\" pause");
	    this.hideButton();
	}

	@Override
	public void resume() {
    	Log.debug("State \"ReplayState\" resume");
	    this.showButton();
	}

	@Override
	public void release() {
    	Log.debug("State \"ReplayState\" release");
	    this.hideButton();
	}
	
	private void showButton() {
	    GameManagerPanel.getInstance().add(this.replayBtn);
	    GameManagerPanel.getInstance().add(this.viewScoreBtn);
	    this.replayBtn.setLocation(100, 50 + 10*Game.getInstance().getBoard().getWidth());
	    this.viewScoreBtn.setLocation(300, 50 + 10*Game.getInstance().getBoard().getHeight());
	    this.replayBtn.addActionListener(this);
	    this.viewScoreBtn.addActionListener(this);
	    GameManagerPanel.getInstance().revalidate();
	}
	
	private void hideButton() {
	    GameManagerPanel.getInstance().remove(this.replayBtn);
	    GameManagerPanel.getInstance().remove(this.viewScoreBtn);		
	    this.replayBtn.removeActionListener(this);
	    this.viewScoreBtn.removeActionListener(this);
	    GameManagerPanel.getInstance().revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == this.replayBtn) {
			GameManager.getInstance().setState(new PlayingState());
		} else if(event.getSource() == this.viewScoreBtn) {
			
		}
	}
}