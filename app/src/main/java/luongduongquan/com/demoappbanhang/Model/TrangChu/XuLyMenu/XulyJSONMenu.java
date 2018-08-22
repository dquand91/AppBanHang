package luongduongquan.com.demoappbanhang.Model.TrangChu.XuLyMenu;

import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import luongduongquan.com.demoappbanhang.Model.ObjectClass.LoaiSanPham;
import luongduongquan.com.demoappbanhang.MyUtils.RetrofitUtils.APIUtils;
import luongduongquan.com.demoappbanhang.MyUtils.RetrofitUtils.DataClientListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class XulyJSONMenu  {
	String tenNguoiDung = "";

	List<LoaiSanPham> loaiSanPhamList = new ArrayList<>();

	public List<LoaiSanPham> parserJSONMenu(String dataJSON){
		List<LoaiSanPham> loaiSanPhamList = new ArrayList<>();

		try {
			JSONObject jsonObject = new JSONObject(dataJSON);
			JSONArray loaisanpham = jsonObject.getJSONArray("LOAISANPHAM");
			int count = loaisanpham.length();
			for (int i = 0; i < count; i++) {

				JSONObject jsonLoaiSanPham = loaisanpham.getJSONObject(i);

				LoaiSanPham dataLoaiSanPham = new LoaiSanPham();
				dataLoaiSanPham.setMALOAICHA(Integer.parseInt(jsonLoaiSanPham.getString("MALOAI_CHA")));
				dataLoaiSanPham.setMALOAISP(Integer.parseInt(jsonLoaiSanPham.getString("MALOAISP")));
				dataLoaiSanPham.setTENLOAISP(jsonLoaiSanPham.getString("TENLOAISP"));

				loaiSanPhamList.add(dataLoaiSanPham);


			}


		} catch (JSONException e) {
			e.printStackTrace();
		}


		return loaiSanPhamList;
	}

	public void getLoaiSanPhamTheoMaLoaiList(int maloaiSP){

		DataClientListener getLoaiSanPhamTheoCha = APIUtils.getDataRetrofit();
		Call<List<LoaiSanPham>> callback = getLoaiSanPhamTheoCha.getLoaiSanPhamTheoCha(String.valueOf(maloaiSP));
		callback.enqueue(new Callback<List<LoaiSanPham>>() {
			@Override
			public void onResponse(Call<List<LoaiSanPham>> call, Response<List<LoaiSanPham>> response) {
			}

			@Override
			public void onFailure(Call<List<LoaiSanPham>> call, Throwable t) {
			}
		});




//		List<LoaiSanPham> listLoaiSanPham = new ArrayList<>();
//		String dataJSON = "";
//
//		String urlLink = "http://172.18.128.58/appbanhang/loaisanpham.php";
//		HashMap<String, String> params = new HashMap<String, String>();;
//		params.put("maloaicha",String.valueOf(maloaiSP));
//		DownloadJSON downloadJSON = new DownloadJSON(urlLink, params);
//		downloadJSON.execute();
//
//		try {
//			dataJSON = downloadJSON.get();
//
//			XulyJSONMenu xulyJSONMenu = new XulyJSONMenu();
//			listLoaiSanPham = xulyJSONMenu.parserJSONMenu(dataJSON);
//
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			e.printStackTrace();
//		}
//
//		return listLoaiSanPham;

	}


	public String LayTenNguoiDungFacebook(AccessToken accessToken){


		GraphRequest request = GraphRequest.newMeRequest(
				accessToken,
				new GraphRequest.GraphJSONObjectCallback() {
					@Override
					public void onCompleted(
							JSONObject object,
							GraphResponse response) {

						try {
							tenNguoiDung = object.getString("name");
						} catch (JSONException e) {
							e.printStackTrace();
						}


					}
				});
		Bundle parameters = new Bundle();
		parameters.putString("fields", "name");
		request.setParameters(parameters);
		request.executeAsync();

		return tenNguoiDung;
	}

}
