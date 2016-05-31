package edu.isep.jeudes6couleurs;

import edu.isep.jeudes6couleurs.view.Home;

/**
 * Jeu des 6 couleurs
 *
 * @package edu.isep.jeudes6couleurs
 * @class   Launcher
 * @desc    Permet de lancer le jeu
 *
 * @author  Thibault Vlacich <thibault.vlacich@isep.fr>
 * @author  Hugo Michard <hugo.michard@isep.fr>
 */
public class Launcher {
  // Version actuelle du jeu
  private static String version = "1.5.1";

  public static void main(String[] args) {
    System.out.println("###\t\tJeu des 6 couleurs en Java\t\t###");
    System.out.println("###\tFait par Thibault VLACICH et Hugo MICHARD\t###");
    System.out.println("###\t\t\tVersion " + version + "\t\t\t###");
    System.out.println("");

    // Ouverture de l'écran d'accueil
    Home.launch(Home.class);
  }
}
