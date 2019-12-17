package top.yifan.service;

import top.yifan.constant.GameConstant;
import top.yifan.constant.GameImagePathConstant;
import top.yifan.dto.GameDTO;
import top.yifan.factory.GameImageFactory;
import top.yifan.thread.actuator.ThreadActuator;
import top.yifan.thread.runnable.MusicRunnable;

import java.awt.*;

/**
 * 游戏业务逻辑实现类
 *
 * @author star
 */
public class GameServiceImpl implements GameService {
    /**
     * 游戏数据载体
     */
    private GameDTO gameDto;

    /**
     * 物体速度
     */
    private final int speed;

    public GameServiceImpl(GameDTO gameDto) {
        this.gameDto = gameDto;
        // 初始化速度
        this.speed = this.gameDto.getHeroSpeed();
    }

    @Override
    public void keyUp() {
        // 暂停时，按键无效
        if (this.gameDto.isPause()) {
            return;
        }
        // 设置飞机加速图片
        Image planeImagePlus = GameImageFactory.createImage(GameImagePathConstant.HERO_PLUS);
        this.gameDto.setPlaneImg(planeImagePlus);
        this.gameDto.getHeroPlane().move(0, -this.speed);

    }

    @Override
    public void keyDown() {
        // 暂停时，按键无效
        if (this.gameDto.isPause()) {
            return;
        }
        this.gameDto.getHeroPlane().move(0, this.speed);
    }

    @Override
    public void keyLeft() {
        // 暂停时，按键无效
        if (this.gameDto.isPause()) {
            return;
        }
        this.gameDto.getHeroPlane().move(-this.speed, 0);
    }

    @Override
    public void keyRight() {
        // 暂停时，按键无效
        if (this.gameDto.isPause()) {
            return;
        }
        this.gameDto.getHeroPlane().move(this.speed, 0);
    }

    @Override
    public void keyPause() {
        // 设置暂停状态
        this.gameDto.changePause();
    }

    @Override
    public void changePlaneImg() {
        // 暂停时，按键无效
        if (this.gameDto.isPause()) {
            return;
        }
        Image heroImage = GameImageFactory.createImage(GameImagePathConstant.HERO);
        this.gameDto.setPlaneImg(heroImage);
    }

    @Override
    public void shot() {
        // 暂停时，按键无效
        if (this.gameDto.isPause()) {
            return;
        }
        // 开火射击
        this.gameDto.getHeroPlane().fire();
        // 启动音效
        ThreadActuator.execute(new MusicRunnable(GameConstant.MUSIC_PATH_MAP.get("bullet")));
    }

    @Override
    public void startGame() {
        // 获取当前游戏时间
        this.gameDto.setNextTime(System.currentTimeMillis());
        // 将游戏状态设置为开始
        this.gameDto.setStart(true);
    }
}
