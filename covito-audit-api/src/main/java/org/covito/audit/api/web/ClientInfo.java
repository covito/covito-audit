package org.covito.audit.api.web;

import javax.servlet.http.HttpServletRequest;

/**
 * 访问者信息
 * @author Brent.Shen
 */
public final class ClientInfo {
	
	public static ClientInfo EMPTY_CLIENT_INFO = new ClientInfo();

	/** IP Address of the server (local). */
	private final String serverIpAddress;

	/** IP Address of the client (Remote) */
	private final String clientIpAddress;

	private ClientInfo() {
		this((String) null, (String) null);
	}

	public ClientInfo(final HttpServletRequest request) {
		this(request.getLocalAddr(), request.getRemoteAddr());
	}

	public ClientInfo(final HttpServletRequest request, final String alternateLocation) {
		this(request.getLocalAddr(), request.getHeader(alternateLocation) != null ? request
				.getHeader(alternateLocation) : request.getRemoteAddr());
	}

	public ClientInfo(final String serverIpAddress, final String clientIpAddress) {
		this.serverIpAddress = serverIpAddress == null ? "unknown" : serverIpAddress;
		this.clientIpAddress = clientIpAddress == null ? "unknown" : clientIpAddress;
	}

	public String getServerIpAddress() {
		return this.serverIpAddress;
	}

	public String getClientIpAddress() {
		return this.clientIpAddress;
	}
}
