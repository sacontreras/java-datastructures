package com.sacontreras.library.algorithms.strings.combination;

public abstract class CharacterCombinationFinder {
	public abstract void findCharacterCombination(String string, CharacterCombinationFinderListener listener);
	public void findCharacterCombination(String string) {
		findCharacterCombination(string, new CharacterCombinationFinderListener() {
			@Override
			public void OnCharacterCombinationFound(String string, String combination) {
				System.out.printf("findCharacterCombination(\"%s\"): found combination: \"%s\"\n", string, combination);
			}
		});
	}
}
