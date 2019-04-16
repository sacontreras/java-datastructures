package com.sacontreras.library.algorithms.strings.nonrepeatingchar;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public class BruteForceNonRepeatingCharFinder implements NonRepeatingCharFinder {
	@Override
	public int findFirst(String string) {
		if (string == null)
			throw new NullPointerException("string cannot be null");
		if (string.isEmpty())
			return -1;
		
		char[] chString = string.toCharArray();
		LinkedHashMap<Character, Integer> charCount = new LinkedHashMap<>();
		LinkedHashMap<Character, Integer> uniqueCharIndexMap = new LinkedHashMap<>();
		int i = 0;
		for (Character ch: chString) {
			int count = 0;
			if (charCount.containsKey(ch))
				count = charCount.get(ch);
			count += 1;
			charCount.put(ch, count);
			if (count == 1)
				uniqueCharIndexMap.put(ch, i);
			else
				uniqueCharIndexMap.remove(ch);
			i++;
		}
		if (!uniqueCharIndexMap.isEmpty()) {
			Iterator<Integer> itUniqueCharIndex = uniqueCharIndexMap.values().iterator();
			return itUniqueCharIndex.next();
		}
		return -1;
	}
}
