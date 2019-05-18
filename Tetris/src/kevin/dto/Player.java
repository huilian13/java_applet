package kevin.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Player implements Comparable<Player>,Serializable{
	
	private String name;
	
	private int score;

	public Player(String name,int score) {
		super();
		this.name = name;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

	@Override
	public int compareTo(Player p) {
		//从大到小排序
		return p.score-this.score;
		//从小到大排序 this.score-p.score
	}
	
}
