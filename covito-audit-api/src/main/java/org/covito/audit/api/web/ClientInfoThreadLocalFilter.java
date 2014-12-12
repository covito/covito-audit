package org.covito.audit.api.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.covito.audit.api.ErrorLogManager;

public class ClientInfoThreadLocalFilter implements Filter {

	public static final String CONST_IP_ADDRESS_HEADER = "nativeIpAddressHeader";

	private String otherHeader;

	public void destroy() {
		// nothing to do here
	}

	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain filterChain)
			throws IOException, ServletException {
		try {
			final ClientInfo clientInfo;

			if (otherHeader == null || otherHeader.isEmpty()) {
				clientInfo = new ClientInfo((HttpServletRequest) request);
			} else {
				clientInfo = new ClientInfo((HttpServletRequest) request, this.otherHeader);
			}
			ClientInfoHolder.setClientInfo(clientInfo);
			filterChain.doFilter(request, response);
		}catch(Exception e){ 
			processError(e);
			throw e;
		}finally {
			ClientInfoHolder.clear();
		}
	}
	
	protected void processError(Exception e){
		ErrorLogManager em=ErrorLogManagerHolder.getErrorLogManager();
		if(em!=null){
			em.recordError(e);
		}
	}

	public void init(final FilterConfig filterConfig) throws ServletException {
		this.otherHeader = filterConfig.getInitParameter(CONST_IP_ADDRESS_HEADER);
	}
}
