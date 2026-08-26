package com.pwdgen.csprng;

/**
 * Linux-specific CSPRNG implementation.
 * Java 17 SecureRandom automatically uses getrandom() and /dev/urandom on Linux.
 */
public class LinuxCSPRNG extends PlatformCSPRNG {
    private static final String PROVIDER = "NativePRNG";
    
    public LinuxCSPRNG() {
        super();
    }
}
