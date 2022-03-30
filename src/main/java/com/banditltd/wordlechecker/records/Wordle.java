package com.banditltd.wordlechecker.records;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record Wordle(String hiddenAsString) {

    record Guess(String guess) {

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

    record Hidden(String hidden, boolean[] used) {

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
            boolean match = guessWithIndex.guess.codePointAt(indexGuess) == codePointAt(indexHidden);
            if (match) {
                used[indexHidden] = true;
            }
            return match;
        }
    }

    record GuessWithIndex(Guess guess, int index) {

        public boolean thatIsWellPlacedIn(Hidden hidden) {
            return hidden.match(this, index, index);
        }

        public boolean thatIsNotWellPlacedIn(Hidden hidden) {
            return IntStream.range(0, hidden.length())
                    .filter(i -> index != i)
                    .filter(i -> guess.codePointAt(i) != hidden.codePointAt(i))
                    .anyMatch(i -> hidden.match(this, index, i));
        }
    }

    public String guess(String guessAsString) {
        Guess guess = new Guess(guessAsString);
        Hidden hidden = new Hidden(hiddenAsString);

        return IntStream.range(0, guess.length())
            .mapToObj(
                    i -> {
                        if (guess.hasALetterAtPosition(i).thatIsWellPlacedIn(hidden)) {
                            return Character.toString(guess.codePointAt(i)).toUpperCase();
                        } else if (guess.hasALetterAtPosition(i).thatIsNotWellPlacedIn(hidden)) {
                            return Character.toString(guess.codePointAt(i));
                        } else {
                            return  ".";
                        }
                    }
            )
            .collect(Collectors.joining());
    }
}
