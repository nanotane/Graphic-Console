package gameFunctions.id2013.Blindman;
/**
 * 
 * @author Sirkorski
 *
 */
public class Level {
	public int[][] board = new int[13][13];

	// 0 is empty
	// 1 is player
	// 2 is wall
	// 3 is door/objective
	// 4 is boxes
	// 5 is player in door
	// 6 is player in box
	// 7 is voice recorder
	// 8 is trap
	public void lvl1() {
		board[0][0] = 2;
		board[0][1] = 2;
		board[0][2] = 2;
		board[0][3] = 2;
		board[0][4] = 2;
		board[0][5] = 2;
		board[0][6] = 2;
		board[0][7] = 2;
		board[0][8] = 2;
		board[0][9] = 2;
		board[0][10] = 2;
		board[0][11] = 2;
		board[0][12] = 2;
		board[1][0] = 0;
		board[1][1] = 2;
		board[1][2] = 7;
		board[1][3] = 0;
		board[1][4] = 0;
		board[1][5] = 0;
		board[1][6] = 1;
		board[1][7] = 0;
		board[1][8] = 2;
		board[2][0] = 0;
		board[2][1] = 2;
		board[2][2] = 2;
		board[2][3] = 2;
		board[2][4] = 2;
		board[2][5] = 0;
		board[2][6] = 0;
		board[2][7] = 0;
		board[2][8] = 2;
		board[2][9] = 2;
		board[3][0] = 0;
		board[3][1] = 2;
		board[3][2] = 7;
		board[3][3] = 0;
		board[3][4] = 2;
		board[3][5] = 2;
		board[3][6] = 2;
		board[3][7] = 0;
		board[3][8] = 7;
		board[3][9] = 2;
		board[4][0] = 0;
		board[4][1] = 2;
		board[4][2] = 2;
		board[4][3] = 0;
		board[4][4] = 7;
		board[4][5] = 2;
		board[4][6] = 2;
		board[4][7] = 0;
		board[4][8] = 2;
		board[4][9] = 2;
		board[5][0] = 0;
		board[5][1] = 0;
		board[5][2] = 2;
		board[5][3] = 0;
		board[5][4] = 0;
		board[5][5] = 0;
		board[5][6] = 0;
		board[5][7] = 0;
		board[5][8] = 0;
		board[5][9] = 2;
		board[6][0] = 0;
		board[6][1] = 0;
		board[6][2] = 2;
		board[6][3] = 7;
		board[6][4] = 2;
		board[6][5] = 0;
		board[6][6] = 2;
		board[6][7] = 0;
		board[6][8] = 2;
		board[6][9] = 2;
		board[7][0] = 0;
		board[7][1] = 0;
		board[7][2] = 2;
		board[7][3] = 2;
		board[7][4] = 2;
		board[7][5] = 0;
		board[7][6] = 0;
		board[7][7] = 0;
		board[7][8] = 3; // This is da door yo dog
		board[8][0] = 0;
		board[8][4] = 2;
		board[8][5] = 2;
		board[8][6] = 0;
		board[8][7] = 2;
		board[8][8] = 2;
		board[9][5] = 2;
		board[9][6] = 7;
		board[9][7] = 2;
		board[10][5] = 2;
		board[10][6] = 2;
		board[10][7] = 2;
	}

	public void lvl2() {
		board[0][0] = 2;
		board[0][1] = 2;
		board[0][2] = 2;
		board[0][3] = 2;
		board[0][4] = 2;
		board[0][5] = 2;
		board[0][6] = 2;
		board[0][7] = 2;
		board[0][8] = 2;
		board[0][9] = 2;
		board[0][10] = 2;
		board[0][11] = 2;
		board[0][12] = 2;
		board[1][4] = 2;
		board[1][5] = 1;
		board[1][6] = 2;
		board[1][8] = 2;
		board[1][9] = 2;
		board[2][3] = 2;
		board[2][4] = 0;
		board[2][5] = 0;
		board[2][6] = 0;
		board[2][7] = 2;
		board[2][8] = 7;
		board[2][9] = 2;
		board[3][0] = 0;
		board[3][1] = 2;
		board[3][2] = 2;
		board[3][3] = 2;
		board[3][4] = 2;
		board[3][5] = 0;
		board[3][6] = 2;
		board[3][7] = 2;
		board[3][8] = 0;
		board[3][9] = 2;
		board[4][0] = 0;
		board[4][1] = 2;
		board[4][2] = 0;
		board[4][3] = 7;
		board[4][4] = 2;
		board[4][5] = 0;
		board[4][6] = 0;
		board[4][7] = 2;
		board[4][8] = 0;
		board[4][9] = 2;
		board[5][0] = 0;
		board[5][1] = 2;
		board[5][2] = 0;
		board[5][3] = 2;
		board[5][4] = 2;
		board[5][5] = 2;
		board[5][6] = 0;
		board[5][7] = 0;
		board[5][8] = 0;
		board[5][9] = 2;
		board[6][0] = 0;
		board[6][1] = 2;
		board[6][2] = 0;
		board[6][3] = 0;
		board[6][4] = 0;
		board[6][5] = 0;
		board[6][6] = 0;
		board[6][7] = 0;
		board[6][8] = 0;
		board[6][9] = 2;
		board[7][0] = 0;
		board[7][1] = 2;
		board[7][2] = 2;
		board[7][3] = 2;
		board[7][4] = 2;
		board[7][5] = 0;
		board[7][6] = 2;
		board[7][7] = 2;
		board[7][8] = 3;
		board[7][9] = 2;
		board[8][0] = 0;
		board[8][3] = 2;
		board[8][4] = 8;
		board[8][5] = 0;
		board[8][6] = 0;
		board[8][7] = 2;
		board[8][8] = 2;
		board[9][0] = 0;
		board[9][2] = 2;
		board[9][3] = 2;
		board[9][4] = 0;
		board[9][5] = 0;
		board[9][6] = 0;
		board[9][7] = 0;
		board[9][8] = 8;
		board[9][9] = 2;
		board[10][0] = 0;
		board[10][1] = 2;
		board[10][2] = 7;
		board[10][3] = 0;
		board[10][4] = 0;
		board[10][5] = 0;
		board[10][6] = 0;
		board[10][7] = 2;
		board[10][8] = 2;
		board[11][0] = 0;
		board[11][2] = 2;
		board[11][3] = 2;
		board[11][4] = 8;
		board[11][5] = 2;
		board[11][6] = 2;
		board[11][7] = 2;
		board[12][0] = 0;
		board[12][3] = 2;
		board[12][4] = 2;
		board[12][5] = 2;

	}

	public Level() {

	}
}
