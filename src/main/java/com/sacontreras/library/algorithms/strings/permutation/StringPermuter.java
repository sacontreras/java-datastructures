package com.sacontreras.library.algorithms.strings.permutation;

public abstract class StringPermuter {
	public abstract void permute(String base, StringPerumuterListener listener);
	public void permute(String base) {
		permute(
			base, 
			new StringPerumuterListener() {
				@Override
				public void OnStringPermuted(String string, String permutation) {
					System.out.printf("permute(\"%s\"): found permutation: \"%s\"\n", base, permutation);
				}
			}
		);
	}
}
