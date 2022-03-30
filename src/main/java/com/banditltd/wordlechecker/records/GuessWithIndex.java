package com.banditltd.wordlechecker.records;

import java.util.stream.IntStream;

public record GuessWithIndex(Guess guess, int index) {

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
