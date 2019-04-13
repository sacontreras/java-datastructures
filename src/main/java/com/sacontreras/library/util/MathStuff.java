package com.sacontreras.library.util;

import java.util.Random;

public class MathStuff {
    private static MathStuff m_this = null;

    private final Random rand;

    private MathStuff() {
        rand = new Random(74699);
    }

    public static MathStuff getInstance() {
        if (m_this == null)
            m_this = new MathStuff();
        return m_this;
    }

	public double log_b(int n, int b) {
	    return (Math.log(n) / Math.log(b));
	}

	public int randomIntVal(int min, int max) {
	    return rand.nextInt((max - min) + 1) + min;
    }
}
