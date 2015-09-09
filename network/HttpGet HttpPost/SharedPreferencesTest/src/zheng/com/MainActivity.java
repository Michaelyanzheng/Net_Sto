package zheng.com;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	private SharedPreferences sharedPreferences;
	private SharedPreferences.Editor editor;
	
	private EditText etName;
	private EditText etPawd;
	
	private CheckBox cbRemember;
	private Button btnLogin;
	
	private boolean isRemember = false; 
	
	private static final String USERNAME = "userName";
	private static final String USERPAWD = "userPawd";
	private static final String IS_REMEMBER = "isRemember";
	private static final String FILE_SHAREDPREFERENCE = "shadow";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		etName = (EditText) findViewById(R.id.etName);
		etPawd = (EditText) findViewById(R.id.etPawd);
		
		cbRemember = (CheckBox) findViewById(R.id.cbRemenber);
		btnLogin = (Button) findViewById(R.id.btnLogin);
		btnLogin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String name = etName.getText().toString();
				String pawd = etPawd.getText().toString();
				
				if (name.equals("zheng") && pawd.equals("123456")) {
					
					if (cbRemember.isChecked()) {
						
						save(name, pawd);
					}else {
						editor = sharedPreferences.edit();
						editor.clear();
						editor.commit();
					}
					Intent intent = new Intent(MainActivity.this,SecondActivity.class);
					startActivity(intent);
				}
			}
		});
		
		sharedPreferences = getSharedPreferences(FILE_SHAREDPREFERENCE, MODE_PRIVATE);
		isRemember = sharedPreferences.getBoolean(IS_REMEMBER, false);
		if (isRemember) {
			String name = sharedPreferences.getString(USERNAME, "");
			String pawd = sharedPreferences.getString(USERPAWD, "");
			etName.setText(name);
			etPawd.setText(pawd);
			cbRemember.setChecked(true);
		}
	}
	
	private void save(String name,String pawd){
		sharedPreferences = getSharedPreferences("shadow", MODE_PRIVATE);
		editor = sharedPreferences.edit();
		editor.putString(USERNAME, name);
		editor.putString(USERPAWD, pawd);
		editor.putBoolean(IS_REMEMBER, true);
		editor.commit();
	}
	
	
	
}
