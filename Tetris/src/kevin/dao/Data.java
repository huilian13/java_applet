package kevin.dao;

import java.util.List;

import kevin.dto.Player;

/**
 * 数据持久层接口
 * @author kevin
 *
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
