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
package com.generallycloud.nio.component;

import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

import com.generallycloud.nio.common.Logger;
import com.generallycloud.nio.common.LoggerFactory;


//FIXME 定时清理DatagramChannel
public class DatagramChannelSelectorLoop extends AbstractSelectorLoop {

	private Logger					logger	= LoggerFactory.getLogger(DatagramChannelSelectorLoop.class);
	private SelectionAcceptor		_read_acceptor;
	private DatagramChannelContext	context;

	public DatagramChannelSelectorLoop(ChannelService service, SelectorLoop[] selectorLoops) {
		super(service, selectorLoops);
		this.context = (DatagramChannelContext) service.getContext();
		this._read_acceptor = new DatagramChannelSelectionReader(this);
		this.selectorLoopStrategy = new PrimarySelectorLoopStrategy(this);
	}
	
	@Override
	public DatagramChannelContext getContext() {
		return context;
	}

	@Override
	public void accept(SelectionKey selectionKey) {
		if (!selectionKey.isValid()) {
			cancelSelectionKey(selectionKey);
			return;
		}

		try {

			if (selectionKey.isReadable()) {
				_read_acceptor.accept(selectionKey);
			} else if (selectionKey.isWritable()) {
				logger.error("Writable=================");
			} else if (selectionKey.isAcceptable()) {
				logger.error("Acceptable=================");
			} else if (selectionKey.isConnectable()) {
				logger.error("Connectable=================");
			}

		} catch (Throwable e) {

			cancelSelectionKey(selectionKey, e);
		}
	}

	@Override
	public Selector buildSelector(SelectableChannel channel) throws IOException {
		// 打开selector
		Selector selector = Selector.open();
		// 注册监听事件到该selector
		channel.register(selector, SelectionKey.OP_READ);

		return selector;
	}
	
}
