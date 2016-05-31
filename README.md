# ![Logo](src/edu/isep/jeudes6couleurs/view/images/logo.png "Jeu des 6 couleurs")

Ce jeu est réalisé par deux étudiants élèves ingénieurs à l'[ISEP](http://www.isep.fr/) comme projet de première année en "Algorithmie et programmation" :
- [Thibault VLACICH](https://github.com/ThibaultVlacich)
- [Hugo MICHARD](https://github.com/HugoMichard)

JavaDoc : [http://thibaultvlacich.github.io/Jeu-des-6-couleurs/](http://thibaultvlacich.github.io/Jeu-des-6-couleurs/)

## Règles du jeu

Le jeu des six couleurs est un jeu de stratégie se déroulant sur un plateau découpé en cases de 6 couleurs différentes (rouge, orange, jaune, vert, bleu, ou violet). Le but du jeu est de contrôler plus de cases que les adversaires à la fin de la partie.

Les joueurs (de 2 à 4) commencent la partie en contrôlant chacun une case de la grille, ces cases doivent être de couleurs différentes. Les joueurs jouent chacun leur tour. À son tour, un joueur choisit une couleur différente de celle qu'il a actuellement, et non utilisée par ses adversaires. Toutes les cases contrôlées par le joueur deviennent alors de la couleur choisie. Toutes les cases de la couleur choisie et qui touchent une case contrôlée par le joueur passent sous son contrôle.

La partie s'arrête lorsqu'il n'y a plus de case non contrôlée, ou lorsqu'un joueur contrôle plus de la moitié des cases. Le joueur gagnant est celui qui contrôle le plus de cases.
