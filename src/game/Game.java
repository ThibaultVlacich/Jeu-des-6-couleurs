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

import observer.Observable;
import observer.Observer;

import models.TileColor;

public class Game implements Observable {
  // L'objet Grid instancié pour le jeu
  private Grid grid;

  // Les objets joueurs
  private Player[] players;

  // Le joueur actuel
  private Player currentPlayer;

  // Défini si la partie est terminée ou non
  private Boolean gameOver = false;

  public Game() {
    // Création d'une grille carrée de 13 de coté
    grid = new GridDiamond(13);

    // On initialise la grille de manière aléatoire
    grid.initRandom();

    // On demande le nombre de joueurs
    int nbOfPlayers = 2;

    // On créé la liste des joueurs
    players = new Player[nbOfPlayers];

    for (int i = 0; i < nbOfPlayers; i++) {
      // On créé l'objet Player
      players[i] = new Player(i + 1);
      
      int[] cornerCoordinates = grid.getCornerCoordinate(i);

      int x = cornerCoordinates[0];
      int y = cornerCoordinates[1];

      // On associe un coin au joueur
      grid.assignTile(x, y, i + 1);
      players[i].setColor(grid.getTile(x, y).getColor());
    }
  }

  /**
   * Démarre une partie
   */
  public void start() {
    // Le joueur 1 commence
    currentPlayer = players[0];
  }

  public void chooseTile(Tile tile) {
    TileColor color = tile.getColor();

    if(isColorOwned(color)) {
      notifyCantChooseColor(color);

      return;
    }

    // On lui assigne la couleur choisie
    currentPlayer.setColor(color);
    grid.assignTiles(currentPlayer.ID, color);

    // Au joueur suivant !
    currentPlayer = (currentPlayer.ID == players.length) ? players[0] : players[currentPlayer.ID];

    // On notifie l'observateur qu'il faut mettre à jour la vue
    notifyUpdateView();

    // On vérifie si la partie est terminée
    checkIsGameIsOver();

    if (gameOver) {
      int winnerID           = 0;
      int tilesOwnedByWinner = 0;

      for (Player player: players) {
        int tilesOwnedByPlayer = grid.countTilesOwnedBy(player.ID);

        if (tilesOwnedByPlayer > tilesOwnedByWinner) {
          winnerID           = player.ID;
          tilesOwnedByWinner = tilesOwnedByPlayer;
        }
      }

      notifyGameIsOver(winnerID);
    }
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

  /**
   * Permet d'obtenir la grille de jeu
   *
   * @return  La grille
   */
  public Grid getGrid() {
    return grid;
  }

  /**
   * Permet de notifier l'observateur qu'il faut mettre à jour la vue
   */
  public void notifyUpdateView() {
    for (Observer obs: observers) {
      obs.updateView();
    }
  }

  /**
   * Permet de notifier l'observateur qu'il n'est pas possible de choisir cette couleur
   *
   * @param c La couleur
   */
  public void notifyCantChooseColor(TileColor c) {
    for (Observer obs: observers) {
      obs.cantChooseColor(c);
    }
  }

  /**
   * Permet de notifier l'obesrvateur que la partie est terminée
   *
   * @param winnerID  l'ID du joueur qui a gagné
   */
  public void notifyGameIsOver(int winnerID) {
    for (Observer obs: observers) {
      obs.gameOver(winnerID);
    }
  }
}
