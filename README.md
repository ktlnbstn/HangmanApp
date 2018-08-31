# HangmanApp

This application creates a Hangman game.

TO RUN:
- Open this [link](https://github.com/ktlnbstn/HangmanApp) to my HangManApp GitHub Repository.
- Please download the ZIP file. Next, unzip the download.
- Please follow the "Cloning an Existing Repository" instructions [here](https://git-scm.com/book/en/v2/Git-Basics-Getting-a-Git-Repository).
- Please download/install [IntelliJ](https://www.jetbrains.com/idea/download/) if necessary.
- Please download/install [MAMP](https://www.mamp.info/en/downloads/) if necessary.
- Open both programs.
- In IntelliJ, press 'Import project'. Navigate to where you downloaded the HangManApp repository and choose it.
  - Next, choose 'Create project from existing sources'. Accept all default settings and click 'Finish'.
  - A notification will pop up, 'Unlinked Gradle Project'. Click it and be sure to check the 'Use auto-import' selection.
  - The project will build. 
- In MAMP, press the 'Start Servers' button.
  - Back in IntelliJ, in the Gradle side pane, under 'Tasks' -> 'Application' -> double click 'bootRun'.
  - Application is live at http://localhost:8080/play

TO PLAY:
- Choose to play the Hangman game by entering a player name and desired difficulty level.
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
