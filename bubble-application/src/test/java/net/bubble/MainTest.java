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
package net.bubble;

import net.bubble.common.utils.StringUtil;

/**
 * @author shiwen_xiao<xiaosw@msn.cn>
 * @since 2015年12月23日
 */
public class MainTest {

	public static void main(String[] args) {
		/*
		 * System.out.println(DateUtil.getDate("dd/MMM/yyyy:HH:mm:ss Z", "yyyy-MM-dd HH:mm:ss",
		 * "21/Dec/2015:00:32:27 +0800",Locale.US,Locale.CHINESE));
		 */

		/*
		 * MultiMap<String> map = new MultiMap<String>(); UrlEncoded.decodeTo(
		 * "http://www.houpix.com/ad/dream/?ref=1450661080_223.71.129.4_1923469611&shareTime=1450661175985&from=timeline&isappinstalled=0",
		 * map, CharsetUtil.CHARSET_GBK); System.out.println(map);
		 */
		
		
		/*String content = "Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Mobile/13B143 MicroMessenger/6.3.8 NetType/WIFI Language/zh_CN";
		content.substring(content.indexOf("("), content.indexOf(")"));
		System.out.println(content.substring(content.indexOf("(")+1, content.indexOf(")")));
		
		content = "Mozilla/5.0 (Linux; U; Android 4.1.2; zh-cn; SM-G3508 Build/JZO54K) AppleWebKit/533.1 (KHTML, like Gecko)Version/4.0 MQQBrowser/5.4 TBS/025483 Mobile Safari/533.1 MicroMessenger/6.2.5.53_r2565f18.621 NetType/WIFI Language/zh_CN";
		content.substring(content.indexOf("("), content.indexOf(")"));
		System.out.println(content.substring(content.indexOf("(")+1, content.indexOf(")")));*/
		String line = "180.213.170.112 - - [16/Jan/2016:08:24:09 +0800] \"GET /mi/images/qrcode.jpg HTTP/1.1\" 200 25654 \"http://share.houpix.com/mi/share.html?id=180451&uid=1452873505_121.26.4.164_646899438&shareTime=1452873741487&shareTo=timeline&from=timeline&isappinstalled=0\" \"Mozilla/5.0 (Linux; U; Android 4.4.2; zh-cn; Che2-TL00 Build/HonorChe2-TL00) AppleWebKit/533.1 (KHTML, like Gecko)Version/4.0 MQQBrowser/5.4 TBS/025488 Mobile Safari/533.1 MicroMessenger/6.3.5.50_r1573191.640 NetType/WIFI Language/zh_CN\"";
		String regex = "([\\d]+.[\\d]+.[\\d]+.[\\d]+) - - \\[([\\s\\S]+)\\] \"([\\S]+) [^\"]*\" ([\\d]{3}) ([\\d]+) \"([^\"]*)\" \"([^\"]*)\"";
		
		String[] values = StringUtil.getMatherContent(line, regex, 1,9);
		for(String val : values){
			System.out.println(val);
		}
	}
}
