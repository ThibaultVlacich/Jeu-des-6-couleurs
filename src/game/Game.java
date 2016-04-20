/**
 * Jeu des 6 couleurs
 *
 * @package game
 * @class   Game
 * @desc    Classe principale du jeu
 *
 * @author  Thibault Vlacich <thibault.vlacich@isep.fr>
 * @author  Hugo Michard <hugo.michard@isep.fr>
 */

package game;

// Importe les modèles utiles au jeu
import models.TileColor;
import models.Utils;

public class Game {
  // Mode d'affichage du jeu (console ou 2D)
  private String mode = "2D";

  // L'objet Grid instancié pour le jeu
  private Grid grid;
  
  // Les objets joueurs
  private Player player1 = new Player(1);
  private Player player2 = new Player(2);
  
  // Défini si la partie est terminée ou non
  private Boolean gameOver = false;

  /**
   * Démarre une partie
   */
  public void start() {
    // Création d'une grille carrée de 13 de coté
    grid = new Grid(13);

    // On initialise la grille de manière aléatoire
    grid.initRandom();
    
    // On associe inialement les cases des coins aux joueurs
    grid.assignTile(0, 0, player1.ID);
    player1.setColor(grid.getTile(0, 0).getColor());
    grid.assignTile(12, 12, player2.ID);
    player2.setColor(grid.getTile(12, 12).getColor());
    
    // On affiche la grille générée en console
    System.out.println("Le joueur 1 débute en haut à gauche");
    System.out.println("Le joueur 2 débute en bas à droite");
    System.out.println("");
    
    if (mode == "console") {
      grid.showConsole();
    } else {
      grid.show2D();
    }
    
    // Variable contenant le joueur actuel
    // Le joueur 1 commence
    Player currentPlayer = player1;
    
    while (!this.gameOver) {
      System.out.println("");
      System.out.println("Au tour du joueur "+currentPlayer.ID);
      System.out.println("Votre couleur actuelle est "+TileColor.getColorCode(currentPlayer.getColor()));
      System.out.println("Joueur "+currentPlayer.ID+", quelle couleur choisissez-vous ?");
      
      String    colorCode = Utils.scan.next();
      TileColor color     = TileColor.getColorFromCode(colorCode);
      
      while (color == null) {
        System.out.println("La couleur demandée n'existe pas");
        
        colorCode = Utils.scan.next();
        color     = TileColor.getColorFromCode(colorCode);
      }
      
      currentPlayer.setColor(color);
      grid.assignTiles(currentPlayer.ID, color);
      
      // On affiche la nouvelle grille
      if (mode == "console") {
        grid.showConsole();
      } else {
        grid.show2D();
      }
      
      // Au joueur suivant !
      currentPlayer = (currentPlayer == player1) ? player2 : player1;
      
      checkIsGameIsOver();
    }
    
    // La partie est terminée !
    // On compte le nombres de cases possédées par chaque joueur
    int tilesOwnedByPlayer1 = grid.countTilesOwnedBy(1);
    int tilesOwnedByPlayer2 = grid.countTilesOwnedBy(2);
    
    // Le gagnant est le joueur ayant le plus de cases
    int winnerID = (tilesOwnedByPlayer1 > tilesOwnedByPlayer2) ? 1 : 2;
    
    System.out.println("La partie est terminée !");
    System.out.println("Le joueur gagnant est le joueur "+winnerID);
  }
  
  /**
   * Permet de définir si la partie est terminée
   */
  private void checkIsGameIsOver() {
    // Calcule le nombre de cases dans la grille
    double numberOfTiles = Math.pow(grid.getSize(), 2);
    // Nombre de cases encore non controllées
    int freeTiles = grid.countTilesOwnedBy(0);
    // Nombre de cases controllées par les joueurs
    int tilesOwnedByPlayer1 = grid.countTilesOwnedBy(1);
    int tilesOwnedByPlayer2 = grid.countTilesOwnedBy(2);
    
    if (
        // Il n'y a plus de cases libres, la partie est terminée
        freeTiles == 0
        // Ou le joueur 1 contrôle plus de 50% des cases
        || tilesOwnedByPlayer1 > numberOfTiles / 2
        // Ou le joueur 2 contrôle plus de 50% des cases
        || tilesOwnedByPlayer2 > numberOfTiles / 2
        ) {
      gameOver = true;
    }
  }

  /**
   * Permet de définir le mode d'affichage du jeu
   *
   * @param m
   */
  public void setMode(String m) {
    mode = m;
  }
}
