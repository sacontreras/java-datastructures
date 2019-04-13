package com.sacontreras.library.algorithms.strings.substring;

public class BruteForceSubstringFinder_chars implements SubstringFinder {
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
			matchLen = 0,
			foundAt = -1;
		
		while (index < baseLen) {
			for (int isub = 0; isub < subLen; isub++) {
				int ibase = index + isub;
				if (ibase < baseLen) {
					char chBase = base.charAt(ibase);
					char chSub = sub.charAt(isub);
					if (chBase == chSub)
						matchLen++;
					else
						break;
				} else
					break;
			}
			if (matchLen == subLen) {
				foundAt = index;
				break;
			} else {
				if (matchLen > 0)
					index += matchLen;
				else
					index++;
			}
			matchLen = 0;
		}
		
		return foundAt;
	}
}
