
public class Launcher {
	private static int defaultGridSize = 13;
	
	public static void main(String[] args) {
		System.out.println("Bienvenue dans le jeu des 6 couleurs !");
		
		Grille grille = new Grille(defaultGridSize);
		grille.initRandom();
		
		grille.displayConsole();
	}

}
