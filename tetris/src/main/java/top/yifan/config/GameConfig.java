package top.yifan.config;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import top.yifan.utils.FileLoadUtil;

/**
 * 游戏配置类
 *
 * @author star
 */
public class GameConfig {
    /**
     * 私有化构造函数，无法创建对象
     */
    private GameConfig() {
    }

    /**
     * 游戏界面配置
     */
    private static FrameConfig frameConfig = null;

    /**
     * 游戏数据配置
     */
    private static DataConfig dataConfig = null;

    /**
     * 游戏数据配置
     */
    private static SystemConfig systemConfig = null;

    /**
     * 配置文件路径
     */
    private static final String CONFIG_PATH = "data/config.xml";


    /**
     * 静态初始化
     */
    static {
        try {
            // 获取文件读取器
            SAXReader reader = new SAXReader();
            // 读取xml文件
            Document doc = reader.read(FileLoadUtil.loadAsURL(CONFIG_PATH).getFile());
            // 读取根元素
            Element gameRoot = doc.getRootElement();
            // 创建游戏界面配置对象
            frameConfig = new FrameConfig(gameRoot.element("frame"));
            // 创建游戏数据配置对象
            dataConfig = new DataConfig(gameRoot.element("data"));
            // 创建系统配置对象
            systemConfig = new SystemConfig(gameRoot.element("system"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static FrameConfig getFrameConfig() {
        return frameConfig;
    }

    public static DataConfig getDataConfig() {
        return dataConfig;
    }

    public static SystemConfig getSystemConfig() {
        return systemConfig;
    }

}
