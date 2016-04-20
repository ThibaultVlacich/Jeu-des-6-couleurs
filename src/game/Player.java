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
  private Color color;

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }
}
