package top.yifan.ui;

import top.yifan.config.GameConfig;
import top.yifan.constant.GameImage;
import top.yifan.entity.GameRect;

import java.awt.*;


/**
 * 游戏层窗口
 *
 * @author star
 */
public class GameLayer extends AbstractLayer {

    /**
     * 方块左偏移量
     */
    private final int RECT_SIZE = GameConfig.getFrameConfig().getRectSize();

    /**
     * 方块图片的末尾 ID
     */
    private final int LOSE_INDEX = GameConfig.getFrameConfig().getLoseIndex();

    public GameLayer(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void paint(Graphics g) {
        // 绘制层窗口
        this.createWindow(g);
        // 获取方块对象
        GameRect gameRect = this.gameDto.getGameRect();
        // 判断方块对象是否为空
        if (gameRect == null) {
            return;
        }
        // 获取方块数组
        Point[] rectPoint = gameRect.getRectPoint();
        // 绘制阴影
        this.drawShadow(rectPoint, g);
        // 绘制游戏活动方块
        this.drawGameRect(rectPoint, g);
        // 绘制地图
        this.drawGameMap(g);

        // 绘制暂停图
        if (this.gameDto.isPause()) {
            // 获取图片
            Image pauseImg = GameImage.PAUSE;
            // 获取坐标
            int x = this.width - pauseImg.getWidth(null) >> 1;
            int y = this.height - pauseImg.getHeight(null) >> 1;
            // 居中绘制
            g.drawImage(pauseImg, this.x + x, this.y + y, null);
        }

    }

    /**
     * 绘制阴影
     *
     * @param rectPoint 方块数组对象
     * @param g         画笔对象
     */
    private void drawShadow(Point[] rectPoint, Graphics g) {
        // 关闭阴影
        if (!this.gameDto.isShadow()) {
            return;
        }
        // 左边界
        int leftX = rectPoint[0].x;
        // 右边界
        int rightX = rectPoint[0].x;
        for (Point p : rectPoint) {
            leftX = leftX < p.x ? leftX : p.x;
            rightX = rightX > p.x ? rightX : p.x;
        }
        // 绘制阴影
        g.drawImage(GameImage.SHADOW,
                this.x + BORDER_SIZE + (leftX << RECT_SIZE),
                this.y + BORDER_SIZE,
                rightX - leftX + 1 << RECT_SIZE,
                this.height - (BORDER_SIZE << 1), null);
    }

    /**
     * 绘制游戏方块
     *
     * @param rectPoint 方块数组对象
     * @param g         画笔对象
     */
    private void drawGameRect(Point[] rectPoint, Graphics g) {
        // 获取方块编号
        int rectCode = this.gameDto.getGameRect().getRectCode();
        // 绘制方块
        for (int i = 0, length = rectPoint.length; i < length; i++) {
            this.drawRectByCode(rectPoint[i].x, rectPoint[i].y, rectCode + 1, g);
        }
    }

    /**
     * 绘制游戏地图
     *
     * @param g
     */
    private void drawGameMap(Graphics g) {
        // 绘制地图
        boolean[][] gameMap = this.gameDto.getGameMap();
        // 获取游戏等级
        int lv = this.gameDto.getRank();
        // 获取方块 ID
        int imgIndex = lv == 0 ? 0 : (lv - 1) % 7 + 1;
        // 设置地图颜色
        for (int x = 0, length = gameMap.length; x < length; x++) {
            for (int y = 0, len = gameMap[x].length; y < len; y++) {
                if (gameMap[x][y]) {
                    // 绘制方块
                    this.drawRectByCode(y, x, imgIndex, g);
                }
            }
        }
    }

    /**
     * 绘制方块
     *
     * @param x     方块的x坐标
     * @param y     方块的y坐标
     * @param imgId 方块的 ID
     * @param g     画笔对象
     */
    private void drawRectByCode(int x, int y, int imgId, Graphics g) {
        // 获取方块图片的 ID
        imgId = this.gameDto.isGameStart() ? imgId : LOSE_INDEX;
        // 绘制方块
        g.drawImage(GameImage.RECT, this.x + (x << RECT_SIZE) + BORDER_SIZE,
                this.y + (y << RECT_SIZE) + BORDER_SIZE,
                this.x + (x + 1 << RECT_SIZE) + BORDER_SIZE,
                this.y + (y + 1 << RECT_SIZE) + BORDER_SIZE,
                imgId << RECT_SIZE, 0, (imgId + 1) << RECT_SIZE, 1 << RECT_SIZE, null);
    }

}
