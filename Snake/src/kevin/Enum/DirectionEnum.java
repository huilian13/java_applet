package kevin.Enum;
/**
 * ����ö��
 * @author kevin
 *
 */
public enum DirectionEnum {
	/**
	 * �����ϡ��¡�����
	 */
	UP(-1),DOWN(1),LEFT(-2),RIGHT(2);
	
	/**
	 * ����ֵ
	 */
	private int direction;

	private DirectionEnum(int direction) {
		this.direction = direction;
	}

	public int getDirection() {
		return direction;
	}
}
