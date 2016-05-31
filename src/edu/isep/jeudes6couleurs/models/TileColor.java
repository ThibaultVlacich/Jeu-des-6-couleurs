package edu.isep.jeudes6couleurs.models;

import java.awt.Color;

/**
 * Jeu des 6 couleurs
 *
 * @package edu.isep.jeudes6couleurs.models
 * @enum    TileColor
 * @desc    Liste les couleurs disponibles
 *
 * @author  Thibault Vlacich <thibault.vlacich@isep.fr>
 * @author  Hugo Michard <hugo.michard@isep.fr>
 */
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
  
  /**
   * Permet d'obtenir l'objet TileColor lié à un code couleur
   * 
   * @param c Le code couleur
   * 
   * @return  La couleur
   */
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
  
  public static Color getDrawColor(TileColor c) {
    switch (c) {
      case Red:
        return Color.RED;
      case Orange:
        return Color.ORANGE;
      case Yellow:
        return Color.YELLOW;
      case Green:
        return Color.GREEN;
      case Blue:
        return Color.BLUE;
      case Purple:
        return Color.MAGENTA;
      default:
        return Color.WHITE;
    }
  }
  
  /**
   * Permet d'obtenir le nom de la classe CSS d'une couleur
   * 
   * @param c La couleur dont on veux le code
   * 
   * @return  Le nom de la classe CSS de la couleur demandée
   */
  public static String getColorClassName(TileColor c) {
    switch (c) {
      case Red:
        return "red";
      case Orange:
        return "orange";
      case Yellow:
        return "yellow";
      case Green:
        return "green";
      case Blue:
        return "blue";
      case Purple:
        return "purple";
      default:
        return "";
    }
  }
}
