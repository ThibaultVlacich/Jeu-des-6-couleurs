/**
 * Jeu des 6 couleurs
 *
 * @package game
 * @class   Player
 * @desc    Classe abstraite définissant l'objet joueur
 *
 * @author  Thibault Vlacich <thibault.vlacich@isep.fr>
 * @author  Hugo Michard <hugo.michard@isep.fr>
 */

package game;

import models.TileColor;

public abstract class Player {
  // ID du joueur
  public int ID;
  // Couleur actuelle du joueur
  private TileColor color;

  Player(int ID) {
    this.ID = ID;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Player)) {
      return false;
    }
    
    return (((Player) obj).ID == this.ID);
  }
  
  /**
   * Demande au joueur de jouer
   */
  abstract void play(Game game);
  
  /**
   * Permet d'obtenir la couleur actuelle du joueur
   * 
   * @return  La couleur actuelle du joueur
   */
  public final TileColor getColor() {
    return color;
  }

  /**
   * Permet de modifier la couleur actuelle du joueur
   * 
   * @param c La nouvelle couleur du joueur
   */
  public final void setColor(TileColor c) {
    color = c;
  }
}
