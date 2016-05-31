package edu.isep.jeudes6couleurs.game.players;

import edu.isep.jeudes6couleurs.game.Game;
import edu.isep.jeudes6couleurs.models.TileColor;

/**
 * Jeu des 6 couleurs
 *
 * @package edu.isep.jeudes6couleurs.game
 * @class   RandomIAPlayer
 * @desc    Classe définissant le joueur IA aléatoire
 *
 * @author  Thibault Vlacich <thibault.vlacich@isep.fr>
 * @author  Hugo Michard <hugo.michard@isep.fr>
 */
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
