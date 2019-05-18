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
		//�Ӵ�С����
		return p.score-this.score;
		//��С�������� this.score-p.score
	}
	
}
