package chapter9;

import java.util.function.Consumer;

/**
 * P130 Ŀ��ӿ�
 * @author skywalker
 *
 */
public interface ArtistAnalyzer {

	public void isLargerGroup(String artistName, String otherArtistName, Consumer<Boolean> handler);
	
}
