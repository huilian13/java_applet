package top.yifan.constant;

/**
 * FigureType 物体类型
 *
 * @author star
 */
public enum FigureType {

    SPACE(0), BARRIER(1), BOX(2), COOP(4), PLAYER(8);

    private int value;

    FigureType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    }
