/**
 * Jeu des 6 couleurs
 *
 * @package game
 * @class   RandomIAPlayer
 * @desc    Classe définissant le joueur IA aléatoire
 *
 * @author  Thibault Vlacich <thibault.vlacich@isep.fr>
 * @author  Hugo Michard <hugo.michard@isep.fr>
 */

package game;

import models.TileColor;

public class RandomIAPlayer extends Player {
  public RandomIAPlayer(int playerID) {
    super(playerID);
    
    playerType = PlayerType.IA;
  }
  
  public void play(Game game) {
    TileColor colorChoosed = TileColor.getRandomColor();
    
    while (game.isColorOwned(colorChoosed)) {
      colorChoosed = TileColor.getRandomColor();
    }
    
    game.chooseColor(colorChoosed);
  }
}
