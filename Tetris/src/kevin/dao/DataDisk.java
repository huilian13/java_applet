package kevin.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import kevin.dto.Player;

public class DataDisk implements Data{
	
	/**
	 * 文件路径
	 */
	private  final String FILE_PATH;
	
	public DataDisk(HashMap<String,String> paramMap){
		//获取文件路径值
		FILE_PATH=paramMap.get("path");
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Player> loadData() {
		//对象输入流
		ObjectInputStream in=null;
		//玩家集合对象
		List<Player> players=null;
		try {
			 //创建对象输入流
			 in=new ObjectInputStream(new FileInputStream(FILE_PATH));
			 //读取对象
			 players= (List<Player>)in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				//关闭资源
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		 return players;
	}

	@Override
	public void saveData(Player player) {
		//对象输出流
		ObjectOutputStream out=null;
		//获取玩家数据
		List<Player> players = this.loadData();
		//添加玩家
		players.add(player);
		//排序
		Collections.sort(players);
		//判断集合中的数据是否超过5条,超过时删除最小的
		while(players.size()>5){
			//删除最小的
			players.remove(players.size()-1);
		}
		try {
			//创建对象输出流
			out = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
			//输出对象
			out.writeObject(players);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				//关闭资源
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
}
