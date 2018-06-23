package com.sacontreras.library;

public class BoxedType<TBoxed> {
	private TBoxed boxed_type;
	public void setValue(final TBoxed boxed_type) {
		this.boxed_type = boxed_type;
	}
	public TBoxed getValue() {
		return this.boxed_type;
	}
	
	public BoxedType(final TBoxed boxed_type) {
		setValue(boxed_type);
	}
	public BoxedType() {}
}
