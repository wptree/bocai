package com.bocai.search.engine;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;

public class CJKAnalyzer extends Analyzer {// 实现了Analyzer接口，这是lucene的要求

	public final static String[] STOP_WORDS = {};

	private Set stopTable;

	public CJKAnalyzer() {
		stopTable = StopFilter.makeStopSet(STOP_WORDS);
	}

	@Override
	public TokenStream tokenStream(String fieldName, Reader reader) {
		return new StopFilter(new CJKTokenizer(reader), stopTable);
	}
	
	public List<String> tokenizer(String expr) {
		
		List<String> terms = new LinkedList<String>();
		StringReader sr = new StringReader(expr);
		// 生成TokenStream对象
		TokenStream ts = tokenStream("name", sr);
		try {
			int i = 0;
			Token t = ts.next();
			while (t != null) {
				// 辅助输出时显示行号
				//i++;
				// 输出处理后的字符
				//System.out.println("第" + i + "行: " + t.termText());
				// 取得下一个字符
				terms.add(t.termText());
				t = ts.next();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return terms;
	}
	
}