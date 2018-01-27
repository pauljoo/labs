package info.xpanda.algorithm;

/**
 * 冒泡排序
 * @author jianghy
 *
 */
public class BubbleSort implements Sort{
	/**
	 * 升序
	 * 比较相邻元素，将大元素沉下去
	 */
	@Override
	public int[] asc(int[] originalArray) {
		for(int i = 0; i < originalArray.length; i++){
			for(int j = i + 1; j < originalArray.length; j++){
				if(originalArray[i] > originalArray[j]){
					int temp = originalArray[i];
					originalArray[i] = originalArray[j];
					originalArray[j] = temp;
				}
			}
		}
		return originalArray;
	}
	
	/**
	 * 降序
	 * 比较相邻元素，将小元素沉下去
	 */
	@Override
	public int[] desc(int[] originalArray) {
		for(int i = 0; i < originalArray.length; i++){
			for(int j = i + 1; j < originalArray.length; j++){
				if(originalArray[i] < originalArray[j]){
					int temp = originalArray[i];
					originalArray[i] = originalArray[j];
					originalArray[j] = temp;
				}
			}
		}
		return originalArray;
	}
}
