package edu.isep.jeudes6couleurs.observer;

import edu.isep.jeudes6couleurs.models.TileColor;

/**
 * Jeu des 6 couleurs
 *
 * @package   observer
 * @interface Observer
 * @desc      Interface définissant un observateur
 *
 * @author    Thibault Vlacich <thibault.vlacich@isep.fr>
 * @author    Hugo Michard <hugo.michard@isep.fr>
 */
public interface Observer {
  /**
   * Permet de mettre à jour la vue
   */
  public void updateView();
  
  /**
   * Affiche au joueur qu'il ne peut pas choisir cette couleur
   * 
   * @param c La couleur en question
   */
  public void cantChooseColor(TileColor c);
  
  /**
   * Affiche aux joueurs que la partie est terminée
   * 
   * @param winnerID
   */
  public void gameOver(int winnerID);
}
