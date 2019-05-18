package kevin.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 * ���ݿ����ӹ�����
 * @author kevin
 *
 */
public class JdbcUtils {
	
	/**
	 * ��ȡ���ݿ�����
	 * @return connection ����
	 */
	public static Connection getConnection(String url,String user,String password){
		try {
			//�����������
			Connection connection = DriverManager.getConnection(url,user,password);
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} 
		
	}
	
	/**
	 * �ر���Դ
	 * @throws Exception 
	 */
	public static void closeResource(Connection connection,Statement statement,ResultSet result) throws Exception{
		if(result!=null){
			result.close();
		}
		
		if(statement!=null){
			statement.close();
		}
		
		if(connection!=null){
			connection.close();
		}
	}
	
}
