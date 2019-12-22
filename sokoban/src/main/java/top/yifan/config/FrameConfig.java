package top.yifan.config;

import org.dom4j.Element;

/**
 * 窗口界面配置
 *
 * @author star
 */
public class FrameConfig {
    /**
     * 窗口宽度
     */
    private int width;

    /**
     * 窗口高度
     */
    private int height;

    /**
     * 窗口标题
     */
    private String title;

    /**
     * 箱子图片尺寸大小
     */
    private int size;

    public FrameConfig(Element frame) {
        // 初始化属性值
        this.width = Integer.parseInt(frame.attributeValue("width"));
        this.height = Integer.parseInt(frame.attributeValue("height"));
        this.title = frame.attributeValue("title");
        this.size = Integer.parseInt(frame.attributeValue("size"));
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getTitle() {
        return title;
    }

    public int getSize() {
        return size;
    }
}
