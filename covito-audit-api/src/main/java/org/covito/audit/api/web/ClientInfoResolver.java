package org.covito.audit.api.web;

import org.aspectj.lang.JoinPoint;

public interface ClientInfoResolver {

	ClientInfo resolveFrom(JoinPoint joinPoint, Object retVal);
}
