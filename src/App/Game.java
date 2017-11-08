package App;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import State.LooseState;
import State.WinState;

public class Game {

	private Board board;
	private int nbMinesLeft;
	private boolean finish, win;
	private double time;

	private static Game instance;
	
	public static Game getInstance() {
		if(instance == null) {
			instance = new Game();
		}
		return instance;
	}
	public Game() {
	
	}
	public void startGame() {
		this.finish = false;
		this.time = 0;
		this.nbMinesLeft = 10;
		this.board = new Board(10,10,10);
	}
	
	public void addTime(double time) {
		this.time += time;
	}
	
	public String getTimeFormat() {
		int heure   = (int) (time/1000) / 60 / 60 % 24;
		int minute  = (int) (time/1000) / 60 % 60;
		int seconde = (int) (time/1000) % 60;
		String format = "";
		if(heure>0) {
			format += ((heure<10)?"0":"") + heure + ":";
		}
		return format + ((minute<10)?"0":"") + minute +":" + ((seconde<10)?"0":"") + seconde;
	}
	
	public void drawGameOnConsole(){
		int width = this.board.getWidth();
		ArrayList<Case> cases = this.board.getCases();
		for(int i = 0; i < cases.size();i++) {
			if(cases.get(i).getX() == 0) {
				System.out.print('\n');
			}
			if(cases.get(i).isMine()) {
				System.out.print("X ");				
			} else {
				System.out.print(cases.get(i).getNumber() + " ");				
			}
		}
		System.out.print('\n');		
	}

	public Board getBoard() {
		return this.board;
	}
	
	public void reveal(int x, int y) {
		int index = y*this.board.getWidth()+x;
		try {
			if(this.board.getCase(x, y).isHidden()) {
				this.reveal(index, new ArrayList<Integer>(), false);
			} else if(this.board.getCase(index).getNumber() != 0){
				int[] check = this.checkFlagCorrect(index);
				if(check[0] != 0) {
					this.loose(x, y);
				} else if(check[1] == 0) {
					this.reveal(index, new ArrayList<Integer>(), true);		
				}
			}			
		}catch(Exception e) {
			Log.error(x + " y:" + y + "::" + index);
			Log.error(e.getCause() + "//" + e.getMessage());
			e.getStackTrace();
		}
	}

	private int[] checkFlagCorrect(int index) {

		int nbMinesLeft = this.board.getCase(index).getNumber();
		int nbErrors    = 0;

		for(Case c:this.board.getAdjacentCases(index)) {
			if(c.isHidden()) {
				if(!c.isMine() && c.isFlaged()) {
					nbErrors++;					
				} else if(c.isMine() && c.isFlaged()) {
					nbMinesLeft--;
				}
			}
		}
		int result[] = {nbErrors, nbMinesLeft};
		return result;
	}

	private void reveal(int index, ArrayList<Integer> listvisited, boolean isANumber) {
		if(!listvisited.contains(index) && !this.board.getCase(index).isMine()) {
			this.board.getCase(index).reveal();
			listvisited.add(index);
			if(this.board.getCase(index).getNumber() == 0 || isANumber) {
				for(Case c: this.board.getAdjacentCases(index)) {
					this.reveal(c.getX() + c.getY()*this.board.getWidth(), listvisited, false);
				}
			}
		}
	}

	private void loose(int x, int y) {
		this.finish = true;
		this.win = false;
		GameManager.getInstance().setState(new LooseState());
	}
	
	private void checkWin() {	
		ArrayList<Case> cases = this.board.getCases();
		int i = 0;
		while(i < cases.size()) {
			if(cases.get(i).isHidden() && !cases.get(i).isMine()) {
				break;
			}
			i++;
		}
		if(i == cases.size()) {
			this.finish = true;
			this.win    = true;
			GameManager.getInstance().setState(new WinState());;
		}
	}
	
	public int getNbMineLeft() {
		return this.nbMinesLeft;
	}

	public void changeFlag(Case c) {
			try {
				this.nbMinesLeft += ((c.changeFlag())?-1:+1);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	public boolean isFinish() {
		return this.finish;
	}

	public boolean isWin() {
		return this.win;
	}

	public void mouseClick(int x, int y, MouseEvent e) {
    	Case c = this.board.getCase(x, y);
    	if(c != null) {
			if(e.getButton() == MouseEvent.BUTTON1) {
				if(c.isMine()){
					this.loose();
				} else {
					this.reveal(x, y);
				}
			} else {
				this.changeFlag(c);
			}
			checkWin();
		}
	}
}