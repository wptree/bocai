package com.bocai.util;

import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.regex.Pattern;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.commons.lang.StringUtils;
import org.htmlparser.Node;
import org.htmlparser.lexer.Lexer;
import org.htmlparser.nodes.TextNode;
import org.htmlparser.util.ParserException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bocai.config.SystemConfig;

/**
 * 字符串的帮助类，提供静态方法，不可以实例化。
 */
public class StringUtil {
	
	public static void main(String[] args){
			String test = "<a href='/sdfa/sdf.cb?id=123123'>我的速度发到付啊第三方啊撒旦法爱上到付爱上到付爱上到付爱上到付啊撒旦法。</a>";
			System.out.println(StringUtil.htmlCut(test, 11, "...."));

	}
	
	 public static String getRandom (String[] array) {
	    	Random generator = new Random();
	        int rnd = generator.nextInt(array.length);
	        return array[rnd];
	    }
	
	//[500,2000,5000,10000]
	public static String printStarts(Integer score) throws UnsupportedEncodingException{
		//"<img src="images/star.jpg"/>"
		StringBuffer html = new StringBuffer();
		int[] levelDef = SystemConfig.getBokeLevelScoreArray();
		if(levelDef!=null){
			if(score<=levelDef[0]){
				html.append(generateImgHtml(score,levelDef[0],false,false));
				for(int i = 1; i < 4; i++){
					html.append(generateImgHtml(levelDef[i],levelDef[i],true,false));
				}
				html.append(generateImgHtml(levelDef[3],levelDef[3],true,true));
				
			}
			if(score>levelDef[0] && score<=levelDef[1]){
				html.append(generateImgHtml(levelDef[0],levelDef[0],false,false));
				html.append(generateImgHtml(score,levelDef[1],false,false));
				for(int i = 2; i < 4; i++){
					html.append(generateImgHtml(levelDef[i],levelDef[i],true,false));
				}
				html.append(generateImgHtml(levelDef[3],levelDef[3],true,true));
			}
			if(score>levelDef[1] && score<=levelDef[2]){
				html.append(generateImgHtml(levelDef[0],levelDef[0],false,false));
				html.append(generateImgHtml(levelDef[1],levelDef[1],false,false));
				html.append(generateImgHtml(score,levelDef[2],false,false));
				html.append(generateImgHtml(levelDef[3],levelDef[3],true,false));
				html.append(generateImgHtml(levelDef[3],levelDef[3],true,true));
			}
			if(score>levelDef[2] && score<=levelDef[3]){
				html.append(generateImgHtml(levelDef[0],levelDef[0],false,false));
				html.append(generateImgHtml(levelDef[1],levelDef[1],false,false));
				html.append(generateImgHtml(levelDef[2],levelDef[2],false,false));
				html.append(generateImgHtml(score,levelDef[3],false,false));
				html.append(generateImgHtml(levelDef[3],levelDef[3],true,true));
			}
			if(score>levelDef[3]){
				html.append(generateImgHtml(levelDef[0],levelDef[0],false,false));
				html.append(generateImgHtml(levelDef[1],levelDef[1],false,false));
				html.append(generateImgHtml(levelDef[2],levelDef[2],false,false));
				html.append(generateImgHtml(levelDef[3],levelDef[3],false,false));
				html.append(generateImgHtml(score,levelDef[3],false,true));
			}
			
		}
		String htmlStr = html.toString();
		html = null;
		
		return htmlStr;
	}
	
	
	private static String generateImgHtml(Integer score, int level, boolean isGray,boolean isLastOne) throws UnsupportedEncodingException{
		StringBuffer html = new StringBuffer("<img src=\"images/");
		html.append(isGray?"star_gray.jpg":"star.jpg");
		html.append("\" title=\"");
		html.append(score2LevelName(score));
		html.append(isLastOne?"，波币多于":"，波币少于");
		html.append(level);
		html.append("\"");
		html.append("/>");
		
		String htmlStr = html.toString();
		html =  null;
		return htmlStr;
		
	}
	
	
	public static String score2LevelName(Integer score) throws UnsupportedEncodingException{
		String level = "未知波客";
		int[] levelDef = SystemConfig.getBokeLevelScoreArray();
		if(levelDef!=null){
			if(score<=levelDef[0]){
				level = SystemConfig.getBokeLevelName(1);
			}
			if(score>levelDef[0] && score<=levelDef[1]){
				level = SystemConfig.getBokeLevelName(2);
			}
			if(score>levelDef[1] && score<=levelDef[2]){
				level = SystemConfig.getBokeLevelName(3);
			}
			if(score>levelDef[2] && score<=levelDef[3]){
				level = SystemConfig.getBokeLevelName(4);
			}
			if(score>levelDef[3]){
				level = SystemConfig.getBokeLevelName(5);
			}
			
		}
		return level;
	}
	
