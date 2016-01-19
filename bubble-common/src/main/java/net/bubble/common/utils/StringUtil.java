/**
 * Copyright [2015-2017] [https://github.com/bubble-light/]
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package net.bubble.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

/**
 * 字符串基本操作
 * 
 * @author shiwen_xiao<xiaosw@msn.cn>
 * @since 2015年1月28日
 */
public class StringUtil extends StringUtils {

	public static String getMatherContent(String src, String regex, int group) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(src);
		if (m.find()) {
			return m.group(group);
		}
		return "";
	}

	public static String[] getMatherContent(String src, String regex, int startGroup, int endGroup) {
		String[] ranges = new String[endGroup - startGroup + 1];
		if (startGroup < 0 || endGroup > 9 || startGroup > 9) {
			return ranges;
		}
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(src);
		int index = 0;
		if (m.find()) {
			for (int i = startGroup; i <= endGroup; i++) {
				ranges[index] = m.group(i).trim();
				index++;
			}
		}
		return ranges;
	}
}
