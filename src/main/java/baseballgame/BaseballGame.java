package baseballgame;
import java.util.Scanner;

public class BaseballGame {
	private static final String GO = "1";
	private static final String STOP = "2";
	private static final String STRIKE = "��Ʈ����ũ";
	private static final String BALL = "��";
	private static final String NOTHING = "����";
	private static final int BALL_LEN = 3;
	private static final int ZERO = 0;
	private static final int THREE_STRIKE = 3;
	Player player = new Player();
	Computer computer = new Computer();
	String restart;
	Scanner sc = new Scanner(System.in);
	
	public void startGame() {
		do {
			computer.makeRandomNums();
			makeMyNumsAndCheckResult();
		} while(restartGame());
	}
	private void makeMyNumsAndCheckResult() {
		do {
			player.makeMyNums();
		} while (checkResult());
	}
	private boolean checkResult() {
		int strike = ZERO;
		int ball = ZERO;
		boolean status = true;
		for(int i = 0; i < BALL_LEN; i++) {
			for(int j = 0; j < BALL_LEN; j++) {
				strike += countStrike(i, j);
				ball += countBall(i,j);
			}
		}
		printResult(strike, ball);
		if (strike == THREE_STRIKE) status = false;
		else if (strike < THREE_STRIKE) status = true;
		return status;
	}
	private int countStrike(int i, int j) {
		int strike = ZERO;
		if((i == j) && (player.myNums.get(i) == computer.randomNums.get(j))) {
			strike++;
		}
		return strike;
	}
	private int countBall(int i, int j) {
		int ball = ZERO;
		if (i == j) {
			ball = ZERO;
		}
		else if(player.myNums.get(i) == computer.randomNums.get(j)) {
			ball++;
		}
		return ball;
	}
	private void printResult(int strike, int ball) {
		if (strike > ZERO && ball > ZERO) {
			System.out.println(strike + STRIKE + " " + ball + BALL);
		}
		else if (strike > ZERO) {
			System.out.println(strike + STRIKE);
		}
		else if (ball > ZERO) {
			System.out.println(ball + BALL);
		}
		else if (strike == ZERO && ball == ZERO) {
			System.out.println(NOTHING);
		}
	}
	private boolean restartGame() {
		System.out.println("������ ���� �����Ϸ��� 1, �����Ϸ��� 2�� �Է��ϼ���.");
		restart = sc.nextLine();
		while (true) {
			if (restart.equals(GO) || restart.equals(STOP)) { break; }
			System.out.println("�߸��Է��ϼ̽��ϴ�. ������ ���� �����Ϸ��� 1, �����Ϸ��� 2�� �Է��ϼ���.");
			restart = sc.nextLine();
		}
		return restart.equals(GO);
	}
}
