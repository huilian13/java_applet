package top.yifan.dao;

import top.yifan.dto.Player;
import top.yifan.utils.FileLoadUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 本地磁盘数据类
 *
 * @author star
 */
public class DataDisk implements Data {

    /**
     * 文件路径
     */
    private final String DATA_FILE_PATH;

    public DataDisk(Map<String, String> paramMap) {
        // 获取文件路径值
        DATA_FILE_PATH = paramMap.get("path");
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Player> loadData() {
        // 玩家集合对象
        List<Player> players = null;
        // 创建对象输入流
        try (ObjectInputStream in = new ObjectInputStream(FileLoadUtil.loadAsStream(DATA_FILE_PATH))) {
            // 读取对象
            players = (List<Player>) in.readObject();
        } catch (Exception e) {
            if (e instanceof EOFException) {
                return new ArrayList<>();
            }
            e.printStackTrace();
        }
        return players;
    }

    @Override
    public void saveData(Player player) {
        // 获取玩家数据
        List<Player> players = this.loadData();
        // 添加玩家
        players.add(player);
        // 排序
        Collections.sort(players);
        // 判断集合中的数据是否超过 5 条,超过时删除最小的
        while (players.size() > 5) {
            // 删除最小的
            players.remove(players.size() - 1);
        }
        // 创建对象输出流
        String filePath = FileLoadUtil.loadAsURL(DATA_FILE_PATH).getFile();
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            // 输出对象
            out.writeObject(players);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
