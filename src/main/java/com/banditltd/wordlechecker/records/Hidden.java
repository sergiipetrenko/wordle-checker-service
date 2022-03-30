package com.banditltd.wordlechecker.records;

public record Hidden(String hidden, boolean[] used) {

    Hidden(String hidden) {
        this(hidden, new boolean[hidden.length()]);
    }

    public int codePointAt(int index) {
        return this.hidden.codePointAt(index);
    }

    public int length() {
        return this.hidden.length();
    }

    boolean match(GuessWithIndex guessWithIndex, int indexGuess, int indexHidden) {
        if(used[indexHidden]) {
            return false;
        }
        boolean match = guessWithIndex.guess().codePointAt(indexGuess) == codePointAt(indexHidden);
        if (match) {
            used[indexHidden] = true;
        }
        return match;
    }
}
