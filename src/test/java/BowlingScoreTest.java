import static org.junit.Assert.assertEquals;

//import java.util.Random;

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
//���� �ö󰡼� ������̼����� �ٲ�
	@Test
	public void GutterGametest() {
		//fail("Not yet implemented");//������ fail
		//������ =���簪
		int n = 20;
		int pins = 0;
		
		rollMany(n, pins);
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
		game.roll(0);
		
		game.roll(3);
		game.roll(4);
		
		rollMany(16,0);
		
		assertEquals(24,game.score());
	}
	
	@Test
	public void GameTest(){
		/*int rolls = 0;
		int pins = 0;
		Random rand = new Random(10);
		for(rolls = 0 ; rolls < 20; rolls++){
			pins = rand.nextInt();
			game.roll(pins);
		}*/
		
	}
}
