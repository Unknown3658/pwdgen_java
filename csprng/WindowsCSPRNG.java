package com.pwdgen.csprng;

/**
 * Windows-specific CSPRNG implementation.
 * Java 17 SecureRandom automatically uses BCryptGenRandom on Windows.
 */
public class WindowsCSPRNG extends PlatformCSPRNG {
    private static final String PROVIDER = "SunJCE";
    
    public WindowsCSPRNG() {
        super();
    }
}
