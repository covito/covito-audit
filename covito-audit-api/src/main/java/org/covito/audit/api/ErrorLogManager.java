package org.covito.audit.api;

import java.util.List;

public interface ErrorLogManager {

	void recordError(String errorDescription);
	
	void recordError(Throwable throwable);
	
	List<ErrorReporter> getErrorReporters();
}
