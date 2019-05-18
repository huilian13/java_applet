package kevin.config;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 游戏配置类
 */
public class GameConfig {
    /**
     * 私有化构造函数
     */
    private GameConfig(){}

    /**
     * 文件路径
     */
    private static final String PATH="data/config.xml";

    /**
     * 窗口配置
     */
    private static FrameConfig frameConfig;

    /**
     * 系统配置
     */
    private static SystemConfig systemConfig;

    static {
        try {
            //创建文件读取器
            SAXReader reader=new SAXReader();
            //读取配置文件，生成文档对象
            Document doc=reader.read(PATH);
            //获取根标签
            Element root=doc.getRootElement();
            //创建窗口配置对象
            frameConfig=new FrameConfig(root.element("frame"));
            //创建系统配置对象
            systemConfig=new SystemConfig(root.element("system"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static FrameConfig getFrameConfig() {
        return frameConfig;
    }

    public static SystemConfig getSystemConfig() {
        return systemConfig;
    }
}
