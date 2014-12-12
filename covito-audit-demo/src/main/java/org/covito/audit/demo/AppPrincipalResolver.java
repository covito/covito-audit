package org.covito.audit.demo;

import org.aspectj.lang.JoinPoint;
import org.covito.audit.api.PrincipalResolver;

public class AppPrincipalResolver implements PrincipalResolver {

	@Override
	public String resolveFrom(JoinPoint auditTarget, Object returnValue) {
		return getLoginName();
	}

	@Override
	public String resolveFrom(JoinPoint auditTarget, Exception exception) {
		return getLoginName();
	}

	@Override
	public String resolve() {
		return getLoginName();
	}
	
	private String getLoginName(){
		return ANONYMOUS_USER;
	}

}
