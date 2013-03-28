package com.bocai.manager.impl;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bocai.dao.domain.DishStyleMeta;
import com.bocai.manager.DishStyleMetaManager;
import com.bocai.manager.ManagerHelper;

@Service("dishStyleMetaManager")
@Transactional
public class DishStyleMetaManagerImpl extends ManagerHelper implements DishStyleMetaManager {

	@Override
	public Long save(DishStyleMeta bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long update(DishStyleMeta bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(DishStyleMeta bean) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<DishStyleMeta> getByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DishStyleMeta getUniqueByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Set<DishStyleMeta> loadAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initiazation() {
		String[] styles = {"川菜","鲁菜","粤菜","湘菜","闽菜","浙菜","苏菜","徽菜","京菜","沪菜","豫菜","湖北菜","东北菜","西北菜","云贵菜","清真菜","山西菜","江西菜","客家","港澳","台湾","泰国","日韩","西餐","大众菜","少数民族菜","其它菜"};
		for(String style : styles){
			DishStyleMeta st = new DishStyleMeta();
			st.setName(style);
			dishStyleMetaDao.save(st);
		}
	}

	@Override
	public DishStyleMeta getById(Long id) {
		return dishStyleMetaDao.findById(id);
	}

}
