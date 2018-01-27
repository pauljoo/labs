package info.xpanda.algorithm;
/**
 * 顺序查找
 * @author jianghy
 *
 */
public class SequentialSearch implements Search{
	@Override
	public int find(String str, String key) {
		for(int i = 0; i < str.length(); i++){
			for(int j = 0; j < key.length(); j++){
				char a = str.charAt(i);
				char b = key.charAt(j);
				if(str.charAt(i) == key.charAt(j)){
					if(j == key.length() - 1){
						return i + 1 - key.length();
					}
					i++;
					continue;
				}else{
					break;
				}
			}
		}
		return -1;
	}
}
