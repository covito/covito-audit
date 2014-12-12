package org.covito.audit.api.util;

public class Assert {

	public static void assertNotNull(final Object o, final String message) {
		if (o == null) {
			throw new AssertionError(message);
		}
	}
}
