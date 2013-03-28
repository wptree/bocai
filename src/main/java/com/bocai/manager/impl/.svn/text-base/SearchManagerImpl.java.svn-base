package com.bocai.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bocai.common.constant.AppConstant;
import com.bocai.common.constant.MapRequestType;
import com.bocai.common.constant.SearchKeywordType;
import com.bocai.common.constant.SearchTargetType;
import com.bocai.common.page.Pagination;
import com.bocai.config.SystemConfig;
import com.bocai.dao.domain.AggregatedSpot;
import com.bocai.manager.ManagerHelper;
import com.bocai.manager.SearchManager;
import com.bocai.vo.AggregatedSpotVo;
import com.bocai.vo.LatLng;
import com.bocai.vo.Marker;
import com.bocai.vo.MarkerData;
import com.bocai.web.util.ImageHelper;
import com.googlecode.ehcache.annotations.Cacheable;

@Service("searchManager")
@Transactional
public class SearchManagerImpl extends ManagerHelper implements SearchManager {

	@Override
	@Cacheable(cacheName="searchCache")
	public Pagination search(String keywords, SearchKeywordType keywordType,
			SearchTargetType targetType, int pageAt, int pageSize) {

		if (keywordType == SearchKeywordType.BOKE) {
			
			if(targetType == SearchTargetType.AGGSPOT){
				return searchAggspotByUserName(MapRequestType.LATEST, keywords,
					pageAt, pageSize, null, null);
			}else if(targetType == SearchTargetType.USER){
				return searchBokeByKeyword(keywords, pageAt, pageSize);
			}else{
				//to be implemented
				return null;
			}

		} else if (keywordType == SearchKeywordType.DISH) {

			return searchAggspotByDishName(MapRequestType.LATEST, keywords, null,
					pageAt, pageSize, null, null);

		} else if (keywordType == SearchKeywordType.PLACE) {

			return searchAggspotByPlaceName(MapRequestType.LATEST, keywords, null,
					pageAt, pageSize, null, null);
		}

		return null;
	}
	
	public Pagination searchBokeByKeyword(String keyword, int pageAt, int pageSize){
		return userDao.searchUsers(keyword, pageSize, pageAt);
	}

	public Pagination searchAggspotByUserName(MapRequestType reqType,
			String userName, int pageAt, int pageSize, LatLng sw,
			LatLng ne) {
		return userDao.searchAggspotByUserName(reqType, userName, pageAt,
				pageSize, sw, ne);
	}

	public Pagination searchAggspotByDishName(MapRequestType reqType,
			String dishName, Long userId, int pageAt, int pageSize, LatLng sw,
			LatLng ne) {
		return aggSpotDao.searchAggspotByDishName(reqType, dishName, userId, pageAt,
				pageSize, sw, ne);
	}

	public Pagination searchAggspotByPlaceName(MapRequestType reqType,
			String placeName, Long userId, int pageAt, int pageSize, LatLng sw,
			LatLng ne) {
		return aggSpotDao.searchAggspotByPlaceName(reqType, placeName, userId, pageAt,
				pageSize, sw, ne);
	}

	
	@Override
	@SuppressWarnings("unchecked")
	@Cacheable(cacheName="mapSearchCache")
	public Object[] mapSearch(String keywords, SearchKeywordType keywordType,
			MapRequestType reqType, int pageAt, int pageSize, LatLng sw,
			LatLng ne, Long userId) {
		// populate result
		Pagination pagn = new Pagination();
		List<Marker> markers = new ArrayList<Marker>();

		// get the aggspot list
		if (keywordType == SearchKeywordType.BOKE) {
			pagn = searchAggspotByUserName(reqType, keywords, pageAt, pageSize,
					sw, ne);
		} else if (keywordType == SearchKeywordType.DISH) {
			pagn = searchAggspotByDishName(reqType, keywords, userId, pageAt,
					pageSize, sw, ne);
		} else if (keywordType == SearchKeywordType.PLACE) {
			pagn = searchAggspotByPlaceName(reqType, keywords, userId, pageAt,
					pageSize, sw, ne);
		} else {
			;// nothing
		}
		Object[] result = new Object[] { pagn, markers };

		// convert domain to vo and populate marker list
		if (pagn != null && pagn.getList() != null && !pagn.getList().isEmpty()) {
			List<AggregatedSpot> aggSpots = (List<AggregatedSpot>) pagn
					.getList();
			// populate the markers
			if (aggSpots != null && aggSpots.size() != 0) {
				// define a marker map to deal with duplicated place markers
				Map<String, List<Marker>> markerMap = new HashMap<String, List<Marker>>();
				for (Object o : aggSpots) {
					AggregatedSpot aggSpot = (AggregatedSpot) o;
					if (aggSpot.getPlace() == null
							|| aggSpot.getPlace().getLocation() == null
							|| aggSpot.getLastSpot() == null)
						continue;
					Marker marker = new Marker();
					marker.setPosition(new LatLng(aggSpot.getPlace()
							.getLocation().getLat(), aggSpot.getPlace()
							.getLocation().getLng()));
					StringBuffer buf = new StringBuffer(SystemConfig.imageContext());
					buf.append("/").append(ImageHelper.getSpotImagePath(aggSpot.getLastSpot().getId(), 90, aggSpot.getLastSpot().getImgType()));
					marker.setIcon(buf.toString());
					buf = null;
					MarkerData data = new MarkerData();
					data.setId(aggSpot.getId());
					data.setPhoto(marker.getIcon());
					StringBuilder titleSB = new StringBuilder()
							.append(aggSpot.getDish().getName())
							.append(AppConstant.AT)
							.append(aggSpot.getPlace().getFullName());
					data.setTitle(titleSB.toString());
					data.setAddress(aggSpot.getPlace().getLocation()
							.toAddress());
					data.setDesc(aggSpot.getDescription() != null ? aggSpot
							.getDescription().substring(
									0,
									Math.min(aggSpot.getDescription().length(),
											30)) : null);
					marker.setData(new ArrayList<MarkerData>());
					marker.getData().add(data);
					if (!markerMap.containsKey(aggSpot.getPlace().getLocation()
							.getId()
							+ "")) {
						markerMap.put(aggSpot.getPlace().getLocation().getId()
								+ "", new ArrayList<Marker>());
					}
					List<Marker> markerList = markerMap.get(aggSpot.getPlace()
							.getLocation().getId()
							+ "");
					markerList.add(marker);
				}

				for (Map.Entry<String, List<Marker>> entry : markerMap
						.entrySet()) {
					List<Marker> dups = entry.getValue();
					if (dups == null || dups.size() == 0)
						continue;
					if (dups.size() == 1) {
						markers.add(dups.get(0));
					} else {
						// merge duplicate markers into one
						List<MarkerData> datas = new ArrayList<MarkerData>();
						for (int i = 0; i < dups.size(); i++) {
							if (dups.get(i).getData() == null
									|| dups.get(i).getData().size() == 0)
								continue;
							datas.add(dups.get(i).getData().get(0));
						}
						Marker marker = dups.get(0);
						marker.setData(datas);
						markers.add(marker);
					}
				}
			}
			//convert domain to vo
			List<AggregatedSpotVo> aggSpotVos = new ArrayList<AggregatedSpotVo>();
			for (int i = 0; i < aggSpots.size(); i++) {
				AggregatedSpotVo vo = AggregatedSpotVo
						.fromAggSpotBase((AggregatedSpot) aggSpots.get(i));
				if (vo != null) {
					aggSpotVos.add(vo);
				}
			}
			pagn.setList(aggSpotVos);
		}
		return result;
	}

}
