package kevin.utils;

public class GameFunction {

	/**
	 * ��ȡ�ȴ�ʱ��
	 * @param rank ��ǰ�ȼ�
	 * @return �ȴ�ʱ��
	 */
	public static long getSleepTime(int rank){
		//ͨ���ȼ�����ȴ�ʱ��
		long sleep=(-40*rank+740);
		//�ж�ʱ��
		sleep=sleep<200?200:sleep;
		return sleep;
	}
}
