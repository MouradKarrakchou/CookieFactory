---
name: fr.unice.polytech.cod.User Story
about: Describe the fr.unice.polytech.cod.User Story
title: "[US]"
labels: bug, US
assignees: ''

---

:star: **Titre** En quelques mots, résumez l'objectif de l'US par exemple _Ajout d'un produit dans le panier_.

:star: **Description :**

**En tant que** [type of user], **Je veux** [an action] **afin de** [a benefit/a value]


:star2: **Priorité :** C'est la priorité dans les attentes du client, ce qui est important c'est que toute l'équipe soit d'accord sur l'échelle, vous pouvez aussi faire le choix d'étoiles, de chiffres ou suivre l'échelle donnée ci-dessous. Mettez à jour le patron quand l'équipe a fait un choix.

Proposition d'échelle, la [méthode MoSCoW](https://paper-leaf.com/insights/prioritize-user-stories/)
  1. _**Doit avoir :** la première version de ce produit nécessite absolument cette fonctionnalité - elle est essentielle au succès du produit._
  2. _**Devrait avoir** : l'idéal serait que la première version de ce produit dispose de cette fonctionnalité, mais elle n'est pas absolument nécessaire. Elles peuvent être aussi importantes, mais pas aussi critiques en termes de temps, que les "Must Have"._
  3. _**Could have/Aurait pu**: l'histoire de l'utilisateur a de la valeur et est souhaitable, mais en fin de compte, elle n'est pas nécessaire._
  4. _**Won't have/N'aura pas** : l'histoire de l'utilisateur est considérée comme étant parmi les moins critiques ou les moins utiles._
  
  
:star2: **Estimation:** Préciser l'effort requis pour mettre en œuvre la US, décrire sa complexité et la durée estimée pour réaliser l'user story.
Ce point est estimé avec la méthode vue en cours.

**Complexité:**
- 1/5 &rarr; Très facile, c'est une formalité
- 2/5 &rarr; Facile mais peut prendre un peu de temps
- 3/5 &rarr; Dans la norme, nécessite du travail mais ce n'est pas un défis technique
- 4/5 &rarr; Nécessite une certaines quantité de travail et de réflexion mais l'issue peut-être assumée seul 
- 5/5 &rarr; Vraiment compliqué, être plusieurs sur cette issue peut-être nécessaire 



:sparkles: **Règle métier:**
Précisez ici les règles métiers essentielles pour le développement de cette user-story. 

Ces règles sont écrites en français avec comme objectif de très facilement comprendre le travail à effectuer. 


_Voici un exemple ci-dessous sur un panier d’un site e-commerce :_
```
lorsque je rajoute un élément supplémentaire d'un produit dans mon 
panier.
    - si quantité > stock alors erreur "pas assez de stock 
      disponible"
    - si quantité < stock alors on ajoute +1 à la quantité_
```
  
:star2: **Critère d'acceptation**
Précisez l'ensemble des conditions que la story doit satisfaire pour être considérée comme complète et terminée.

Plus spécifiquement décrivez un ensemble de scénario qui deviendront des tests d'acceptation. 
Précisez bien les données associées comme vous le voyez dans les scénarios d'exemples données ci-dessous. 
Vous pouvez faire référence ici aux tests Gherkins correspondant

**!!! METTRE LE LIEN VERS NOTRE FICHIER FEATURE NE PAS RECOPIER LE SCENARIO JUSTE METTRE LE LIEN VERS LE FICHIER !!!** <br>
**Scenario:** objective <br>
**Given** some context <br>
**When** some action is carried out <br>
**Then** a set of observable outcomes should occur <br>
  

## Legende 
- :star:  Requis 
- :star2: Requis avant le passage en développement.
- :sparkles: Un plus
