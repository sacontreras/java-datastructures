package com.sacontreras.library.common;

import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;


public class TestCaseDataStreamBuilder<TTestCaseData extends TestCaseData, TIImplementationUnderTest> implements Stream.Builder<TTestCaseData> {
	protected final Stream.Builder<TTestCaseData> theBuilder = Stream.builder();
	
	protected final List<TIImplementationUnderTest> implsUnderTest;
	
	public TestCaseDataStreamBuilder(List<TIImplementationUnderTest> implsUnderTest) {
		this.implsUnderTest = implsUnderTest;
	}

	@Override
	public void accept(TTestCaseData t) {
		theBuilder.accept(t);
	}
	
	@Override
	public Builder<TTestCaseData> add(TTestCaseData t) {
		return theBuilder.add(t);
	}
	
	@Override
	public Stream<TTestCaseData> build() {
		return theBuilder.build();
	}
}
