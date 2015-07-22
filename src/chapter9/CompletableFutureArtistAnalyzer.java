package chapter9;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;

import roles.Artist;

/**
 * ʹ��CompletableFuture�ع�isLagerGroup()ʹ֮���Բ���ִ��
 * @author skywalker
 *
 */
public class CompletableFutureArtistAnalyzer implements ArtistAnalyzer {
	
	private final Function<String, Artist> artistLookUpService;
	
	public CompletableFutureArtistAnalyzer(Function<String, Artist> artistLookUpService) {
		this.artistLookUpService = artistLookUpService;
	}

	@Override
	public void isLargerGroup(String artistName, String otherArtistName,
			Consumer<Boolean> handler) {
		CompletableFuture<Integer> first = CompletableFuture.supplyAsync(() -> getNumberOfMembers(artistName));
		CompletableFuture<Integer> second = CompletableFuture.supplyAsync(() -> getNumberOfMembers(artistName));
		second.thenCombine(first, (count, otherCount) -> count > otherCount)
			.thenAccept(handler);
	}
	
	//�˴�ʹ�������ҵ��������
	private int getNumberOfMembers(String name) {
		return artistLookUpService.apply(name).getAge();
	}

}
