package homework5.microTasks.task1;

public class UnicodeCharInfo {
    private char symbol;

    public int getUnicodeInt() {
        return (int) symbol;
    }

    public String getUnicodeHex() {
        // return Character.toString(symbol);
        return String.format("U+%04x", (int) symbol).toUpperCase();
    }

    public char getNextChar() {
        return (char) (symbol + 1);
    }

    public char getPrevChar() {
        return (char) (symbol - 1);
    }

    public String getCharType() {
        if (Character.isDigit(symbol)) {
            return "DIGIT";
        }
        if (Character.isAlphabetic(symbol)) {
            if (Character.isUpperCase(symbol)) {
                return "LETTER_UPPER";
            }
            if (Character.isLowerCase(symbol)) {
                return "LETTER_LOVER";
            }
        }

        if (Character.isWhitespace(symbol)) {
            return "SPACE";
        }

        return "OTHER";
    }

    public int alphabetNumber() {
        if (Character.isAlphabetic(symbol)) {
            return (int) Character.toLowerCase(symbol) - 'a' + 1;
        }
        return -1;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
}
