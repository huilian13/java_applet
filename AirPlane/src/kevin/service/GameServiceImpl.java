package kevin.service;

import kevin.dto.GameDto;
import kevin.entity.Bullet;
import kevin.entity.Enemy;
import kevin.gameThread.MusicThread;
import kevin.ui.GameImage;
import kevin.util.MusicUtil;

import java.util.ArrayList;

/**
 * 游戏业务逻辑实现类
 * @author kevin
 */
public class GameServiceImpl implements GameService{
    /**
     * 游戏数据载体
     */
    private GameDto gameDto;

    /**
     * 音效线程
     */
    private MusicThread musicThread;

    /**
     * 物体速度
     */
    private final int speed;

    public GameServiceImpl(GameDto gameDto){
        this.gameDto=gameDto;
        //初始化速度
        this.speed=this.gameDto.getSpeed();
    }

    @Override
    public void keyUp() {
        //暂停时，按键无效
        if(this.gameDto.isPause()){
            return;
        }
        //设置飞机加速图片
        this.gameDto.setPlaneImg(GameImage.HERO_BOM);
        this.gameDto.getHero().move(0,-this.speed);

    }

    @Override
    public void keyDown() {
        //暂停时，按键无效
        if(this.gameDto.isPause()){
            return;
        }
        this.gameDto.getHero().move(0,this.speed);
    }

    @Override
    public void keyLeft() {
        //暂停时，按键无效
        if(this.gameDto.isPause()){
            return;
        }
        this.gameDto.getHero().move(-this.speed,0);
    }

    @Override
    public void keyRight() {
        //暂停时，按键无效
        if(this.gameDto.isPause()){
            return;
        }
        this.gameDto.getHero().move(this.speed,0);
    }

    @Override
    public void keyPause() {
        //设置暂停状态
        this.gameDto.changePause();
    }

    @Override
    public void changePlaneImg() {
        //暂停时，按键无效
        if(this.gameDto.isPause()){
            return;
        }
        this.gameDto.setPlaneImg(GameImage.HERO);
    }

    @Override
    public void shot() {
        //暂停时，按键无效
        if(this.gameDto.isPause()){
            return;
        }
        //开火射击
        this.gameDto.getHero().fire();
        //启动音效
        this.musicThread=new MusicThread("gameMusic/bullet.mp3");
        this.musicThread.start();
    }

    @Override
    public void startGame() {
        //获取当前游戏时间
        this.gameDto.setNextTime(System.currentTimeMillis());
        //将游戏状态设置为开始
        this.gameDto.setStart(true);
    }
}
