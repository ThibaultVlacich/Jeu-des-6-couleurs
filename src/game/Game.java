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

import models.TileColor;
import models.Utils;

public class Game {
  // Mode d'affichage du jeu (console ou 2D)
  private String mode = "2D";

  // L'objet Grid instancié pour le jeu
  private Grid grid;
  
  // Les objets joueurs
  private Player[] players;
  
  // Défini si la partie est terminée ou non
  private Boolean gameOver = false;
  
  // Stock les coordonnées des coins
  // TODO : Stocker cette info dans la classe Grid
  // quand il y aura différents types de grilles
  private int[][] cornersCoordinates = {
      {0,  0},  // En haut à gauche - Joueur 1
      {12, 12}, // En bas à droite  - Joueur 2
      {0,  12}, // En haut à droite - Joueur 3
      {12,  0}  // En bas à gauche  - Joueur 4
  };
  
  public Game() {
    // Création d'une grille carrée de 13 de coté
    grid = new GridSquare(13);

    // On initialise la grille de manière aléatoire
    grid.initRandom();
    
    // On demande le nombre de joueurs
    int nbOfPlayers = 2;
    
    // On créé la liste des joueurs
    players = new Player[nbOfPlayers];
    
    for (int i = 0; i < nbOfPlayers; i++) {
      // On créé l'objet Player
      players[i] = new Player(i + 1);
      
      int x = cornersCoordinates[i][0];
      int y = cornersCoordinates[i][1];
      
      // On associe un coin au joueur
      grid.assignTile(x, y, i + 1);
      players[i].setColor(grid.getTile(x, y).getColor());
    }
    
    // On affiche la grille générée 
    /*if (mode == "console") {
      grid.showConsole();
    } else {
      grid.show2D();
    }*/
  }

  /**
   * Démarre une partie
   */
  public void start() {    
    // Variable contenant le joueur actuel
    // Le joueur 1 commence
    Player currentPlayer = players[0];
    
    while (!this.gameOver) {
      System.out.println("");
      System.out.println("Au tour du joueur "+currentPlayer.ID);
      System.out.println("Votre couleur actuelle est "+TileColor.getColorCode(currentPlayer.getColor()));
      System.out.println("Joueur "+currentPlayer.ID+", quelle couleur choisissez-vous ?");
      
      String    colorCode = Utils.scan.next();
      TileColor color     = TileColor.getColorFromCode(colorCode);
      
      while (
          // La couleur demandée n'existe pas
          color == null
          // La couleur demandée est déjà controlée
          || isColorOwned(color)
            )
      {
        if(color == null) {
          System.out.println("La couleur demandée n'existe pas");
        } else if (isColorOwned(color)) {
          System.out.println("Vous ne pouvez pas choisir une couleur déjà contrôlée !");
        }
        
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
      currentPlayer = (currentPlayer.ID == players.length) ? players[0] : players[currentPlayer.ID];
      
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
   * Permet de déterminer si une couleur appartient déjà à un joueur
   * 
   * @param c La couleur à tester
   * 
   * @return  (True|False)
   */
  private Boolean isColorOwned(TileColor c) {
    for (Player player: players) {
      if (player.getColor() == c) {
        return true;
      }
    }
    
    return false;
  }
  
  /**
   * Permet de définir si la partie est terminée
   */
  private void checkIsGameIsOver() {
    // Calcule le nombre de cases dans la grille
    double numberOfTiles = Math.pow(grid.getSize(), 2);
    // Nombre de cases encore non controllées
    int freeTiles = grid.countTilesOwnedBy(0);
    
    if (freeTiles == 0) {
      // Il n'y a plus de cases libres, la partie est terminée
      gameOver = true;
      
      return;
    }
    
    for (Player player: players) {
      // ID du joueur
      int pID = player.ID;
      
      // Nombre de cases controllées par le joueur
      int tilesOwnedByPlayer = grid.countTilesOwnedBy(pID);
      
      if (tilesOwnedByPlayer > numberOfTiles / 2) {
        // Le joueur possède plus de la moitié des cases, la partie est terminée
        gameOver = true;
        
        return;
      }
    }
  }

  public Grid getGrid() {
    return grid;
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
