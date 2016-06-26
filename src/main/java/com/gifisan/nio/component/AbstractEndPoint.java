package com.gifisan.nio.component;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.concurrent.atomic.AtomicLong;

import com.gifisan.nio.Attachment;

public abstract class AbstractEndPoint implements EndPoint {

	private static AtomicLong	autoEndPointID = new AtomicLong(10000);
	private Attachment			attachment	;
	private NIOContext			context		;
	private Long				endPointID	;
	protected InetSocketAddress	local		;
	protected InetSocketAddress	remote		;
	

	public AbstractEndPoint(NIOContext context){
		this.context = context;
		this.endPointID = autoEndPointID.getAndIncrement();
	}

	public void attach(Attachment attachment) {
		this.attachment = attachment;
	}

	public Attachment attachment() {
		return attachment;
	}

	public String getLocalHost() {
		return local.getHostName();
	}

	public int getLocalPort() {
		return local.getPort();
	}
	
	public String getLocalAddr() {
		
		InetAddress address = local.getAddress();
		
		if (address == null) {
			return "127.0.0.1";
		}
		
		return address.getHostAddress();
	}
	
	protected abstract InetSocketAddress getLocalSocketAddress() ;
	
	public String getRemoteAddr() {
		
		InetSocketAddress address = getRemoteSocketAddress();
		
		if (address == null) {
			
			return "closed";
		}
		
		return address.getAddress().getHostAddress();
	}

	public String getRemoteHost() {
		
		InetSocketAddress address = getRemoteSocketAddress();
		
		if (address == null) {
			
			return "closed";
		}
		
		return address.getAddress().getHostName();
	}

	public int getRemotePort() {
		
		InetSocketAddress address = getRemoteSocketAddress();
		
		if (address == null) {
			
			return -1;
		}
		
		return address.getPort();
	}

	protected abstract InetSocketAddress getRemoteSocketAddress();

	public NIOContext getContext() {
		return context;
	}

	public String toString() {
		return new StringBuilder("[")
				.append(getMarkPrefix())
				.append("(id:")
				.append(endPointID)
				.append(") remote /")
				.append(this.getRemoteHost())
				.append("(")
				.append(this.getRemoteAddr())
				.append("):")
				.append(this.getRemotePort())
				.append("]")
				.toString();
	}
	
	protected abstract String getMarkPrefix();

	public Long getEndPointID() {
		return endPointID;
	}
}