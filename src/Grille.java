import java.util.*;

public class Grille {
	int size;
	String[][] grillecouleur;
	int[][] grillejoueur;
	
	private static String[] availableColors = {"r", "o", "j", "v", "b", "i"};
	private static String[] Colors = {"r", "o", "j", "v", "b", "i","R","O","J","V","B","I"};

	public Grille(int size) {
		this.size = size;
		this.grillecouleur = new String[this.size][this.size];
		this.grillejoueur = new int[this.size][this.size];
	}
	
	public void initRandom() {
		
		Random r = new Random();
		int Low = 1;
		int High = 6;
		
		for(int i = 0; i < this.size; i++) {
			for(int j = 0; j < this.size; j++) {
				int Result = r.nextInt(High-Low) + Low;
				
				grillecouleur[j][i] = Grille.availableColors[Result - 1];
				grillejoueur[j][i] = 0;
			}
		}
		
		grillecouleur[0][0] = grillecouleur[0][0].toUpperCase();
		grillecouleur[size-1][size-1] = grillecouleur[size-1][size-1].toUpperCase();
		grillejoueur[0][0] = 1;
		grillejoueur[size-1][size-1] = 2;

	}
	
	public void displayConsole() {
		for(int i = 0; i < this.size; i++) {
			System.out.print("|");
			for(int j = 0; j < this.size; j++) {
				System.out.print(grillecouleur[j][i]);
				System.out.print("|");
			}
			System.out.println("");
		}
		for(int i = 0; i < this.size; i++) {
			System.out.print("|");
			for(int j = 0; j < this.size; j++) {
				System.out.print(grillejoueur[j][i]);
				System.out.print("|");
			}
			System.out.println("");
		}
	}
}
