package top.yifan.dao;

import top.yifan.dto.Player;

import java.util.List;

/**
 * 数据持久层接口
 *
 * @author star
 */
public interface Data {

    /**
     * 载入数据
     */
    public List<Player> loadData();


    /**
     * 存储数据
     */
    public void saveData(Player players);

}
