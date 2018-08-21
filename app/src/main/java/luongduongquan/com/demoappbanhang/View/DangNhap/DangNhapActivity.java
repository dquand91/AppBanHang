package luongduongquan.com.demoappbanhang.View.DangNhap;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import luongduongquan.com.demoappbanhang.Adapter.ViewPagerAdapterDangNhap;
import luongduongquan.com.demoappbanhang.R;

public class DangNhapActivity extends AppCompatActivity {

	TabLayout tabLayout;
	ViewPager viewPager;

	Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dang_nhap);

		tabLayout = findViewById(R.id.tabLayout_dangnhap);
		viewPager = findViewById(R.id.viewpager_dangnhap);
		toolbar = findViewById(R.id.appbar_toolbar_dangnhap);

		setSupportActionBar(toolbar);

		ViewPagerAdapterDangNhap viewPagerAdapterDangNhap = new ViewPagerAdapterDangNhap(getSupportFragmentManager());
		viewPager.setAdapter(viewPagerAdapterDangNhap);
		tabLayout.setTabTextColors(Color.BLACK, Color.YELLOW);

		tabLayout.setupWithViewPager(viewPager);

	}
}
