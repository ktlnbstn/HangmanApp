package org.ktlnbstn.hangmanapp.controllers;

import org.ktlnbstn.hangmanapp.model.Game;
import org.ktlnbstn.hangmanapp.model.data.GameDAO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;

public class AbstractController {

    @Autowired
    protected GameDAO gameDAO;

    public static final String gameInSession = "game";
    public static final String hiddenWordInSession = "hiddenWord";
    public static final String badTryCountInSession = "badTries";


    protected Game getGameFromSession(HttpSession session) {
        Game game = (Game) session.getAttribute("game");
        return game;
    }

    protected void setGameInSession(HttpSession session, Game game) {
        session.setAttribute(gameInSession, game);
    }

    protected String getHiddenWordInSession(HttpSession session) {
        String hiddenWord = (String) session.getAttribute("hiddenWord");
        return hiddenWord;
    }

    protected void setHiddenWordInSession(HttpSession session, String hiddenWord) {
        session.setAttribute(hiddenWordInSession, hiddenWord);
    }

    protected int getBadTryCountInSession(HttpSession session) {
        int badTries = (int) session.getAttribute("badTries");
        return badTries;
    }

    protected void setBadTryCountInSession(HttpSession session, int badTries) {
        session.setAttribute(badTryCountInSession, badTries);
    }

    protected void clearSession(HttpSession session){
        session.setAttribute(gameInSession, null);
    }

    protected boolean isSessionActive(HttpSession session){
        Game game = getGameFromSession(session);
        return game != null;
    }

}
