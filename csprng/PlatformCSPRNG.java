package com.pwdgen.csprng;

import java.security.SecureRandom;
import java.util.Locale;

public abstract class PlatformCSPRNG extends BaseCSPRNG {
    protected PlatformCSPRNG() {
        super();
    }
    
    public static CSPRNG create() {
        String os = System.getProperty("os.name", "").toLowerCase(Locale.ROOT);
        
        if (os.contains("win")) {
            return new WindowsCSPRNG();
        } else if (os.contains("mac")) {
            return new MacCSPRNG();
        } else if (os.contains("linux")) {
            return new LinuxCSPRNG();
        }
        
        return new LinuxCSPRNG();
    }
}
