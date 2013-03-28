/*
 * Licensed to the bocai007.com under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The bocai.com licenses this file to You under the bocai.com License, Version 1.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.bocai007.com/licenses/LICENSE-1.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bocai.exception;

/**
 * <p>
 * Base exception class of Bocai exception system.
 * </p>
 * 
 * @author Shi,Tao
 * @since 0.1
 * @version 0.1
 * 
 * Revision History
 * DATE            | REVISER      | REASON
 * ---------------------------------------
 * 2011.03.20      | Shi,Tao      | Creation.
 * 
 */
public class BaseException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private String msg;
	private Throwable cause;
	
	public BaseException(){}
	
	public BaseException(String msg){
		super(msg);
		this.msg = msg;
	}
	
	public BaseException(Throwable cause){
		super(cause);
		this.cause = cause;
	}
	
	public BaseException(String msg, Throwable cause){
		super(msg, cause);
		this.msg = msg;
		this.cause = cause;
	}
	
	public String getMessage(){
		return msg;
	}
	
	public Throwable getCause(){
		return cause;
	}
	
	
}
