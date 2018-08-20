package luongduongquan.com.demoappbanhang.View.TrangChu;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import java.util.List;

import luongduongquan.com.demoappbanhang.Adapter.ExpandListAdapterCustom;
import luongduongquan.com.demoappbanhang.Adapter.ViewPagerAdapterMain;
import luongduongquan.com.demoappbanhang.Model.ObjectClass.LoaiSanPham;
import luongduongquan.com.demoappbanhang.Presenter.TrangChu.PresenterLogicXuLyMenu;
import luongduongquan.com.demoappbanhang.R;

public class MainActivity extends AppCompatActivity implements IViewXuLyMenu {

	Toolbar toolbar;
	private TabLayout mTabLayout;
	private ViewPager mViewPager;
	private ViewPagerAdapterMain mViewPagerAdapter;

	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle; // cái dấu 3 gạch để bấm vào sẽ show ra Drawer Layout

	ExpandableListView expandableListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		toolbar = findViewById(R.id.appbar_toolbar_main);
		mTabLayout = findViewById(R.id.tabLayout_main);
		mViewPager = findViewById(R.id.viewpager_main);
		expandableListView = findViewById(R.id.expandListViewMenu_main);

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


		PresenterLogicXuLyMenu presenterLogicXuLyMenu = new PresenterLogicXuLyMenu(this);
		presenterLogicXuLyMenu.LayDanhSachMenu();


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

	@Override
	public void HienThiDanhSachMenu(List<LoaiSanPham> loaiSanPhamList) {
		Log.d("QUAN123", "HienThiDanhSachMenu: " + loaiSanPhamList.get(0).getTENLOAISP());
		ExpandListAdapterCustom expandListAdapter = new ExpandListAdapterCustom(this, loaiSanPhamList);
		expandableListView.setAdapter(expandListAdapter);
		expandListAdapter.notifyDataSetChanged();
	}
}
