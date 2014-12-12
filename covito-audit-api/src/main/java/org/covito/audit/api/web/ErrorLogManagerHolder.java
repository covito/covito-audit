package org.covito.audit.api.web;

import org.covito.audit.api.ErrorLogManager;

public class ErrorLogManagerHolder {

	private static ErrorLogManager errorLogManager;
	
	public static ErrorLogManager getErrorLogManager(){
		return errorLogManager;
	}

	public void setErrorLogManager(ErrorLogManager errorLogManager) {
		ErrorLogManagerHolder.errorLogManager = errorLogManager;
	}
	
}
