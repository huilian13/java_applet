package kevin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import kevin.dto.Player;
import kevin.utils.JdbcUtils;

/**
 * ���ݿ�����ʵ����
 * @author kevin
 *
 */
public class DataBase implements Data{
    /**
     * ���ݿ�����
     */
	private final String DB_URL;
	
	/**
	 * ���ݿ��û�
	 */
	private final String DB_USER;
	
	/**
	 * ����
	 */
	private final String DB_PASSWORD;
	
	/**
	 * ��ѯSQL���
	 */
	private static final String SELECT_SQL="SELECT TOP 5 user_name,score FROM user_point where type_id=1 ORDER BY score DESC";
	
	/**
	 * ����SQL���
	 */
	private static final String INSERT_SQL="INSERT INTO user_point(user_name,score,type_id)VALUES(?,?,?)";
	
	public DataBase(HashMap<String,String> paramMap) {
		//��ȡ���ݿ�����
		DB_URL=paramMap.get("url");
		//��ȡ�û���
		DB_USER=paramMap.get("user");
		//��ȡ��¼����
		DB_PASSWORD=paramMap.get("password");
		try {
			//�������ݿ�����
			Class.forName(paramMap.get("driver"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Player> loadData() {
		//���Ӷ���
		Connection connection=null;
		//Ԥ�������
		PreparedStatement statement =null;
		//���������
		ResultSet resultSet =null;
		//��Ҽ��϶���
		List<Player> players=new ArrayList<Player>();
		
		try {
			//��ȡ��������
			connection=JdbcUtils.getConnection(DB_URL,DB_USER,DB_PASSWORD);
			//Ԥ����SQL���
			statement = connection.prepareStatement(SELECT_SQL);
			//��ȡ�����
			resultSet = statement.executeQuery();
			while(resultSet.next()){
				//��ȡ�������
				String name = resultSet.getString("user_name");
				//��ȡ��ҷ���
				int score = resultSet.getInt("score");
				//������ݵ�����
				players.add(new Player(name, score));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			 try {
				JdbcUtils.closeResource(connection, statement, resultSet);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return players;
	}

	@Override
	public void saveData(Player player) {
		//���Ӷ���
		Connection connection=null;
		//Ԥ�������
		PreparedStatement statement =null;
		try {
			//��ȡ��������
			connection=JdbcUtils.getConnection(DB_URL,DB_USER,DB_PASSWORD);
			//Ԥ����SQL���
			statement=connection.prepareStatement(INSERT_SQL);
			//���ò���
			statement.setObject(1,player.getName());
			statement.setObject(2, player.getScore());
			statement.setObject(3, 1);
			//ִ�����
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				JdbcUtils.closeResource(connection, statement, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
}




