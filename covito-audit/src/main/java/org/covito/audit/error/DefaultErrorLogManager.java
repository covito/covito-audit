package org.covito.audit.error;

import java.util.List;

import org.covito.audit.api.ErrorLogManager;
import org.covito.audit.api.ErrorReporter;
import org.covito.audit.api.PrincipalResolver;

/**
 * 默认异常LOG管理
 * 
 * @author Brent.Shen
 *
 */
public class DefaultErrorLogManager implements ErrorLogManager {

	private String applicationCode;

	private PrincipalResolver principalResolver;

	private List<ErrorReporter> errorReporters;

	public DefaultErrorLogManager(String applicationCode, List<ErrorReporter> errorReporters) {
		this.applicationCode = applicationCode;
		this.errorReporters = errorReporters;
	}
	
	public void setPrincipalResolver(PrincipalResolver resolver) {
		this.principalResolver = resolver;
	}

	@Override
	public void recordError(String errorDescription) {
		String principal = resolvePrincipal();
		for (ErrorReporter r : this.errorReporters) {
			r.reportError(this.applicationCode, principal, errorDescription);
		}
	}

	@Override
	public void recordError(Throwable throwable) {
		String principal = resolvePrincipal();
		for (final ErrorReporter r : this.errorReporters) {
			r.reportError(this.applicationCode, principal, throwable);
		}

	}

	private String resolvePrincipal() {
		return (this.principalResolver == null) ? null : this.principalResolver.resolve();
	}

	@Override
	public List<ErrorReporter> getErrorReporters() {
		return this.errorReporters;
	}

}
