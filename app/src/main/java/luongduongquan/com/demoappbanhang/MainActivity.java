package luongduongquan.com.demoappbanhang;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import luongduongquan.com.demoappbanhang.Adapter.ViewPagerAdapterMain;

public class MainActivity extends AppCompatActivity {

	Toolbar toolbar;
	private TabLayout mTabLayout;
	private ViewPager mViewPager;
	private ViewPagerAdapterMain mViewPagerAdapter;

	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle; // cái dấu 3 gạch để bấm vào sẽ show ra Drawer Layout

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		toolbar = findViewById(R.id.appbar_toolbar_main);

		mTabLayout = findViewById(R.id.tabLayout_main);
		mViewPager = findViewById(R.id.viewpager_main);

		toolbar.setTitle("");
		setSupportActionBar(toolbar);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		mDrawerLayout = findViewById(R.id.drawerLayout_main);
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,  R.string.open_drawer, R.string.close_drawer);
		mDrawerLayout.addDrawerListener(mDrawerToggle); // gán cái toggle cho DrawerLayout, để khi bấm vào Toggle sẽ show ra cái DrawerLayout.
		mDrawerToggle.syncState();

		mViewPagerAdapter = new ViewPagerAdapterMain(getSupportFragmentManager());
		mViewPager.setAdapter(mViewPagerAdapter);
		mTabLayout.setTabTextColors(Color.BLACK, Color.YELLOW);

		mTabLayout.setupWithViewPager(mViewPager);


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menutrangchu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(mDrawerToggle.onOptionsItemSelected(item)){
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
