package com.bocai.recommender;

import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class RecommenderEngineImpl implements RecommenderEngine {

	private DataModel model;
	
	public RecommenderEngineImpl() throws Exception {
		//DataModel model = new FileDataModel(new File("F:\\intro.csv"));
		MysqlDataSource datasource = new MysqlDataSource();
		datasource.setUser("root");
		datasource.setPassword("123456");
		datasource.setURL("jdbc:mysql://localhost:3306/bocai?useUnicode=true&amp;characterEncoding=utf-8&amp;mysqlEncoding=utf8");
		model = new MySQLJDBCDataModel(datasource,"bc_taste_preferences","user_id","item_id","preference",null);
	}
	
	@Override
	public long[] recommendSimilarUser(long userID) {
		
		long[] userList = null;
		
		try {
			UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
			
			UserNeighborhood neighborhood = new NearestNUserNeighborhood(2,similarity,model);

			userList = neighborhood.getUserNeighborhood(userID);
		} catch (TasteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i = 0; i < userList.length; i++) {
			System.out.println(userList[i]);
		}
		
		return userList;
	}

	@Override
	public List<RecommendedItem> recommendDishForUser(long userID) {
		
		List<RecommendedItem> recommendations = null;
		
		try {
			UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
			
			UserNeighborhood neighborhood = new NearestNUserNeighborhood(2,similarity,model);
			
			Recommender recommender = new GenericUserBasedRecommender(model,neighborhood,similarity);
			
			recommendations = recommender.recommend(userID, 5);
		} catch (TasteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (RecommendedItem recommendation : recommendations) {
			System.out.println(recommendation);
		}
		
		return recommendations;
	}

}
