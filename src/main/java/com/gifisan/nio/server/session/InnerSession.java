package com.gifisan.nio.server.session;

import com.gifisan.nio.component.ServerProtocolData;
import com.gifisan.nio.server.selector.ServiceAcceptorJob;

public interface InnerSession extends Session {
	
	public abstract byte getSessionID();
	
	public abstract void destroyImmediately();
	
	public abstract ServiceAcceptorJob updateAcceptor(ServerProtocolData decoder);
	
}
