package org.ktlnbstn.hangmanapp.model.forms;

import javax.validation.constraints.NotNull;


public class ResultsForm {

    @NotNull
    private int difficulty;

    public ResultsForm() {
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

}
