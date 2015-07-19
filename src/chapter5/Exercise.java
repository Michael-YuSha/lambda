package chapter5;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * �κ���ϰ
 * ȫ��ʹ�÷�������ʵ��
 * @author skywalker
 *
 */
public class Exercise {

	/**
	 * תΪ��д
	 */
	@Test
	public void upper() {
		Stream<String> stream = Stream.of("hello", "java", "groovy");
		List<String> list = stream.map(String::toUpperCase).collect(Collectors.toList());
		System.out.println(list);
	}
	
	/**
	 * ʹ��reduceʵ��count()
	 */
	public <T> int count(Stream<T> stream) {
		int sum = stream.map(t -> 1)
				.reduce(0, Integer::sum);
		return sum;
	}
	
	@Test
	public void testCount() {
		Stream<String> stream = Stream.of("hello", "java", "groovy");
		System.out.println(count(stream));
	}
	
	/**
	 * ʹ���ռ����ó��������
	 */
	@Test
	public void collectLonger() {
		Stream<String> names = Stream.of("John Lennon", "Paul McCartney", "George Harrison", 
				"Ringo Starr", "Pete Best", "Stuart Sutcliffe");
		System.out.println(names.collect(Collectors.maxBy(Comparator.comparing(String::length))).get());
	}
	
	/**
	 * ʹ�ø߽�reduceʵ������ĺ���
	 */
	@Test
	public void reduceLonger() {
		Stream<String> names = Stream.of("Stuart Sutcliffe", "Paul McCartney", "George Harrison", 
				"Ringo Starr", "Pete Best", "John Lennon");
		String longest = names.reduce((pre, name) -> {
			return name.length() > pre.length() ? name : pre;
		}).get();
		System.out.println(longest);
	}
	
	/**
	 * ͳ�Ƶ��ʳ��ֵĴ���
	 */
	@Test
	public void wordsCount() {
		Stream<String> stream = Stream.of("John", "Paul", "George", "John", "Paul", "John");
		Map<String, Long> groups = stream.collect(Collectors.groupingBy(word -> word, Collectors.counting()));
		groups.forEach((key, value) -> {
			System.out.println(key + " �� " + value);
		});
	}
	
	//쳲��������л���
	private Map<Integer, Integer> cache = new HashMap<Integer, Integer>();
	{
		cache.put(1, 1);
		cache.put(2, 1);
	}
	
	/**
	 * ʵ��һ���������쳲�������ֵ����
	 */
	public int fibonacci(int n) {
		if (n < 1) {
			throw new IllegalArgumentException("the argument can't be smaller than 1.");
		} else if (n <= 2) {
			return 1;
		} else {
			return cache.computeIfAbsent(n, i -> fibonacci(i - 1) + fibonacci(i - 2));
		}
	}
	
	@Test
	public void testFibonacci() {
		System.out.println(fibonacci(10));
	}
	
}
