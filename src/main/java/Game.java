class Frame{
		static final int STRIKE = 2; 
		static final int SPARE = 1; 
		static final int OTHERSTAT = 0; 

		int score = 0;
		int bonus_score = 0;
		int last_bonus = 0;
		int[] roll = new int[2];
		int stat = 0;
		public Frame(){
			this.roll[0] = 0;
			this.roll[1] = 0;
		}
		
		public int getStat() {
			return stat;
		}
		public void setStat(int stat) {
			this.stat = stat;
		}
		public int getRoll0() {
			return roll[0];
		}
		public int getRoll1() {
			return roll[1];
		}
		public void setRoll0(int roll) {
			this.roll[0] = roll;
		}
		public void setRoll1(int roll) {
			this.roll[1] = roll;
		}
		public int getScore() {
			return score;
		}
		public void setScore(int score) {
			this.score = score;
		}
		public void setScore() {
			this.score = this.roll[0] + this.roll[1];
		}
		public int getBonus_score() {
			return bonus_score;
		}
		public void setBonus_score(int bonus_score) {
			this.bonus_score = bonus_score;
		}
}

public class Game {
	Frame[] frame = new Frame[12];
	int currentFrame = 0;
	public int getCurrentFrame() {
		if(currentFrame != 10)
			return currentFrame+1;
		else
			return currentFrame;
	}
	int rolls = 0;
	public static final int Nomal_Frame_number = 10;
	public static final int strike_score = 10;
	public static final int spare_complete_score = 10;
	public static final int frame_number = 10;
	
	public Game(){
		for(int index= 0 ; index < Nomal_Frame_number+2 ; index++)
			frame[index] = new Frame();
	}

	public void roll(final int pins) {
		if(currentFrame >= 10 ){//11th
			if(frame[currentFrame-1].getStat() == Frame.SPARE && currentFrame == 10){
				if(rolls == 0){
					frame[currentFrame].setRoll0(pins);
					frame[currentFrame].setScore();
					rolls++;
				}else if(rolls == 1){
					frame[currentFrame].setRoll1(pins);
					frame[currentFrame].setScore();
					rolls = 0;
					if(frame[currentFrame].getScore() == 10 && frame[currentFrame].getStat() != Frame.STRIKE)
						frame[currentFrame].setStat(Frame.SPARE);
					currentFrame++;
				}
			}else if(frame[currentFrame-1].getStat() == Frame.STRIKE){
				if(pins == strike_score){
					frame[currentFrame].setRoll0(strike_score);
					frame[currentFrame].setRoll1(0);
					frame[currentFrame].setScore();
					frame[currentFrame].setStat(Frame.STRIKE);
					currentFrame++;
				}
			}
			return;
		}
		if(pins == strike_score){
			frame[currentFrame].setRoll0(strike_score);
			frame[currentFrame].setRoll1(0);
			frame[currentFrame].setScore();
			frame[currentFrame].setStat(Frame.STRIKE);
			currentFrame++;
		}else if(rolls == 0){
			frame[currentFrame].setRoll0(pins);
			frame[currentFrame].setScore();
			rolls++;
		}else if(rolls == 1){
			frame[currentFrame].setRoll1(pins);
			frame[currentFrame].setScore();
			rolls = 0;
			if(frame[currentFrame].getScore() == 10 && frame[currentFrame].getStat() != Frame.STRIKE)
				frame[currentFrame].setStat(Frame.SPARE);
			currentFrame++;
		}
	}

	public int score(){
		int score = 0; 
		for(int calframe = 0; calframe < Nomal_Frame_number; calframe++){
			if(isStrike(calframe)){
				score += strike_score + bonusScoreOfStrike(calframe);
			}else if(isSpare(calframe)){
				score += spare_complete_score + bonusScoreOfSpare(calframe);
			}else{
				score += frame[calframe].getScore();
			}
		}
		return score;
	}

	private boolean isStrike(final int calframe) {
		return frame[calframe].getStat() == Frame.STRIKE;
	}

	private int bonusScoreOfSpare(final int calframe) {
		frame[calframe].setBonus_score( frame[calframe+1].getRoll0() );
		return frame[calframe].getBonus_score();
	}

	private boolean isSpare(final int calframe) {
		return frame[calframe].getStat() == Frame.SPARE;
	}

	private int bonusScoreOfStrike(final int calframe) {
		if(!isStrike(calframe+1))
			frame[calframe].setBonus_score( frame[calframe+1].getScore() );
		if(isStrike(calframe+1)){
			if(isStrike(calframe+2))
				frame[calframe].setBonus_score( strike_score + strike_score );
			else
				frame[calframe].setBonus_score( strike_score + frame[calframe+2].getRoll0() );
		}
		return frame[calframe].getBonus_score();
	}
}