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
public class BooleanAuditActionResolver extends AbstractAuditActionResolver {

	protected BooleanAuditActionResolver(String successSuffix, String failureSuffix) {
		super(successSuffix, failureSuffix);
	}

	@Override
	public String resolveFrom(JoinPoint auditableTarget, Object retval, Audit audit) {
		final Boolean bool = (Boolean) retval;
		final String action = audit.action();
		return action + (bool ? getSuccessSuffix() : getFailureSuffix());
	}

	@Override
	public String resolveFrom(JoinPoint auditableTarget, Exception exception, Audit audit) {
		return audit.action() + getFailureSuffix();
	}

}
