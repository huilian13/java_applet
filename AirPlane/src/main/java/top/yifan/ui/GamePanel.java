package top.yifan.ui;

import top.yifan.config.GameConfig;
import top.yifan.constant.GameConstant;
import top.yifan.constant.GameImagePathConstant;
import top.yifan.control.GameControl;
import top.yifan.control.PlayerControl;
import top.yifan.dto.GameDTO;
import top.yifan.entity.Bomb;
import top.yifan.entity.Bullet;
import top.yifan.entity.PlaneLife;
import top.yifan.entity.plane.BigEnemyPlane;
import top.yifan.entity.plane.BoosEnemyPlane;
import top.yifan.entity.plane.EnemyPlane;
import top.yifan.entity.plane.HeroPlane;
import top.yifan.factory.GameImageFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * 游戏面板
 */
public class GamePanel extends JPanel {
    /**
     * 游戏数据载体
     */
    private GameDTO gameDto;

    /**
     * 游戏控制器
     */
    private GameControl gameControl;

    /**
     * 开始标签
     */
    private JLabel startLabel;

    /**
     * 返回标签
     */
    private JLabel backLabel;

    /**
     * 返回标志(点击返回时消除画面中的飞机)
     */
    private boolean backFlag;

    /**
     * 背景图1的起始y坐标
     */
    private int bg_img_y1 = 0;

    /**
     * 背景图2的起始y坐标
     */
    private int bg_img_y2 = -GameConstant.HEIGHT;

    public GamePanel(GameDTO gameDto, GameControl gameControl) {
        // 初始化数据源
        this.gameDto = gameDto;
        // 初始化控制器
        this.gameControl = gameControl;
        // 设置为自由布局
        this.setLayout(null);
        // 添加开始标签
        this.createStartLabel();
        // 创建返回标签
        this.createBackLabel();
        // 添加键盘监听
        this.addKeyListener(new PlayerControl(this.gameControl));
        // 创建线程
        PaintThread paint = new PaintThread();
        paint.start();
    }

