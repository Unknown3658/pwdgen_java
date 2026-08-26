package com.pwdgen.password;

import com.pwdgen.csprng.CSPRNG;
import com.pwdgen.charset.Charset;

public final class PasswordGenerator {
    private static final long BITS_64 = 0x100000000L;
    
    private PasswordGenerator() {
    }
    
    public static String generate(CSPRNG rng, Charset charset, int length) {
        if (charset.isEmpty()) {
            throw new PasswordValidationError("Charset cannot be empty");
        }
        if (length > 256) {
            throw new PasswordValidationError("Password length must be <= 256");
        }
        
        StringBuilder result = new StringBuilder(length);
        int alphabetSize = charset.size();
        
        for (int i = 0; i < length; i++) {
            long idxLong = Integer.toUnsignedLong(rng.nextUint32()) % alphabetSize;
            int idx = (int) idxLong;
            result.append(charset.get(idx));
        }
        
        return result.toString();
    }
    
    public static String[] generateMultiple(CSPRNG rng, Charset charset, int count, int length) {
        if (count < 1) {
            throw new PasswordValidationError("Password count must be >= 1");
        }
        if (count > 10000) {
            throw new PasswordValidationError("Password count must be <= 10000");
        }
        
        String[] result = new String[count];
        for (int i = 0; i < count; i++) {
            result[i] = generate(rng, charset, length);
        }
        
        return result;
    }
    
    public static void validateCharset(Charset charset) {
        if (charset.isEmpty()) {
            throw new PasswordValidationError("Charset cannot be empty");
        }
    }
}
