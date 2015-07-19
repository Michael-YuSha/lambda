package chapter3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import roles.Artist;

/**
 * P32 ��ϰ
 * @author skywalker
 *
 */
public class Exercises {
	
	/**
	 * ��ͺ���
	 */
	public int addUp(Stream<Integer> stream) {
		if (stream == null) {
			throw new IllegalArgumentException();
		}
		return stream.reduce(0, (acc, num) -> acc + num);
	}
	
	@Test
	public void testAddUp() {
		Stream<Integer> stream = Stream.of(1, 4, 10, 13);
		System.out.println(addUp(stream));
	}
	
	/**
	 * ����һ����������������͹������ַ����б�
	 */
	public List<String> getAgeAndHome(List<Artist> artists) {
		if (artists == null) {
			throw new IllegalArgumentException();
		}
		return artists.stream()
				.map(artist -> artist.getAge() + artist.getHome())
				.collect(Collectors.toList());
	}
	
	@Test
	public void testGetAgeAndHome() {
		List<Artist> artists = new ArrayList<Artist>();
		artists = new ArrayList<Artist>();
		artists.add(new Artist("London", 20));
		artists.add(new Artist("Shanghai", 19));
		artists.add(new Artist("Qingdao", 25));
		artists.add(new Artist("Beijing", 30));
		artists.add(new Artist("Henan", 27));
		System.out.println(getAgeAndHome(artists));
	}
	
	/**
	 * �������ܺ�
	 */
	public int getAgeTotal(List<Artist> artists) {
		if (artists == null) {
			throw new IllegalArgumentException();
		}
		return artists.stream()
				.map(artist -> artist.getAge())
				.reduce(0, Integer::sum);
	}
	
	/**
	 * ͳ���ַ�����Сд��ĸ�ĸ���
	 */
	public long lowerCount(String str) {
		return str.chars()
				.filter(c -> c >= 'a' && c <= 'z')
				.count();
	}
	
	@Test
	public void testLowerCount() {
		System.out.println(lowerCount("HelloWorLD"));
	}
	
	/**
	 * �ҳ��������Сд��ĸ���ַ��� 
	 */
	public String mostLowers(List<String> strs) {
		Optional<String> result = strs.stream()
				.max(Comparator.comparingLong(str -> lowerCount(str)));
		return result.isPresent() ? result.get() : null;
	}
	
	@Test
	public void testMostLowers() {
		//System.out.println(mostLowers(Arrays.asList("ABc", "aBc", "ABC", "abc")));
		System.out.println(mostLowers(Collections.emptyList()));
	}
	
	/**
	 * ʵ��map
	 * �Ѷ��Դ�
	 * <I> ��������
	 * <O> ���(ת����Ϊ)������
	 */
	public <I, O> Stream<O> map(Stream<I> stream, Function<I, O> mapper) {
		//��ʼ���ͱ�����List������O����Ϊ����acc�����ͺͳ�ʼ������һ�µģ������Ļ�acc������O���ǻ���ô��
		return stream.reduce(new ArrayList<O>(),
				//ÿ�����
				(acc, x) -> {
					List<O> result = new ArrayList<O>();
					result.add(mapper.apply(x));
					acc.addAll(result);
					return result;
				}, 
				//�ϲ���
				(List<O> left, List<O> right) -> {
					List<O> result = new ArrayList<O>(left);
					result.addAll(right);
					return result;
				}).stream();
	}
	
	/**
	 * �Լ�ʵ��filter
	 */
	public <T> Stream<T> filter(Stream<T> stream, Predicate<T> predicate) {
		return stream.reduce(new ArrayList<T>(),
				//ÿ�����
				(acc, x) -> {
					List<T> result = new ArrayList<T>(acc);
					if (predicate.test(x)) {
						result.add(x);
					}
					return result;
				}, 
				//�ϲ���
				(List<T> left, List<T> right) -> {
					List<T> result = new ArrayList<T>(left);
					result.addAll(right);
					return result;
				}).stream();
	}
	
	@Test
	public void testFilter() {
		Stream<Integer> input = Stream.of(1, 4, 5, 10, 14, 12, 11, 7);
		input = filter(input, i -> i > 10);
		//����������ȫ���������������
		/*input = filter(input, new Predicate<Integer>() {
			@Override
			public boolean test(Integer t) {
				return t > 10;
			}
		});*/
		input.forEach(System.out::println);
	}
	
}
