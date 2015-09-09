package zheng.com;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private EditText editText = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		editText = (EditText) findViewById(R.id.editText1);
		String tmp = onLoad();
		
		if (!TextUtils.isEmpty(tmp)) {
			editText.setText(tmp);
			editText.setSelection(tmp.length());
			Toast.makeText(MainActivity.this, "load success", Toast.LENGTH_LONG).show();
		}
	}
	
	@Override
	protected void onDestroy() {
		onSave();
		super.onDestroy();
	}
	
	private void onSave(){
	
		String tmp = editText.getText().toString();
		if (!TextUtils.isEmpty(tmp)) {
			try {
				FileOutputStream fileOutputStream = openFileOutput("tmp", MODE_PRIVATE);
				OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
				BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
				bufferedWriter.write(tmp);
				
				bufferedWriter.flush();
				bufferedWriter.close();
				outputStreamWriter.close();
				fileOutputStream.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private String onLoad(){
		try {
			FileInputStream fileInputStream = openFileInput("tmp");
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			String line = "";
			StringBuffer resultBuffer = new StringBuffer();

			while ((line = bufferedReader.readLine()) != null) {
				resultBuffer.append(line);
			}
			
			bufferedReader.close();
			inputStreamReader.close();
			fileInputStream.close();
			
			return resultBuffer.toString();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
