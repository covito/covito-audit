package org.covito.audit.resolver;

import org.aspectj.lang.JoinPoint;
import org.covito.audit.AbstractAuditActionResolver;
import org.covito.audit.api.annotation.Audit;

/**
 * 方法返回值为boolean类型
 * 
 * @author Brent.Shen
 *
 */
public class ObjectCreationAuditActionResolver extends AbstractAuditActionResolver {

	public ObjectCreationAuditActionResolver(final String successSuffix, final String failureSuffix) {
		super(successSuffix, failureSuffix);
	}

	public String resolveFrom(final JoinPoint auditableTarget, final Object retval, final Audit audit) {
		final String action = audit.action();

		return action + (retval == null ? getFailureSuffix() : getSuccessSuffix());
	}

	public String resolveFrom(final JoinPoint auditableTarget, final Exception exception, final Audit audit) {
		return audit.action() + getFailureSuffix();
	}

}
