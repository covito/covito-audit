package org.covito.audit.api;

import org.aspectj.lang.JoinPoint;
import org.covito.audit.api.web.ClientInfo;
import org.covito.audit.api.web.ClientInfoHolder;
import org.covito.audit.api.web.ClientInfoResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DefaultClientInfoResolver implements ClientInfoResolver{

	private final Logger log = LoggerFactory.getLogger(getClass());

	public ClientInfo resolveFrom(final JoinPoint joinPoint, final Object retVal) {
		final ClientInfo clientInfo = ClientInfoHolder.getClientInfo();
		
		if (clientInfo != null) {
			return clientInfo;
		}
		
		log.warn("No ClientInfo could be found.  Returning empty ClientInfo object.");
		
		return ClientInfo.EMPTY_CLIENT_INFO;
	}

}
