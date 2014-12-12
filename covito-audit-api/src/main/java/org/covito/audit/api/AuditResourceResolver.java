package org.covito.audit.api;

import org.aspectj.lang.JoinPoint;

public interface AuditResourceResolver {

	/**
	 * Resolve the auditable resource.
	 * 
	 * @param target
	 *            the join point that contains the arguments.
	 * @param returnValue
	 *            The returned value
	 * @return The resource String.
	 */
	String[] resolveFrom(JoinPoint target, Object returnValue);

	/**
	 * Resolve the auditable resource for an audit-able action that has incurred
	 * an exception.
	 * 
	 * @param target
	 *            the join point that contains the arguments.
	 * @param exception
	 *            The exception incurred when the join point proceeds.
	 * @return The resource String.
	 */
	String[] resolveFrom(JoinPoint target, Exception exception);
}
