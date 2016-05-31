package edu.isep.jeudes6couleurs.game.players;

import edu.isep.jeudes6couleurs.game.Game;

/**
 * Jeu des 6 couleurs
 *
 * @package edu.isep.jeudes6couleurs.game
 * @class   LocalPlayer
 * @desc    Classe définissant un joueur local
 *
 * @author  Thibault Vlacich <thibault.vlacich@isep.fr>
 * @author  Hugo Michard <hugo.michard@isep.fr>
 */
public class LocalPlayer extends Player {
  public LocalPlayer(int playerID) {
    super(playerID);
    
    playerType = PlayerType.LOCAL;
  }

  public void play(Game game) {
    // Il s'agit d'un joueur local, il n'y a rien à faire
    // Le jeu attend simplement un input utilisateur
  }
  
}
