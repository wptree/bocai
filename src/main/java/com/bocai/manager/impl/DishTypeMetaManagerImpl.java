package com.bocai.manager.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bocai.config.HQLContainer;
import com.bocai.dao.domain.DishTypeMeta;
import com.bocai.manager.DishTypeMetaManager;
import com.bocai.manager.ManagerHelper;
import com.bocai.vo.Token;

@Service("dishTypeMetaManager")
@Transactional
public class DishTypeMetaManagerImpl extends ManagerHelper implements DishTypeMetaManager {

	@Override
	public Long save(DishTypeMeta bean) {
		if(bean!=null){
			dishTypeMetaDao.save(bean);
			return bean.getId();
		}else{
			return null;
		}
		
	}

	@Override
	public Long update(DishTypeMeta bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(DishTypeMeta bean) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<DishTypeMeta> getByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DishTypeMeta getUniqueByProperty(String prop, Object value) {
		if(prop!=null&&!"".equals(prop)){
			return dishTypeMetaDao.findUniqueBy(prop, value);
		}else{
			return null;
		}
		
	}

	@Override
	public int countByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Set<DishTypeMeta> loadAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initiazation() {
		String[] types = {"家常菜","私家菜","凉菜","冰品","海鲜","猪肉","牛肉","羊肉","禽类","蛋类","水产","素菜","烘焙","煲汤","粥饭","饮品","甜品","面点","豆制品","西餐","药膳","小吃","其他"};
		for(String type : types){
			DishTypeMeta tm = new DishTypeMeta();
			tm.setName(type);
			dishTypeMetaDao.save(tm);
		}
	}

	@Override
	public DishTypeMeta getById(Long id) {
		return dishTypeMetaDao.findById(id);
	}

	@Override
	public List<Token> getTypeMatchedByName(String queryStr) {
		List<Token> tokens = new ArrayList<Token>(); 
		String hql = HQLContainer.getHql("query.dishType.like.name");
		List result = dishTypeMetaDao.find(hql, "%"+queryStr+"%");
		if(result != null){
			for(int i = 0; i<result.size(); i++){
				Object[] objects = (Object[]) result.get(i);
				tokens.add(new Token(objects[1].toString(), objects[1].toString()));
			}
		}
		return tokens;
	}

}