	public static String getOriginalAvatar(String avatar){
		if(avatar==null){
			avatar = "default";
		}else{
			//thirdparty user avatar
			if(avatar.startsWith("http")){
				avatar = "default";
			}else{
				avatar = avatar.replaceAll("small", "original");
			}
			
		}
		return avatar;
	}
	public static String getMiddleAvatar(String avatar){
		if(avatar==null){
			avatar = "default";
		}else{
			//thirdparty user avatar
			if(avatar.startsWith("http")){
				avatar = "default";
			}else{
				avatar = avatar.replaceAll("small", "middle");
			}
			
		}
		return avatar;
	}
	
	/**
	 * 禁止实例化
	 *
	private StringUtil() {
	}
	
	/**
	 * 得到 全拼
	 * 
	 * @param src
	 * @return
	 */
	public static String getPingYin(String src) {
		char[] t1 = null;
		t1 = src.toCharArray();
		String[] t2 = new String[t1.length];
		HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
		t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		t3.setVCharType(HanyuPinyinVCharType.WITH_V);
		String t4 = "";
		int t0 = t1.length;
		try {
			for (int i = 0; i < t0; i++) {
				// 判断是否为汉字字符
				if (java.lang.Character.toString(t1[i]).matches(
						"[\\u4E00-\\u9FA5]+")) {
					t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
					t4 += t2[0];
				} else {
					t4 += java.lang.Character.toString(t1[i]);
				}
			}
			return t4;
		} catch (BadHanyuPinyinOutputFormatCombination e1) {
			e1.printStackTrace();
		}
		return t4;
	}

	/**
	 * 得到中文首字母
	 * 
	 * @param str
	 * @return
	 */
	public static String getPinYinHeadChar(String str) {

		String convert = "";
		for (int j = 0; j < str.length(); j++) {
			char word = str.charAt(j);
			String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
			if (pinyinArray != null) {
				convert += pinyinArray[0].charAt(0);
			} else {
				convert += word;
			}
		}
		return convert;
	}

	/*
	* 对应javascript的escape()函数, 加码后的串可直接使用javascript的unescape()进行解码
	*/
	public static String escape(String src) {
	   int i;
	   char j;
	   StringBuffer tmp = new StringBuffer();
	   tmp.ensureCapacity(src.length() * 6);
	   for (i = 0; i < src.length(); i++) {
	    j = src.charAt(i);
	    if (Character.isDigit(j) || Character.isLowerCase(j)
	      || Character.isUpperCase(j))
	     tmp.append(j);
	    else if (j < 256) {
	     tmp.append("%");
	     if (j < 16)
	      tmp.append("0");
	     tmp.append(Integer.toString(j, 16));
	    } else {
	     tmp.append("%u");
	     tmp.append(Integer.toString(j, 16));
	    }
	   }
	   return tmp.toString();
	}

	/*
	* 对应javascript的unescape()函数, 可对javascript的escape()进行解码
	*/
	public static String unescape(String src) {
	   StringBuffer tmp = new StringBuffer();
	   tmp.ensureCapacity(src.length());
	   int lastPos = 0, pos = 0;
	   char ch;
	   while (lastPos < src.length()) {
	    pos = src.indexOf("%", lastPos);
	    if (pos == lastPos) {
	     if (src.charAt(pos + 1) == 'u') {
	      ch = (char) Integer.parseInt(src
	        .substring(pos + 2, pos + 6), 16);
	      tmp.append(ch);
	      lastPos = pos + 6;
	     } else {
	      ch = (char) Integer.parseInt(src
	        .substring(pos + 1, pos + 3), 16);
	      tmp.append(ch);
	      lastPos = pos + 3;
	     }
	    } else {
	     if (pos == -1) {
	      tmp.append(src.substring(lastPos));
	      lastPos = src.length();
	     } else {
	      tmp.append(src.substring(lastPos, pos));
	      lastPos = pos;
	     }
	    }
	   }
	   return tmp.toString();
	}

