package com.sacontreras.library.algorithms.strings.combination;

public class BacktrackingCharacterCombinationFinder extends CharacterCombinationFinder {
	@Override
	public void findCharacterCombination(String string, CharacterCombinationFinderListener listener) {
		if (string == null)
			throw new NullPointerException("string cannot be null");
		
		if (string.isEmpty())
			listener.OnCharacterCombinationFound("", "");
		
		//all other cases...
		listener.OnCharacterCombinationFound(string, string);
	}
}
