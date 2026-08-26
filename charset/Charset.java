package com.pwdgen.charset;

public final class Charset {
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()_+-=[]{}|;':\",./<>?_-={};:\"%";
    
    private final String lower;
    private final String upper;
    private final String digits;
    private final String symbols;
    
    Charset(boolean lower, boolean upper, boolean digits, boolean symbols) {
        this.lower = lower ? LOWER : "";
        this.upper = upper ? UPPER : "";
        this.digits = digits ? DIGITS : "";
        this.symbols = symbols ? SYMBOLS : "";
    }
    
    public static CharsetBuilder builder() {
        return new CharsetBuilder();
    }
    
    public int size() {
        return lower.length() + upper.length() + digits.length() + symbols.length();
    }
    
    public char get(int index) {
        int combinedIndex = index;
        int lowerLen = lower.length();
        int upperLen = upper.length();
        int digitsLen = digits.length();
        int symbolsLen = symbols.length();
        
        // Validate index
        if (combinedIndex < 0 || combinedIndex >= lowerLen + upperLen + digitsLen + symbolsLen) {
            throw new IndexOutOfBoundsException("Index " + combinedIndex + " out of bounds for charset of size " + (lowerLen + upperLen + digitsLen + symbolsLen));
        }
        
        if (combinedIndex < lowerLen) {
            return lower.charAt(combinedIndex);
        } else {
            combinedIndex -= lowerLen;
            if (combinedIndex < upperLen) {
                return upper.charAt(combinedIndex);
            } else {
                combinedIndex -= upperLen;
                if (combinedIndex < digitsLen) {
                    return digits.charAt(combinedIndex);
                } else {
                    combinedIndex -= digitsLen;
                    if (combinedIndex < symbolsLen) {
                        return symbols.charAt(combinedIndex);
                    } else {
                        throw new IndexOutOfBoundsException("Index " + combinedIndex + " out of bounds for symbols of length " + symbolsLen);
                    }
                }
            }
        }
    }
    
    public boolean hasLower() {
        return !lower.isEmpty();
    }
    
    public boolean hasUpper() {
        return !upper.isEmpty();
    }
    
    public boolean hasDigits() {
        return !digits.isEmpty();
    }
    
    public boolean hasSymbols() {
        return !symbols.isEmpty();
    }
    
    public boolean isEmpty() {
        return size() == 0;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Charset[lower=").append(lower.isEmpty() ? "false" : "true")
          .append(", upper=").append(upper.isEmpty() ? "false" : "true")
          .append(", digits=").append(digits.isEmpty() ? "false" : "true")
          .append(", symbols=").append(symbols.isEmpty() ? "false" : "true")
          .append("]");
        return sb.toString();
    }
}
