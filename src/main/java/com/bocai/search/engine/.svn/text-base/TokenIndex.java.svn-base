package com.bocai.search.engine;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Set;

public class TokenIndex implements Index {

	private Map<String,Set<String> > indexCache;
	
	private Set<String> source;
	
	public TokenIndex() {
		indexCache = new HashMap<String,Set<String> >();
//		List<String> dishNames = dishManager.retrieveAllDishNames();
//		source = new HashSet<String>();
//		if (dishNames != null) {
//			Iterator<String> it = dishNames.iterator();
//			while(it.hasNext()) {
//				String s = it.next();
//				if (s != "" || s != null) {
//					source.add(s);
//				}
//			}
//		}
//		buildIndex();
	}
	
/*	public static TokenIndex getInstance() {
		if (instance == null) {
			instance = new TokenIndex();
		}
		return instance;
	}*/
	
	@Override
	public synchronized void buildIndex() {
		//indexCache.clear();
		if (source != null) {
			CJKAnalyzer analyzer = new CJKAnalyzer();
			Iterator<String> it = source.iterator();
			while(it.hasNext()) {
				String s = it.next();
				List<String> terms = analyzer.tokenizer(s);
				Iterator<String> iTerm = terms.iterator();
				while(iTerm.hasNext()) {
					String term = iTerm.next();
					if (!indexCache.containsKey(term)) {
						Set<String> itemSet = new HashSet<String>();
						itemSet.add(s);
						indexCache.put(term, itemSet);
					} else {
						indexCache.get(term).add(s);
					}
				}
			}

		}
	}

	@Override
	public void updateIndex() {
	}
	
	public synchronized void appendIndex(String s) {
		CJKAnalyzer analyzer = new CJKAnalyzer();
		List<String> terms = analyzer.tokenizer(s);
		Iterator<String> iTerm = terms.iterator();
		while(iTerm.hasNext()) {
			String term = iTerm.next();
			if (!indexCache.containsKey(term)) {
				Set<String> itemSet = new HashSet<String>();
				itemSet.add(s);
				indexCache.put(term, itemSet);
			} else {
				indexCache.get(term).add(s);
			}
		}
	}
	
	public void printIndexCache() {
		System.out.println("-------------------------------");
		if (indexCache != null) {
			Set<String> tokenSet = indexCache.keySet();
			Iterator<String> it = tokenSet.iterator();
			while(it.hasNext()) {
				String s = it.next();
				// can be elaborated later using more accurate algorithm 
				System.out.print(s);
				Set<String> valueSet = indexCache.get(s);
				Iterator<String> iValue = valueSet.iterator();
				while(iValue.hasNext()) {
					System.out.print(" " + iValue.next());
				}
				System.out.println();
			}
		}
		System.out.println("-------------------------------");
	}
	
	public void setSource(Set<String> source) {
		this.source = source;
	}
	
	public Set<String> getSource() {
		return source;
	}
	
	public Set<String> tokenQuery(String token) {
		Set<String> result = new HashSet<String>();
		if (indexCache != null) {
			Set<String> tokenSet = indexCache.keySet();
			Iterator<String> it = tokenSet.iterator();
			while(it.hasNext()) {
				String s = it.next();
				// can be elaborated later using more accurate algorithm 
				if (s.contains(token)) { 
					result.addAll(indexCache.get(s));
				}
			}
		}
		return result;
	}
	
	public Set<String> doQuery(String query) {
		CJKAnalyzer analyzer = new CJKAnalyzer();
		List<String> terms = analyzer.tokenizer(query);
		
		Set<String> result = null;
		Iterator<String> it = terms.iterator();
		boolean onlyOneToken = true;
		while(it.hasNext()) {
			String token = it.next();
			if (onlyOneToken) {
				result = tokenQuery(token);
				onlyOneToken = false;
			} else {
				Set<String> temp = tokenQuery(token);
				Iterator<String> iTemp = temp.iterator();
				while(iTemp.hasNext()) {
					String s = iTemp.next();
					if (result != null && !result.contains(s)) {
						iTemp.remove();
					}
				}
				result = temp;
			}
		}
		return result;
	}
	
/*	  public DishManager getDishManager() {
			return dishManager;
		}

		public void setDishManager(DishManager dishManager) {
			this.dishManager = dishManager;
		}*/


}
