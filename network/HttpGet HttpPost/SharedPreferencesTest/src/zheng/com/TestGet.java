package zheng.com;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class TestGet {
	
	public static final String TAG  = "TestGet";

	public static void getRunning(){
		new Post().start();
	}
	

}

class Get extends Thread{
	
	@Override
	public void run() {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet("http://www.google.com");
		try {
			HttpResponse httpResponse = httpClient.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();
			String result = EntityUtils.toString(httpEntity);
			Log.d(TestGet.TAG, result);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class Post extends Thread{
	
	@Override
	public void run() {
//http://fanyi.youdao.com/openapi.do?
//keyfrom=zhengwen&key=1318083984&type=data&doctype=xml&version=1.1&q=要翻译的
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost("http://fanyi.youdao.com/openapi.do");
		
		try {
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			
			parameters.add(new BasicNameValuePair("keyfrom", "zhengwen"));
			parameters.add(new BasicNameValuePair("key", "1318083984"));
			parameters.add(new BasicNameValuePair("type", "data"));
			parameters.add(new BasicNameValuePair("doctype", "xml"));
			parameters.add(new BasicNameValuePair("version", "1.1"));
			parameters.add(new BasicNameValuePair("q", "love"));
			
			post.setEntity(new UrlEncodedFormEntity(parameters, "UTF-8"));
			HttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity);
			Log.d(TestGet.TAG, result);
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}















