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
package com.generallycloud.nio.container.service;

import com.generallycloud.nio.common.Logger;
import com.generallycloud.nio.common.LoggerFactory;
import com.generallycloud.nio.common.StringUtil;
import com.generallycloud.nio.component.IoEventHandle;
import com.generallycloud.nio.component.SocketSession;
import com.generallycloud.nio.container.ApplicationContext;
import com.generallycloud.nio.container.HotDeploy;
import com.generallycloud.nio.container.Initializeable;
import com.generallycloud.nio.container.InitializeableImpl;
import com.generallycloud.nio.container.configuration.Configuration;
import com.generallycloud.nio.protocol.ReadFuture;

public abstract class FutureAcceptorService extends InitializeableImpl implements Initializeable, HotDeploy,
		IoEventHandle {

	private Logger	logger	= LoggerFactory.getLogger(FutureAcceptorService.class);

	@Override
	public void initialize(ApplicationContext context, Configuration config) throws Exception {

	}

	@Override
	public void prepare(ApplicationContext context, Configuration config) throws Exception {
		this.initialize(context, config);
	}

	@Override
	public void unload(ApplicationContext context, Configuration config) throws Exception {
		this.destroy(context, config);
	}

	@Override
	public void futureSent(SocketSession session, ReadFuture future) {

	}
	
	@Override
	public void exceptionCaught(SocketSession session, ReadFuture future, Exception cause, IoEventState state) {
		logger.error(cause.getMessage(), cause);
	}

	@Override
	public String toString() {

		Configuration configuration = this.getConfig();

		String serviceName = null;

		if (configuration == null) {

			serviceName = this.getClass().getSimpleName();
		} else {

			serviceName = configuration.getParameter("service-name");

			if (StringUtil.isNullOrBlank(serviceName)) {
				serviceName = this.getClass().getSimpleName();
			}
		}

		return "(service-name:" + serviceName + "@class:" + this.getClass().getName() + ")";
	}

}
