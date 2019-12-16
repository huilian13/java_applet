package top.yifan.config;

import org.dom4j.Element;

/**
 * 窗口配置类
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
     * 开始按钮的y坐标
     */
    private int btn_startY;

    /***
     * 返回按钮的y坐标
     */
    private int btn_backY;

    public FrameConfig(Element frame) {
        // 获取参数的值
        this.width=Integer.parseInt(frame.attributeValue("width"));
        this.height=Integer.parseInt(frame.attributeValue("height"));
        this.btn_startY=Integer.parseInt(frame.attributeValue("btn_startY"));
        this.btn_backY=Integer.parseInt(frame.attributeValue("btn_backY"));
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getBtn_startY() {
        return btn_startY;
    }

    public int getBtn_backY() {
        return btn_backY;
    }
}
