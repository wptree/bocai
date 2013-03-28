package com.bocai.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bocai.common.page.Pagination;
import com.bocai.config.HQLContainer;
import com.bocai.dao.domain.Dish;
import com.bocai.dao.domain.Place;
import com.bocai.manager.DishManager;
import com.bocai.manager.ManagerHelper;
import com.bocai.search.engine.IndexCache;
import com.bocai.search.engine.IndexConstant;
import com.bocai.search.engine.TokenIndex;


@Service("dishManager")
@Transactional
public class DishManagerImpl extends ManagerHelper implements DishManager{
	
	@Autowired 
	private IndexCache indexCache;
	
	@Override
	public Long save(Dish dish){
		if(dish!=null){
			dishDao.save(dish);
			updateIndex(dish.getName());
			return dish.getId();
		}else{
			return null;
		}
	}

	@Override
	public Long update(Dish bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Dish bean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Dish> getByProperty(String prop, Object value) {
		return dishDao.findBy(prop, value);
	}

	@Override
	public Dish getUniqueByProperty(String prop, Object value) {
		if(prop!=null&&!"".equals(prop)){
			return dishDao.findUniqueBy(prop, value);
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
	public void initiazation() {
		String[] lucai = {"诗礼银杏","一卵孵双凤","八仙过海闹罗汉","孔府一品锅","神仙鸭子","带子上朝","怀抱鲤","花蓝桂鱼","玉带虾仁","油发豆莛","红扒鱼翅","白扒通天翅"};
		String[] chuancai = {"麻婆豆腐","辣子鸡丁","东坡肘子","豆瓣鲫鱼","口袋豆腐","酸菜鱼","夫妻肺片","蚂蚁上树","叫化鸡","茄汁鱼卷","鱼香肉丝","干煸冬笋","魔芋烧鸭","锅贴鱼片","麻辣肉丁","鱼香茄饼","冬菜肉末","粉蒸鸡"};
		String[] yuecai = {"咸菜焖猪肉","酿茄子","酿豆腐","梅菜扣肉","客家盐焗鸡","红糟排骨","清风送爽","炊太极虾","广式烧填鸭","池塘莲花"};
		String[] mincai = {"佛跳墙","醉排骨","荔枝肉","扳指干贝","尤溪卜鸭","七星鱼丸汤","软溜珠廉鱼","龙身凤尾虾","清炖全鸡","油爆双脆","香露全鸡","醉蚌肉"};
		String[] sucai = {"烤方","水晶肴蹄","清炖蟹粉狮子头","金陵丸子","白汁圆菜","黄泥煨鸡","清炖鸡孚","金陵板鸭","金香饼","鸡汤煮干丝","肉酿生麸","凤尾虾","三套鸭","无锡肉骨头","陆稿荐酱猪头肉","沛县狗肉"};
		String[] zhecai = {"西湖醋鱼","东坡肉","赛蟹羹","家乡南肉","干炸响铃","荷叶粉蒸肉","西湖莼菜汤","龙井虾仁","杭州煨鸡","虎跑素火煺","干菜焖肉","蛤蜊黄鱼羹","叫化童鸡","香酥焖肉","丝瓜卤蒸黄鱼","三丝拌蛏","油焖春笋","虾爆鳝背"};
		String[] xiangcai = {"海参盆蒸","腊味合蒸","走油豆鼓扣肉","麻辣子鸡","洞庭金龟","网油叉烧洞庭桂鱼","蝴蝶飘海","冰糖湘莲","红烧寒菌","板栗烧菜心","湘西酸肉","炒血鸭"};
		String[] huicai = {"清炖马蹄鳖","黄山炖鸽","腌鲜鳜鱼","红烧果子狸","徽州毛豆腐","徽州桃脂烧肉","清香炒悟鸡","生熏仔鸡","八大锤","毛峰熏鲥鱼","火烘鱼","蟹黄虾盅","奶汁肥王鱼","香炸琵琶虾","鱼咬羊","香菇盒"};
		
		for(String name:lucai){
			Dish dish = new Dish();
			dish.setName(name);
			dish.setStyle("鲁菜");
			dishDao.save(dish);
		}
		
		for(String name:chuancai){
			Dish dish = new Dish();
			dish.setName(name);
			dish.setStyle("川菜");
			dishDao.save(dish);
		}
		
		for(String name:yuecai){
			Dish dish = new Dish();
			dish.setName(name);
			dish.setStyle("粤菜");
			dishDao.save(dish);
		}
		
		for(String name:mincai){
			Dish dish = new Dish();
			dish.setName(name);
			dish.setStyle("闽菜");
			dishDao.save(dish);
		}
		
		for(String name:sucai){
			Dish dish = new Dish();
			dish.setName(name);
			dish.setStyle("苏菜");
			dishDao.save(dish);
		}
		
		for(String name:zhecai){
			Dish dish = new Dish();
			dish.setName(name);
			dish.setStyle("浙菜");
			dishDao.save(dish);
		}
		
		for(String name:xiangcai){
			Dish dish = new Dish();
			dish.setName(name);
			dish.setStyle("湘菜");
			dishDao.save(dish);
		}
		
		for(String name:huicai){
			Dish dish = new Dish();
			dish.setName(name);
			dish.setStyle("徽菜");
			dishDao.save(dish);
		}
		
	}

	@Override
	public List<String> retrieveAllDishNames() {
		return dishDao.retrieveAllDishNames();
	}

	@Override
	public Dish getById(Long id) {
		return dishDao.findById(id);
	}
	
	public IndexCache getIndexCache() {
		return indexCache;
	}

	public void setIndexCache(IndexCache indexCache) {
		this.indexCache = indexCache;
	}

	private void updateIndex(String indexName) {
		TokenIndex tokenIndex = (TokenIndex)indexCache.getIndex(IndexConstant.DISH_NAME_INDEX);
		if (tokenIndex != null) {
			tokenIndex.appendIndex(indexName);
		}
	}

	@Override
	public Pagination getAggSpotsByDish(Long dishId, int pageNo, int pageAt) {
		return dishDao.getAggSpotsByDish(dishId, pageNo, pageAt);
	}
	
	public Pagination getSuggestedDish(Long userId){
		return dishDao.getMostSpottedDishs(userId, 0, 16);
	}
	
	public Dish makeSure(String dishName){
		if(dishName==null) return null;
		Dish dish = getUniqueByProperty("name", dishName);
		if(dish == null){
			dish = new Dish();
			dish.setName(dishName);
			save(dish);
		}
		return dish;
	}

	@Override
	public List<Dish> getDishsMatchedByName(String queryStr) {
		String hql = HQLContainer.getHql("query.dish.like.name");
		List<Dish> dishList = dishDao.find(hql, "%"+queryStr+"%");
		return dishList;
	}
}
