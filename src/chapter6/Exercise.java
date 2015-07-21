package chapter6;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

public class Exercise {

	/**
	 * ������ƽ����
	 */
	@Test
	public void paralleSumOfQuares() {
		IntStream intStream = IntStream.range(1, 11);
		int sum = intStream.parallel()
			.map(x -> x * x)
			.sum();
		System.out.println(sum);
	}
	
	/**
	 * ���б������е�������ˣ����Ľ���ٳ���5
	 * ��ȷ���3000��������1875000����3000��625(5��4�η�)��
	 */
	@Test
	public void multiply() {
		//3000
		Stream<Integer> stream = Stream.of(1, 4, 3, 5, 10);
		int mul = stream.parallel().reduce(1, (result, num) -> result * num) * 5;
		System.out.println(mul);
	}
	
	/**
	 * ������ƽ����
	 * �򵥵ĸĶ�
	 * 3 ==>> �������˼�ǰ������Ϊ���飬���뱾��û����
	 */
	@Test
	public void sumOfSquares() {
		//151
		Stream<Integer> stream = Stream.of(1, 4, 3, 5, 10);
		int result = stream.parallel()
				.map(x -> x * x)
				.reduce(0, Integer::sum);
		System.out.println(result);
	}
	
}
