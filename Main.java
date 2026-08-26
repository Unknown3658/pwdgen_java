package com.pwdgen;

import com.pwdgen.cli.CliArgs;
import com.pwdgen.cli.CliParser;
import com.pwdgen.charset.Charset;
import com.pwdgen.csprng.CSPRNG;
import com.pwdgen.csprng.PlatformCSPRNG;
import com.pwdgen.password.PasswordGenerator;
import java.util.Locale;
import java.util.Optional;

public class Main {
    private Main() {
    }
    
    public static void main(String[] args) {
        Optional<CliArgs> parsedArgs = CliParser.parse(args);
        if (parsedArgs.isEmpty()) {
            System.err.println("Error: no valid arguments provided");
            System.exit(1);
            return;
        }
        
        CliArgs cliArgs = parsedArgs.get();
        CSPRNG rng = PlatformCSPRNG.create();
        Charset charset = buildCharset(cliArgs);
        
        if (charset.isEmpty()) {
            System.err.println("Error: charset cannot be empty");
            System.exit(1);
            return;
        }
        
        String[] passwords = PasswordGenerator.generateMultiple(rng, charset, cliArgs.count(), cliArgs.length());
        
        for (String pwd : passwords) {
            System.out.println(pwd);
        }
    }
    
    private static Charset buildCharset(CliArgs cliArgs) {
        return Charset.builder()
                .withLower(cliArgs.lower())
                .withUpper(cliArgs.upper())
                .withDigits(cliArgs.digits())
                .withSymbols(cliArgs.symbols())
                .build();
    }
}
