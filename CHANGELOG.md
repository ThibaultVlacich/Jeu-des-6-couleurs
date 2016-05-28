# Changelog du Jeu des 6 couleurs

Réalisé par Thibault VLACICH et Hugo MICHARD.

Les changements notables sont documentés dans ce fichier. Ce projet respecte le [versionnage sémantique](http://semver.org/).

## Master

## Version 1.5.1 [28/05/2016]

### Bugs
- Corrige le fonctionnement de la sauvegarde du jeu

## Version 1.5 [23/05/2016]

### Ajouts
- Ajout de type de grilles supplémentaires :
	- Grille en losange
	- Grille rectangulaire

### Améliorations
- Suppression des settings, désormais inutiles avec l'écran "nouvelle partie"

### Bugs
- Corrige un bug qui pouvait faire que plusieurs joueurs débutent la partie avec la même couleur

## Version 1.4 [15/05/2016]

### Ajouts
- Ajout d'un écran permettant de choisir les options de jeu en lançant une nouvelle partie
- Ajout de la possibilité de jouer contre une IA :
	- Niveau 0 : l'IA choisit une couleur de manière aléatoire
	- Niveau 1 : l'IA choisit la couleur qui lui permet de gagner le plus de cases

### Améliorations
- Améliore le fonctionnement de la boucle de jeu

## Version 1.3.1 [04/05/2016]

### Améliorations
- Compression des images

### Bugs
- Laisse la possibilité au joueur de reprendre sa couleur. Permet, par exemple, d'avancer lors du premier tour lorsque le joueur est entouré de la couleur qu'il s'est vu assigné.

## Version 1.3 [04/05/2016]

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
