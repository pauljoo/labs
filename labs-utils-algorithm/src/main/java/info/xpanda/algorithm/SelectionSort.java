package info.xpanda.algorithm;

import utils.algorithm.Sort;

/**
 * 选择排序
 * @author jianghy
 *
 */
public class SelectionSort implements Sort{
	/**
	 * 升序
	 * 遍历数组找出最小的与第一个交换，第二小的与第二个交换...
	 * @param originalArr
	 * @return
	 */
	@Override
	public int[] asc(int[] originalArr){
		for(int i = 0; i < originalArr.length; i++){
			int min = i;
			for(int j = i+1; j < originalArr.length; j++){
				if(originalArr[min] > originalArr[j]){
					min = j;
				}
			}
			
			int tmp = originalArr[min];
			originalArr[min] = originalArr[i];
			originalArr[i] = tmp;
		}
		return originalArr;
	}
	
	/**
	 * 降序
	 * 遍历数组找出最大的与第一个交换，第二大的与第二个交换...
	 * @param originalArr
	 * @return
	 */
	@Override
	public int[] desc(int[] originalArr){
		for(int i = 0; i < originalArr.length; i++){
			int max = i;
			for(int j = i+1; j < originalArr.length; j++){
				if(originalArr[max] < originalArr[j]){
					max = j;
				}
			}
			
			int tmp = originalArr[max];
			originalArr[max] = originalArr[i];
			originalArr[i] = tmp;
		}
		return originalArr;
	}
}
