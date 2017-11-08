package Display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import App.Case;
import App.Game;
import App.GameManagerPanel;
import App.Window;
import Sprite.Sprite;

public class BoardDisplay {

	private int offsetX = 50;
	private int offsetY = 50;
    private int nbCaseX;
    private int nbCaseY;
    private int boardWidth;
    private int boardHeight;
	private ArrayList<Sprite> spritesList = new ArrayList<Sprite>();

	private static BoardDisplay instance = null;
	
	public static BoardDisplay getInstance() {
		if(BoardDisplay.instance == null) {
			BoardDisplay.instance = new BoardDisplay();
		}
		return BoardDisplay.instance;
	}
	
	private BoardDisplay () {

	}

	public void initBoard() {
	    this.nbCaseX = Game.getInstance().getBoard().getWidth();
	    this.nbCaseY = Game.getInstance().getBoard().getHeight();
	    this.boardWidth = 20;
	    this.boardHeight = 20;
	    Window.getInstance().setSize(20*nbCaseX+120, 20*nbCaseY+120);	
	}
/*
	private void endScreen(boolean win) {
		if(!endScreenShow) {
			if(win) {
				this.add(this.playerText);
			    this.add(this.playerSendButton);
			    this.playerText.addTextListener(this);
			    this.playerText.setLocation(100, 50 + 10*nbCaseY);
			    this.playerSendButton.setLocation(300, 50 + 10*nbCaseY);
			} else {
			    this.add(this.replayButton);
			    this.add(this.viewScoreButton);
			    this.replayButton.setLocation(100, 50 + 10*nbCaseY);
			    this.viewScoreButton.setLocation(300, 50 + 10*nbCaseY);
			}
		    this.endScreenShow = true;
		    this.revalidate();	
		}
	}
*/
	public void displayCase(Graphics g, Case c) {
		int x,y;
    	x = offsetX + c.getX()*boardWidth;
    	y = offsetY + c.getY()*boardHeight;

		if(c.isHidden()) {
    		g.setColor(Color.CYAN);
    		g.fillRect(x, y, boardWidth, boardHeight);
    	}

		g.setColor(Color.GRAY);
		g.drawRect(x, y, boardWidth, boardHeight);
		switch(c.getDisplayType()) {
			case "String":
		        g.drawString(c.toString(), x + boardWidth/2, y + boardWidth/2 + boardWidth/4);
			break;
			case "Sprite":
			try {
				c.drawSprite(GameManagerPanel.getInstance(), g, x, y);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			default:
				break;
		}		
	}

	public int[] getReelXY(MouseEvent e) {
		int x = (e.getX()-offsetX)/boardWidth;
		int y = (e.getY()-offsetY)/boardHeight;
		if(x >= 0 && y >= 0 && x < nbCaseX && y < nbCaseY) {
			int result[] = {x, y};
			return result;
		}

		return null;
	}

	public void draw(Graphics g) {
	    ArrayList<Case> cases = Game.getInstance().getBoard().getCases();
	    g.clearRect(0, 0, 20*nbCaseX+100, 20*nbCaseY+100);
	    for(int i = 0; i < cases.size(); i++) {
	    	BoardDisplay.getInstance().displayCase(g, cases.get(i));
	    }
		g.drawRect(50, 10, 200, 30);
		if(Game.getInstance().isFinish()) {
			g.setColor(new Color(150, 150, 150, 60));
			g.fillRect(50, 50, 20*nbCaseX, 20*nbCaseY);
			g.setColor(Color.GRAY);
		}
		
		for(Sprite s: this.spritesList) {
//			s.draw(GameManagerPanel.getInstance(), g, x, y);
		}
	}

	public void addSprite(Sprite sprite) {
		this.spritesList.add(sprite);
	}
}
