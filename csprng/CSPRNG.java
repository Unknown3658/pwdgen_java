package com.pwdgen.csprng;

import java.security.SecureRandom;
import java.util.Random;

public interface CSPRNG {
    /**
     * Заполняет буфер криптографически стойкими случайными байтами.
     * @param buf буфер для заполнения
     * @param n количество байтов
     */
    void fill(byte[] buf, int n);
    
    /**
     * Возвращает следующее случайное 32-битное число.
     * @return unsigned int value
     */
    int nextUint32();
}
