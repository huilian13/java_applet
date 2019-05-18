package kevin.control;

import kevin.dto.GameDto;
import kevin.service.GameService;
import kevin.service.GameServiceImpl;
import kevin.ui.GameFrame;
import kevin.ui.GamePanel;

import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 游戏控制器
 * @author kevin
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
     * 动作集合
     */
    private HashMap<Integer,Method> actionList;

    public GameControl() {
        //初始化数据源
        this.gameDto=new GameDto();
        //初始化业务逻辑对象
        this.gameService=new GameServiceImpl(this.gameDto);
        //初始化游戏面板
        this.gamePanel=new GamePanel(this.gameDto,this);
        //初始化游戏窗口
        new GameFrame(this.gamePanel);
        //初始化按键动作
        this.initKeyAction();
    }

    /**
     * 初始化按键动作
     */
    private void initKeyAction(){
        //创建动作集合
        this.actionList=new HashMap<Integer, Method>();
        //创建对象输入流
        ObjectInputStream objIn=null;
        try {
            //读取文件
            objIn=new ObjectInputStream(new FileInputStream("data/control.dat"));
            //获取对象
            HashMap<Integer,String> keyMap= (HashMap<Integer,String>)objIn.readObject();
            //将Map集合转换为Set集合
            Set<Entry<Integer, String>> entries = keyMap.entrySet();
            //遍历集合
            for(Entry<Integer,String> e:entries){
                //将内容添加到集合中
                this.actionList.put(e.getKey(),this.gameService.getClass().getMethod(e.getValue()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                objIn.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 动作按键
     * @param keyCode 键值
     */
    public void keyActionList(int keyCode) {
        //当游戏开始时，按键可以移动
        try {
            if(this.gameDto.isStart()){
                //执行动作
                if(this.actionList.containsKey(keyCode)){
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
    public void startGame(){
        this.gameService.startGame();
    }

    /**
     * 功能按键
     * @param keyCode 键值
     */
    public void keyFunction(int keyCode) {
        if(!this.gameDto.isStart()){
            return;
        }
        //改变飞机形态
        if(keyCode==KeyEvent.VK_W){
            this.gameService.changePlaneImg();
        }
        //发射子弹
        if(keyCode==KeyEvent.VK_ENTER){
            this.gameService.shot();
        }
    }
}
