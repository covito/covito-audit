package org.covito.audit.resolver;

import org.aspectj.lang.JoinPoint;
import org.covito.audit.AbstractAuditActionResolver;
import org.covito.audit.api.annotation.Audit;

public class DefaultAuditActionResolver extends AbstractAuditActionResolver {

	public DefaultAuditActionResolver() {
        this("","");
    }
	
	protected DefaultAuditActionResolver(String successSuffix, String failureSuffix) {
		super(successSuffix, failureSuffix);
	}

	@Override
	public String resolveFrom(JoinPoint auditableTarget, Object retval, Audit audit) {
		return audit.action() + getSuccessSuffix();
	}

	@Override
	public String resolveFrom(JoinPoint auditableTarget, Exception exception, Audit audit) {
		return audit.action() + getFailureSuffix();
	}

}
