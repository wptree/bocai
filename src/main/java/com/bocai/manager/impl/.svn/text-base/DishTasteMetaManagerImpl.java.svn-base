package com.bocai.manager.impl;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bocai.dao.domain.DishTasteMeta;
import com.bocai.manager.DishTasteMetaManager;
import com.bocai.manager.ManagerHelper;

@Service("dishTasteMetaManager")
@Transactional
public class DishTasteMetaManagerImpl extends ManagerHelper implements
		DishTasteMetaManager {

	@Override
	public Long save(DishTasteMeta bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long update(DishTasteMeta bsean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(DishTasteMeta bean) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<DishTasteMeta> getByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DishTasteMeta getUniqueByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Set<DishTasteMeta> loadAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initiazation() {
		String[] tastes = {"酸","甜","苦","辣","咸","鲜","香","麻辣","咸香","咸鲜","清淡","酸甜","酸辣","微苦","微辣","甜辣","清鲜","香酥","香脆","变态辣","超级咸","腻人甜","无敌酸","怪味","其它"};
		for(String taste:tastes){
			DishTasteMeta tt = new DishTasteMeta();
			tt.setName(taste);
			dishTasteMetaDao.save(tt);
		}

	}

	@Override
	public DishTasteMeta getById(Long id) {
		return dishTasteMetaDao.findById(id);
	}

}
