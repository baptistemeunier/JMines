import App.*;
import App.Task.GamePanelTask;
import Display.BoardDisplay;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.TextField;

public class GamePanel extends JPanel implements MouseListener, ActionListener, TextListener {
	
	private Game game;
/*	private int offsetX = 50;
	private int offsetY = 50;
    private int nbCaseX;
    private int nbCaseY;
    private int boardWidth;
    private int boardHeight;
*/
	private Window window;
	private Timer timer;
	
	private JButton replayButton;
	private JButton viewScoreButton;
	private TextField playerText;
	private JButton playerSendButton;
	private boolean endScreenShow = false;
	
	GamePanel() {
		this.window = Window.getInstance();
	    this.replayButton = new JButton("Rejouer");
	    this.viewScoreButton = new JButton("Voir les scores");
	    this.viewScoreButton.setEnabled(false);
	    this.replayButton.setActionCommand("replay");
	    this.replayButton.addActionListener(this);	
	    this.playerText = new TextField("Votre pseudo");
	    this.playerText.setEditable(true);
	    this.playerText.setColumns(10);
	    this.playerSendButton = new JButton("Valider");
	    this.playerSendButton.setEnabled(false);
	    this.playerSendButton.addActionListener(this);	
	    
		this.init();
	}
	
	private void init() {

		game = new Game();
		game.drawGameOnConsole();
		timer = new Timer();
		timer.schedule(new GamePanelTask(), 1000, 1000/10);
/*
	    nbCaseY = this.game.getBoard().getHeight();
	    boardWidth = 20;
	    boardHeight = 20;
*/
		BoardDisplay.getInstance().initBoard();
	    this.addMouseListener(this);
	}

	public void paintComponent(Graphics g){
		BoardDisplay.getInstance().draw(g);
	}
	
	public Game getGame() {
		return this.game;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(!game.isFinish()) {
			int xy[] = BoardDisplay.getInstance().getReelXY(e);
	    	game.mouseClick(xy[0], xy[1], e);
			this.repaint();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent action) {
		if(action.getSource() == this.replayButton) {
			this.remove(replayButton);
			this.remove(viewScoreButton);
			this.revalidate();
			this.init();
		} else if(action.getSource() == this.playerSendButton) {
			this.remove(playerText);
			this.remove(playerSendButton);
			this.endScreenShow = false;	
		//	this.endScreen(false);
		}
	}

	@Override
	public void textValueChanged(TextEvent event) {
		if(event.getSource() == this.playerText) {
			if(this.playerText.getText().length() > 5 && this.playerText.getText() != "Votre pseudo") {
				this.playerSendButton.setEnabled(true);
			} else {
				this.playerSendButton.setEnabled(false);
			}
		}
	}
}