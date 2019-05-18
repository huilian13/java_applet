package kevin.Enum;
/**
 * 方向枚举
 * @author kevin
 *
 */
public enum DirectionEnum {
	/**
	 * 方向：上、下、左、右
	 */
	UP(-1),DOWN(1),LEFT(-2),RIGHT(2);
	
	/**
	 * 方向值
	 */
	private int direction;

	private DirectionEnum(int direction) {
		this.direction = direction;
	}

	public int getDirection() {
		return direction;
	}
}
