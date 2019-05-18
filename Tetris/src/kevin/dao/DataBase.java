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
 * 数据库数据实现类
 * @author kevin
 *
 */
public class DataBase implements Data{
    /**
     * 数据库链接
     */
	private final String DB_URL;
	
	/**
	 * 数据库用户
	 */
	private final String DB_USER;
	
	/**
	 * 密码
	 */
	private final String DB_PASSWORD;
	
	/**
	 * 查询SQL语句
	 */
	private static final String SELECT_SQL="SELECT TOP 5 user_name,score FROM user_point where type_id=1 ORDER BY score DESC";
	
	/**
	 * 插入SQL语句
	 */
	private static final String INSERT_SQL="INSERT INTO user_point(user_name,score,type_id)VALUES(?,?,?)";
	
	public DataBase(HashMap<String,String> paramMap) {
		//获取数据库链接
		DB_URL=paramMap.get("url");
		//获取用户名
		DB_USER=paramMap.get("user");
		//获取登录密码
		DB_PASSWORD=paramMap.get("password");
		try {
			//加载数据库驱动
			Class.forName(paramMap.get("driver"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Player> loadData() {
		//连接对象
		Connection connection=null;
		//预编译对象
		PreparedStatement statement =null;
		//结果集对象
		ResultSet resultSet =null;
		//玩家集合对象
		List<Player> players=new ArrayList<Player>();
		
		try {
			//获取驱动连接
			connection=JdbcUtils.getConnection(DB_URL,DB_USER,DB_PASSWORD);
			//预编译SQL语句
			statement = connection.prepareStatement(SELECT_SQL);
			//获取结果集
			resultSet = statement.executeQuery();
			while(resultSet.next()){
				//获取玩家姓名
				String name = resultSet.getString("user_name");
				//获取玩家分数
				int score = resultSet.getInt("score");
				//添加数据到集合
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
		//连接对象
		Connection connection=null;
		//预编译对象
		PreparedStatement statement =null;
		try {
			//获取驱动连接
			connection=JdbcUtils.getConnection(DB_URL,DB_USER,DB_PASSWORD);
			//预编译SQL语句
			statement=connection.prepareStatement(INSERT_SQL);
			//设置参数
			statement.setObject(1,player.getName());
			statement.setObject(2, player.getScore());
			statement.setObject(3, 1);
			//执行语句
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




