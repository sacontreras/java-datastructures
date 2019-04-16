package com.sacontreras.library.algorithms.strings.combination;

public class CCharacterCombinationFinder extends CharacterCombinationFinder {
	@Override
	public void findCharacterCombination(String string, CharacterCombinationFinderListener listener) {
		if (string == null)
			throw new NullPointerException("string cannot be null");

		if (string.isEmpty())
			listener.OnCharacterCombinationFound("", "");

		findCharacterCombination(string, 0, new StringBuilder(), listener);
	}
	
	private void findCharacterCombination(String string, int start, StringBuilder sb, CharacterCombinationFinderListener listener) {
		int len = string.length();
        for (int i = start; i < len; ++i) {
        	sb.append(string.charAt(i));
        	listener.OnCharacterCombinationFound(string, sb.toString());
            if (i < len)
            	findCharacterCombination(string, i+1, sb, listener);
            sb.setLength(sb.length() - 1);
        }
    }
}
