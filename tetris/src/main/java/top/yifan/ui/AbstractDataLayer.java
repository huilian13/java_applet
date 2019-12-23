package top.yifan.ui;

import top.yifan.config.GameConfig;
import top.yifan.dto.Player;

import java.awt.*;
import java.util.List;


/**
 * 数据窗口抽象层
 *
 * @author star
 */
public abstract class AbstractDataLayer extends AbstractLayer {

    /**
     * 最大行数
     */
    private static final int MAX_ROW = GameConfig.getDataConfig().getMaxRow();

    /**
     * 值槽高度
     */
    private static final int RECT_H = RECT_EXP_H + 4;

    protected AbstractDataLayer(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    protected void showData(Image img, List<Player> players, Graphics g) {
        // 初始化值槽间距
        int rectPad = (this.height - RECT_H * MAX_ROW - (PADDING << 1) - img.getHeight(null)) / MAX_ROW;
        // 初始化值槽开始的y坐标
        int startY = this.y + img.getHeight(null) + PADDING + rectPad;
        //绘制字体图片
        g.drawImage(img, this.x + PADDING, this.y + PADDING, null);
        //获取当前玩家分数
        double newScore = (double) this.gameDto.getNewScore();
        //声明玩家对象
        Player player = null;
        //声明比值变量
        double percent = 0.0;
        //绘制值槽
        for (int i = 0; i < MAX_ROW; i++) {
            //获取玩家对象
            player = players.get(i);
            //获取玩家分数
            int recodeScore = player.getScore();
            //计算比值
            if (recodeScore != 0) {
                percent = newScore / recodeScore;
                //判断percent是否大于1
                percent = percent > 1 ? 1.0 : percent;
            } else {
                percent = 0;
            }
            //判断得分是否是0
            String score = recodeScore == 0 ? null : String.valueOf(recodeScore);
            this.drawSlot(startY + (RECT_H + rectPad) * i, player.getName(), score, percent, g);
        }

    }

    @Override
    public abstract void paint(Graphics g);
}
