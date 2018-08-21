package luongduongquan.com.demoappbanhang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;

import luongduongquan.com.demoappbanhang.Model.ObjectClass.LoaiSanPham;
import luongduongquan.com.demoappbanhang.R;

public class ExpandListAdapterCustom extends BaseExpandableListAdapter  {
	private static final String TAG = "ExpandListAdapterCustom";

	Context context;
	List<LoaiSanPham> loaiSanPhamList;

	public ExpandListAdapterCustom(Context context, List<LoaiSanPham> loaiSanPhamList) {
		this.context = context;
		this.loaiSanPhamList = loaiSanPhamList;
	}

	@Override
	public int getGroupCount() {
		return loaiSanPhamList.size();
	}

	@Override
	public int getChildrenCount(int vitriGroup) {
		return loaiSanPhamList.get(vitriGroup).getListCon().size();
	}

	@Override
	public Object getGroup(int vitriGroup) {
		return loaiSanPhamList.get(vitriGroup);
	}

	@Override
	public Object getChild(int vitriGroup, int vitriChild) {
		return loaiSanPhamList.get(vitriGroup).getListCon().get(vitriChild);
	}

	@Override
	public long getGroupId(int vitriGroup) {
		return loaiSanPhamList.get(vitriGroup).getMALOAISP();
	}

	@Override
	public long getChildId(int vitriGroup, int vitriChild) {
		return loaiSanPhamList.get(vitriGroup).getListCon().get(vitriChild).getMALOAISP();
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	// Tạo giao diện cho Group
	@Override
	public View getGroupView(int vitriGroup, boolean isExpaned, View view, ViewGroup viewGroup) {
		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View itemViewGroupCha = layoutInflater.inflate(R.layout.item_group_loaisanpham_main, viewGroup, false);
		TextView tvTenLoaiSanPham = itemViewGroupCha.findViewById(R.id.tvTenLoaiSanPham_itemGroup);
		tvTenLoaiSanPham.setText(loaiSanPhamList.get(vitriGroup).getTENLOAISP());
		return itemViewGroupCha;
	}

	// Tạo giao diện cho Child
	@Override
	public View getChildView(int vitriGroup, int vitriChild, boolean isExpaned, View view, ViewGroup viewGroup) {

		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View itemViewChildCon = layoutInflater.inflate(R.layout.item_group_loaisanpham_main, viewGroup, false);
		TextView tvTenLoaiSanPham = itemViewChildCon.findViewById(R.id.tvTenLoaiSanPham_itemGroup);
//		tvTenLoaiSanPham.setText(loaiSanPhamList.get(vitriGroup).getListCon().get(vitriChild).getTENLOAISP());
		tvTenLoaiSanPham.setText(loaiSanPhamList.get(vitriGroup).getListCon().get(vitriChild).getTENLOAISP());
		return itemViewChildCon;
	}

	@Override
	public boolean isChildSelectable(int vitriGroup, int vitriChild) {
		return false;
	}
}
