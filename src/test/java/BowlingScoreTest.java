import static org.junit.Assert.assertEquals;


import org.junit.Before;
import org.junit.Test;


public class BowlingScoreTest {

private Game game;

	@Before
	//test가 하나하나 별개->setup실행 끝나면 테어다운?실행 
	//리펙터링 재사용성 코드 따로 뺌. 중복소스 없앰
	public void setUp() throws Exception {
		game = new Game();
	}
	
	@Test
	public void FrameNumtest() {
		rollMany(9, 0);
		
		assertEquals(5,game.getCurrentFrame());
	}
//버전 올라가서 어노테이션으로 바뀜
	@Test
	public void GutterGametest() {
		//fail("Not yet implemented");//무조건 fail
		//실제값 =현재값
		
		rollMany(20, 0);
		assertEquals(0,game.score());
	}
	
	//함수는 무조건 하나의 기능을 가지도록 만들어야 함.
	//매직넘버를 가지면안됨...
	private void rollMany(int n, int pins) {
		for(int index = 0 ; index < n; index++){
			game.roll(pins);
		}
	}
	
	@Test
	public void WholeGameTest(){
		int n = 20;
		int pins = 1;
		
		rollMany(n, pins);
		
		assertEquals(20,game.score());
	}
	
	@Test
	public void OneSpareGameTest(){
		game.roll(5);
		game.roll(5);
		game.roll(5);
		
		rollMany(17,0);
		
		assertEquals(20,game.score());
	}
	
	@Test
	public void OneStrikeGameTest(){
		game.roll(10);
		
		game.roll(3);
		game.roll(4);
		
		rollMany(16,0);
		
		assertEquals(24,game.score());
	}
	
	@Test
	public void OneStrikeGameTest2(){
		game.roll(10);
		game.roll(0);
		game.roll(3);
		game.roll(4);
		
		rollMany(16,0);
		
		assertEquals(20,game.score());
	}
	
	@Test
	public void perfectStrikeGameTest(){
		rollMany(12,10);
		
		
		assertEquals(300,game.score());
		
	}
	
	@Test
	public void perfectSpareGameTest(){
		rollMany(21,5);
		
		
		assertEquals(150,game.score());
		
	}
}
