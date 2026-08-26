package com.pwdgen.cli;

import java.util.Optional;

public final class CliParser {
    private CliParser() {
    }
    
    public static Optional<CliArgs> parse(String[] args) {
        try {
            return Optional.of(CliArgs.parse(args));
        } catch (CliException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
            return Optional.empty();
        }
    }
}
