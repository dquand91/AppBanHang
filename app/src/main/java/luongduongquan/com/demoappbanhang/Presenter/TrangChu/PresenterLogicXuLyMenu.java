package luongduongquan.com.demoappbanhang.Presenter.TrangChu;


import android.util.Log;

import java.util.List;

import luongduongquan.com.demoappbanhang.Model.ObjectClass.LoaiSanPham;
import luongduongquan.com.demoappbanhang.MyUtils.RetrofitUtils.APIUtils;
import luongduongquan.com.demoappbanhang.MyUtils.RetrofitUtils.DataClientListener;
import luongduongquan.com.demoappbanhang.View.TrangChu.IViewXuLyMenu;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterLogicXuLyMenu implements IPresenterXuLyMenu{

	IViewXuLyMenu iViewXuLyMenu;

	public PresenterLogicXuLyMenu(IViewXuLyMenu iViewXuLyMenu){
		this.iViewXuLyMenu = iViewXuLyMenu;
	}

	@Override
	public void LayDanhSachMenu() {

		DataClientListener getLoaiSanPhamTheoCha = APIUtils.getDataRetrofit();
		Call<List<LoaiSanPham>> callback =getLoaiSanPhamTheoCha.getLoaiSanPhamTheoCha("1");
		callback.enqueue(new Callback<List<LoaiSanPham>>() {
			@Override
			public void onResponse(Call<List<LoaiSanPham>> call, Response<List<LoaiSanPham>> response) {
				final List<LoaiSanPham> loaiSanPhamList = response.body();

				int count = loaiSanPhamList.size();
				for (int i = 0; i < count; i++) {
					int maloaiSP = loaiSanPhamList.get(i).getMALOAISP();
					DataClientListener getLoaiSanPhamTheoCha = APIUtils.getDataRetrofit();
					Call<List<LoaiSanPham>> call1 = getLoaiSanPhamTheoCha.getLoaiSanPhamTheoCha(String.valueOf(maloaiSP));
					final int finalI = i;
					call1.enqueue(new Callback<List<LoaiSanPham>>() {
						@Override
						public void onResponse(Call<List<LoaiSanPham>> call, Response<List<LoaiSanPham>> response) {
							loaiSanPhamList.get(finalI).setListCon(response.body());
						}

						@Override
						public void onFailure(Call<List<LoaiSanPham>> call, Throwable t) {
							iViewXuLyMenu.FailFromGetDanhSachMenu("FAIL, can not get data Child from server --- " + t.getMessage());
						}
					});
					if(i == count-1){
						iViewXuLyMenu.HienThiDanhSachMenu(loaiSanPhamList);
					}
				}

			}

			@Override
			public void onFailure(Call<List<LoaiSanPham>> call, Throwable t) {
				iViewXuLyMenu.FailFromGetDanhSachMenu("FAIL, can not get data Father from server --- " + t.getMessage());
			}
		});

//		String dataJSON = "";
//
//		String urlLink = "http://172.18.128.58/appbanhang/loaisanpham.php";
//		HashMap<String, String> params = new HashMap<String, String>();;
//		params.put("maloaicha","1");
//		DownloadJSON downloadJSON = new DownloadJSON(urlLink, params);
//		downloadJSON.execute();
//
//		try {
//			dataJSON = downloadJSON.get();
//
//			XulyJSONMenu xulyJSONMenu = new XulyJSONMenu();
//			loaiSanPhamList = xulyJSONMenu.parserJSONMenu(dataJSON);
//			iViewXuLyMenu.HienThiDanhSachMenu(loaiSanPhamList);
//
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			e.printStackTrace();
//		}


	}


}
