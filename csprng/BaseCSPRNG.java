package com.pwdgen.csprng;

import java.security.SecureRandom;
import java.util.Random;

public abstract class BaseCSPRNG implements CSPRNG {
    protected final SecureRandom secureRandom;
    
    protected BaseCSPRNG() {
        this.secureRandom = new SecureRandom();
    }
    
    @Override
    public void fill(byte[] buf, int n) {
        if (buf == null) {
            throw new IllegalArgumentException("buffer cannot be null");
        }
        if (n <= 0) {
            throw new IllegalArgumentException("n must be > 0");
        }
        
        secureRandom.nextBytes(buf);
    }
    
    @Override
    public int nextUint32() {
        byte[] buffer = new byte[4];
        fill(buffer, 4);
        long val = ((long) buffer[0] & 0xFF) | 
                   ((long) buffer[1] & 0xFF) << 8 | 
                   ((long) buffer[2] & 0xFF) << 16 | 
                   ((long) buffer[3] & 0xFF) << 24;
        return (int) (val & 0xFFFFFFFFL);
    }
}
