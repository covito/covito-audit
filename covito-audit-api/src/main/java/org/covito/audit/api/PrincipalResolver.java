package org.covito.audit.api;

import org.aspectj.lang.JoinPoint;

/**
 * 操作者解析器
 * @author Brent.Shen
 *
 */
public interface PrincipalResolver {

	 /**
     * Default String that can be used when the user is anonymous.
     */
    final String ANONYMOUS_USER = "audit:anonymous";

    /**
     * Default String that can be used when the user cannot be determined.
     */
    final String UNKNOWN_USER = "audit:unknown";

    
    String resolveFrom(JoinPoint auditTarget, Object returnValue);
    
   
    String resolveFrom(JoinPoint auditTarget, Exception exception);

   
    String resolve();
}
