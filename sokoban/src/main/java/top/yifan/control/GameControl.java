package top.yifan.control;

import top.yifan.dto.GameDto;
import top.yifan.service.GameService;
import top.yifan.service.GameServiceImpl;
import top.yifan.ui.GameFinishFrame;
import top.yifan.ui.GameFrame;
import top.yifan.constant.GameImageConstant;
import top.yifan.ui.GamePanel;
import top.yifan.util.FileLoadUtil;

import java.io.File;
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
     * 游戏面板
     */
    private GamePanel gamePanel;

    /**
     * 游戏数据源
     */
    private GameDto gameDto;

    /**
     * 游戏结束窗口
     */
    private GameFinishFrame gameFinishFrame;

    /**
     * 行为控制
     */
    private Map<Integer, Method> actionList;

    /**
     * 文件路径
     */
    private static final String CONTROL_PATH = "data/control.dat";

    public GameControl() {
        // 创建游戏数据源
        this.gameDto=new GameDto();
        // 创建业务逻辑
        this.gameService=new GameServiceImpl(gameDto);
        // 创建游戏面板
        this.gamePanel=new GamePanel(gameDto,this);
        // 初始化游戏完成窗口
        this.gameFinishFrame=new GameFinishFrame(this);
        // 创建游戏窗口
        new GameFrame(this.gamePanel);
        // 初始化行为控制
        this.initActionList();

    }

    /**
     * 初始化行为控制
     */
    @SuppressWarnings("unchecked")
    private void initActionList() {
        // 对象输入流
        ObjectInputStream in = null;
        try {
            this.actionList = new HashMap<>();
            // 创建文件输入流对象
            in = new ObjectInputStream(FileLoadUtil.loadAsStream(CONTROL_PATH));
            // 读取文件
            HashMap<Integer, String> keysMap = (HashMap<Integer, String>) in.readObject();
            // 将 map 集合转换成 set 集合
            Set<Entry<Integer, String>> entrySet = keysMap.entrySet();
            for (Entry<Integer, String> entry : entrySet) {
                this.actionList.put(entry.getKey(), this.gameService.getClass().getMethod(entry.getValue()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 游戏行为控制
     *
     * @param keyCode
     */
    public void actionKeys(int keyCode) {
        try {
            // 检测游戏完成情况,如果游戏完成，显示窗口
            if (this.gameService.gameFinish()) {
                this.gameFinishFrame.setVisible(true);
                return;
            }
            // 判断是否有无效键
            if (this.actionList.containsKey(keyCode)) {
                // 执行动作
                this.actionList.get(keyCode).invoke(this.gameService);
            }
            // 刷新界面
            this.gamePanel.repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
		/*
		 写入配置文件control.dat
		 switch(keyCode){
		case KeyEvent.VK_UP:
			this.gameService.keyUp();
			break;
		case KeyEvent.VK_DOWN:
			this.gameService.keyDown();
			break;
		case KeyEvent.VK_LEFT:
			this.gameService.keyLeft();
			break;
		case KeyEvent.VK_RIGHT:
			this.gameService.keyRight();
			break;
		}*/
    }

    /**
     * 关闭子窗口
     */
    public void setOver() {
        // 关闭子窗口
        this.gameFinishFrame.setVisible(false);
        // 刷新游戏画面
        this.gamePanel.repaint();
    }

    /**
     * 开始下一关的游戏
     */
    public void startNextGame(int index) {
        // 获取皮肤路径
        File file = this.gameDto.getFiles().get(index);
        // 更新地图
        GameImageConstant.setGameSkin(file.getName());
        // 初始化数据源
        this.gameDto.initDto(index);
        // 关闭子窗口
        this.setOver();

    }

}
