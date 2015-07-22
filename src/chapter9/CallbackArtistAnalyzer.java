package chapter9;

import java.util.function.Consumer;
import java.util.function.Function;

import roles.Artist;

/**
 * ʹ�ûص������ع���������
 * @author skywalker
 *
 */
public class CallbackArtistAnalyzer implements ArtistAnalyzer {
	
	private final Function<String, Artist> artistLookUpService;
	
	public CallbackArtistAnalyzer(Function<String, Artist> artistLookUpService) {
		this.artistLookUpService = artistLookUpService;
	}

	@Override
	public void isLargerGroup(String artistName, String otherArtistName,
			Consumer<Boolean> handler) {
		new Thread(() -> {
			//���ûص�����
			handler.accept(getNumberOfMembers(artistName) > getNumberOfMembers(otherArtistName));
		}).start();
	}
	
	//�˴�ʹ�������ҵ��������
	private int getNumberOfMembers(String name) {
		return artistLookUpService.apply(name).getAge();
	}

}
