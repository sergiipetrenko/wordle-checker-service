package com.banditltd.wordlechecker.records;

public record Guess(String guess) {

    public int length() {
        return this.guess.length();
    }

    public GuessWithIndex hasALetterAtPosition(int index) {
        return new GuessWithIndex(this, index);
    }

    public int codePointAt(int index) {
        return this.guess.codePointAt(index);
    }
}
