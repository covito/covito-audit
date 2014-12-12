package org.covito.audit.error;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.covito.audit.api.ErrorReporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jLogErrorReporter implements ErrorReporter {

	private final Logger logger = LoggerFactory.getLogger(this.getClass()); 
	
	@Override
	public void reportError(String applicationCode, String principal, String errorDescription) {
		logErrorRecord(applicationCode, principal, errorDescription);
	}

	protected void logErrorRecord(String applicationCode, String principal, String errorDescription) {
		final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS Z");
		final StringBuilder builder = new StringBuilder(512);
		builder.append("\n\n===ERROR LOG record BEGIN==========================================")
               .append("\nAPPLICATION CODE: ")
               .append(applicationCode)
               .append("\nPRINCIPAL: ")
               .append(principal)
               .append("\nWHEN: ")
               .append(dateTimeFormat.format(new Date()))
               .append("\nDESCRIPTION: ")
               .append(errorDescription)
               .append("\n===ERROR LOG record END============================================")
               .append("\n\n");
        logger.error(builder.toString());
	}

	@Override
	public void reportError(String applicationCode, String principal, Throwable throwable) {
		final StackTraceElement[] stackTraceElements = throwable.getStackTrace();
		final StringBuilder builder = new StringBuilder(512);
		builder.append("\n\t").append(throwable.toString());
		
		for (final StackTraceElement element : stackTraceElements) {
			builder.append("\n\tat ").append(element.toString());
		}

		logErrorRecord(applicationCode, principal, builder.toString());
	}

}
