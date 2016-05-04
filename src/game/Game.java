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

import settings.Settings;

import models.TileColor;

public class Game implements Observable {
  // L'objet Grid instancié pour le jeu
  private Grid grid;
  
  // Les objets joueurs
  private Player[] players;
  
  // ID du joueur qui débute la partie
  private int startingPlayer = 1;
  
  // Le joueur actuel
  private Player currentPlayer;
  
  // Défini si la partie est terminée ou non
  private Boolean gameOver = false;
  
  // Stock les coordonnées des coins
  // TODO : Stocker cette info dans la classe Grid
  // quand il y aura différents types de grilles
  private String[][] cornersCoordinates = {
      {"min", "min"}, // En haut à gauche - Joueur 1
      {"max", "max"}, // En bas à droite  - Joueur 2
      {"min", "max"}, // En haut à droite - Joueur 3
      {"max", "min"}  // En bas à gauche  - Joueur 4
  };
  
  public Game() {
    // Création de la grille par défaut
    String gridType = (String) Settings.get("gridType");
    int    gridSize = ((Long) Settings.get("gridSize")).intValue();
    
    switch (gridType) {
      case "square":
      default:
        grid = new GridSquare(gridSize);
    }

    // On initialise la grille de manière aléatoire
    grid.initRandom();
    
    // On demande le nombre de joueurs
    int nbOfPlayers = 2;
    
    // On créé la liste des joueurs
    players = new Player[nbOfPlayers];
    
    for (int i = 0; i < nbOfPlayers; i++) {
      // On créé l'objet Player
      if(i == 0)
        players[i] = new LocalPlayer(i + 1);
      else
        players[i] = new NoobIAPlayer(i + 1);
      
      int x = cornersCoordinates[i][0] == "min" ? 0 : gridSize - 1;
      int y = cornersCoordinates[i][1] == "min" ? 0 : gridSize - 1;
      
      // On associe un coin au joueur
      grid.assignTile(x, y, i + 1);
      players[i].setColor(grid.getTile(x, y).getColor());
    }
  }
  
  /**
   * Initialise le jeu avec grille défà définie
   * 
   * @param _grid La grille du jeu
   */
  public Game(Grid _grid) {
    // On demande le nombre de joueurs
    int nbOfPlayers = 2;
    
    // On créé la liste des joueurs
    players = new Player[nbOfPlayers];
    
    for (int i = 0; i < nbOfPlayers; i++) {
      // On créé l'objet Player
      players[i] = new LocalPlayer(i + 1);
    }
    
    grid = _grid;
  }

  /**
   * Démarre une partie
   */
  public void start() {
    // Le joueur 1 commence
    currentPlayer = players[startingPlayer - 1];
    
    // Débute le tour du joueur
    startTurn();
  }
  
  /**
   * Débute le tour
   */
  public void startTurn() {
    if (gameOver) {
      // La partie est terminée, on ne fait rien !
      return;
    }
    
    if (currentPlayer.playerType == PlayerType.IA) {
      // On demande à l'IA de jouer
      currentPlayer.play(this);
    }
  }
  
  /**
   * Le joueur a choisi une couleur
   * 
   * @param color La couleur choisie
   */
  public void chooseColor(TileColor color) {
    if(isColorOwned(color) && currentPlayer.getColor() != color) {
      notifyCantChooseColor(color);
      
      return;
    }
    
    // On lui assigne la couleur choisie
    currentPlayer.setColor(color);
    grid.assignTiles(currentPlayer.ID, color);
    
    endTurn();
  }
  
  /**
   * Termine le tour
   */
  public void endTurn() {
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
    } else {
      // On débute le tour du joueur suivant
      startTurn();
    }
  }
  
  /**
   * Permet de déterminer si une couleur appartient déjà à un joueur
   * 
   * @param c La couleur à tester
   * 
   * @return  (True|False)
   */
  public Boolean isColorOwned(TileColor c) {
    for (Player player: players) {
      if (player.getColor() == c) {
        return true;
      }
    }
    
    return false;
  }
  
  /**
   * Permet d'assigner une couleur au joueur
   * 
   * @param pID ID du joueur
   * @param c   La couleur à assigner
   */
  public void setColor(int pID, TileColor c) {
    players[pID - 1].setColor(c);
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
   * Permet d'obtenir le nombre de joueurs
   * 
   * @return  Le nombre de joueurs
   */
  public int getNbOfPlayers() {
    return players.length;
  }
  
  /**
   * Permet d'obtenir le joueur avec l'ID demandé
   * 
   * @param   pID L'ID du joueur
   * 
   * @return  Le joueur
   */
  public Player getPlayerWithID(int pID) {
    for (Player player: players) {
      if (player.ID == pID) {
        return player;
      }
    }
    
    return null;
  }
  
  /**
   * Permet de définir le joueur qui débute la partie
   * 
   * @param playerID  ID du joueur débutant la partie
   */
  public void setStartingPlayer(int playerID) {
    startingPlayer = playerID;
  }
  
  /**
   * Permet d'obtenir le joueur actuel
   * 
   * @return  Le joueur dont c'est actuellement le tour
   */
  public Player getCurrentPlayer() {
    return currentPlayer;
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
