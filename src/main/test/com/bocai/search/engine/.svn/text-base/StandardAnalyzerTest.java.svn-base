package com.bocai.search.engine;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.junit.Test;

public class StandardAnalyzerTest {

	public StandardAnalyzerTest() {
	}

	public static void main(String[] args) {
		// 生成一个StandardAnalyzer对象
		Analyzer aAnalyzer = new CJKAnalyzer();
		// 测试字符串
		StringReader sr = new StringReader(
				"海洋石油总公司（以下简称海油）是最大的国家石油公司之一，" +
				"负责在海域对外合作开采海洋石油及天然气资源，是最大的海上油气生产商。" +
				"公司成立于1982年，注册资本949亿元人民币，总部位于北京，现有员工5.3万人  ");

		// 生成TokenStream对象
		TokenStream ts = aAnalyzer.tokenStream("name", sr);
		try {
			int i = 0;
			Token t = ts.next();
			while (t != null) {
				// 辅助输出时显示行号
				i++;
				// 输出处理后的字符
				System.out.println("第" + i + "行: " + t.termText());
				// 取得下一个字符
				t = ts.next();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	@Test
//	public void testTokenIndex() {
//		TokenIndex index = TokenIndex.getInstance();
//		Set<String> source = new HashSet<String>();
//		source.add("西湖醋鱼");
//		source.add("西湖莲藕");
//		source.add("西湖东坡肉");
//		source.add("千岛湖有机鱼头");
//		index.setSource(source);
//		index.buildIndex();
//		
//		index.printIndexCache();
//		
//		Set<String> tokenResult = index.doQuery("西湖");
//		Iterator<String> it = tokenResult.iterator();
//		while(it.hasNext()) {
//			String result = it.next();
//			System.out.println(result);
//		}
//	}
}
