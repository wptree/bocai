package com.bocai.manager.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bocai.common.constant.UserTokenStatus;
import com.bocai.common.constant.UserTokenType;
import com.bocai.common.hibernate.Updater;
import com.bocai.config.HQLContainer;
import com.bocai.dao.domain.User;
import com.bocai.dao.domain.UserToken;
import com.bocai.exception.ManagerException;
import com.bocai.manager.ManagerHelper;
import com.bocai.manager.UserTokenManager;

@Service("userTokenManager")
@Transactional
public class UserTokenManagerImpl extends ManagerHelper implements
		UserTokenManager {

	private static final String QUERY_TOKEN_BY_USER_AND_TYPE = 
			"select distinct ut from UserToken as ut left join" +
			" ut.user as user " +
			" where user.id = ? and " +
			" ut.type = ? and ut.status = ? order by ut.createAt desc";
	
	@Override
	public Long save(UserToken bean) {
		UserToken userToken = userTokenDao.save(bean);
		return userToken.getId();
	}

	@Override
	public Long update(UserToken bean) {
		userTokenDao.updateByUpdater(new Updater<UserToken>(bean));
		return bean.getId();
	}

	@Override
	public void delete(UserToken bean) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<UserToken> getByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserToken getUniqueByProperty(String prop, Object value) {
		if(prop!=null && !"".equals(prop.trim())){
			return userTokenDao.findUniqueBy(prop, value);
		}
		return null;
	}

	@Override
	public int countByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UserToken getById(Long id) {
		return userTokenDao.findById(id);
	}
	
	@SuppressWarnings("rawtypes")
	public boolean needVerify(Long uid, UserTokenType type){
		List tokens = userTokenDao.find(QUERY_TOKEN_BY_USER_AND_TYPE, 
				uid, UserTokenType.SIGN_UP_VALIDATION, UserTokenStatus.ACTIVE);
		if(tokens!=null  && tokens.size()!=0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public UserToken verifyToken(Long uid, String token) {
		UserToken ut = userTokenDao.findUniqueBy("token", token);
		if(ut == null) return ut;
		User user = ut.getUser();
		if(uid.equals(user.getId())){
			return ut;
		}else{
			return null;
		}
	}
	
	@SuppressWarnings("rawtypes")
	public UserToken fetchToken(Long uid, UserTokenType type){
		List tokens = userTokenDao.find(QUERY_TOKEN_BY_USER_AND_TYPE, uid, UserTokenType.SIGN_UP_VALIDATION, UserTokenStatus.ACTIVE);
		if(tokens == null || tokens.size() ==0){
			User user = userDao.findById(uid);
			if(user == null){
				throw new ManagerException("Could not find user entity, the user id [" + uid + "] is invalid.");
			}
			UserToken bean = new UserToken();
			bean.setUser(user);
			bean.setStatus(UserTokenStatus.ACTIVE);
			bean.setType(UserTokenType.SIGN_UP_VALIDATION);
			bean.setToken(UUID.randomUUID().toString());
			bean.setCreateAt(new Date());
			userTokenDao.save(bean);
			return bean;
		}else{
			int len = tokens.size();
			if(len > 1){
				StringBuilder sb = new StringBuilder()
						.append("update UserToken ut set ut.status = ?,")
						.append(" ut.closeAt = ?" )
						.append(" where ut.id in (");
				for (int i=len-1; i>0; i--){
					sb.append(((UserToken)tokens.get(i)).getId() + (i==1? "" : ",")); 
				}
				String hql = sb.append(")").toString();
				userTokenDao.updateBulk(hql, UserTokenStatus.CLOSE, new Date());
			}
			return (UserToken)tokens.get(0);
		}
	}

}
