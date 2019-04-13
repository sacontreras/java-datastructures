package com.sacontreras.library.algorithms.strings.permutation;

import java.util.Arrays;
import java.util.TreeSet;

public class BacktrackingStringPermuter extends StringPermuter {

	@Override
	public void permute(String base, StringPerumuterListener listener) {
		if (base == null)
			throw new NullPointerException("base cannot be null");

//		System.out.printf("\npermute(\"%s\"): building permutations...\n\n", base);

		if (base.isEmpty())
			listener.OnStringPermuted(base, "");
		else {
			char[] chBase = base.toCharArray();
			char[] permutation = new char[chBase.length];
			boolean[] available = new boolean[chBase.length];
			Arrays.fill(available, true);
			permute(chBase, permutation, available, 0, listener);
		}
	}

	private void permute(char[] base, char[] permutation, boolean[] available, int depth, StringPerumuterListener listener) {
//		System.out.printf("permute[depth==%d]: base==\"%s\", available==%s\n", depth,
//				new String(base), Arrays.toString(available));

		int len = base.length;

		if (depth == len) {
			String sBase = new String(base);
			String sPermutation = new String(permutation);
//			System.out.printf("permute[depth==%d]: calling listener.OnStringPermuted(base==\"%s\", permutation==\"%s\")...\n", depth, sBase, sPermutation);
			listener.OnStringPermuted(sBase, sPermutation);
		} else {
			for (int i = 0; i < len; i++) {
				if (available[i]) {
//					System.out.printf("permute[depth==%d]: base[%d]=='%c' is available\n", depth, i, base[i]);
					permutation[depth] = base[i];
//					System.out.printf("permute[depth==%d]: set permutation[depth==%d]=base[i==%d]=='%c'\n", depth, depth, i,
//							permutation[depth]);
					available[i] = false;
					permute(base, permutation, available, depth + 1, listener);
					
					//now backtrack
					available[i] = true;
				} else {
//					System.out.printf("permute[depth==%d]: base[%d]=='%c' is NOT available\n", depth, i, base[i]);
				}
			}
		}
	}
	

	public static void main(String[] args) {
		BacktrackingStringPermuter stringPermuter = new BacktrackingStringPermuter();
	
		String s = "abcdefghi";
		stringPermuter.permute(s);
	}
}