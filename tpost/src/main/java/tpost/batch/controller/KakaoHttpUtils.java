package tpost.batch.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException; 
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



public class KakaoHttpUtils {
	
	private int httpStatusCd;
	private String respStatusCd;
	
	
	public KakaoHttpUtils() {
		
		System.out.println(" KakaoHttpUtils ----> [1]");
	}
	/**
	
	 * @throws URISyntaxException, ClientProtocolException, ClientProtocolException, IOException
	 */
	public HttpResponse sendToKakao(String uri, String method, String apikey, Map<String,Object> paramMap ) throws URISyntaxException, ClientProtocolException, ClientProtocolException, IOException{
		
		HttpResponse response = null;
		
		try(final CloseableHttpClient httpClient = getHttpClent()){
			
			System.out.println("sendToKakao 전송요청----> [1] ==> " + uri);

			try {
			
				if( "GET".equals(method.toUpperCase()) )
				{
					System.out.println("sendToKakao 전송요청----> [get] ==> " + uri);
					response = doGet(uri, apikey, httpClient, paramMap );
				}else if( "POST".equals(method.toUpperCase()) ) {
					System.out.println("sendToKakao 전송요청----> [post] ==> " + uri);
					response = doPost(uri, apikey, httpClient, paramMap );
				}
			}finally {
				System.out.println("sendToKakao 전송요청----> [3] ==> " + uri);
				httpClient.close();
			}
			System.out.println("sendToKakao 전송요청----> [end] ==> " + uri);
			httpClient.close();
		}
		
		return response;
	}
	
	/**
	
	 * @return HttpResponse
	 * @throws IOException
	 */
	public HttpResponse doPost(String domain, String apiKey, CloseableHttpClient client, Map<String,Object> params) throws IOException{
		
		HttpPost httpPost = new HttpPost( domain );
		
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectionRequestTimeout(10000)
				.setConnectTimeout(10000)
				.setSocketTimeout(10000)
				.build();
		
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
		httpPost.addHeader("Cache-Control", "no-cache");
		httpPost.addHeader("Authorization", "Bearer " + apiKey);
		
		System.out.println("doPost[1] ==> " + domain); 
		httpPost.setConfig(requestConfig);
				
		httpPost.setEntity(getUrlEncodedFormEntity(params));
		 
		System.out.println("doPost[2] ==> " + domain); 
		HttpResponse resp = client.execute(httpPost);
		System.out.println("doPost[3] ==> " + domain); 

		return resp;
		
	}
	
	/**
	
	 * @return HttpResponse
	 * @throws IOException
	 */
	public HttpResponse doGet(String domain, String apiKey, CloseableHttpClient client, Map<String,Object> params) throws IOException{
		
		/*
		 * getMethod�� parameter�� ����� 
		 */
		StringBuilder paramsBuilder = new StringBuilder(domain);
		
		if (domain.indexOf("?") == -1) {
            paramsBuilder.append("?");
        }
		paramsBuilder.append(makeParameterString(params));
		
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectionRequestTimeout(10000)
				.setConnectTimeout(10000)
				.setSocketTimeout(10000)
				.build();
		
		HttpGet httpGet = new HttpGet(paramsBuilder.toString());
		
		httpGet.addHeader("Content-Type", "application/x-www-form-urlencoded");
		httpGet.addHeader("Cache-Control", "no-cache");
		httpGet.addHeader("Authorization", "Bearer " + apiKey);

		httpGet.setConfig(requestConfig);

		HttpResponse resp = client.execute(httpGet);
		
		//setHttpStatusCd(resp);

		return resp;
	}
	
		
	/**
	
	 * @throws IOException
	 */
	public String  makeParameterString( Map<String,Object> params ) throws IOException {

		List<BasicNameValuePair> arryPairList = new ArrayList<BasicNameValuePair>();

		if( params == null ) return "" ;
	
		BasicNameValuePair nvPair =  null; 
		arryPairList = new ArrayList<BasicNameValuePair>();
		
		for (Iterator<String> i = params.keySet().iterator() ; i.hasNext();){
			String key = i.next();
			nvPair = new BasicNameValuePair( key , String.valueOf(params.get(key)));
			arryPairList.add( nvPair);

		}
		
		String paramsStr = EntityUtils.toString(new UrlEncodedFormEntity(arryPairList,"UTF-8"));
	
		return paramsStr;
	}

