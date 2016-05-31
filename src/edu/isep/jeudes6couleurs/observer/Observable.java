package edu.isep.jeudes6couleurs.observer;

import java.util.ArrayList;

import edu.isep.jeudes6couleurs.models.TileColor;

/**
 * Jeu des 6 couleurs
 *
 * @package   edu.isep.jeudes6couleurs.observer
 * @interface Observable
 * @desc      Interface définissant un objet observable
 *
 * @author    Thibault Vlacich <thibault.vlacich@isep.fr>
 * @author    Hugo Michard <hugo.michard@isep.fr>
 */
public interface Observable {
  // Liste des observateurs
  ArrayList<Observer> observers = new ArrayList<Observer>();
  
  /**
   * Permet d'ajouter un observateur
   * 
   * @param obs
   */
  public default void addObserver(Observer obs) {
   observers.add(obs);
  }
  
  /**
   * Permet de supprimer un observateur
   * 
   * @param obs
   */
  public default void removeObserver(Observer obs) {
    observers.remove(obs);
  }
  
  /**
   * Permet de notifier l'observateur qu'il faut mettre à jour la vue
   */
  public void notifyUpdateView();
  
  /**
   * Permet de notifier l'observateur qu'il n'est pas possible de choisir cette couleur
   *
   * @param c La couleur
   */
  public void notifyCantChooseColor(TileColor c);
  
  /**
   * Permet de notifier l'obesrvateur que la partie est terminée
   * 
   * @param winnerID  l'ID du joueur qui a gagné
   */
  public void notifyGameIsOver(int winnerID);
}
