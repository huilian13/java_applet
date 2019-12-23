package top.yifan.dto;

import java.io.Serializable;

/**
 * 玩家类
 *
 * @author star
 */
public class Player implements Comparable<Player>, Serializable {

	private static final long serialVersionUID = -5482281937014329674L;

	private String name;

    private int score;

    public Player(String name, int score) {
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
        // 从大到小排序
        return p.score - this.score;
        // 从小到大排序 this.score - p.score
    }

}

