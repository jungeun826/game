
public class Game {
	int rolls[];
	int currentIndex= 0;

	public void roll(final int pins) 
	{
		rolls[currentIndex++]= pins;
		rolls = new int[20];
	}

	public int score() 
	{
		int score = 0; 

		for(int frame = 0; frame < 10; frame++)
		{
			if(isStrike(frame)){
				score += 10 + frameScore(frame+1);
			}else if(isSpare(frame)){
				score += 10 + bonusScoreOfSpare(frame);
			}else{
				score += frameScore(frame);
			}
		}
		return score;
	}

	private boolean isStrike(final int frame) {
		return rolls[frame*2] == 10;
	}

	private int bonusScoreOfSpare(final int frame) {
		return rolls[2*(frame+1)];
	}

	private boolean isSpare(final int frame) {
		return frameScore(frame) == 10;
	}

	private int frameScore(final int frame) {
		return rolls[2*frame]+rolls[2*frame+1];
	}

}
