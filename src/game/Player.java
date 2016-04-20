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

import models.Color;

public class Player {
  // Couleur actuelle du joueur
  private Color color;

  /**
   * Permet d'obtenir la couleur actuelle du joueur
   * 
   * @return  La couleur actuelle du joueur
   */
  public Color getColor() {
    return color;
  }

  /**
   * Permet de modifier la couleur actuelle du joueur
   * 
   * @param c La nouvelle couleur du joueur
   */
  public void setColor(Color c) {
    color = c;
  }
}
