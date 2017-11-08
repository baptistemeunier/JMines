package App;

import java.util.ArrayList;

public class Board {
	private ArrayList<Case> cases;
	private int width;
	private int height;

	public Board(int width, int height, int nbMines) {
		this.width = width;
		this.height = height;
		this.cases = new ArrayList<Case>();
		this.createBoard(nbMines);
	}
	
	private void createBoard(int nbMines) {
		this.cases.clear();
		/** Create all case **/
		int x;
		for	(int i = 0; i < this.width*this.height;i++) {
			x = i%this.width;
			this.cases.add(new Case(x , (i-x)/this.width));
		}
		int mineAnBoard = 0;
		int i;
		/** Place mine on the board **/
		ArrayList<Integer> minesIndex = new ArrayList<Integer>();
		while(mineAnBoard != nbMines) {
			i = (int) Math.floor(Math.random()*this.height*this.width);
			if(!this.getCase(i).isMine()){
				minesIndex.add(i);
				this.getCase(i).setMine(true);
				mineAnBoard++;
			}
		}
		for(int index: minesIndex) {
			for(Case c : this.getAdjacentCases(index)) {
				c.incrementNumber();
			}
		}
		minesIndex = null;
	}
	public ArrayList<Case> getCases(){
		return this.cases;
	}
	
	public ArrayList<Case> getAdjacentCases(int index) {
		ArrayList<Case> cases = new ArrayList<Case>();
		boolean hasTop    = index >= this.width;
		boolean hasBottom = index <= this.width*(this.height-1);
		boolean hasLeft   = index%this.width != 0;
		boolean hasRight  = index%(this.width) != (this.width-1);			
		if(hasTop) {
			cases.add(this.getCase(index-this.width));
			if(hasLeft) {
				cases.add(this.getCase(index-this.width-1));
			}
			if(hasRight) {
				cases.add(this.getCase(index-this.width+1));
			}
		}
		if(hasLeft) {
			cases.add(this.getCase(index-1));
		}
		if(hasRight) {
			cases.add(this.getCase(index+1));
		}
		if(hasBottom) {
			cases.add(this.getCase(index+this.width));
			if(hasLeft) {
				cases.add(this.getCase(index+this.width-1));
			}
			if(hasRight) {
				cases.add(this.getCase(index+this.width+1));
			}
		}
		return cases;
	}
	
	public Case getCase(int index) {
		try {
			return this.cases.get(index);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	public Case getCase(int x, int y) {
		Case c;
		try {
			c = this.cases.get(y*width+x);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
		return c;
	}
	
	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

}