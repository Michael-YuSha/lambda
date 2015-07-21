package chapter6;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * P73 6-3
 * ʹ��Lambda������ؿ���ģ��
 * @author skywalker
 *
 */
public class ParalleDiceRolls {
	
	//n ģ��Ĵ���
	public static Map<Integer, Double> paralleDiceRolls(int n) {
		//����
		double fraction = 1D / n;
		return IntStream.range(0, n)
				.parallel()
				.mapToObj(i -> twoDiceThrows())
				.collect(Collectors.groupingBy(i -> i, Collectors.summingDouble(i -> fraction)));
		
	}
	
	/**
	 * ִ��һ��������ģ��
	 */
	public static int twoDiceThrows() {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		int first = random.nextInt(1, 7);
		int second = random.nextInt(1, 7);
		return first + second;
	}
	
	public static void main(String[] args) {
		Map<Integer, Double> result = paralleDiceRolls(1000);
		result.forEach((key, value) -> {
			System.out.println("key:" + key + " value:" + value);
		});
	}
	
}
