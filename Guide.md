Exercice 3
==========


Votre travail pour cette semaine sera d'implanter un correcteur
orthographique faisant des suggestions de corrections aux utilisateurs
pour les mots mal écrits.

Le programme doit passer à travers tous les mots du texte et, pour les
mots qui ne sont pas présents dans le dictionnaire, suggérer des
corrections lorsque possible.

Le programme doit s'appeler Correcteur et doit pouvoir être appelé en
ligne de commande avec les arguments suivants :

java Correcteur texte-a-corriger.txt dictionnaire.txt


Le fichier dict.txt sur StudiUM est un exemple de dictionnaire, qui
contient un mot de la langue française par ligne.


# Exemple d'exécution du programme avec le fichier mon-texte.txt

## Contenu du fichier mon-texte.txt

    Voici un texte écrit par moi. La corection du texte est une
    activité amusante. Ce texte a une xerreur et un mot très
    étrange. jboiuhsiuhiqwjoijriuhsaiuhdsa.


## Exécution du programme:

    java Correcteur mon-texte.txt dict.txt

## Sortie attendue:

    Voici un texte écrit par moi. La [corection => correction] du texte est une
    activité amusante. Ce texte a une [xerreur => erreur,terreur] et un mot très
    étrange. [jboiuhsiuhiqwjoijriuhsaiuhdsa => (?)].



# Correction des mots

La correction des mots sera limitée aux cas suivants :

1. Une lettre a été retirée, par exemple :

    corretion => correction
    corection => correction

2. Une lettre a été ajoutée, par exemple :

    correcbtion => correction
    correctionz => correction

3. Une des lettres du mot a été mêlée avec une autre, par exemple :

    correctxon => correction
    coreection => correction



La stratégie pour faire cette correction efficacement (vous ne devez
*pas* revérifier toute la liste de mots à chaque fois) sera de garder
en mémoire deux structures de données :

- Un *Ensemble* des vrais mots connus (tirés du dictionnaire)
- Une *Liste Associative* contenant des corrections possibles


Au début du programme, on doit parcourir la liste de mots une première
fois et créer les deux structures de données


Initialiser l'ensemble de mots W (vide)

Initialiser la liste associative S qui va contenir les
suggestions reliées à chaque mot

Pour chaque mot M du dictionnaire de référence :
|  Ajouter M à l'ensemble de mots W
|
|  Pour chaque lettre L dans M :
|  |  Définir le mot tronqué M2 qui contient les mêmes lettres que
|  |          M excepté la lettre L
|  |
|  |  Ajouter à la liste S le mot M2 associé au mot complet M


Pour un mot complet M donné, on va donc ajouter à la liste associative
S plusieurs entrées M2 reliées au même M :

    onjour => bonjour
    bnjour => bonjour
    bojour => bonjour
    bonour => bonjour
    bonjur => bonjour
    bonjor => bonjour
    bonjou => bonjour


Notez qu'un même mot tronqué M2 peut revenir plusieurs fois pour
différents mots complets M. Par exemple, avec les mots frange, grange
et ranger, on va avoir :

    range => frange, grange, ranger

Il faut donc stocker toutes les possibilités pour un même mot tronqué.


Pour déterminer si un mot présent dans le texte devrait être corrigé,
il suffit de vérifier s'il est présent dans l'ensemble de mots connus.


Lorsqu'on sait qu'un mot est incorrect et qu'il doit être corrigé, on
peut trouver toutes les suggestions de corrections en regardant:

    A. Le mot lui-même dans la liste associative
    B. Chaque variation du mot où une lettre à la fois a été supprimée
       (B1) dans la liste associative et (B2) dans l'ensemble des mots
       connus


Cette stratégie nous permet de détecter les trois cas supportés
(ajout/retrait/remplacement) :

- S'il manque une seule lettre au mot, le cas A. va trouver les
  suggestions possibles

- Si une des lettres a été remplacée par une autre lettre, le cas
  B1. va trouver les suggestions possibles

- S'il y a une lettre de trop dans le mot, le cas B2 va trouver les
  suggestions possibles



L'union de tous les mots trouvés (en retirant les doublons au besoin)
est la liste de suggestions à afficher.

La sortie du programme devrait être exactement la même chose que le
texte à corriger, mais mettant entre crochets les mots erronés, suivi
d'un => et des suggestions :

        [le mot erroné => liste,des,suggestions]

Par exemple :

        [corection => correction]

        [xerreur => erreur,terreur]


Dans le cas où le mot n'a aucune suggestion, on affiche un (?) :

        [jboiuhsiuhiqwjoijriuhsaiuhdsa => (?)]


Dans le cas où le texte ne contient pas d'erreur, le texte entré
devrait être le même que le texte de sortie.


# Exemples de code

Pour vous aider à séparer correctement le texte en mots et utiliser
les structures de données demandées, le fichier `Exemple.java` sur
StudiUM est fourni et contient du code similaire aux différentes
tâches que vous aurez besoin de faire.

# Design

Bien que ce travail ne soit pas explicitement au sujet de la
programmation orientée objet, essayez de penser à des façons efficaces
de découper votre code.

Dans tous les cas, la classe principale de votre programme (celle qui
contient le main) doit s'appeler Correcteur.java

Remettez un fichier .zip qui contient toutes vos classes.


# Bonus (10%)

L'algorithme expliqué ici demande de réserver beaucoup de mémoire, ce
qui n'est pas pratique si la taille du dictionnaire augmente...

Trouvez une façon de *ne pas* charger l'ensemble des mots et des
corrections possibles en mémoire.

Testez avec le dictionnaire `dict.txt` sur StudiUM ainsi qu'avec le
plus gros dictionnaire ici : http://316k.legtux.org/dict-fr-large.txt

Notez: Votre programme doit rester efficace. Vous ne pouvez *pas*
retraverser toute la liste de mots à chaque fois, inspirez-vous plutôt
de la fin des notes de cours sur les fichiers pour réaliser le
programme.