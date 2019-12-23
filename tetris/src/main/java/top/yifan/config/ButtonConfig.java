package top.yifan.config;

import org.dom4j.Element;

import java.io.Serializable;

/**
 * 按钮配置
 *
 * @author star
 */
public class ButtonConfig implements Serializable {

	private static final long serialVersionUID = -6639353780273499035L;

	/**
     * 按钮的宽度
     */
    private int width;

    /**
     * 按钮的高度
     */
    private int height;

    /**
     * 开始按钮的 x 坐标
     */
    private int startX;

    /**
     * 开始按钮的 y 坐标
     */
    private int startY;

    /**
     * 设置按钮的 x 坐标
     */
    private int settingX;

    /**
     * 设置按钮的 y 坐标
     */
    private int settingY;

    public ButtonConfig(Element button) {

        this.width = Integer.parseInt(button.attributeValue("width"));
        this.height = Integer.parseInt(button.attributeValue("height"));
        // 获取 start 标签
        Element start = button.element("start");
        this.startX = Integer.parseInt(start.attributeValue("x"));
        this.startY = Integer.parseInt(start.attributeValue("y"));
        // 获取 setting 标签
        Element setting = button.element("setting");
        this.settingX = Integer.parseInt(setting.attributeValue("x"));
        this.settingY = Integer.parseInt(setting.attributeValue("y"));

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getSettingX() {
        return settingX;
    }

    public int getSettingY() {
        return settingY;
    }

}

