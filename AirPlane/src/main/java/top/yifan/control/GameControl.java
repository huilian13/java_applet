package top.yifan.control;


import top.yifan.dto.GameDTO;
import top.yifan.service.GameService;
import top.yifan.service.GameServiceImpl;
import top.yifan.ui.GameFrame;
import top.yifan.ui.GamePanel;
import top.yifan.util.FileLoadUtil;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 游戏控制器
 *
 * @author star
 */
public class GameControl {

    /**
     * 游戏业务逻辑
     */
    private GameService gameService;

    /**
     * 游戏数据源
     */
    private GameDTO gameDto;

    /**
     * 游戏面板
     */
    private GamePanel gamePanel;

    /**
     * 动作集合
     */
    private HashMap<Integer, Method> actionList;

    private boolean isDebug = true;

    public GameControl() {
        // 初始化数据源
        this.gameDto = new GameDTO();
        // 初始化业务逻辑对象
        this.gameService = new GameServiceImpl(this.gameDto);
        // 初始化游戏面板
        this.gamePanel = new GamePanel(this.gameDto, this);
        // 初始化游戏窗口
        new GameFrame(this.gamePanel);
        // 初始化按键动作
        this.initKeyAction();
    }

    /**
     * 获取按键配置
     *
     * @return
     */
    private Map<Integer, String> getKeyMap() {
        Map<Integer, String> keyMap = new HashMap<>();
        if (isDebug) {
            // "W" 键
            keyMap.put(87, "keyUp");
            // "S" 键
            keyMap.put(83, "keyDown");
            // "A" 键
            keyMap.put(65, "keyLeft");
            // "D" 键
            keyMap.put(68, "keyRight");
            // "P" 键
            keyMap.put(80, "keyPause");
            return keyMap;
        }

        // 从配置文件中读取
        ObjectInputStream objIn = null;
        try {
            // 读取文件
            objIn = new ObjectInputStream(FileLoadUtil.loadAsStream("data/control.dat"));
            keyMap = (HashMap<Integer, String>)objIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return keyMap;

    }

    /**
     * 初始化按键动作
     */
    private void initKeyAction() {
        // 创建动作集合
        this.actionList = new HashMap<>();
        // 获取按键集合对象
        Map<Integer, String> keyMap = this.getKeyMap();
        // 将 Map 集合转换为 Set 集合
        Set<Entry<Integer, String>> entries = keyMap.entrySet();
        try {
            // 遍历集合
            for (Entry<Integer, String> e : entries) {
                // 将内容添加到集合中
                this.actionList.put(e.getKey(), this.gameService.getClass().getMethod(e.getValue()));
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }


    /**
     * 动作按键
     *
     * @param keyCode 键值
     */
    public void keyActionList(int keyCode) {
        // 当游戏开始时，按键可以移动
        try {
            if (this.gameDto.isStart()) {
                // 执行动作
                if (this.actionList.containsKey(keyCode)) {
                    this.actionList.get(keyCode).invoke(this.gameService);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 开始游戏
     */
    public void startGame() {
        this.gameService.startGame();
    }

    /**
     * 功能按键
     *
     * @param keyCode 键值
     */
    public void keyFunction(int keyCode) {
        if (!this.gameDto.isStart()) {
            return;
        }
        // 改变飞机形态
        if (keyCode == KeyEvent.VK_W) {
            this.gameService.changePlaneImg();
        }
        // 发射子弹
        if (keyCode == KeyEvent.VK_ENTER) {
            this.gameService.shot();
        }
    }
}
