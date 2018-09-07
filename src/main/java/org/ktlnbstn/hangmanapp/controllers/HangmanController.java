// KATE'S UPDATED HMController
package org.ktlnbstn.hangmanapp.controllers;

import org.ktlnbstn.hangmanapp.model.Game;
import org.ktlnbstn.hangmanapp.model.HangmanLogic;
import org.ktlnbstn.hangmanapp.model.forms.ResultsForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
//@RequestMapping("play")
public class HangmanController extends AbstractController {

    // API Logic
    @Autowired
    private WordsController wordsController;

    //request path: play/
    // Create a Game Object
    @GetMapping("play")
    public String index(Model model, HttpServletRequest request){

        //return homepage
        model.addAttribute("title", "Play Hangman or Check out the Leader Board!");
        model.addAttribute("game", new Game());

        return "index";
    }

    // Process Game Object
    @PostMapping("play")
    public String processGame (@ModelAttribute("game") Game game,
                               Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();

        // grab a word for the game object
        if(game.getWord().isEmpty()) {

            // logic to grab words from API by difficulty level,
            // choose one randomly, then add it to game object
            String chosenWord = wordsController.getByDifficulty(Integer.toString(game.getDifficulty()));

            // set properties in game object
            game.setPlayerName(game.getPlayerName());
            game.setDifficulty(game.getDifficulty());
            game.setWord(chosenWord);

            // persist game properties
            gameDAO.save(game);
            setGameInSession(session, game);
        }

        // generate the originalWord as underlines
        String hiddenWord = HangmanLogic.createHiddenWord(game.getWord(), game.getTries());

        setHiddenWordInSession(session, hiddenWord);
        setBadTryCountInSession(session, 0);

        model.addAttribute("title", "Play Hangman");
        model.addAttribute("game", game);
        model.addAttribute("hiddenWord", hiddenWord);

        return "gameplay";
    }

    // process updated gameForm
    @PostMapping("gameplay/{gameId}")
    public String gameInPlayPosted(@PathVariable int gameId, @RequestParam String userInput,
                                   Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();

        if(gameDAO.findById(gameId).isPresent()) {
            Game game = (Game) gameDAO.findById(gameId).get();
            setGameInSession(session, game);
        }

        Game game = getGameFromSession(session);

        // validate user input
        if(!HangmanLogic.validateUserInput(userInput, game.getTries(), game.getWord())) {

            // generate the word as underlines
            String hiddenWord = HangmanLogic.createHiddenWord(game.getWord(), game.getTries());
            setHiddenWordInSession(session, hiddenWord);

            model.addAttribute("title", "Please check your input!");
            model.addAttribute("triesErrors", "Please enter a single letter.");
            model.addAttribute("game", game);
            model.addAttribute("hiddenWord", getHiddenWordInSession(session));

            return "gameplay";

        }

//        // check if originalWord was guessed. Check guess move before HiddenWord logic
        if(HangmanLogic.checkIfCompleteWord(game.getWord(), userInput)) {

            // if so: return user score and results page for that difficulty
            int score = HangmanLogic.calculateScore(game);
            game.setScore(score);

            // save game with updated score
            setGameInSession(session, game);
            gameDAO.save(game);

            model.addAttribute("title", "Game Over - You Win");
            model.addAttribute("game", game);

            return "score";

        }

        // generate the hiddenWord
        String hiddenWord = HangmanLogic.createHiddenWord(game.getWord(), game.getTries());
        setHiddenWordInSession(session, hiddenWord);
        System.out.println(game.getWord());

        // check number of userInput
        int numberOfBadTries = HangmanLogic.countBadTries(game.getWord(), userInput);
        setBadTryCountInSession(session, (getBadTryCountInSession(session) + numberOfBadTries));

        // check if originalWord is complete. kept a copy here to catch after HiddenWord generated
        if(HangmanLogic.checkIfCompleteWord(game.getWord(), getHiddenWordInSession(session))) {

            // if so: return user score and results page for that difficulty
            int score = HangmanLogic.calculateScore(game);
            game.setScore(score);

            // save game with updated score
            setGameInSession(session, game);
            gameDAO.save(game);

            model.addAttribute("title", "Game Over - You Win");
            model.addAttribute("game", game);

            return "score";

        }

        // check number of userInput
        if(numberOfBadTries >= 6) {

            // if so: return user score and the results page for that difficulty level
            int score = 0;
            game.setScore(score);

            // save game with updated score
            setGameInSession(session, game);
            gameDAO.save(game);

            model.addAttribute("title", "Game Over - You Lose");
            model.addAttribute("game", game);

            return "score";

        }

        // else: continue gathering user input

        model.addAttribute("title", "Play Hangman");
        model.addAttribute("game", game);
        model.addAttribute("hiddenWord", hiddenWord);
        model.addAttribute("badTries", (6 - getBadTryCountInSession(session)));

        // persist game properties
        gameDAO.save(game);
        setGameInSession(session, game);

        return "gameplay";

    }

    // search for score leaders based on difficulty
    @GetMapping("results")
    public String results(Model model, HttpServletRequest request){

        model.addAttribute("title", "Search for Score Leaders by Difficulty");
        model.addAttribute("games", gameDAO.findAllByOrderByScoreDesc());
        model.addAttribute("resultsForm", new ResultsForm());

        return "results";
    }

    // process results form
    @PostMapping("results")
    public String processSearchForm(@ModelAttribute("resultsForm") ResultsForm resultsForm,
                                    Model model, HttpServletRequest request){

        model.addAttribute("title", "Search for Score Leaders by Difficulty");
        model.addAttribute("resultsForm", new ResultsForm());

        if(resultsForm.getDifficulty() == 11) {
            model.addAttribute("games", gameDAO.findAllByOrderByScoreDesc());
        } else {
            model.addAttribute("games", gameDAO.findByDifficultyOrderByScoreDesc(resultsForm.getDifficulty()));
        }

        return "results";
    }

}
