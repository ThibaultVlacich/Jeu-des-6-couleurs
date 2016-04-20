/**
 * Jeu des 6 couleurs
 *
 * @package models
 * @enum    Color
 * @desc    Liste les couleurs disponibles
 *
 * @author  Thibault Vlacich <thibault.vlacich@isep.fr>
 * @author  Hugo Michard <hugo.michard@isep.fr>
 */

package models;

public enum Color {
  Red,
  Orange,
  Yellow,
  Green,
  Blue,
  Purple;

  /**
   * Permet d'obtenir une couleur aléatoire
   * 
   * @return  La couleur obtenue aléatoirement
   */
  public static Color getRandomColor()  {
    return values()[(int) (Math.random() * values().length)];
  }
  
  /**
   * Permet d'obtenir le code d'une couleur
   * 
   * @param c La couleur dont on veux le code
   * 
   * @return  Le code de la couleur demandée
   */
  public static String getColorCode(Color c) {
    switch (c) {
      case Red:
        return "r";
      case Orange:
        return "o";
      case Yellow:
        return "j";
      case Green:
        return "v";
      case Blue:
        return "b";
      case Purple:
        return "i";
      default:
        return "?";
    }
  }
}
