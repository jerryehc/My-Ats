/* Copyright (C) 2013 Interactive Brokers LLC. All rights reserved.  This code is subject to the terms
 * and conditions of the IB API Non-Commercial License or the IB API Commercial License, as applicable. */

package com.ib.controller;


import com.ib.client.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ats.controller.Constant;

import java.io.IOException;

// NOTE: TWS 936 SERVER_VERSION is 67.

public class ApiConnection extends EClientSocket {
	public interface ILogger {
		void log(String valueOf);
	}

	public static final char EOL = 0;
	public static final char LOG_EOL = '_';

	private final Logger m_inLogger = LogManager.getLogger(Constant.inLoggerName);
	private final Logger m_outLogger = LogManager.getLogger(Constant.outLoggerName);;
	private static final EJavaSignal m_signal = new EJavaSignal();

	public ApiConnection(EWrapper wrapper) {
		super( wrapper, m_signal);
	}

	@Override
	protected void sendMsg(EMessage msg) throws IOException {
		// TODO Auto-generated method stub
		super.sendMsg(msg);
		
		byte[] buf = msg.getRawData();

		
		m_outLogger.trace(new String(buf, 0, buf.length));
	}

	@Override
	public int readInt() throws IOException {
		int c = super.readInt();
		
		m_inLogger.trace( String.valueOf( (char)c) );
		
		return c;
	}

	@Override
	public int read(byte[] buf, int off, int len) throws IOException {
		int n = super.read(buf, off, len);
		
		m_inLogger.trace(new String(buf, 0, n));
		
		return n;
	}

	public synchronized void placeOrder(Contract contract, Order order) {
		// not connected?
		if( !isConnected() ) {
            notConnected();
			return;
		}

		// ApiController requires TWS 932 or higher; this limitation could be removed if needed
		if (serverVersion() < 66) {
            error( EClientErrors.NO_VALID_ID, EClientErrors.UPDATE_TWS, "ApiController requires TWS build 932 or higher to place orders.");
            return;
		}
	    placeOrder(order.orderId(), contract, order);
	} 
}