    /**
     * 添加开始标签
     */
    private void createStartLabel() {
        // 获取开始标签图片
        final ImageIcon startImg = GameImageFactory.createImageIcon(GameImagePathConstant.START_BUTTON);
        // 初始化”开始“标签
        this.startLabel = new JLabel(startImg);
        // 设置标签位置
        this.startLabel.setBounds(GameConstant.WIDTH - startImg.getIconWidth() >> 1,
                GameConfig.getFrameConfig().getBtn_startY(),
                startImg.getIconWidth(), startImg.getIconHeight());
        // 添加鼠标事件监听
        this.startLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == e.BUTTON1) {
                    // 将开始标签设置为不可见
                    startLabel.setVisible(false);
                    // 将标志设为false
                    backFlag = false;
                    // 开始游戏
                    gameControl.startGame();
                }
            }
        });
    }

    /**
     * 添加返回标签
     */
    private void createBackLabel() {
        // 获取返回标签图片
        ImageIcon backImg = GameImageFactory.createImageIcon(GameImagePathConstant.BACK_BUTTON);
        // 初始化”返回“标签
        this.backLabel = new JLabel(backImg);
        // 设置标签位置
        this.backLabel.setBounds(GameConstant.WIDTH - backImg.getIconWidth() >> 1,
                GameConfig.getFrameConfig().getBtn_backY(),
                backImg.getIconWidth(), backImg.getIconHeight());
        // 给标签添加鼠标事件监听
        this.backLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == e.BUTTON1) {
                    // 创建新的飞机
                    gameDto.initHeroPlaneInfo();
                    // 将开始标签设置为可见
                    startLabel.setVisible(true);
                    // 将返回标签设置为不可见
                    backLabel.setVisible(false);
                    // 将标志设为 true
                    backFlag = true;
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        // 调用基类
        super.paintComponent(g);
        // 绘制背景
        this.drawBackground(g);
        // 绘制我方飞机
        this.drawHeroPlane(g);
        // 绘制子弹
        this.drawBullets(g);
        // 绘制敌机
        this.drawEnemyPlane(g);
        // 绘制奖励
        this.drawPlaneLife(g);
        // 绘制爆炸
        this.drawBombs(g);
        // 绘制开始界面
        this.drawStartUI(g);
        // 绘制飞机的得分和生命值
        this.drawInfo(g);
        // 绘制暂停界面
        this.drawPauseUI(g);
        // 绘制结束界面
        this.drawGameOver(g);
        // 返回焦点
        this.requestFocus();
    }

    /**
     * 绘制英雄飞机的得分和生命值
     *
     * @param g
     */
    private void drawInfo(Graphics g) {
        if (!this.gameDto.isStart()) {
            return;
        }
        // 设置画笔颜色
        g.setColor(Color.cyan);
        // 设置字体
        g.setFont(new Font("黑体", Font.BOLD, 15));
        // 绘制字体
        g.drawString("得分：" + this.gameDto.getScore(), 0, 15);
        g.drawString("生命：" + this.gameDto.getHeroPlane().getLifeValue(), 0, 30);
    }

    /**
     * 绘制首页界面
     */
    private void drawStartUI(Graphics g) {
        if (!this.gameDto.isStart() && this.gameDto.getHeroPlane().isLive()) {
            // 创建首页图片
            Image homeImage = GameImageFactory.createImage(GameImagePathConstant.HOME);
            // 绘制图片
            g.drawImage(homeImage, 0, 0, null);
            // 添加开始标签
            this.add(this.startLabel);
        }
    }

    /**
     * 绘制暂停界面
     */
    private void drawPauseUI(Graphics g) {
        // 如果暂停则绘制
        if (this.gameDto.isPause()) {
            // 创建暂停界面图
            Image pauseImage = GameImageFactory.createImage(GameImagePathConstant.PAUSE);
            // 绘制图片
            g.drawImage(pauseImage, 0, 0, null);
        }
    }

    /**
     * 绘制结束界面
     */
    private void drawGameOver(Graphics g) {
        // 获取我方飞机对象
        HeroPlane heroPlane = this.gameDto.getHeroPlane();
        // 飞机存在时，游戏未结束
        if (heroPlane.isLive()) {
            return;
        }
        // 创建 game over 图片
        Image overImage = GameImageFactory.createImage(GameImagePathConstant.GAME_OVER);
        // 游戏结束时绘制图片
        g.drawImage(overImage, 0, 0, null);
        // 设置游戏状态为结束
        this.gameDto.setStart(false);
        // 将返回标签设置为可见
        this.backLabel.setVisible(true);
        // 添加返回标签
        this.add(this.backLabel);
    }

    /**
     * 绘制背景图（实现图片移动切换）
     */
    private void drawBackground(Graphics g) {
        Image backgroundImage = GameImageFactory.createImage(GameImagePathConstant.BACKGROUND);
        // 绘制背景图 1
        g.drawImage(backgroundImage, 0, this.bg_img_y1++, null);
        // 绘制背景图 2
        g.drawImage(backgroundImage, 0, this.bg_img_y2++, null);

        if (this.bg_img_y1 >= GameConstant.HEIGHT) {
            // 如果第一张图片移出边界，则返回顶部
            this.bg_img_y1 = -GameConstant.HEIGHT;
        }

        if (this.bg_img_y2 >= GameConstant.HEIGHT) {
            // 如果第二张图片移出边界，则返回顶部
            this.bg_img_y2 = -GameConstant.HEIGHT;

        }
    }

    /**
     * 绘制我方飞机
     */
    private void drawHeroPlane(Graphics g) {
        // 游戏没有开始时不绘制飞机
        if (!this.gameDto.isStart()) {
            return;
        }
        // 获取飞机
        HeroPlane heroPlane = this.gameDto.getHeroPlane();
        // 获取敌方飞机
        List<EnemyPlane> enemyPlaneList = this.gameDto.getEnemyPlaneList();
        // 获取奖励
        List<PlaneLife> lifeList = this.gameDto.getLifeList();
        // 如果飞机存在就绘制飞机
        if (heroPlane != null && heroPlane.isLive()) {
            // 实现飞机闪烁
            this.spark(heroPlane, g);
            // 遍历敌方飞机，检测敌方飞机是否碰撞到我方飞机
            for (int i = 0; i < enemyPlaneList.size(); i++) {
                EnemyPlane enemyPlane = enemyPlaneList.get(i);
                // 判断是否发生碰撞,发生碰撞则产生爆炸
                if (heroPlane.hitEnemyPlane(enemyPlane)) {
                    // 根据飞机类型产生相应变化
                    this.checkPlaneType(enemyPlane);
                    // 敌机的爆炸
                    this.gameDto.getBombList().add(new Bomb(enemyPlane.getX(), enemyPlane.getY(), enemyPlane.getWidth()));
                    // 我方飞机的爆炸
                    this.gameDto.getBombList().add(new Bomb(heroPlane.getX(), heroPlane.getY(), heroPlane.getWidth()));
                    // 击中我方飞机时，生命值减1
                    heroPlane.lifeValueDown();
                    if (heroPlane.getLifeValue() > 0) {
                        heroPlane.setSparkValue(GameConstant.SPARK_VALUE);
                    }
                }
            }

            for (int i = 0; i < lifeList.size(); i++) {
                PlaneLife planeLife = lifeList.get(i);
                // 如果碰撞到奖励
                if (planeLife != null && heroPlane.hitPlaneLife(planeLife)) {
                    // 生命值加 1
                    heroPlane.lifeValueUp();
                    // 奖励消失
                    planeLife.setLive(false);
                }
            }
        }
        // 如果生命值为 0，飞机则消失
        if (heroPlane != null && heroPlane.getLifeValue() <= 0) {
            heroPlane.setLive(false);
        }
    }

    /**
     * 飞机闪烁
     *
     * @param g         画笔对象
     * @param heroPlane 飞机对象
     */
    private void spark(HeroPlane heroPlane, Graphics g) {
        // 绘制飞机，实现短暂闪烁
        if (heroPlane.getSparkValue() > 12) {
            g.drawImage(this.gameDto.getPlaneImg(), heroPlane.getX(), heroPlane.getY(), null);
        } else if (heroPlane.getSparkValue() > 9) {
            g.drawImage(null, 0, 0, null);
        } else if (heroPlane.getSparkValue() > 6) {
            g.drawImage(this.gameDto.getPlaneImg(), heroPlane.getX(), heroPlane.getY(), null);
        } else if (heroPlane.getSparkValue() > 3) {
            g.drawImage(null, 0, 0, null);
        } else {
            g.drawImage(this.gameDto.getPlaneImg(), heroPlane.getX(), heroPlane.getY(), null);
        }
        // 闪烁值减 1
        heroPlane.sparkDown();
    }

    /**
     * 绘制敌机
     */
    private void drawEnemyPlane(Graphics g) {
        // 获取所有敌机
        List<EnemyPlane> enemyPlaneList = this.gameDto.getEnemyPlaneList();
        // 获取我方飞机的子弹
        List<Bullet> heroBullets = this.gameDto.getHeroPlane().getBullets();
        // 声明 EnemyPlane 对象
        EnemyPlane enemyPlane = null;
        // 检测敌机是否被子弹击中
        for (int i = 0; i < enemyPlaneList.size(); i++) {
            enemyPlane = enemyPlaneList.get(i);
            if (enemyPlane == null) {
                continue;
            }
            // 如果敌机存在，绘制敌机图片
            if (enemyPlane.isLive()) {
                // 绘制图片
                g.drawImage(enemyPlane.getPlaneImg(), enemyPlane.getX(), enemyPlane.getY(), null);
            }
            int enemyPlaneLifeValue = enemyPlane.getLifeValue();
            // 遍历所有 Hero 子弹，判断每个子弹与飞机的碰撞情况
            for (Bullet bullet : heroBullets) {
                // 判断子弹是否击中敌机
                if (bullet.hitPlane(enemyPlane) && enemyPlaneLifeValue <= 0) {
                    // 飞机死亡
                    enemyPlane.setLive(false);
                    // 根据飞机类型产生相应变化
                    this.checkPlaneType(enemyPlane);
                    // 更新分数
                    this.gameDto.setScore(enemyPlane.getScoreValue());
                    // 产生爆炸
                    this.gameDto.getBombList().add(new Bomb(enemyPlane.getX(), enemyPlane.getY(), enemyPlane.getWidth()));
                }
            }
            // 当飞机不存在时（移出窗口或被子弹击中）或点击返回键时
            if (!enemyPlane.isLive() || this.backFlag) {
                // 删除不存在的飞机
                enemyPlaneList.remove(enemyPlane);
            }
        }
    }

    /**
     * 根据击中的飞机类型，产生相应变化
     */
    private void checkPlaneType(EnemyPlane enemyPlane) {
        // 击中大飞机
        if (enemyPlane instanceof BigEnemyPlane) {
            // 增加敌机数量
            this.gameDto.shotEnemyNumUp();
        }
        // 击中终极飞机
        if (enemyPlane instanceof BoosEnemyPlane) {
            // 产生奖励
            this.gameDto.getLifeList().add(new PlaneLife(enemyPlane.getX(), enemyPlane.getY(), this.gameDto));
        }
    }

    /**
     * 绘制子弹
     */
    private void drawBullets(Graphics g) {
        // 获取子弹
        List<Bullet> bullets = this.gameDto.getHeroPlane().getBullets();
        Bullet bullet = null;
        for (int i = 0; i < bullets.size(); i++) {
            bullet = bullets.get(i);
            if (bullet == null) {
                continue;
            }
            if (bullet.isLive()) {
                Image bulletImage = GameImageFactory.createImage(GameImagePathConstant.BULLET);
                // 绘制子弹
                g.drawImage(bulletImage, bullet.getX(), bullet.getY(), null);
                continue;
            }
            // 删除子弹，结束线程
            if (!bullet.isLive()) {
                bullets.remove(bullet);
            }
        }
    }

    /**
     * 绘制奖励
     */
    private void drawPlaneLife(Graphics g) {
        List<PlaneLife> lifeList = this.gameDto.getLifeList();
        for (int i = 0; i < lifeList.size(); i++) {
            PlaneLife planeLife = lifeList.get(i);
            if (planeLife != null && planeLife.isLive()) {
                Image lifeImage = GameImageFactory.createImage(GameImagePathConstant.LIFE);
                // 绘制奖励
                g.drawImage(lifeImage, planeLife.getX(), planeLife.getY(), null);
            }
            // 如果奖励消失，则删除集合中的对象
            if (!planeLife.isLive()) {
                lifeList.remove(planeLife);
            }
        }
    }

    /**
     * 绘制爆炸效果图
     *
     * @param g 画笔对象
     */
    private void drawBombs(Graphics g) {
        // 创建 Bomb 对象集合
        List<Bomb> bombList = this.gameDto.getBombList();
        // 获取爆炸图片
        Image[] bombImages = GameImagePathConstant.BOMBS;
        // 声明 Bomb 对象
        Bomb bomb = null;
        // 爆炸范围
        int range = 0;
        // 绘制爆炸效果
        for (int i = 0; i < bombList.size(); i++) {
            bomb = bombList.get(i);
            if (bomb == null) {
                continue;
            }
            // 获取爆炸范围
            range = bomb.getBombRange();
            if (bomb.getLifeValue() > 6) {
                g.drawImage(bombImages[0], bomb.getX(), bomb.getY(), range, range, null);
            } else if (bomb.getLifeValue() > 3) {
                g.drawImage(bombImages[1], bomb.getX(), bomb.getY(), range, range, null);
            } else {
                g.drawImage(bombImages[2], bomb.getX(), bomb.getY(), range, range, null);
            }
            // 减少爆炸生命值
            bomb.lifeDown();
            // 如果爆炸消失，则删除爆炸对象
            if (!bomb.isLive()) {
                bombList.remove(bomb);
            }
        }
    }

    /**
     * 重画窗口的线程内部类
     */
    private class PaintThread extends Thread {

        @Override
        public void run() {
            try {
                for (; ; ) {
                    Thread.sleep(GameConstant.SLEEP);
                    if (gameDto.isPause()) {
                        // 暂停时重新获取时间毫秒数
                        gameDto.setNextTime(System.currentTimeMillis());
                    }
                    // 刷新游戏画面
                    repaint();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
