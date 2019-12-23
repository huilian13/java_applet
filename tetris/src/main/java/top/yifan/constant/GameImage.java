package top.yifan.constant;

import top.yifan.factory.GameFactory;
import top.yifan.utils.FileLoadUtil;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 游戏图片
 *
 * @author star
 */
public class GameImage {

    /**
     * 私有化构造函数，无法创建对象
     */
    private GameImage() {
        throw new IllegalStateException("Cannot create instance of static util class");
    }

    /**
     * 层窗口图片
     */
    public static Image WINDOW;

    /**
     * 数据库图片
     */
    public static Image DATA;

    /**
     * 本地记录图片
     */
    public static Image DISK;

    /**
     * 等级图片
     */
    public static Image RANK;

    /**
     * 方块图片
     */
    public static Image RECT;

    /**
     * 数字
     */
    public static Image NUMBERS;

    /**
     * 分数
     */
    public static Image SCORE;

    /**
     * 消行
     */
    public static Image RMLINE;

    /**
     * 名称图
     */
    public static Image NAME;

    /**
     * 矩形值槽图片
     */
    public static Image RECT_EXP;

    /**
     * 阴影图片
     */
    public static Image SHADOW;

    /**
     * 控键设置图
     */
    public static Image PSP;

    /**
     * 暂停图
     */
    public static Image PAUSE;

    /**
     * 开始按钮
     */
    public static ImageIcon START_BTN;

    /**
     * 设置按钮
     */
    public static ImageIcon SETTING_BTN;

    /**
     * 提示方块的图片数组
     */
    public static Image[] NEXT_RECT;

    /**
     * 背景图片
     */
    public static List<Image> BG_IMG;

    /**
     * 图片路径
     */
    public static final String GRAPHICS_PATH = "graphics/";

    /**
     * 默认路径
     */
    private static final String DEFAULT_PATH = "default";

    /**
     * 设置游戏皮肤
     */
    public static void setGameSkin(String path) {
        // 皮肤路径
        String skinPath = GRAPHICS_PATH + path;

        // 层窗口图片
        WINDOW = FileLoadUtil.loadAsImage(skinPath + "/window/window.png");
        // 数据库图片
        DATA = FileLoadUtil.loadAsImage(skinPath + "/string/dataBase.png");
        // 本地记录图片
        DISK = FileLoadUtil.loadAsImage(skinPath + "/string/disk.png");
        // 等级图片
        RANK = FileLoadUtil.loadAsImage(skinPath + "/string/rank.png");
        // 方块图片
        RECT = FileLoadUtil.loadAsImage(skinPath + "/rectangle/rect.png");
        // 数字
        NUMBERS = FileLoadUtil.loadAsImage(skinPath + "/string/numbers.png");
        // 分数
        SCORE = FileLoadUtil.loadAsImage(skinPath + "/string/score.png");
        // 消行图
        RMLINE = FileLoadUtil.loadAsImage(skinPath + "/string/rmline.png");
        // 名称图
        NAME = FileLoadUtil.loadAsImage(skinPath + "/string/name.png");
        // 矩形值槽图片
        RECT_EXP = FileLoadUtil.loadAsImage(skinPath + "/window/exp.png");
        // 阴影图片
        SHADOW = FileLoadUtil.loadAsImage(skinPath + "/rectangle/shadow.png");
        // 控键设置图
        PSP = FileLoadUtil.loadAsImage("data/psp.png");
        // 暂停图
        PAUSE = FileLoadUtil.loadAsImage(skinPath + "/string/pause.png");
        // 开始按钮
        START_BTN = FileLoadUtil.loadAsImageIcon(skinPath + "/string/start.png");
        // 设置按钮
        SETTING_BTN = FileLoadUtil.loadAsImageIcon(skinPath + "/string/config.png");

        // 初始化方块图片数组
        NEXT_RECT = new Image[GameFactory.getRectNumber()];
        for (int i = 0, length = NEXT_RECT.length; i < length; i++) {
            NEXT_RECT[i] = FileLoadUtil.loadAsImage(skinPath + "/rectangle/" + i + ".png");
        }
        // 初始化背景图片集合
        BG_IMG = new ArrayList<>();
        // 创建文件对象
        File file = new File(FileLoadUtil.loadAsURL(skinPath + "/background").getFile());
        // 获取所有子文件
        File[] listFiles = file.listFiles();
        // 遍历所有子文件
        for (File f : listFiles) {
            // 判断是否是文件夹
            if (!f.isDirectory()) {
                // 将图片文件添加到集合
                BG_IMG.add(new ImageIcon(f.getPath()).getImage());
            }
        }
    }

    static {
        setGameSkin(DEFAULT_PATH);
    }
}
