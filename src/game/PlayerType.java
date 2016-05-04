/**
 * Jeu des 6 couleurs
 *
 * @package   game
 * @interface Player
 * @desc      Interface listant les types de joueurs possibles
 *
 * @author    Thibault Vlacich <thibault.vlacich@isep.fr>
 * @author    Hugo Michard <hugo.michard@isep.fr>
 */

package game;

public enum PlayerType {
  LOCAL,
  IA;
  
  public static String getTypeName(PlayerType type) {
    switch (type) {
      case LOCAL:
        return "local";
      case IA:
        return "ia";
      default: 
        return "unknown";
    }
  }
  
  public static PlayerType getTypeFromName(String name) {
    switch (name) {
      case "local":
        return LOCAL;
      case "ia":
        return IA;
      default:
        return null;
    }
  }
}
