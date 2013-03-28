package com.bocai.manager;

import com.bocai.common.constant.UserTokenType;
import com.bocai.dao.domain.UserToken;

public interface UserTokenManager extends BaseManager<UserToken> {
	
	public boolean needVerify(Long uid, UserTokenType type);
	
	public UserToken verifyToken(Long uid, String token);
	
	public UserToken fetchToken(Long uid, UserTokenType type);
	
}
