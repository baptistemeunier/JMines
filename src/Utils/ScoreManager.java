package Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.stream.Stream;

import App.Log;

public class ScoreManager {
	private File scoresFile;
	private boolean hasError = false;
	
	private static ScoreManager instance = null;

	ArrayList<Score> scoreEasy = null;
	ArrayList<Score> scoreMedium = null;
	ArrayList<Score> scoreHard = null;
	
	public static ScoreManager getInstance() {
		if(instance == null) {
			instance = new ScoreManager();
		}
		return instance;
	}
	
	private ScoreManager() {
		try {
			this.scoresFile = new File("data/score");
			this.hasError = true;
			Log.debug("Fichier des scores chargé");
		} catch(Exception e) {
			Log.error("Impossible d'ouvrir le fichier de scores");
		}
	}

	public ArrayList<Score> getEasy() {
		if(scoreEasy == null) {
			this.formatScoreFile();
		}
		return this.scoreEasy;
	}

	public boolean addScore(int dificulty, Score score) {
		if(scoreEasy == null) {
			this.formatScoreFile();
		}
		switch(dificulty) {
			case 0:
				this.scoreEasy = addScore(this.scoreEasy, score);
				break;
			case 1:
				this.scoreMedium = addScore(this.scoreMedium, score);				
				break;
			case 2:
				this.scoreHard = addScore(this.scoreHard, score);
				break;
			default:
				return false;
		}
		this.saveFile();
		return true;
	}

	private void saveFile() {
		String content = "!easy\n";
		for(Score s:this.scoreEasy) {
			content += s.toString() + '\n';
		}
		content += "!medium\n";
		for(Score s:this.scoreMedium) {
			content += s.toString() + '\n';
		}
		content += "!hard\n";
		for(Score s:this.scoreHard) {
			content += s.toString() + '\n';
		}
		Log.debug(content);
		try {
			Files.write(Paths.get("data/score"), content.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}

	private ArrayList<Score> addScore(ArrayList<Score> scoreList, Score score) {
		ArrayList<Score> newScoreList = new ArrayList<Score>();
		Iterator<Score> it = scoreList.iterator();
		Score s;
		boolean insert = false;
		while(it.hasNext()) {
			s = it.next();
			if(s.sup(score) && !insert) {
				newScoreList.add(score);
				insert = true;
			}
			newScoreList.add(s);
		}
		if(!insert) {
			newScoreList.add(score);
		}
		return newScoreList;
	}

	private void formatScoreFile() {
		this.scoreEasy   = new ArrayList<Score>();
		this.scoreMedium = new ArrayList<Score>();
		this.scoreHard   = new ArrayList<Score>();

		int type = 0;
		try (Stream<String> stream = Files.lines(Paths.get("data/score"))) {
	        Object scoresArray[] = stream.toArray();
	        Score score;
			for(int i = 0; i < scoresArray.length;i++) {
				String el = scoresArray[i].toString();
				if(el.charAt(0) == '!') {
					type++;
				}else {
					String data[] = el.split(":");
					score = new Score(data[0], Integer.parseInt(data[1]));
					if(type == 1) {
						this.scoreEasy.add(score);
					}else if(type == 2) {
						this.scoreMedium.add(score);					
					}else if(type == 3) {
						this.scoreHard.add(score);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}	
}
