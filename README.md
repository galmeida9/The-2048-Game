# The-2048-Game
2048 game made in java 8, to run open jar file or compile the following way:<br> 
<pre>javac App.java -cp .:core/:app/</pre>

# Changelog

Version 1.3<br>
    &nbsp;&nbsp;&nbsp;&nbsp;-Added prompt before exit,<br>
    &nbsp;&nbsp;&nbsp;&nbsp;-jar file now works standalone,<br>
    &nbsp;&nbsp;&nbsp;&nbsp;-couldnt put background image in a separate folder, for some reason the jar file could load the program, so it is in the app/ folder;<br>

Version 1.2<br>
    &nbsp;&nbsp;&nbsp;&nbsp;-fixed end of game algorithm, now the game doesnt end when there are no more free slots, but when its impossible to make a move,<br>
    &nbsp;&nbsp;&nbsp;&nbsp;-fixed issue where it added a number to the game when a not valid move was made, sush as moving the board and nothing happend;<br>

Version 1.1<br>
    &nbsp;&nbsp;&nbsp;&nbsp;-Modified the UI, now it has a background, and the buttons have color,<br>
    &nbsp;&nbsp;&nbsp;&nbsp;-changed the size of the buttons, where the numbers are displayed, now a bigger number such as 1024 fits it;<br>

Version 1.0<br>
    &nbsp;&nbsp;&nbsp;&nbsp;-Initial version, just a basic concept, with a simple UI and a general algorithm, especific cases not implemented;<br>
