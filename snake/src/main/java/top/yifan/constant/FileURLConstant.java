package top.yifan.constant;

import top.yifan.factory.GameFactory;

/**
 * FileURLConstant
 *
 * @author star
 */
public final class FileURLConstant {

    private FileURLConstant() {

    }

    /**
     * 配置文件路径
     */
    public static final String CONFIG_PATH = "data/config.xml";

    /**
     * 控制文件路径
     */
    public static final String CONTROL_PATH = GameFactory.getDataMap().get("control");

    /**
     * 方向控制路径
     */
    public static final String DIRECTION_PATH = GameFactory.getDataMap().get("direction");
}
