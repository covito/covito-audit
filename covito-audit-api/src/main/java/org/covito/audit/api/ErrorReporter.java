package org.covito.audit.api;

public interface ErrorReporter {

	/**
	 * Reports the specified error.
	 * 
	 * @param applicationCode	The application identifier.
	 * @param principal			Optional application-specific context information on the
	 *      principal incurring the error. 
	 * @param errorDescription 	Details on the error.
	 */
	void reportError(String applicationCode, String principal, String errorDescription);
	
	/**
	 * Reports the specified <code>Throwable</code>.
	 * 
	 * @param applicationCode	The application identifier.
	 * @param principal			Optional application-specific context information on the 
	 * 		principal incurring the error.
	 * @param throwable			The <code>Throwable</code> to be reported.
	 */
	void reportError(String applicationCode, String principal, Throwable throwable);
}
