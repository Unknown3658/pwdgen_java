package com.pwdgen.charset;

public final class CharsetBuilder {
    private boolean includeLower = true;
    private boolean includeUpper = true;
    private boolean includeDigits = true;
    private boolean includeSymbols = true;
    
    CharsetBuilder() {
    }
    
    public static CharsetBuilder createLower() {
        return new CharsetBuilder();
    }
    
    public static CharsetBuilder createUpper() {
        return new CharsetBuilder();
    }
    
    public static CharsetBuilder createDigits() {
        return new CharsetBuilder();
    }
    
    public static CharsetBuilder createSymbols() {
        return new CharsetBuilder();
    }
    
    public static CharsetBuilder all() {
        return new CharsetBuilder();
    }
    
    public static CharsetBuilder create() {
        return new CharsetBuilder();
    }
    
    public CharsetBuilder withLower(boolean include) {
        this.includeLower = include;
        return this;
    }
    
    public CharsetBuilder withUpper(boolean include) {
        this.includeUpper = include;
        return this;
    }
    
    public CharsetBuilder withDigits(boolean include) {
        this.includeDigits = include;
        return this;
    }
    
    public CharsetBuilder withSymbols(boolean include) {
        this.includeSymbols = include;
        return this;
    }
    
    public Charset build() {
        return new Charset(includeLower, includeUpper, includeDigits, includeSymbols);
    }
    
    public boolean lower() {
        return includeLower;
    }
    
    public boolean upper() {
        return includeUpper;
    }
    
    public boolean digits() {
        return includeDigits;
    }
    
    public boolean symbols() {
        return includeSymbols;
    }
}
