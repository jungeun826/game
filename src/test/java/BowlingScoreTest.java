import static org.junit.Assert.assertEquals;


import org.junit.Before;
import org.junit.Test;


public class BowlingScoreTest {

private Game game;

	@Before
	//test�� �ϳ��ϳ� ����->setup���� ������ �׾�ٿ�?���� 
	//�����͸� ���뼺 �ڵ� ���� ��. �ߺ��ҽ� ����
	public void setUp() throws Exception {
		game = new Game();
	}
	
	@Test
	public void FrameNumtest() {
		rollMany(9, 0);
		
		assertEquals(5,game.getCurrentFrame());
	}
//���� �ö󰡼� ������̼����� �ٲ�
	@Test
	public void GutterGametest() {
		//fail("Not yet implemented");//������ fail
		//������ =���簪
		
		rollMany(20, 0);
		assertEquals(0,game.score());
	}
	
	//�Լ��� ������ �ϳ��� ����� �������� ������ ��.
	//�����ѹ��� ������ȵ�...
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
