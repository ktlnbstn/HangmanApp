# HangmanApp

This application creates a Hangman game.

TO RUN:
- Please download the HangmanApp ZIP file. Next, unzip the download.
- Please download/install [IntelliJ](https://www.jetbrains.com/idea/download/) if necessary.
- Please download/install [MAMP](https://www.mamp.info/en/downloads/) if necessary.
- Open both programs.
- In IntelliJ, press 'Import project'. Navigate to where you downloaded the HangmanApp repository and choose it.
  - Next, choose 'Create project from existing sources'. Accept all default settings and click 'Finish'.
  - A notification will pop up, 'Unlinked Gradle Project'. Click it and be sure to check the 'Use auto-import' selection.
  - The project will build. 
- In MAMP, press the 'Start Servers' button.
- Back in IntelliJ, in the Gradle side pane, under 'Tasks' -> 'Application' -> double click 'bootRun'.
  - Application is live at http://localhost:8080/play

TO PLAY:
- Choose to start playing the Hangman game, enter a player name and desired difficulty level. Click the submit button.
- Players are given 6 wrong guesses before they lose.
- Or check out the leader board, linked in the navigation bar.

REQUIREMENTS MET:
- Grab a word from the REACH API
- Display the word hidden as underscores
- Display incorrect guesses.
- Display the number of guesses remaining.
- Update the hidden word if correct letters are guessed.

EXTENSION MET:
- Users choose their own difficulty level.
- Score board is implemented.
- Score board can display leaders based on difficulty level.


THOUGHT PROCESS:
- First I built up Spring Framework basics so I could display the application in a clean way. This involves lots of
folder structures.
- Then, I knew the hangman game is focused of a few pieces of very important logic. I placed those methods under a
class called 'HangmanLogic.'
- Next I focused on creating an object called 'Game'. This is the key object that holds all the useful
info/getters/setters about the game when it is currently being played, and later when we want to compare scores.
Comparing scores is also why I created a data access object for the game, under 'GameDAO'. This file allows me to
reach into the database and pull out all or specific games.
- Next, I created the service to grab the secret word from the REACH api. This logic is found in 'RestClientService'.
- Then came controllers. I created 'AbstractController' to help temporarily persist details like the hidden word in
it's underline form and the number of bad guesses. These are both pieces of information that don't need to be kept in
a database for later.
- 'WordsController' is in charge of calling the REACH api service and transfering the chosen word from the back-end
to the front-end.
- The 'HangmanController' is where the meat of this project lies. This is where the front-end and the back-end of
the application we see on localhost meet. Here, I do everything from grab methods from classes to preform tasks,
grab info from the front-end to be able to persist it to the database, and grab info from the back-end/save it to
the model/display it on the front-end. 'HangmanController' controls the logical flow of the game and it's rules
as information is fed into it.



