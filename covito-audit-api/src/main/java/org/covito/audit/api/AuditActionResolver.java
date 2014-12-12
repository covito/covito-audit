package org.covito.audit.api;

import org.aspectj.lang.JoinPoint;
import org.covito.audit.api.annotation.Audit;

public interface AuditActionResolver {

	 /**
     * Resolve the action for the audit event.
     * 
     * @param auditableTarget
     * @param retval	The returned value
     * @param audit the Audit annotation that may contain additional information.
     * @return	The resource String
     */
    String resolveFrom(JoinPoint auditableTarget, Object retval, Audit audit);
    
    /**
     * Resolve the action for the audit event that has incurred
     * an exception.
     * 
     * @param auditableTarget
     * @param exception	The exception incurred when the join point proceeds.
     * @param audit the Audit annotation that may contain additional information.
     * @return	The resource String
     */
    String resolveFrom(JoinPoint auditableTarget, Exception exception, Audit audit);
}
