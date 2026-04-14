Pour répondre au problème de l'escape room, nous allons y aller par plusieurs étapes :

Je vais dans ce markdown, vous expliquez, comment j'ai procedé et ce que l'on veut faire.

---------------------------------------------
## 0 - Initialisation
---------------------------------------------
On va rajouter dans le application.properties ceci :
spring.profiles.active=passage,treasure
C'est pour définir nos profiles (nos beans avec ces profiles dedans)

---------------------------------------------
## 1 - Les 4 services BLL (Dans le /bll)
---------------------------------------------
On veut mettre dans ces 4 fichiers le module @Service,
avec des profiles différents, mais pourquoi ?
Car d'après le cours, j'ai conclus que Spring peut pas avoir plus de 2 beans du même type, en même temps.
Donc on va segmenter en 2 partie, "passage" & "trap"

Enfin, pour être plus précis : il ne peut pas avoir plus d'1 bean du même type actif en même temps sans ambiguité
    Donc enfaite, ici :
    Room1Service et Room2Service qui implémentent tous les deux RoomService, Spring ne sait pas lequel injecter -> erreur "NoUniqueBeanDefinitionException".
    Les profils servent à n'activer qu'un seul des deux selon le contexte.

Comme ça pas de problème de confusion.

---------------------------------------------
## 2 - Les controllers
---------------------------------------------
Sur les controlleurs, nous allons rajouter les modules @Component.
Voici ce que l'on a fait :
    EscapeRoom1Controller : On met un simple @Component, c'est la première salle
    EscapeRoom2Controller : Ici le @Component doit avoir un nom "room2" Car avant de commencer à coder, il est important d'aller regarder ce qu'il y a dans le Startup.java, et ici il appelle un bean nommé "room2", Injection ce fait par le constructeur via @Autowired, comme expliquer dans l'exercice précédent.
    TreasureRoomController : On met 2 module le @Component et le @Autowired sur le setter setTresorService. On fait donc ici une injection par setter, ici Spring va appeler le setter après avoir créé l'object.

---------------------------------------------
## 3 - Schéma complet de la logique
---------------------------------------------

RoomService -> Room1Service (Passage) / Room2Service(trap)
TreasureService -> Treasure1Service (trap) / Treasure2Service(treasure)

---------------------------------------------
## 4 - Schéma d'injection
---------------------------------------------

Nous avons 3 types d'injection utilisées : 
Par type : Via EscapeRoom1Controller, il trouve le bean via ça classe
Par nom : Il trouve via le Component "room2" utiliser et appeler dans le Startup
Par setter : TreasureRoomController via le @Autowired sur le setTresorService   
             Comme vu dans le TP2 de base.

