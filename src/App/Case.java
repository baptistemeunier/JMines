package App;

import java.awt.Graphics;

import javax.swing.JPanel;

import State.PlayingState;

public class Case {
	private int number = 0;
	private boolean mine = false;
	private boolean hidden = true;
	private boolean flag = false;
	private int x, y;
	private FlagSprite flagSprite;
	
	public Case(int x, int y) {
		this.x = x;
		this.y = y;
		this.mine = false;
	}

	Case(int x, int y, boolean mine) {
		this.x = x;
		this.y = y;
		this.mine = mine;
	}
	
	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
	
	public int getNumber(){
		return this.number;
	}
	
	public boolean isMine() {
		return this.mine;
	}

	public void setMine(boolean mine){
		this.mine = mine;
	}

	public boolean isHidden() {
		return this.hidden;
	}
	public void incrementNumber() {
		if(!this.mine) {
			this.number++;			
		}
	}
	
	public void reveal() {
		flagSprite = null;
		this.hidden = false;
	}

	public boolean changeFlag() throws Exception {
		if(!this.hidden) {
			throw new Exception("Imposible de changer le flag d'une case hidden");
		}
		if(this.flag) {
			this.flag = false;
			flagSprite = null;
		} else {
			this.flag = true;
			flagSprite = new FlagSprite();			
		}
		return this.flag;
	}

	public boolean isFlaged() {
		return this.flag;
	}

	public String toString() {
		if(this.hidden && this.flag) {
			return "F";
		}
		if(!this.hidden && this.mine) {
			return "X";
		}
		if(this.hidden || this.number == 0) {
			return " ";
		}
		return (new Integer(this.number)).toString();
	}

	public String getDisplayType() {
		if(this.flagSprite != null) {
			return "Sprite";
		}
		return "String";
	}

	public void drawSprite(JPanel panel,Graphics g, int x, int y) throws Exception {
		if(this.flagSprite == null) {
			throw new Exception("Imposible de dessiner le flag");			
		}
		this.flagSprite.draw(panel, g, x, y);
		this.flagSprite.update(1000/60);
	}

}
