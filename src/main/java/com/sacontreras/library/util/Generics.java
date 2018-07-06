package com.sacontreras.library.util;

import java.lang.reflect.Array;

public class Generics {
	@SuppressWarnings("unchecked")
	public final static <TData> TData[] newArray(final TData tdata_proto_instance, final int size) {
		return (TData[])Array.newInstance(tdata_proto_instance.getClass(), size);
	}
}
