# Changelog du Jeu des 6 couleurs

Réalisé par Thibault VLACICH et Hugo MICHARD.

Les changements notables sont documentés dans ce fichier. Ce projet respecte le [versionnage sémantique](http://semver.org/).

## Master

### Ajouts
- Ajout d'un popup "A propos" du jeu
- Possibilité de sauvegarder la partie en cours
- Possibilité de charger le jeu depuis une sauvegarde
- Ecran des options

### Améliorations
- Améliore l'écran de jeu :
	- Affiche la listes des joueurs, associée au nombre de cases possédées par les joeurs et leur couleur actuelle
	- Affiche le joueur dont c'est le tour

## Version 1.2 [28/04/2016]

### Ajouts
- Interface graphique complète avec JavaFX
- Possibilité de jouer à plus de 2 joueurs (jusqu'à 4 max)

## Version 1.1.1 [21/04/2016]

### Bugs
- Il n'est plus possible de capturer une case en la touchant par un coin, il faut toucher une case par un coté pour la capturer.

## Version 1.1 [21/04/2016]

### Ajouts
- Affichage en mode graphique de la grille de jeu

### Améliorations
- Améliore le script assignant les cases au joueur de la couleur qu'il a choisie

### Bugs
- Vérifie que la couleur choisie par le joueur n'est pas celle de son adversaire

## Version 1.0 [20/04/2016]
Version initiale correspondant aux demandes minimales du cahier des charges.
