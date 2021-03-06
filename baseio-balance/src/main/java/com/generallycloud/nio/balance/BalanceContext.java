/*
 * Copyright 2015 GenerallyCloud.com
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 
package com.generallycloud.nio.balance;

import com.generallycloud.nio.balance.router.BalanceRouter;

public class BalanceContext {

	public static final String				BALANCE_CHANNEL_LOST		= "BALANCE_CHANNEL_LOST";
	public static final String				BALANCE_RECEIVE_BROADCAST	= "BALANCE_RECEIVE_BROADCAST";

	private BalanceFacadeAcceptor				balanceFacadeAcceptor		= new BalanceFacadeAcceptor();
	private BalanceReverseAcceptor			balanceReverseAcceptor		= new BalanceReverseAcceptor();
	private BalanceFacadeAcceptorSEListener		balanceFacadeAcceptorSEListener;
	private BalanceReverseAcceptorSEListener	balanceReverseAcceptorSEListener;
	private BalanceRouter					balanceRouter;
	private BalanceReverseAcceptorHandler		balanceReverseAcceptorHandler;
	private BalanceFacadeAcceptorHandler		balanceFacadeAcceptorHandler;
	private ChannelLostReadFutureFactory		channelLostReadFutureFactory;
	private FacadeInterceptor				facadeInterceptor;

	public void initialize() {
		this.balanceFacadeAcceptorSEListener = new BalanceFacadeAcceptorSEListener(this);
		this.balanceReverseAcceptorSEListener = new BalanceReverseAcceptorSEListener(this);
		this.balanceFacadeAcceptorHandler = new BalanceFacadeAcceptorHandler(this);
		this.balanceReverseAcceptorHandler = new BalanceReverseAcceptorHandler(this);
	}

	public BalanceFacadeAcceptor getBalanceFacadeAcceptor() {
		return balanceFacadeAcceptor;
	}

	public BalanceFacadeAcceptorHandler getBalanceFacadeAcceptorHandler() {
		return balanceFacadeAcceptorHandler;
	}

	public BalanceFacadeAcceptorSEListener getBalanceFacadeAcceptorSEListener() {
		return balanceFacadeAcceptorSEListener;
	}

	public BalanceReverseAcceptor getBalanceReverseAcceptor() {
		return balanceReverseAcceptor;
	}

	public BalanceReverseAcceptorHandler getBalanceReverseAcceptorHandler() {
		return balanceReverseAcceptorHandler;
	}

	public BalanceReverseAcceptorSEListener getBalanceReverseAcceptorSEListener() {
		return balanceReverseAcceptorSEListener;
	}

	public BalanceRouter getBalanceRouter() {
		return balanceRouter;
	}

	public ChannelLostReadFutureFactory getChannelLostReadFutureFactory() {
		return channelLostReadFutureFactory;
	}

	public void setChannelLostReadFutureFactory(ChannelLostReadFutureFactory channelLostReadFutureFactory) {
		this.channelLostReadFutureFactory = channelLostReadFutureFactory;
	}

	public FacadeInterceptor getFacadeInterceptor() {
		return facadeInterceptor;
	}

	public void setBalanceRouter(BalanceRouter balanceRouter) {
		this.balanceRouter = balanceRouter;
	}

	public void setFacadeInterceptor(FacadeInterceptor facadeInterceptor) {
		if (facadeInterceptor == null) {
			throw new IllegalArgumentException("null facadeInterceptor");
		}
		this.facadeInterceptor = facadeInterceptor;
	}

}
