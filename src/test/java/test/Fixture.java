package test;

public interface Fixture<T> {
	static <T> T given(Fixture<T> fixture) {
		return fixture.setup();
	}

	T setup();
}
