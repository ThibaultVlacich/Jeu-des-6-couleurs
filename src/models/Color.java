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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Color {
  Red,
  Orange,
  Yellow,
  Green,
  Blue,
  Purple;
  
  private static final List<Color> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
  private static final int SIZE = VALUES.size();
  private static final Random RANDOM = new Random();

  /**
   * Permet d'obtenir une couleur aléatoire
   * 
   * @return  La couleur obtenue aléatoirement
   */
  public static Color getRandomColor()  {
    return VALUES.get(RANDOM.nextInt(SIZE));
  }
  
  /**
   * Permet d'obtenir le code d'une couleur
   * 
   * @param c La couleur dont on veux le code
   * 
   * @return  Le code de la couleur demandée
   */
  public String getColorCode(Color c) {
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
