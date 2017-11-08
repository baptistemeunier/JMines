package State;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import App.Log;
import Display.BoardDisplay;
import App.ExplosionSprite;
import App.Game;
import App.GameManagerPanel;

public class PlayingState implements GameState, MouseListener {

		
	private ExplosionSprite test;

	public PlayingState() {
	    this.test = new ExplosionSprite();
	}
	
	@Override
	public void init() {
		
    	Log.debug("State \"PlayingState\" init");
    	Game.getInstance().startGame();
    	BoardDisplay.getInstance().initBoard();
	    GameManagerPanel.getInstance().addMouseListener(this);
	    GameManagerPanel.getInstance().repaint();
	}

	@Override
	public void update(double deltatime) {
		if(!Game.getInstance().isFinish()) {
			Game.getInstance().addTime(deltatime);			
		}
		test.update(deltatime);
	}

	@Override
	public void draw(Graphics g) {
		BoardDisplay.getInstance().draw(g);
		g.drawString(Game.getInstance().getNbMineLeft() + " " + Game.getInstance().getTimeFormat(), 150, 30);
		test.draw(GameManagerPanel.getInstance(), g, 0, 0);
	}

	@Override
	public void handleEvent() {
		// TODO Auto-generated method stub
		
	}

	@Override 
	public void pause() {
    	Log.debug("State \"PlayingState\" pause");
	    GameManagerPanel.getInstance().removeMouseListener(this);
	}

	@Override
	public void resume() {
    	Log.debug("State \"PlayingState\" resume");
	    GameManagerPanel.getInstance().addMouseListener(this);
	}

	@Override
	public void release() {
    	Log.debug("State \"PlayingState\" release");
	    GameManagerPanel.getInstance().removeMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		Game game = Game.getInstance();
		if(!game.isFinish()) {
			int xy[] = BoardDisplay.getInstance().getReelXY(e); 
			if(xy != null) {
		    	game.mouseClick(xy[0], xy[1], e);				
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}