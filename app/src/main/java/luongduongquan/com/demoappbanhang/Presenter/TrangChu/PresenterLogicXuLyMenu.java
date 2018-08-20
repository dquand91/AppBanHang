package luongduongquan.com.demoappbanhang.Presenter.TrangChu;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import luongduongquan.com.demoappbanhang.Model.ObjectClass.LoaiSanPham;
import luongduongquan.com.demoappbanhang.Model.TrangChu.XuLyMenu.XulyJSONMenu;
import luongduongquan.com.demoappbanhang.MyUtils.DownloadJSON;
import luongduongquan.com.demoappbanhang.View.TrangChu.IViewXuLyMenu;

public class PresenterLogicXuLyMenu implements IPresenterXuLyMenu {

	IViewXuLyMenu iViewXuLyMenu;

	public PresenterLogicXuLyMenu(IViewXuLyMenu iViewXuLyMenu){
		this.iViewXuLyMenu = iViewXuLyMenu;
	}

	@Override
	public void LayDanhSachMenu() {

		List<LoaiSanPham> loaiSanPhamList;

		String dataJSON = "";

		String urlLink = "http://172.18.128.58/appbanhang/loaisanpham.php";
		HashMap<String, String> params = new HashMap<String, String>();;
		params.put("maloaicha","1");
		DownloadJSON downloadJSON = new DownloadJSON(urlLink, params);
		downloadJSON.execute();

		try {
			dataJSON = downloadJSON.get();

			XulyJSONMenu xulyJSONMenu = new XulyJSONMenu();
			loaiSanPhamList = xulyJSONMenu.parserJSONMenu(dataJSON);
			iViewXuLyMenu.HienThiDanhSachMenu(loaiSanPhamList);

		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}


	}
}
