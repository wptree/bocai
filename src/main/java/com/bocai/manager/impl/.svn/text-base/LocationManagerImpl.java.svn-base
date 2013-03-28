package com.bocai.manager.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bocai.common.hibernate.Updater;
import com.bocai.dao.domain.Location;
import com.bocai.dao.domain.User;
import com.bocai.manager.LocationManager;
import com.bocai.manager.ManagerHelper;

@Service("locationManager")
@Transactional
public class LocationManagerImpl extends ManagerHelper implements LocationManager{
	public Long save(Location bean){
		if(bean!=null){
			locationDao.save(bean);
			return bean.getId();
		}else{
			return null;
		}
	}

	@Override
	public Long update(Location bean) {
		locationDao.updateByUpdater(new Updater<Location>(bean));
		return bean.getId();
	}

	@Override
	public void delete(Location bean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Location> getByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Location getUniqueByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countByProperty(String prop, Object value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Location getById(Long id) {
		return locationDao.findById(id);
	}
}
