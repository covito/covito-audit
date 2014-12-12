package org.covito.audit;

import org.aspectj.lang.JoinPoint;
import org.covito.audit.api.AuditResourceResolver;

public abstract class AbstractAuditResourceResolver implements AuditResourceResolver{

	@Override
	public String[] resolveFrom(JoinPoint target, Object returnValue) {
		return createResource(target.getArgs());
	}

	@Override
	public String[] resolveFrom(JoinPoint target, Exception exception) {
		return createResource(target.getArgs());
	}

	protected abstract String[] createResource(final Object[] args);
}
