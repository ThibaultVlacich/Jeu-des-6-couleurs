/**
 * Jeu des 6 couleurs
 *
 * @package models
 * @enum    TileColor
 * @desc    Liste les couleurs disponibles
 *
 * @author  Thibault Vlacich <thibault.vlacich@isep.fr>
 * @author  Hugo Michard <hugo.michard@isep.fr>
 */

package models;

public enum TileColor {
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
  public static TileColor getRandomColor()  {
    return values()[(int) (Math.random() * values().length)];
  }
  
  /**
   * Permet d'obtenir le code d'une couleur
   * 
   * @param c La couleur dont on veux le code
   * 
   * @return  Le code de la couleur demandée
   */
  public static String getColorCode(TileColor c) {
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
  
  public static TileColor getColorFromCode(String c) {
    switch (c) {
      case "r":
        return Red;
      case "o":
        return Orange;
      case "j":
        return Yellow;
      case "v":
        return Green;
      case "b":
        return Blue;
      case "i":
        return Purple;
       default:
         return null;
    }
  }
}
