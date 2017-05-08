package com.xy.yiyao.util.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

/**
 * <p>
 * HttpClient 工具类
 * </p>
 *
 * @author heyin
 * @version 1.0
 * @since [产品/模块版本] （可选）
 */
public class HttpClientUtils {

	private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

	public static String toGet(String urlWithParams) throws IOException {
		return toGet(urlWithParams, null, "UTF-8");
	}

	public static String toGet(String urlWithParams, String encoding) throws IOException {
		return toGet(urlWithParams, null, encoding);
	}

	public static String toGet(String url, Map<String, String> lParams, String encoding) throws IOException {
		CloseableHttpClient httpClient = createDefault();
		StringBuilder urlWithParams = new StringBuilder(url);
		if (lParams != null && lParams.size() > 0) {
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			for (String key : lParams.keySet()) {
				nvps.add(new BasicNameValuePair(key, lParams.get(key)));
			}
			urlWithParams.append("?");
			try {
				urlWithParams.append(EntityUtils.toString(new UrlEncodedFormEntity(nvps, "UTF-8")));
			} catch (IOException e) {
				logger.error("请求参数转换异常：" + e.getLocalizedMessage());
				throw new IOException(e);
			}
		}
		HttpGet httpGet = new HttpGet(urlWithParams.toString());
		httpGet.setHeader(HTTP.CONTENT_ENCODING, encoding);
		// 设置超时 60s
		httpGet.setConfig(getTimeOutConfig(60000));
		
		CloseableHttpResponse response = null;
		String result = null;
		try {
			response = httpClient.execute(httpGet);
			response.setHeader(HTTP.CONTENT_ENCODING, encoding);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				logger.debug("响应状态码:" + response.getStatusLine());
				result = EntityUtils.toString(entity);
				logger.debug("响应结果：" + result);
			}
		} catch (IOException e) {
			logger.error("请求参数转换异常：" + e.getLocalizedMessage());
			throw new IOException(e);
		} finally {
			if (response != null) {
				response.close();
			}
			if (httpClient != null) {
				httpClient.close();
			}
		}
		return result;
	}

	public static String toPost(String url, Map<String, String> map) throws IOException {
		return toPost(url, map, "UTF-8");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String toPost(String url, Map<String, String> map, String charset) throws IOException {
		CloseableHttpClient httpClient = null;
		HttpPost httpPost = null;
		CloseableHttpResponse response = null;
		String result = null;
		try {
			httpClient = createDefault();
			httpPost = new HttpPost(url);
			// 设置超时 10s
			httpPost.setConfig(getTimeOutConfig(10000));
			// 设置参数
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			Iterator iterator = map.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<String, String> elem = (Entry<String, String>) iterator.next();
				list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
			}
			if (list.size() > 0) {
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
				httpPost.setEntity(entity);
			}
			response = httpClient.execute(httpPost);
			if (response != null) {
				response.setHeader(HTTP.CONTENT_ENCODING, charset);
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					logger.debug("响应状态码:" + response.getStatusLine());
					result = EntityUtils.toString(resEntity, charset);
					logger.debug("响应结果：" + result);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (response != null) {
				response.close();
			}
			if (httpClient != null) {
				httpClient.close();
			}
		}
		return result;
	}

	/**
	 * <p>
	 * JSON格式，Post
	 * </p>
	 *
	 * @param url
	 *            地址
	 * @param jsonObj
	 * @return
	 * @throws IOException
	 */
	public static String toJsonPost(String url, JSONObject jsonObj) throws IOException {
		return toJsonPost(url, jsonObj, "UTF-8", 10000);
	}

	/**
	 * <p>
	 * JSON格式，Post (支持超时时常设置)
	 * </p>
	 *
	 * @param url
	 *            地址
	 * @param jsonObj
	 *            JSON参数对象
	 * @param timeout
	 *            超时时长（单位毫秒）
	 * @return
	 * @throws IOException
	 */
	public static String toJsonPost(String url, JSONObject jsonObj, int timeout) throws IOException {
		return toJsonPost(url, jsonObj, "UTF-8", timeout);
	}

	/**
	 * <p>
	 * JSON格式Post
	 * </p>
	 *
	 * @param url
	 *            地址
	 * @param param
	 *            jsonObj对象参数
	 * @param charset
	 *            字符编码
	 * @return
	 * @throws IOException
	 */
	public static String toJsonPost(String url, JSONObject jsonObj, String charset, int timeout) throws IOException {
		CloseableHttpClient httpClient = null;
		HttpPost httpPost = null;
		CloseableHttpResponse response = null;
		String result = null;
		try {
			httpClient = createDefault();
			httpPost = new HttpPost(url);
			// 设置超时 10s
			httpPost.setConfig(getTimeOutConfig(timeout));
			// 设置参数
			if (jsonObj != null) {
				StringEntity entity = new StringEntity(jsonObj.toString(), charset);// 解决中文乱码问题
				entity.setContentEncoding(charset);
				entity.setContentType("application/json");
				httpPost.setEntity(entity);
			}
			response = httpClient.execute(httpPost);
			if (response != null) {
				response.setHeader(HTTP.CONTENT_ENCODING, charset);
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					logger.debug("响应状态码:" + response.getStatusLine());
					result = EntityUtils.toString(resEntity, charset);
					logger.debug("响应结果：" + result);
				}
			}
		} catch (Exception ex) {
			logger.error("调用httpClient发送toJsonPost发生异常", ex.getMessage(), ex);
			throw new RuntimeException(ex);
		} finally {
			if (response != null) {
				response.close();
			}
			if (httpClient != null) {
				httpClient.close();
			}
		}
		return result;
	}

	/**
	 * 创建Default httpclient
	 * 
	 * @return
	 */
	private static CloseableHttpClient createDefault() {
		return HttpClients.createDefault();
	}

	/**
	 * <p>
	 * 获取超时配置
	 * </p>
	 *
	 * @return
	 */
	private static RequestConfig getTimeOutConfig(int timeout) {
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(4000).setConnectionRequestTimeout(1000)
				.setSocketTimeout(timeout).build();
		// httpGet.setConfig(requestConfig);
		return requestConfig;
	}
}
