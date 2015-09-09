import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PostTest {

	public static void main(String[] args) {
		
		new MyThread().start();
	}
}

class MyThread extends Thread{
	 
	@Override
	public void run() {
		//http://fanyi.youdao.com/openapi.do?keyfrom=zhengwen&key=1318083984&type=data&doctype=<doctype>&version=1.1&q=要翻译的
		try {
			URL url = new URL("http://fanyi.youdao.com/openapi.do?");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			connection.addRequestProperty("encoding", "UTF-8");
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			
			OutputStream outputStream = connection.getOutputStream();
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
			BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
			
			bufferedWriter.write("keyfrom=zhengwen&key=1318083984&type=data&doctype=xml&version=1.1&q=love");
			bufferedWriter.flush();
			
			InputStream inputStream = connection.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			String line = "";
			StringBuffer resultsBuffer = new StringBuffer();
			while ((line = bufferedReader.readLine()) != null) {
				resultsBuffer.append(line);
			}
			System.out.println(resultsBuffer.toString());
			
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			
			bufferedWriter.close();
			outputStreamWriter.close();
			outputStream.close();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}