	/**

	 * @throws UnsupportedEncodingException
	 */
    private UrlEncodedFormEntity getUrlEncodedFormEntity(Map<String, Object> params) throws UnsupportedEncodingException {
    	
    	System.out.println("param :" + params.toString());
    	
        if (params != null) {
        	
            List<NameValuePair> list = new ArrayList<>();

            Set<String> keySet = params.keySet();
            Iterator<String> iterator = keySet.iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                list.add(new BasicNameValuePair(key, String.valueOf(params.get(key))));
                
                System.out.println("key:"+key + "  value:" + String.valueOf(params.get(key)));
            }
            
            System.out.println(list.toString());
            return new UrlEncodedFormEntity(list,"UTF-8");
        }
        return null;
    }
	
    /**
	 * httpClient�� �����ؼ� return �Ѵ�  
	 * @return CloseableHttpClient
	 * @throws IOException
	 */
	public CloseableHttpClient getHttpClent() throws IOException{
		return HttpClients.createDefault();
	}
	
	/**

	 * @return RecvData
	 * @throws IOException, Exception
	 */ 
	public RecvData getRecvData (HttpResponse response) throws IOException, Exception {
		
		String result="";
		String tmp=null;
		
		try (BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(response.getEntity().getContent()))) { 
			 while((tmp=bufferedReader.readLine())!=null){
              result+=tmp;
             }
             
             RecvData   recv = new RecvData();
     		 JSONObject json = (JSONObject) new JSONParser().parse(result);
     		 recv.setErrcode((String) json.get("errcode"));
     		 recv.setErrmsg((String) json.get("errmsg"));
     		 recv.setData(json.get("data"));
     		 
     		 bufferedReader.close();
 
     		 return recv;
		}
	}

	/**

	 * @param response HTTP response  
	 */ 
	public void setHttpStatusCd( HttpResponse response ) {
		this.httpStatusCd = response.getStatusLine().getStatusCode();
	}
	
	/**
	
	 * @param response HTTP response  
	 */
	public int getHttpStatusCd( HttpResponse response ) {
		return response.getStatusLine().getStatusCode();
	}
	
	
	/**
	
	 * @param recv RecvData  
	 * @return Object 
	 */
	public Object getResponseData( RecvData recv ) {
		return recv.getData();
	}
	
	/**
	
	 * @param recv RecvData  
	 * @return Object 
	 */
	public String getResponseCd( RecvData recv ) {
		return recv.getErrcode();
	}
	
	/**
	
	 * @param recv RecvData  
	 * @return Object 
	 */
	public String getResponseMsg( RecvData recv ) {
		return recv.getErrmsg();
	}
	
	/**
	 */ 
	public int getHttpStatusCd() {
		return httpStatusCd;
	}

	/**
	 * @param httpStatusCd  
	 */
	public void setHttpStatusCd(int httpStatusCd) {
		this.httpStatusCd = httpStatusCd;
	}

	/**
	 */ 
	public String getRespStatusCd() {
		return respStatusCd;
	}

	/**
	 * @param respStatusCd  
	 */
	public void setRespStatusCd(String respStatusCd) {
		this.respStatusCd = respStatusCd;
	}

	/**
	 * @param httpStatusCd   
	 */ 
	//public abstract void httpErrorHandler(int httpStatusCd);
	
	
	/**
	 * @param httpStatusCd   
	 */ 
	//public abstract void respErrorHandler(String respStatusCd);
}
