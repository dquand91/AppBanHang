package luongduongquan.com.demoappbanhang.Model.TrangChu.XuLyMenu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import luongduongquan.com.demoappbanhang.Model.ObjectClass.LoaiSanPham;
import luongduongquan.com.demoappbanhang.MyUtils.DownloadJSON;

public class XulyJSONMenu {

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

	public List<LoaiSanPham> getLoaiSanPhamTheoMaLoaiList(int maloaiSP){

		List<LoaiSanPham> listLoaiSanPham = new ArrayList<>();
		String dataJSON = "";

		String urlLink = "http://172.18.128.58/appbanhang/loaisanpham.php";
		HashMap<String, String> params = new HashMap<String, String>();;
		params.put("maloaicha",String.valueOf(maloaiSP));
		DownloadJSON downloadJSON = new DownloadJSON(urlLink, params);
		downloadJSON.execute();

		try {
			dataJSON = downloadJSON.get();

			XulyJSONMenu xulyJSONMenu = new XulyJSONMenu();
			listLoaiSanPham = xulyJSONMenu.parserJSONMenu(dataJSON);

		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		return listLoaiSanPham;

	}

}