	/**
	 * 处理url
	 * 
	 * url为null返回null，url为空串或以http://或https://开头，则加上http://，其他情况返回原参数。
	 * 
	 * @param url
	 * @return
	 */
	public static String handelUrl(String url) {
		if (url == null) {
			return null;
		}
		url = url.trim();
		if (url.equals("") || url.startsWith("http://")
				|| url.startsWith("https://")) {
			return url;
		} else {
			return "http://" + url.trim();
		}
	}

	/**
	 * 分割并且去除空格
	 * 
	 * @param str
	 *            待分割字符串
	 * @param sep
	 *            分割符
	 * @param sep2
	 *            第二个分隔符
	 * @return 如果str为空，则返回null。
	 */
	public static String[] splitAndTrim(String str, String sep, String sep2) {
		if (StringUtils.isBlank(str)) {
			return null;
		}
		if (!StringUtils.isBlank(sep2)) {
			str = StringUtils.replace(str, sep2, sep);
		}
		String[] arr = StringUtils.split(str, sep);
		// trim
		for (int i = 0, len = arr.length; i < len; i++) {
			arr[i] = arr[i].trim();
		}
		return arr;
	}

	/**
	 * 文本转html
	 * 
	 * @param txt
	 * @return
	 */
	public static String txt2htm(String txt) {
		if (StringUtils.isBlank(txt)) {
			return txt;
		}
		StringBuilder sb = new StringBuilder((int) (txt.length() * 1.2));
		char c;
		boolean doub = false;
		for (int i = 0; i < txt.length(); i++) {
			c = txt.charAt(i);
			if (c == ' ') {
				if (doub) {
					sb.append(' ');
					doub = false;
				} else {
					sb.append("&nbsp;");
					doub = true;
				}
			} else {
				doub = false;
				switch (c) {
				case '&':
					sb.append("&amp;");
					break;
				case '<':
					sb.append("&lt;");
					break;
				case '>':
					sb.append("&gt;");
					break;
				case '"':
					sb.append("&quot;");
					break;
				case '\n':
					sb.append("<br/>");
					break;
				default:
					sb.append(c);
					break;
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 剪切文本。如果进行了剪切，则在文本后加上"..."
	 * 
	 * @param s
	 *            剪切对象。
	 * @param len
	 *            编码小于256的作为一个字符，大于256的作为两个字符。
	 * @return
	 */
	public static String textCut(String s, int len, String append) {
		if (s == null) {
			return null;
		}
		int slen = s.length();
		if (slen <= len) {
			return s;
		}
		// 最大计数（如果全是英文）
		int maxCount = len * 2;
		int count = 0;
		int i = 0;
		for (; count < maxCount && i < slen; i++) {
			if (s.codePointAt(i) < 256) {
				count++;
			} else {
				count += 2;
			}
		}
		if (i < slen) {
			if (count > maxCount) {
				i--;
			}
			if (!StringUtils.isBlank(append)) {
				if (s.codePointAt(i - 1) < 256) {
					i -= 2;
				} else {
					i--;
				}
				return s.substring(0, i) + append;
			} else {
				return s.substring(0, i);
			}
		} else {
			return s;
		}
	}

	public static String htmlCut(String s, int len, String append) {
		String text = html2Text(s, len * 2);
		return textCut(text, len, append);
	}

	public static String html2Text(String html, int len) {
		try {
			Lexer lexer = new Lexer(html);
			Node node;
			StringBuilder sb = new StringBuilder(html.length());
			while ((node = lexer.nextNode()) != null) {
				if (node instanceof TextNode) {
					sb.append(node.toHtml());
				}
				if (sb.length() > len) {
					break;
				}
			}
			return sb.toString();
		} catch (ParserException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 检查字符串中是否包含被搜索的字符串。被搜索的字符串可以使用通配符'*'。
	 * 
	 * @param str
	 * @param search
	 * @return
	 */
	public static boolean contains(String str, String search) {
		if (StringUtils.isBlank(str) || StringUtils.isBlank(search)) {
			return false;
		}
		String reg = StringUtils.replace(search, "*", ".*");
		Pattern p = Pattern.compile(reg);
		return p.matcher(str).matches();
	}
	
	/**
     * 判断是否为空
     */
	public static boolean isNULL(String value) {
		return value == null || value.equals("") || value.length() < 1;
	}
	
	/**
	 * 提供把字符串转为整数
	 * 
	 * @param value
	 *            转换的数字
	 * @return 四舍五入后的结果
	 */
	public static int toInt(String value) {
		if ("true".equalsIgnoreCase(value) || "t".equalsIgnoreCase(value))
			return 1;
		if ("false".equalsIgnoreCase(value) || "f".equalsIgnoreCase(value))
			return 0;
		return toInt(value, 0);
	}
	
	/**
	 * 提供把字符串转为整数
	 * 
	 * @param value
	 *            转换的数字
	 * @param nint
	 *            空备用返回
	 * @return 四舍五入后的结果
	 */
	public static int toInt(String value, int nint) {
		if (value == null)
			return nint;
		if (!StringUtil.isStandardNumber(value))
			return nint;
		if (value.indexOf(".") != -1) {
			try {
				return (int) Double.parseDouble(value);
			} catch (NumberFormatException e) {
				return nint;
			}
		}
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return nint;
		}
	}
	
	/**
     * <p>是否为一个标准的数学数字Checks whether the String a valid Java number.</p>
     * <p/>
     * <p>Valid numbers include hexadecimal marked with the <code>0x</code>
     * qualifier, scientific notation and numbers marked with a type
     * qualifier (e.g. 123L).</p>
     * <p/>
     * <p><code>Null</code> and empty String will return
     * <code>false</code>.</p>
     *
     * @param str the <code>String</code> to check
     * @return <code>true</code> if the string is a correctly formatted number
     */
    public static boolean isStandardNumber(String str) {
        if (isNULL(str)) {
            return false;
        }
        char[] chars = str.toCharArray();
        int sz = chars.length;
        boolean hasExp = false;
        boolean hasDecPoint = false;
        boolean allowSigns = false;
        boolean foundDigit = false;
        // deal with any possible sign up front
        int start = (chars[0] == '-') ? 1 : 0;
        if (sz > start + 1) {
            if (chars[start] == '0' && chars[start + 1] == 'x') {
                int i = start + 2;
                if (i == sz) {
                    return false; // str == "0x"
                }
                // checking hex (it can't be anything else)
                for (; i < chars.length; i++) {
                    if ((chars[i] < '0' || chars[i] > '9')
                            && (chars[i] < 'a' || chars[i] > 'f')
                            && (chars[i] < 'A' || chars[i] > 'F')) {
                        return false;
                    }
                }
                return true;
            }
        }
        sz--; // don't want to loop to the last char, check it afterwords
        // for type qualifiers
        int i = start;
        // loop to the next to last char or to the last char if we need another digit to
        // make a valid number (e.g. chars[0..5] = "1234E")
        while (i < sz || (i < sz + 1 && allowSigns && !foundDigit)) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                foundDigit = true;
                allowSigns = false;

            } else if (chars[i] == '.') {
                if (hasDecPoint || hasExp) {
                    // two decimal points or dec in exponent
                    return false;
                }
                hasDecPoint = true;
            } else if (chars[i] == 'e' || chars[i] == 'E') {
                // we've already taken care of hex.
                if (hasExp) {
                    // two E's
                    return false;
                }
                if (!foundDigit) {
                    return false;
                }
                hasExp = true;
                allowSigns = true;
            } else if (chars[i] == '+' || chars[i] == '-') {
                if (!allowSigns) {
                    return false;
                }
                allowSigns = false;
                foundDigit = false; // we need a digit after the E
            } else {
                return false;
            }
            i++;
        }
        if (i < chars.length) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                // no type qualifier, OK
                return true;
            }
            if (chars[i] == 'e' || chars[i] == 'E') {
                // can't have an E at the last byte
                return false;
            }
            if (!allowSigns
                    && (
                    chars[i] == 'd'
                            || chars[i] == 'D'
                            || chars[i] == 'f'
                            || chars[i] == 'F')) {
                return foundDigit;
            }
            if (chars[i] == 'l'
                    || chars[i] == 'L') {
                // not allowing L with an exponent
                return foundDigit && !hasExp;
            }
            // last zhex is illegal
            return false;
        }
        // allowSigns is true iff the val ends in 'E'
        // found digit it to make sure weird stuff like '.' and '1E-' doesn't pass
        return !allowSigns && foundDigit;
    }

}
