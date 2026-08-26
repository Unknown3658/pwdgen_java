package com.pwdgen.csprng;

/**
 * macOS-specific CSPRNG implementation.
 * Java 17 SecureRandom automatically uses SecRandomCopyBytes on macOS.
 */
public class MacCSPRNG extends PlatformCSPRNG {
    private static final String PROVIDER = "Conscrypt";
    
    public MacCSPRNG() {
        super();
    }
}
