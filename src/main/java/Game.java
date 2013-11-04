
public class Game {
	int[] rolls;
	int currentIndex;
	int rolling_number;
	int strike_score;
	int spare_complete_score;
	int frame_number;
	public Game(){
		currentIndex = 0;
		rolling_number = 20;
		strike_score = 10;
		spare_complete_score = 10;
		frame_number = 10;
		rolls = new int[rolling_number];
	}
	public void roll(final int pins) {
		rolls[currentIndex++]= pins;
	}

	public int score(){
		int score = 0; 
		for(int frame = 0; frame < frame_number; frame++){
			if(isStrike(frame)){
				score += strike_score + frameScore(frame+1);
			}else if(isSpare(frame)){
				score += spare_complete_score + bonusScoreOfSpare(frame);
			}else{
				score += frameScore(frame);
			}
		}
		return score;
	}

	private boolean isStrike(final int frame) {
		return rolls[frame*2] == strike_score;
	}

	private int bonusScoreOfSpare(final int frame) {
		return rolls[2*(frame+1)];
	}

	private boolean isSpare(final int frame) {
		return frameScore(frame) == spare_complete_score;
	}

	private int frameScore(final int frame) {
		return rolls[2*frame]+rolls[2*frame+1];
	}

}
