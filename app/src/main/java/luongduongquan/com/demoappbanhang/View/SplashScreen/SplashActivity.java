package luongduongquan.com.demoappbanhang.View.SplashScreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import luongduongquan.com.demoappbanhang.MainActivity;
import luongduongquan.com.demoappbanhang.R;

public class SplashActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent intentToTrangChu = new Intent(SplashActivity.this, MainActivity.class);
				startActivity(intentToTrangChu);
			}
		}, 3000);
	}

	@Override
	protected void onStop() {
		super.onStop();
		finish();
	}
}
