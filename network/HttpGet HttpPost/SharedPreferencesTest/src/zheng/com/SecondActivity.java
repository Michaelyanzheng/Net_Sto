package zheng.com;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_activity);
		
		findViewById(R.id.btnHttpGet).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				TestGet.getRunning();
			}
		});
		
	}
}
