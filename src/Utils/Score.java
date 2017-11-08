package Utils;

public class Score {
	private String name;
	private int time;

	public Score(String name, int time) {
		this.name = name;
		this.time = time;
	}
	
	public String getTimeFormat() {
		int heure   = time / 60 / 60 % 24;
		int minute  = time / 60 % 60;
		int seconde = time % 60;
		String format = "";
		if(heure>0) {
			format += ((heure<10)?"0":"") + heure + ":";
		}
		return format + ((minute<10)?"0":"") + minute +":" + ((seconde<10)?"0":"") + seconde;
	}

	public String toString() {
		return this.name + ":" + this.time;
	}

	public boolean sup(Score score) {
		return this.time > score.getTime(); 
	}

	public int getTime() {
		return this.time;
	}

	public String getReadible() {
		return this.name + " : " + this.getTimeFormat();
	}
}
