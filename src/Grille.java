import java.util.*;
import java.util.Scanner;

public class Grille {
	int size;
	String[][] grillecouleur;
	int[][] grillejoueur;
	
	private static String[] availableColors = {"r", "o", "j", "v", "b", "i"};

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
	
	public String choisirCouleur(int joueur){
		Scanner scan = new Scanner(System.in);
		System.out.println("Joueur "+joueur+", à vous de sélectionner une couleur");
		String choix = scan.next();
		scan.close();
		
		return choix;
	}
	
	public void modifGrille(String couleur,int joueur){
		int[][] newgrillejoueur = new int[this.size][this.size];
		String[][] newgrillecouleur = new String[this.size][this.size];
		for(int i = 0; i < this.size; i++) {
			for(int j = 0; j < this.size; j++) {
				if(grillejoueur[j][i] == joueur){
					newgrillecouleur[j][i] = couleur.toUpperCase();
					newgrillejoueur[j][i] = joueur;
					System.out.println("Normal");
					if(j > 0){
						if(grillecouleur[j-1][i] == couleur || grillecouleur[j-1][i] == couleur.toUpperCase()){
							newgrillecouleur[j-1][i] = couleur.toUpperCase();
							newgrillejoueur[j-1][i] = joueur;
						}
					}
					if(i > 0){
						if(grillecouleur[j][i-1] == couleur || grillecouleur[j][i-1] == couleur.toUpperCase()){
							newgrillecouleur[j][i-1] = couleur.toUpperCase();
							newgrillejoueur[j][i-1] = joueur;
						}
					}
					if(j < this.size){
						if(grillecouleur[j+1][i] == couleur || grillecouleur[j+1][i] == couleur.toUpperCase()){
							newgrillecouleur[j+1][i] = couleur.toUpperCase();
							newgrillejoueur[j+1][i] = joueur;
							System.out.println("Cool");
						}
					}
					if(i < this.size){
						if(grillecouleur[j][i+1] == couleur || grillecouleur[j][i+1] == couleur.toUpperCase()){
							newgrillecouleur[j][i+1] = couleur.toUpperCase();
							newgrillejoueur[j][i+1] = joueur;
							System.out.println("Sympa");

						}
					}
				}
				else{
					if(newgrillejoueur[j][i] != joueur){
						newgrillecouleur[j][i] = grillecouleur[j][i];
						newgrillejoueur[j][i] = grillejoueur[j][i];
					}
				}
			}
		}
		grillecouleur = newgrillecouleur;
		grillejoueur = newgrillejoueur;
	}
	
	
}
