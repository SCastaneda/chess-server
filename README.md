# chess-server

A little chess server to learn some clojure.

## Priorities

###Move validation
* make sure the move comes from the correct player and it's his/her turn
* make sure the player is moving his/her own piece
* make sure the target square is not occupied by another piece of the same player
* validate piece moves according to its restrictions/rules
* check for a check/check mate/end of game

###Server
* allow 2 clients(players) to start a game
* assign a token to the game, send it to the clients
* send each client a unique token
* each client must send both the game's token and their token for each move they send
* either use sockets to update the clients or have the clients poll for updates every x seconds
* save all the moves in the DB for a game & allow for a client to receive all moves in order.


###Users & Logins
This would be at the very last stage, and may not even be implemented. To keep track of wins and losses, we could add users and logins.
This would also ensure that the token system does not get abused. Some one could share the tokens with another person and let them take over their game (might call that a feature).
