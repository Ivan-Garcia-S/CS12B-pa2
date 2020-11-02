import java.util.InputMismatchException;
import java.util.Scanner;

class Queens{

	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		String mode = "";
		int n = 0;
		
		if(args.length == 1) {
			try {
				n = Integer.parseInt(args[0]);
			}
			catch(InputMismatchException e){
				System.out.println("Usage: Queens [-v] number");
				System.out.println("Option: -v verbose output, print all solutions");
			}
			finally {
				if(n != 0) {
					int[][]A = new int[n+1][n+1];
					int solutions = findSolutions(A,1,mode);
					System.out.print(n + "-Queens has " + solutions + " solutions");
				}
			}
		}
		else if(args.length == 2) {
			try {
				mode = args[0];
				n = Integer.parseInt(args[1]);
			}
			catch(Exception e){
				System.out.println("Usage: Queens [-v] number");
				System.out.println("Option: -v verbose output, print all solutions");
			}
			finally {
				
				if(mode.equals("-v")) {
					int[][]A = new int[n+1][n+1];
					int solutions = findSolutions(A,1,"verbose");
					System.out.print(n + "-Queens has " + solutions + " solutions");
				}
			}
		}
		else {
			System.out.println("Usage: Queens [-v] number");
			System.out.println("Option: -v verbose output, print all solutions");
		}		
	
	}

	static void placeQueen(int[][] B, int i, int j){
		B[i][j] = 1;
		B[i][0] = j;
		for(int k = i+1; k < B.length;k++){
			B[k][j]--;
		}
		for(int l = j-1, d = i+1; l > 0 && d < B.length;l--,d++){
			B[d][l]--;
		}
		for(int r = j+1,d = i+1; r < B[0].length && d < B.length;r++,d++){
			B[d][r]--;
		}
	}

	static void removeQueen(int[][] B, int i, int j){
		B[i][j] = 0;
		B[i][0] = 0;
		for(int k = i+1; k < B.length;k++){
			B[k][j]++;
		}
		for(int l = j-1, d = i+1; l > 0 && d< B.length;l--,d++){
			B[d][l]++;
		}
		for(int r = j+1,d = i+1; r < B[0].length && d < B.length;r++,d++){
			B[d][r]++;
		}
	}
	static void printBoard(int[][]B){
		String solution = "(";
		for(int i = 1; i < B.length;i++)
		{
			solution = solution + B[i][0] + ", ";
		}
		solution = solution.substring(0,solution.length()-2);
		solution = solution + ")";
		System.out.println(solution);

	}

	static int findSolutions(int[][]B,int i, String mode){
		int sum = 0;
		if(i > B.length-1) {
			if(mode.equals("verbose")) {
				printBoard(B);
			}
			return 1;
		}
		
		else {
			for(int c = 1; c < B.length; c++) {
				if(B[i][c] >=0) {
					placeQueen(B,i,c);
					sum += findSolutions(B,i+1,mode);
					removeQueen(B,i,c);
				}
			}
		}
		return sum;
	}
}
