/**
 * Jeu des 6 couleurs
 *
 * @package default
 * @class   Launcher
 * @desc    Permet de lancer le jeu
 *
 * @author  Thibault Vlacich <thibault.vlacich@isep.fr>
 * @author  Hugo Michard <hugo.michard@isep.fr>
 */

// Importe la classe Utils
import models.Utils;

// Importe la classe principale du jeu
import game.Game;

public class Launcher {

  private static String version = "1.1";

  public static void main(String[] args) {
    System.out.println("###\t\tJeu des 6 couleurs en Java\t\t###");
    System.out.println("###\tFait par Thibault VLACICH et Hugo MICHARD\t###");
    System.out.println("###\t\t\tVersion " + version + "\t\t\t###");
    System.out.println("");

    Game g = new Game();

    if(args.length > 0 && args[0].equals("console")) {
      // Permet de démarrer le jeu en mode console
      g.setMode("console");
    }

    // Démarre le jeu
    g.start();

    // Ferme le scanner
    Utils.scan.close();
  }

}
