package top.yifan.control;

import top.yifan.constant.FileURLConstant;
import top.yifan.dto.GameDto;
import top.yifan.service.GameService;
import top.yifan.service.GameServiceImpl;
import top.yifan.ui.GameFrame;
import top.yifan.ui.GamePanel;
import top.yifan.ui.GameSetting;
import top.yifan.util.FileLoadUtil;

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
    private GameDto gameDto;

    /**
     * 游戏面板
     */
    private GamePanel gamePanel;

    /**
     * 游戏设置窗口
     */
    private GameSetting gameSetting;

    /**
     * 游戏行为控制
     */
    private HashMap<Integer, Method> actionList;

    public GameControl() {
        // 创建游戏数据源
        this.gameDto = new GameDto();
        // 创建游戏业务逻辑对象
        this.gameService = new GameServiceImpl(this.gameDto);
        // 创建游戏面板
        this.gamePanel = new GamePanel(this.gameDto, this);
        // 创建游戏设置窗口
        this.gameSetting = new GameSetting(this);
        // 创建窗口
        new GameFrame(this.gamePanel);
        // 设置游戏控制
        this.setControlConfig();
    }

    /**
     * 游戏控制行为
     *
     * @param keyCode 按键码
     */
    public void actionByKey(int keyCode) {
        try {
            // 判断游戏是否失败
            if (!this.gameDto.isStart()) {
                return;
            }
            // 判断是否包含键值
            if (this.actionList.containsKey(keyCode)) {
                // 执行方法
                this.actionList.get(keyCode).invoke(this.gameService);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 刷新画面
        this.gamePanel.repaint();
    }

    /**
     * 设置游戏控键
     */
    @SuppressWarnings("unchecked")
    private void setControlConfig() {
        // 初始化游戏行为控制
        this.actionList = new HashMap<Integer, Method>();
        // 声明对象流
        ObjectInputStream objectIn = null;
        try {
            // 创建对象输入流
            objectIn = new ObjectInputStream(FileLoadUtil.loadAsStream(FileURLConstant.CONTROL_PATH));
            // 读取对象流
            Map<Integer, String> keyMap = (HashMap<Integer, String>) objectIn.readObject();
            // 将 Map 集合转换为 Set 集合
            Set<Entry<Integer, String>> entrySet = keyMap.entrySet();
            // 遍历集合
            for (Entry<Integer, String> e : entrySet) {
                this.actionList.put(e.getKey(), this.gameService.getClass().getMethod(e.getValue()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (objectIn != null) {
                    objectIn.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 启动游戏
     */
    public void startGame() {
        // 当游戏开始时，设置“开始”按钮无法点击
        this.gamePanel.buttonSwitch(false);
        // 开始游戏
        this.gameService.startGame();
        // 刷新游戏画面
        this.gamePanel.repaint();
        // 创建线程
        MoveThread moveThread = new MoveThread();
        // 启动线程
        moveThread.start();

    }

    /**
     * 设置移动速度
     */
    public void setSpeed(long speed) {
        //设置速度
        this.gameDto.setSpeed(speed);
        //刷新画面
        this.gamePanel.repaint();
    }

    /**
     * 显示玩家设置窗口
     */
    public void showPlayerConfig() {
        this.gameSetting.setVisible(true);
    }

    /**
     * 设置子窗口后，刷新画面
     */
    public void configOver() {
        // 刷新画面
        this.gamePanel.repaint();
        // 返回焦点
        this.gamePanel.requestFocus();
        // 设置游戏控制
        this.setControlConfig();
    }

    /**
     * 游戏失败后的处理
     */
    private void afterLose() {
        // 使按钮可以点击
        this.gamePanel.buttonSwitch(true);

    }

    /**
     * 游戏线程（内部类）
     *
     * @author kevin
     */
    private class MoveThread extends Thread {

        @Override
        public void run() {
            try {
                while (gameDto.isStart()) {
                    // 刷新画面
                    gamePanel.repaint();
                    // 时间间隔（速度）
                    Thread.sleep(gameDto.getSpeed());
                    // 不同方向移动
                    gameService.directionMove();

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 使按钮可以点击
            afterLose();
        }

    }

}
