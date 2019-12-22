package top.yifan.constant;

/**
 * 方向枚举
 *
 * @author star
 */
public enum Direction {

    /**
     * 方向：上、下、左、右
     */
    UP(-1), DOWN(1), LEFT(-2), RIGHT(2);

    /**
     * 方向值
     */
    private int direction;

    Direction(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }
}
