package edu.isep.jeudes6couleurs.game;

import edu.isep.jeudes6couleurs.models.TileColor;

/**
 * Jeu des 6 couleurs
 *
 * @package edu.isep.jeudes6couleurs.game
 * @class   Tile
 * @desc    Classe définissant une case du jeu
 *
 * @author  Thibault Vlacich <thibault.vlacich@isep.fr>
 * @author  Hugo Michard <hugo.michard@isep.fr>
 */
public class Tile {
  // Couleur de la case
  private TileColor color;
  
  // Joueur à qui la case appartient
  // Par défaut la case appartient à personne (0)
  private int playerID = 0;
  
  /**
   * Initialise la case avec une couleur
   * 
   * @param c La couleur de la case
   */
  public Tile(TileColor c) {
    color = c;
  }
  
  /**
   * Permet d'obtenir la couleur de la case
   * 
   * @return  Couleur de la case
   */
  public TileColor getColor() {
    return color;
  }
  
  /**
   * Permet de modifier la couleur de la case
   * 
   * @param c Nouvelle couleur
   */
  public void setColor(TileColor c) {
    color = c;
  }
  
  /**
   * Permet d'obtenir l'ID du joueur à qui la case appartient
   * 
   * @return  ID du joueur à qui la case appartient
   */
  public int getPlayerID() {
    return playerID;
  }
  
  /**
   * Permet de modifier le joueur qui détient la case
   * 
   * @param p ID du nouveau joueur
   */
  public void setPlayerID(int ID) {
    playerID = ID;
  }
}
