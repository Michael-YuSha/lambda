package chapter5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * P68 2-c �Լ�ʵ��Collectors.groupingBy()
 * �ɲο�62ҳ��ʵ��
 * @author skywalker
 *
 * @param <T>
 * @param <K>
 */
public class GroupingBy<T, K> implements Collector<T, Map<K, List<T>>, Map<K, List<T>>> {
	
	//����������������
	//ע�ⷺ�͵�д������һ���ǲ������ڶ����Ƿ���ֵ��������˼
	private final Function<? super T, ? extends K> function;
	
	public GroupingBy(Function<? super T, ? extends K> function) {
		this.function = function;
	}
	
	/**
	 * ����Ǵ�����������Ĺ����������൱��reduce�ĵ�һ������
	 */
	@Override
	public Supplier<Map<K, List<T>>> supplier() {
		return () -> new HashMap<K, List<T>>();
	}

	/**
	 * �൱��reduce�ĵڶ�������
	 */
	@Override
	public BiConsumer<Map<K, List<T>>, T> accumulator() {
		return (map, element) -> {
			K key = function.apply(element);
			List<T> list = map.computeIfAbsent(key, k -> new ArrayList<T>());
			list.add(element);
		};
	}

	/**
	 * �൱��reduce�ĵ���������
	 */
	@Override
	public BinaryOperator<Map<K, List<T>>> combiner() {
		return ((m1, m2) -> {
			m1.putAll(m2);
			return m1;
		});
	}

	/**
	 * �˴�ֱ�ӷ���map���ɣ�����ת��
	 */
	@Override
	public Function<Map<K, List<T>>, Map<K, List<T>>> finisher() {
		return map -> map;
	}

	/**
	 * ��δʵ��
	 */
	@Override
	public Set<java.util.stream.Collector.Characteristics> characteristics() {
		return null;
	}

}
