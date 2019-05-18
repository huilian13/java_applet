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
	 * �ļ�·��
	 */
	private  final String FILE_PATH;
	
	public DataDisk(HashMap<String,String> paramMap){
		//��ȡ�ļ�·��ֵ
		FILE_PATH=paramMap.get("path");
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Player> loadData() {
		//����������
		ObjectInputStream in=null;
		//��Ҽ��϶���
		List<Player> players=null;
		try {
			 //��������������
			 in=new ObjectInputStream(new FileInputStream(FILE_PATH));
			 //��ȡ����
			 players= (List<Player>)in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				//�ر���Դ
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		 return players;
	}

	@Override
	public void saveData(Player player) {
		//���������
		ObjectOutputStream out=null;
		//��ȡ�������
		List<Player> players = this.loadData();
		//������
		players.add(player);
		//����
		Collections.sort(players);
		//�жϼ����е������Ƿ񳬹�5��,����ʱɾ����С��
		while(players.size()>5){
			//ɾ����С��
			players.remove(players.size()-1);
		}
		try {
			//�������������
			out = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
			//�������
			out.writeObject(players);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				//�ر���Դ
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
}
