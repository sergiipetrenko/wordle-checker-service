package com.banditltd.wordlechecker.records;

public record Wordle(String hidden) {

    public String guess(String guess) {
        String result = "";
        boolean[] used = new boolean[this.hidden.length()];
        for (int i = 0; i < guess.length(); i++) {
            if (guess.codePointAt(i) == this.hidden.codePointAt(i)) {
                result += Character.toString(guess.codePointAt(i)).toUpperCase();
                used[i] = true;
            } else {
                boolean found = false;
                for (int j = 0; j < this.hidden.length(); j++) {
                    if (used[j]) {
                        continue;
                    }
                    if (i != j
                            && guess.codePointAt(j) != this.hidden.codePointAt(j)
                            && guess.codePointAt(i) == this.hidden.codePointAt(j)) {
                        result += Character.toString(guess.codePointAt(i));
                        used[j] = true;
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    result += ".";
                }
            }
        }
        return result;
    }
}
