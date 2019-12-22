package top.yifan.factory;

import top.yifan.config.ButtonConfig;
import top.yifan.config.GameConfig;

import java.util.HashMap;

/**
 * 游戏工厂
 *
 * @author star
 */
public class GameFactory {
    /**
     * 私有化构造函数
     */
    private GameFactory() {
    }

    /**
     * 按钮配置
     */
    private static ButtonConfig buttonConfig = null;

    /**
     * 数据集合
     */
    private static HashMap<String, String> dataMap = new HashMap<String, String>();

    static {
        try {
            // 按钮配置
            buttonConfig = GameConfig.getFrameConfig().getButtonConfig();
            // 数据集合
            dataMap = GameConfig.getDataConfig().getDataMap();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ButtonConfig getButtonConfig() {
        return buttonConfig;
    }

    public static HashMap<String, String> getDataMap() {
        return dataMap;
    }

}
