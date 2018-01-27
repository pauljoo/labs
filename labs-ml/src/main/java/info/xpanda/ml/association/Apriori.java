package info.xpanda.ml.association;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class Apriori {
	private class AprioriBean
	{
		public List<Set<Object>> l;
		public Map<Object, Float> supportData;
	}
	/**
	 * 构建大小为1的所有候选项集的集合
	 * @param dataSet
	 * @return
	 */
	public List<Set<Object>> createC1(List<Set<Object>> dataSet)
	{
		List<Set<Object>> retList = new ArrayList<Set<Object>>();
		for(Set<Object> datas : dataSet)
		{
			for(Object data : datas)
			{
				Set<Object> set = new HashSet<Object>();
				set.add(data);
				if(!retList.contains(set))
				{
					retList.add(set);
				}
			}
		}
		Collections.sort(retList, new Comparator<Set<Object>>() {
			public int compare(Set<Object> o1, Set<Object> o2) 
			{
				return o1.toString().compareTo(o2.toString());
			};
		});
		return retList;
	}
	
	/**
	 * 扫描数据集判断候选项集是否满足最低支持度
	 * @param dataSet
	 * @param c1
	 * @param miniSupport
	 * @return
	 */
	public AprioriBean scanD(List<Set<Object>> dataSet, List<Set<Object>> c1, float miniSupport)
	{
		AprioriBean aprioriBean = new AprioriBean();
		Map<Object, Integer> ssCnt = new HashMap<Object, Integer>();
		List<Set<Object>> retList = new ArrayList<Set<Object>>();
		for(Set<Object> datas : dataSet)
		{
			for(Set<Object> can : c1)
			{
				if(datas.containsAll(can))
				{
					if(ssCnt.containsKey(can))
					{
						ssCnt.put(can, ssCnt.get(can) + 1);
					}
					else
					{
						ssCnt.put(can, 1);
					}
				}
			}
		}
		int numItems = dataSet.size();
		Map<Object, Float> supportData = new HashMap<Object, Float>();
		for(Map.Entry<Object, Integer> entry : ssCnt.entrySet())
		{
			float support = (float)entry.getValue()/(float)numItems;
			if(support >= miniSupport)
			{
				Set<Object> set = new HashSet<Object>();
				set.add(entry.getKey());
				retList.add(set);
			}
			supportData.put(entry.getKey(), support);
		}
		aprioriBean.l = retList;
		aprioriBean.supportData = supportData;
		return aprioriBean;
	}
	
	/**
	 * 前k - 2个项相同时，将两个集合合并
	 * @param lK
	 * @param k
	 * @return
	 */
	public List<Set<Object>> aprioriGen(List<Set<Object>> lK, int k)
	{
		List<Set<Object>> retList = new ArrayList<Set<Object>>();
		for(int i = 0; i < lK.size(); i++)
		{
			for(int j = i + 1; j < lK.size(); j++)
			{
				Set<Object> l1 = lK.get(i);
				Set<Object> l2 = lK.get(j);
				
				TreeSet<Object> tl1 = new TreeSet<Object>();
				TreeSet<Object> tl2 = new TreeSet<Object>();
				for(Object temp : l1)
				{
					tl1.add(temp);
				}
				for(Object temp : l2)
				{
					tl2.add(temp);
				}
				if(tl1.subSet(0, k - 2).equals(tl2.subSet(0, k - 2)))
				{
					l1.addAll(l2);
					retList.add(l1);
				}
			}
		}
		return retList;
	}
	public void aprioriCore(List<Set<Object>> dataSet, float miniSupport)
	{
		List<Set<Object>> c1 = createC1(dataSet);
		AprioriBean aprioriBean1 = scanD(dataSet, c1, miniSupport);
		int k = 2;
		List<Set<Object>> l1 = aprioriBean1.l;
		Map<Object, Float> supportData = aprioriBean1.supportData;
		
		List<List<Set<Object>>> l = new ArrayList<List<Set<Object>>>();
		l.add(0, l1);
		
		while(l.get(k-2).size() > 0)
		{
			List<Set<Object>> ck = aprioriGen(l.get(k - 2), k);
			AprioriBean aprioriBean = scanD(dataSet, ck, miniSupport);
			supportData.putAll(aprioriBean.supportData);
			l.add(l.size(), aprioriBean.l);
			k += 1;
		}
		System.out.println(supportData);
	}
	public static void main(String[] args) {
		Set<Object> set1 = new HashSet<Object>();
		set1.add(1);
		set1.add(3);
		set1.add(4);
		Set<Object> set2 = new HashSet<Object>();
		set2.add(2);
		set2.add(3);
		set2.add(5);
		Set<Object> set3 = new HashSet<Object>();
		set3.add(1);
		set3.add(2);
		set3.add(3);
		set3.add(5);
		Set<Object> set4 = new HashSet<Object>();
		set4.add(2);
		set4.add(5);
		List<Set<Object>> dataSet = new ArrayList<Set<Object>>();
		dataSet.add(set1);
		dataSet.add(set2);
		dataSet.add(set3);
		dataSet.add(set4);
		Apriori apriori = new Apriori();
		apriori.aprioriCore(dataSet, 0.5f);
	}
}
