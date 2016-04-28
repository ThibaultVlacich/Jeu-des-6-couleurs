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

import models.Utils;

import view.Home;

public class Launcher {
  // Version actuelle du jeu
  private static String version = "1.1.1";

  public static void main(String[] args) {
    System.out.println("###\t\tJeu des 6 couleurs en Java\t\t###");
    System.out.println("###\tFait par Thibault VLACICH et Hugo MICHARD\t###");
    System.out.println("###\t\t\tVersion " + version + "\t\t\t###");
    System.out.println("");

    Home.launch(Home.class);

    // Ferme le scanner
    Utils.scan.close();
  }
}
