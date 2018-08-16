package luongduongquan.com.demoappbanhang.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import luongduongquan.com.demoappbanhang.View.Fragment.FragmentChuongTrinhKhuyenMai;
import luongduongquan.com.demoappbanhang.View.Fragment.FragmentDienTu;
import luongduongquan.com.demoappbanhang.View.Fragment.FragmentLamDep;
import luongduongquan.com.demoappbanhang.View.Fragment.FragmentMeVaBe;
import luongduongquan.com.demoappbanhang.View.Fragment.FragmentNhaCuaVaDoiSong;
import luongduongquan.com.demoappbanhang.View.Fragment.FragmentNoiBat;
import luongduongquan.com.demoappbanhang.View.Fragment.FragmentTheThaoVaDuLich;
import luongduongquan.com.demoappbanhang.View.Fragment.FragmentThoiTrang;
import luongduongquan.com.demoappbanhang.View.Fragment.FragmentThuongHieu;

public class ViewPagerAdapterMain extends FragmentPagerAdapter {
	List<Fragment> listFragment = new ArrayList<Fragment>();
	List<String> titleFragment = new ArrayList<String>();

	public ViewPagerAdapterMain(FragmentManager fm) {
		super(fm);
		listFragment.add(new FragmentNoiBat());
		listFragment.add(new FragmentChuongTrinhKhuyenMai());
		listFragment.add(new FragmentDienTu());
		listFragment.add(new FragmentNhaCuaVaDoiSong());
		listFragment.add(new FragmentMeVaBe());
		listFragment.add(new FragmentLamDep());
		listFragment.add(new FragmentThoiTrang());
		listFragment.add(new FragmentTheThaoVaDuLich());
		listFragment.add(new FragmentThuongHieu());

		titleFragment.add("Nổi bật");
		titleFragment.add("Chương trình khuyến mãi");
		titleFragment.add("Điện tử");
		titleFragment.add("Nhà cửa & đời sống");
		titleFragment.add("Mẹ và bé");
		titleFragment.add("Làm đẹp");
		titleFragment.add("Thời trang");
		titleFragment.add("Thể thao & du lịch");
		titleFragment.add("Thương hiệu");
	}

	@Override
	public Fragment getItem(int position) {
		return listFragment.get(position);
	}

	@Override
	public int getCount() {
		return listFragment.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return titleFragment.get(position);
	}
}
