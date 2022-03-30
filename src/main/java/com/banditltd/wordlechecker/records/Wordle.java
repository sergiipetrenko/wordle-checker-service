package com.banditltd.wordlechecker.records;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record Wordle(String hiddenAsString) {

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
