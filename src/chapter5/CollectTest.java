package chapter5;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import roles.Artist;

public class CollectTest {

	/**
	 * ����ת��Ϊ����ļ���
	 */
	@Test
	public void toCollect() {
		//��Ч�ڴ���TreeSet::new
		TreeSet<Integer> set = Stream.of(1, 2, 4, 5)
				.collect(Collectors.toCollection(() -> new TreeSet<Integer>()));
		System.out.println(set.size());
	}
	
	/**
	 * ת���������ֵ
	 * ����������̫TM������
	 * ����һ�����⣬ֻ�а�װ���͵�ʱ������Ҫ��ʽ���������ͣ�������������˷�������ԭ��δ֪
	 */
	@Test
	public void toValue() {
		Stream<Integer> stream = Stream.of(1, 2, 5, 10, 98);
		//�����ֵ
		System.out.println(stream.collect(Collectors.maxBy(Integer::compare)).get());
		//��ƽ��ֵ
		stream = Stream.of(1, 2, 5, 10, 98);
		System.out.println(stream.collect(Collectors.<Integer>averagingInt(i -> i.intValue())));
	}
	
	/**
	 * ���ݷֿ�
	 */
	@Test
	public void partition() {
		Stream<Integer> stream = Stream.of(1, 2, 4, 7, 10, 11, 14, 15);
		Map<Boolean, List<Integer>> result = stream.collect(
				Collectors.<Integer>partitioningBy(i -> i > 10));
		System.out.println(result.get(Boolean.TRUE));
		System.out.println(result.get(Boolean.FALSE));
	}
	
	/**
	 * ���ݷ��飬ò����һ�ֱȷֿ�����ķ�ʽ
	 * �Ҿ��������ڶ��һ�����
	 */
	@Test
	public void group() {
		List<Artist> artists = Arrays.asList(new Artist("London", 12), new Artist("London", 22), new Artist("Beijing", 24));
		Map<String, List<Artist>> groups = artists.stream()
				.collect(Collectors.groupingBy(artist -> artist.getHome()));
		for (String key : groups.keySet()) {
			System.out.println(groups.get(key));
		}
	}
	
	/**
	 * ����ת��Ϊ�ַ���
	 */
	@Test
	public void join() {
		String result = Stream.of(1, 2, 3, 4, 5, 6, 7)
				//�˴���Ҫת��һ�γ���of�Ĳ�����String
				.map(i -> i.toString())
				.collect(Collectors.joining(",", "[", "]"));
		//joining()û�в����������1234567
		System.out.println(result);
	}
	
	/**
	 * ����ռ���, keyΪ��ַ��valueΪ����
	 */
	@Test
	public void combineCollect() {
		List<Artist> artists = Arrays.asList(new Artist("London", 12), new Artist("London", 22), new Artist("Beijing", 24));
		Map<String, List<Integer>> result = artists.stream()
				.collect(Collectors.groupingBy(Artist::getHome, Collectors.mapping(Artist::getAge, Collectors.toList())));
		for (Map.Entry<String, List<Integer>> entry : result.entrySet()) {
			System.out.println(entry.getValue());
		}
	}
	
	/**
	 * 1.8�����java.util.StringJoiner��
	 * Ȼ��������ʵ�ֺ�Lambdaû�а�ëǮ��ϵ
	 */
	@Test
	public void stringJoiner() {
		StringJoiner sj = new StringJoiner(":", "[", "]");
		sj.add("Tom").add("Jack").add("Blosh");
		//[Tom:Jack:Blosh]
		System.out.println(sj.toString());
	}
	
	/**
	 * 1.8������µĵ���map�ķ�ʽ
	 * �ڲ���ôʵ�ֵ���Ӧ�ÿ��Բ³������²�������Դ��
	 */
	@Test
	public void forEachMap() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "Tom");
		map.put(2, "Jack");
		map.put(3, "Jerry");
		map.putIfAbsent(4, "Four");
		map.forEach((key, value) -> {
			System.out.println("key:" + key + " value:" + value);
		});
	}
	
}
