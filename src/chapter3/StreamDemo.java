package chapter3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

import roles.Artist;

/**
 * ���Լ��ϵ�"��"����
 * @author skywalker
 *
 */
public class StreamDemo {
	
	private List<Artist> artists;

	@Before
	public void before() {
		artists = new ArrayList<Artist>();
		artists.add(new Artist("London", 20));
		artists.add(new Artist("Shanghai", 19));
		artists.add(new Artist("Qingdao", 25));
		artists.add(new Artist("Beijing", 30));
		artists.add(new Artist("Henan", 27));
	}
	
	@Test
	public void test() {
		artists.stream().filter(artist -> {
			System.out.println(artist.getHome());
			return artist.isFrom("London");
		})
		.count();
	}
	
	/**
	 * �������Ľ��תΪ����
	 */
	@Test
	public void collect() {
		List<String> list = Stream.of("a", "b", "c").collect(Collectors.toList());
		System.out.println(list);
	}
	
	/**
	 * map()�������ڰ�һ�����ͱ�Ϊ��һ������
	 * map�������յ���һ��Function����
	 * ����ǩ��: R apply(T t);
	 * Lambda���ʽ��������д����{}����ôӦ����ʾ��ʹ��return������->����ľ��Ƿ���ֵ
	 * ���������ֵ�Ч��д��:
	 */
	@Test
	public void map() {
		System.out.println(Stream.of("a", "hello", "test")
				.map(str -> {
					return str.toUpperCase();
				})
				.collect(Collectors.toList()));
		//�ȼ���:
		System.out.println(Stream.of("a", "hello", "test").map(str -> str.toUpperCase())
				.collect(Collectors.toList()));
	}
	
	@Test
	public void flatMap() {
		System.out.println(Stream.of(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6))
				.flatMap(numbers -> numbers.stream()).count());
	}
	
	/**
	 * Lambda��Compator��������
	 */
	@Test
	public void compare() {
		//artist -> artist.getAge()��ͬ��Artist::getAge
		System.out.println(artists.stream()
				.min(Comparator.comparing(artist -> artist.getAge())).get().getHome());
	}
	
	/**
	 * P24 "reduce"ģʽ
	 * Integer��sum��min��max�������Ǵ�JDK1.8�����
	 */
	@Test
	public void reduce() {
		//int sum = Stream.of(1, 2, 3).reduce(0, (acc, num) -> acc + num);
		//��
		int sum = Stream.of(1, 2, 3).reduce(0, Integer::sum);
		System.out.println(sum);
	}
	
}
