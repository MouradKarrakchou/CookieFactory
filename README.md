# Cookiefactory-22-23-Team-K

## doc
Contient le rapport final au format pdf

## .github
   1. Contient sous workflows/maven.yml, une version d'un fichier d'actions qui est déclenché dès que vous poussez du code. 
Sur cette version initiale seule un test Junit5 est déclenché pour vérifier que tout fonctionne.
       - Github Actions (See in .github/workflows) to simply make a maven+test compilation
  2. Contient sous ISSUE_TEMPLATE, les modèles pour les issues user_story et bug. Vous pouvez le compléter à votre guise.

## src
 - pom.xml : 
       - Cucumber 7 et JUnit 5
       - Maven compatible
       - JDK 17



## User stories: src/test/ressources/features.client

Chaque user story est décrite par 
   - son identifiant en tant que issue github (#), 
   - sa forme classique (As a… I want to… In order to…)
   - Le nom du fichier feature Cucumber et le nom des scénarios qui servent de tests d’acceptation pour la story.
   Les contenus détaillés sont dans l'issue elle-même, ainsi que dans les .feature associés.
   
La liste des fonctionnalités livrées par user story :

# Milestone : Sprint 1

Retirer un cookie du panier #08 :
[remove_one_cookie_from_the_cart.feature](https://github.com/PNS-Conception/cookiefactory-22-23-k/blob/main/src/test/resources/features/client/remove_one_cookie_from_the_cart.feature)

Choisir un magasin #10 :
[choose_store_for_cookie.feature](https://github.com/PNS-Conception/cookiefactory-22-23-k/blob/main/src/test/resources/features/client/choose_store_for_cookie.feature)

Création de la facture #12 :
[create_the_bill.feature](https://github.com/PNS-Conception/cookiefactory-22-23-k/blob/main/src/test/resources/features/client/create_the_bill.feature)

# Milestone : Sprint 2

Annuler une commande #22 :
[cancel_order.feature](https://github.com/PNS-Conception/cookiefactory-22-23-k/blob/main/src/test/resources/features/client/cancel_order.feature)

Travaille du chef #24 :
[chef_work.feature](https://github.com/PNS-Conception/cookiefactory-22-23-k/blob/main/src/test/resources/features/client/chef_work.feature)

Choisir une heure de récupération #11 :
[choose_timeslot_for_order.feature](https://github.com/PNS-Conception/cookiefactory-22-23-k/blob/main/src/test/resources/features/client/choose_timeslot_for_order.feature)

Regarder l'historique des commandes #27 :
[client_check_history.feature](https://github.com/PNS-Conception/cookiefactory-22-23-k/blob/main/src/test/resources/features/client/client_check_history.feature)

Définir les heures d'ouvertures du magasin #25 :
[define_opening_hour.feature](https://github.com/PNS-Conception/cookiefactory-22-23-k/blob/main/src/test/resources/features/client/define_opening_hour.feature)

Modifier le catalogue de cookie #17 :
[edit_cookie_in_the_cookieBook.feature](https://github.com/PNS-Conception/cookiefactory-22-23-k/blob/main/src/test/resources/features/client/edit_cookie_in_the_cookieBook.feature)

Gérer le passage en cuisine #24 :
[manage_the_kitchen_passage.feature](https://github.com/PNS-Conception/cookiefactory-22-23-k/blob/main/src/test/resources/features/client/manage_the_kitchen_passage.feature)

Recevoir une réduction #16 :
[receive_a_discount.feature](https://github.com/PNS-Conception/cookiefactory-22-23-k/blob/main/src/test/resources/features/client/receive_a_discount.feature)

Prévenir le client qu'un panier est disponlible #28 :
[notify_client_for_a_suprise_basket](https://github.com/PNS-Conception/cookiefactory-22-23-k/blob/main/src/test/resources/features/client/otify_client_for_a_suprise_basket.feature)

S'abonner au programme de fidélité #13 :
[subscribe_to_the_fidelity_program.feature](https://github.com/PNS-Conception/cookiefactory-22-23-k/blob/main/src/test/resources/features/client/subscribe_to_the_fidelity_program.feature)

# Implémentation Gherkin supplémentaire

Ajouter un cookie au panier :
[add_cookie_to_cart.feature](https://github.com/PNS-Conception/cookiefactory-22-23-k/blob/main/src/test/resources/features/client/add_cookie_to_cart.feature)

Récupérer une commande :
[retrieve_order.feature](https://github.com/PNS-Conception/cookiefactory-22-23-k/blob/main/src/test/resources/features/client/retrieve_order.feature)

Commander un Party Cookie :
[order_a_party_cookie.feature](https://github.com/PNS-Conception/cookiefactory-22-23-k/blob/main/src/test/resources/features/client/order_a_party_cookie.feature)

Verouiller les ingrédients :
[lock_ingredient_to_stock.feature](https://github.com/PNS-Conception/cookiefactory-22-23-k/blob/main/src/test/resources/features/client/lock_ingredient_to_stock.feature)
   
Changer l'état d'une commande :
[change_order_state.feature](https://github.com/PNS-Conception/cookiefactory-22-23-k/blob/main/src/test/resources/features/client/change_order_state.feature)
