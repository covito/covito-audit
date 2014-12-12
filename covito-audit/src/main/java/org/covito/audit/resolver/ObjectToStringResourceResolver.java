package org.covito.audit.resolver;

import org.aspectj.lang.JoinPoint;
import org.covito.audit.api.AuditResourceResolver;

public class ObjectToStringResourceResolver implements AuditResourceResolver {

	public String[] resolveFrom(JoinPoint target, Object returnValue) {
		return new String[] { target.getTarget().toString() };
	}

	public String[] resolveFrom(JoinPoint target, Exception exception) {
		return new String[] { target.getTarget().toString() + "__EXCEPTION: [" + exception.getMessage() + "]" };
	}

}
