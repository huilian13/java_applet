package kevin.dao;

import java.util.List;

import kevin.dto.Player;

/**
 * ���ݳ־ò�ӿ�
 * @author kevin
 *
 */
public interface Data {

	/**
	 * ��������
	 */
	public List<Player> loadData();
	
	
	/**
	 * �洢����
	 */
	public void saveData(Player players);
	
}
