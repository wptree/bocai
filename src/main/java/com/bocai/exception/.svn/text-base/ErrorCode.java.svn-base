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
 * Error Code.
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
public enum ErrorCode {
    
    /**
     * We need to group error codes by modules Prefix:
     * Module Name								Error Code Prefix
     * ----------------------------------------------------------
     * Common Error								COM_
     * AdministrationService					ADM_
     * ReportingService							RPT_
     * ExportService						    EXP_
     * End of module definition
     */
	
	/****************************************************************
	 *	BASIC ERROR TYPE
	 ***************************************************************/
	SUCCESS,
	/**
	 * Default Error code for PersistenceException
	 * This is exception thrown when an error occurs during DB operation.
	 */
    PERSISTENCE_FAILED,
    
    /**
	 * Default Error code for BusinessException
	 * This is exception thrown from business layer.
	 */
	BUSINESS_FAILED,
    
    /**
	 * Default Error code for PushException
	 * This is exception thrown from push service.
	 */
    PUSH_MESSAGE_FAILED,
    
    /**
	 * Default Error code for JobExcuteException
	 * This is exception thrown when an error occurs during Job execution.
	 */
    JOB_EXCUTE_FAILED,
    
    /**
	 * Default Error code for AppException
	 * This is exception thrown from utilities methods.
	 */
    APP_FAILED,
    
    /**
	 * Default Error code for EmailException
	 * This is exception thrown from email functional methods.
	 */
    EMAIL_FAILED,
    
    /**
     * Default Error code for Exception
     * This is exception caught from facade layer.
     */
    FATAL_INT_ERROR
    
}
