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
package com.bocai.manager;

import javax.annotation.Resource;

import com.bocai.dao.AggSpotTagXrefDao;
import com.bocai.dao.AggregatedSpotDao;
import com.bocai.dao.CityMappingDao;
import com.bocai.dao.CommentDao;
import com.bocai.dao.CorrectionDao;
import com.bocai.dao.DialogDao;
import com.bocai.dao.DishDao;
import com.bocai.dao.DishStyleMetaDao;
import com.bocai.dao.DishTasteMetaDao;
import com.bocai.dao.DishTypeMetaDao;
import com.bocai.dao.FeedDao;
import com.bocai.dao.FeedbackDao;
import com.bocai.dao.InvitationDao;
import com.bocai.dao.LocationDao;
import com.bocai.dao.NotificationDao;
import com.bocai.dao.PlaceDao;
import com.bocai.dao.PrivateLetterDao;
import com.bocai.dao.ReplyDao;
import com.bocai.dao.SpotDao;
import com.bocai.dao.StatisticalDao;
import com.bocai.dao.TagDao;
import com.bocai.dao.UserDao;
import com.bocai.dao.UserTaskDao;
import com.bocai.dao.UserTaskXrefDao;
import com.bocai.dao.UserThirdpartyDao;
import com.bocai.dao.UserTokenDao;

/**
 * <p>
 * Base class for manager layer, it's used to be auto-wired Dao beans.
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
 * 2011.03.22      | Shi,Tao      | Add instance variable 'roleDao'
 */
public abstract class ManagerHelper{
	
	@Resource protected UserDao userDao;
	@Resource protected UserThirdpartyDao userThirdpartyDao;
	
	@Resource protected SpotDao spotDao;
	@Resource protected CommentDao commentDao;
	@Resource protected ReplyDao replyDao;
	@Resource protected DishDao dishDao;
	@Resource protected PlaceDao placeDao;
	@Resource protected LocationDao locationDao;
	
	@Resource protected DishStyleMetaDao dishStyleMetaDao;
	@Resource protected DishTypeMetaDao dishTypeMetaDao;
	@Resource protected DishTasteMetaDao dishTasteMetaDao;
	@Resource protected TagDao tagDao;
	
	@Resource protected AggregatedSpotDao aggSpotDao;
	
	@Resource protected FeedDao feedDao;
	@Resource protected NotificationDao notificationDao;
	
	@Resource protected StatisticalDao statisticalDao;
	
	@Resource protected CityMappingDao cityMappingDao;
	
	@Resource protected CorrectionDao correctionDao;
	@Resource protected FeedbackDao feedbackDao;
	@Resource protected UserTokenDao userTokenDao;
	
	@Resource protected DialogDao dialogDao;
	@Resource protected PrivateLetterDao privateLetterDao;
	
	@Resource protected InvitationDao invitationDao;
	@Resource protected UserTaskDao userTaskDao;
	@Resource protected UserTaskXrefDao userTaskXrefDao;
	@Resource protected AggSpotTagXrefDao aggSpotTagXrefDao;
}
