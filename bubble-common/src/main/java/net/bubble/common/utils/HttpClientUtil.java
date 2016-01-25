package net.bubble.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientUtil {

	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

	/**
	 * 下载文件
	 * 
	 * @param url
	 *            文件源
	 * @param userName
	 *            用户名
	 * @param password
	 *            密码
	 * @param localDst
	 *            本地目的文件路径
	 */
	public static void downLoadFile(String url, String userName, String password, String localDst) {
		HttpClient client = new HttpClient();
		HttpMethod method = new GetMethod(url);
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			client.getParams().setAuthenticationPreemptive(true);
			if (!StringUtil.isEmpty(userName) && !StringUtil.isEmpty(password)) {
				Credentials credentials = new UsernamePasswordCredentials(userName, password);
				client.getState().setCredentials(AuthScope.ANY, credentials);
			}
			int statusCode = client.executeMethod(method);
			if (statusCode != HttpStatus.SC_OK) {
				logger.warn("WARN: download file error,status code is {}.", statusCode);
				return;
			}
			String path = method.getPath();
			String fileName = path.substring(path.lastIndexOf("/") + 1, path.length());
			InputStream is = method.getResponseBodyAsStream();
			bis = new BufferedInputStream(is);
			File parentFolder = new File(localDst);
			if (!parentFolder.exists())
				parentFolder.mkdirs();
			File dstFile = new File(parentFolder,fileName);
			bos = new BufferedOutputStream(new FileOutputStream(dstFile));
			byte[] bytes = new byte[1024];
			while (bis.read(bytes) != -1) {
				bos.write(bytes);
			}
			logger.info("Download file successful,file path is:{}", dstFile.getAbsolutePath());
		} catch (IOException e) {
			logger.warn(e.getMessage(), e.getCause());
		} catch (Exception e) {
			logger.warn(e.getMessage(), e.getCause());
		} finally {
			try {
				if (bis != null) {
					bis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (bos != null) {
					bos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			method.releaseConnection();
		}
	}

	/*
	 * public static void main(String[] args) { String url = "https://api.houpix.com/houpix-logs/2016-01-18-access.log"; downLoadFile(url,
	 * "logger", "Asv7HPHhLVzHolPF", "D:/test"); }
	 */
}
