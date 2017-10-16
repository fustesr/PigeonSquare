# PigeonSquare

Cours 8INF957-01

Groupe de projet : 

- Virgile Lafontaine
- Fustes Raphael

Vous trouverez le fichier JAR éxécutable de notre jeu ainsi que les fichiers sources.

Résumé:

- Chaque pigeon est représenté par un Thread.
- Lorsqu'il n y a pas de nourriture sur le terrain, les pigeons ne bougent pas. Le thread (tout comme le pigeon) s'endort. C'est lorsque l'utilisateur clique (gauche ou droit) que les threads vont etre réveillés.
- Un clique gauche permet de déposer un Hamburger sur le terrain. Il est possible d'en poser autant que l'on veut et chaque pigeon se déplacera vers le Hamburger le plus proche.
- Lorsqu'un pigeon mange un Hamburger, il disparait du terrain
- Lorsqu'un Hamburger reste 8 sec sur le terrain sans avoir été mangé, il pourri :(
- Il est possible d'effrayer les pigeons ! Lorsque le joueur effectue un clique droit, une bombe est envoyée à l'emplacement du curseur ce qui a pour effet d'épouvanter les pauvres petit pigeons


Cela va de soi : deux pigeons ne peuvent pas manger le meme Hamburger
