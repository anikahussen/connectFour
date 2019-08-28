package connectFour;
import java.util.Scanner;


public class connectFour {
	static final int X =1, O=2;
	static final int NUM_COLUMNS = 4;
	static final int NUM_IN_ROW=4;
	static Scanner input = new Scanner(System.in);
	static int firstplayer;
	static int p1=0,p2=0,ties=0;
	static long cnt=0;
	

	public static void main(String[] args) {

		for (int i=0; i<4; i++) {
			int[ ][ ] list = new int[NUM_COLUMNS][NUM_COLUMNS];
			firstplayer = X;
			p1=0;p2=0;cnt=0;ties=0;
			
			
		    switch(i) {//  possible first positions
		    case 0: list[3][0]=X; break; 
		    case 1: list[3][1]=X; break; 
		    case 2: list[3][2]=X; break; 
		    case 3: list[3][3]=X; break; 
		    
		    }
		    
		    int colCount = i+1;
		    
			Play(list , O);
			System.out.println ();
			System.out.println ("COLUMN "+ colCount);
			System.out.println ("NetWins:" + (p1-p2));
			System.out.println("RED-wins:  "+p1 +" BLUE-Wins:"+p2+ " ties:" + ties);
			System.out.println ("Total Recursion calls: " + cnt);
		}
	}
	public static int Play(int[][] inlist, int clr) {
		cnt++;
		int res=checkBoard(inlist,clr);
		// 0 - board full, 1- X wins  2 = O wins   3-keep playing
		if (res < 3) { 
			if (res == 0) {ties++;return 0;
			} else {
				if (res == firstplayer) {p1++; return 1;} else {p2++; return -1;}
			}
		}	
		res = 0;
		
		
		
		// for each space that can be the next move
		//    make a copy of board (next lines)	

		//   update the board for this move
		for (int col = 3; col>=0;col--  ){
			for (int row = 3; row>=0;row--  ){
				if (inlist[row][col] == 0) {
					int[][] clonelist = new int[NUM_COLUMNS][NUM_COLUMNS];
					for (int x = 0;x <NUM_COLUMNS;x++  ){
						for (int y = 0; y<NUM_COLUMNS;y++  ){
							clonelist[x][y] = inlist[x][y] ;
						}
					}
					
					clonelist[row][col] = clr;
				
					Play(clonelist, 3- clr);
					break;
				}
			}
		}
		//  recursively call Play
		return res;
	}
	public static boolean isFull(int[][] inlist){
		boolean empty = true;
		for (int i = 0 ; i<NUM_COLUMNS ; i++ ) {
			for (int i2 = 0 ; i2<NUM_COLUMNS ; i2++ ) {
				if (inlist[i][i2] ==0   ) { empty = false; break;} 
			}
		}
		return empty;
	}
	public static int checkBoard(int[][] inlist ,int clr){
		int chkclr = 3-clr;
		
		for (int i = 0 ; i<NUM_COLUMNS; i++ ) {
			int colcnt = 0;
			for (int j=0; j<NUM_COLUMNS; j++) {
				if (inlist[i][j] == chkclr) {
					colcnt++;
					if (colcnt == NUM_IN_ROW)  { return chkclr;}	 
				}  else {
					colcnt =0;
				}
			}
		}
		
		
		for (int i = 0 ; i<NUM_COLUMNS; i++ ) {
			int colcnt = 0;
			for (int j=0; j<NUM_COLUMNS; j++) {
				if (inlist[j][i] == chkclr) {
					colcnt++;
					if (colcnt == NUM_IN_ROW)  { return chkclr;}	 
				}  else {
					colcnt =0;
				}
			}
		}
		
		
		int colcnt = 0;
		for (int i = 0 ; i<NUM_COLUMNS; i++ ) {
			if (inlist[i][i] == chkclr) {
				colcnt++;
				if (colcnt == NUM_IN_ROW)  {return chkclr;}	 
			}  else {
				colcnt =0;
			}
		}
		
		
		colcnt = 0;
		for (int i = 0 ; i<NUM_COLUMNS; i++ ) {
			if (inlist[NUM_COLUMNS-1-i][i] == chkclr) {
				colcnt++;
				if (colcnt == NUM_IN_ROW)  { return chkclr;}	 
			}  else {
				colcnt =0;
			}
		}
		
		
		if (isFull(inlist)) {  return 0; 
		} else {
			return 3;
		}


	}
	public static void printlist(int[][] inlist) {
		for (int i =0; i<inlist.length; i++) {
			for (int j =0; j<inlist.length; j++) {
				System.out.print(inlist[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
