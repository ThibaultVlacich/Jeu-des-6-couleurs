/**
 * Jeu des 6 couleurs
 *
 * @package game
 * @class   Player
 * @desc    Classe définissant l'objet joueur
 *
 * @author  Thibault Vlacich <thibault.vlacich@isep.fr>
 * @author  Hugo Michard <hugo.michard@isep.fr>
 */

package game;

import models.TileColor;

public class Player {
  // ID du joueur
  public int ID;
  // Couleur actuelle du joueur
  private TileColor color;

  Player(int ID) {
    this.ID = ID;
  }
  
  /**
   * Permet d'obtenir la couleur actuelle du joueur
   * 
   * @return  La couleur actuelle du joueur
   */
  public TileColor getColor() {
    return color;
  }

  /**
   * Permet de modifier la couleur actuelle du joueur
   * 
   * @param c La nouvelle couleur du joueur
   */
  public void setColor(TileColor c) {
    color = c;
  }
}
