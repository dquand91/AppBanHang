package luongduongquan.com.demoappbanhang.Model.TrangChu.XuLyMenu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import luongduongquan.com.demoappbanhang.Model.ObjectClass.LoaiSanPham;

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

}
