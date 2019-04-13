package com.sacontreras.library.algorithms.strings.substring;

public class BruteForceSubstringFinder_str implements SubstringFinder {
	@Override
	public int FindSubstring(String base, String sub) {
		if (base == null)
			throw new NullPointerException("base cannot be null");
		if (sub == null)
			throw new NullPointerException("sub cannot be null");
		
		if (sub.isEmpty())
			return 0;
		
		int index = 0, 
			baseLen = base.length(), 
			subLen = sub.length(),
			foundAt = -1;
			
			while (index < baseLen) {
				int end = index + subLen;
				if (end <= baseLen && sub.equals(base.substring(index, index + subLen))) {
					foundAt = index;
					break;
				} else
					index++;
			}
			
			return foundAt;
	}
}
