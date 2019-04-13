package com.sacontreras.library.hash;

public interface IHashable<THashContent, THashContext> {
	int hash(final THashContent hashContent, final THashContext hashContext);
}
