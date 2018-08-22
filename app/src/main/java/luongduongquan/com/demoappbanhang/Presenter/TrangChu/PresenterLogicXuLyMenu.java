package luongduongquan.com.demoappbanhang.Presenter.TrangChu;


import com.facebook.AccessToken;

import java.util.List;

import luongduongquan.com.demoappbanhang.Model.DangNhap.ModelDangNhap;
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

		// Ở đây là do cái menu Loai San Pham là menu đa cấp (ở đây là 3 cấp) Cha - Con - Cháu
		// Nên ở đây mình sẽ chạy API 3 lần để lấy được hết các list San Pham con.

		DataClientListener getLoaiSanPhamTheoCha = APIUtils.getDataRetrofit();
		Call<List<LoaiSanPham>> callback =getLoaiSanPhamTheoCha.getLoaiSanPhamTheoCha("0");
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

							final List<LoaiSanPham> loaiSanPhamListCon = response.body();

							int count = loaiSanPhamListCon.size();
							for (int j = 0; j < count; j++) {
								int maloaiSP = loaiSanPhamListCon.get(j).getMALOAISP();
								DataClientListener getLoaiSanPhamTheoCha = APIUtils.getDataRetrofit();
								Call<List<LoaiSanPham>> call2 = getLoaiSanPhamTheoCha.getLoaiSanPhamTheoCha(String.valueOf(maloaiSP));
								final int finalJ = j;
								call2.enqueue(new Callback<List<LoaiSanPham>>() {
									@Override
									public void onResponse(Call<List<LoaiSanPham>> call, Response<List<LoaiSanPham>> response) {
										loaiSanPhamListCon.get(finalJ).setListCon(response.body());
										iViewXuLyMenu.HienThiDanhSachMenu(loaiSanPhamList);
									}

									@Override
									public void onFailure(Call<List<LoaiSanPham>> call, Throwable t) {
										iViewXuLyMenu.FailFromGetDanhSachMenu("FAIL, can not get data Child of Child from server --- " + t.getMessage());
									}

								});
								if(j == count -1){
									loaiSanPhamList.get(finalI).setListCon(loaiSanPhamListCon);
								}
							}
						}
						@Override
						public void onFailure(Call<List<LoaiSanPham>> call, Throwable t) {
							iViewXuLyMenu.FailFromGetDanhSachMenu("FAIL, can not get data Child from server --- " + t.getMessage());
						}
					});
//					if(i == count-1){
//						// Sau khi đã lấy xong hết cái list mới trả về cho View hiển thị.
//						iViewXuLyMenu.HienThiDanhSachMenu(loaiSanPhamList);
//					}
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

	@Override
	public AccessToken LayUserNameFacebook() {
		ModelDangNhap modelDangNhap = new ModelDangNhap();
		AccessToken accessToken = modelDangNhap.LayTokenFacebookHienTai();

		return accessToken;



	}


}
