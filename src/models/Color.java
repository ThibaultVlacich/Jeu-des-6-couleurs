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

  public static Color getRandomColor()  {
    return VALUES.get(RANDOM.nextInt(SIZE));
  }
  
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
