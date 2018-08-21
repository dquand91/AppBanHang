package luongduongquan.com.demoappbanhang.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import luongduongquan.com.demoappbanhang.Model.ObjectClass.LoaiSanPham;
import luongduongquan.com.demoappbanhang.R;

public class ExpandListAdapterCustom extends BaseExpandableListAdapter  {
	private static final String TAG = "ExpandListAdapterCustom";

	Context context;
	List<LoaiSanPham> loaiSanPhamList;

	ViewHolderMenu viewHolderMenu;

	public ExpandListAdapterCustom(Context context, List<LoaiSanPham> loaiSanPhamList) {
		this.context = context;
		this.loaiSanPhamList = loaiSanPhamList;
	}

	public class ViewHolderMenu{
		TextView tvTenLoaiSP;
		ImageView imgExpandButton;
	}



	@Override
	public int getGroupCount() {
		return loaiSanPhamList.size();
	}

	@Override
	public int getChildrenCount(int vitriGroup) {
		if(loaiSanPhamList.get(vitriGroup).haveChild()){
			return 1;
		} else {
			return 0;
		}
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
	public View getGroupView(final int vitriGroup, boolean isExpaned, View view, ViewGroup viewGroup) {

		View resultView = view;
		if(resultView == null){
			viewHolderMenu = new ViewHolderMenu();
			LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			resultView = layoutInflater.inflate(R.layout.item_group_loaisanpham_main, viewGroup, false);

			viewHolderMenu.imgExpandButton = resultView.findViewById(R.id.imgMenuPlus_itemGroup);
			viewHolderMenu.tvTenLoaiSP = resultView.findViewById(R.id.tvTenLoaiSanPham_itemGroup);

			resultView.setTag(viewHolderMenu);
		} else {
			viewHolderMenu = (ViewHolderMenu) resultView.getTag();
		}

		viewHolderMenu.tvTenLoaiSP.setText(loaiSanPhamList.get(vitriGroup).getTENLOAISP());



//		TextView tvTenLoaiSanPham = itemViewGroupCha.findViewById(R.id.tvTenLoaiSanPham_itemGroup);

//		ImageView imgMenuPlus = itemViewGroupCha.findViewById(R.id.imgMenuPlus_itemGroup);
		if(isExpaned){
			viewHolderMenu.imgExpandButton.setImageResource(R.drawable.ic_remove_black_24dp);
		} else {
			viewHolderMenu.imgExpandButton.setImageResource(R.drawable.ic_add_black_24dp);
		}
		boolean haveChild = loaiSanPhamList.get(vitriGroup).haveChild();
		Log.d(TAG, "getGroupView: " + "haveChild = " + haveChild);
		if(haveChild){
			viewHolderMenu.imgExpandButton.setVisibility(View.VISIBLE);
		} else {
			viewHolderMenu.imgExpandButton.setVisibility(View.INVISIBLE);
		}

		viewHolderMenu.tvTenLoaiSP.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Log.d(TAG, "onClick: " + loaiSanPhamList.get(vitriGroup).getTENLOAISP() + " - " + loaiSanPhamList.get(vitriGroup).getMALOAISP());
			}
		});

		return resultView;
	}

	// Tạo giao diện cho Child
	@Override
	public View getChildView(int vitriGroup, int vitriChild, boolean isExpaned, View view, ViewGroup viewGroup) {

//		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		View itemViewChildCon = layoutInflater.inflate(R.layout.item_child_loaisanpham_main, viewGroup, false);//
//		ExpandableListView expandableListView = itemViewChildCon.findViewById(R.id.expandListViewCon_main);
//		SecondExpandListAdapter secondExpandListAdapter = new SecondExpandListAdapter(context, loaiSanPhamList.get(vitriGroup).getListCon());
//		expandableListView.setAdapter(secondExpandListAdapter);
//		return itemViewChildCon;

		SecondExpanable secondExpanable = new SecondExpanable(context);
		ExpandListAdapterCustom secondAdapter = new ExpandListAdapterCustom(context,loaiSanPhamList.get(vitriGroup).getListCon());
		secondExpanable.setAdapter(secondAdapter);
//		SecondExpandListAdapter secondExpandListAdapter = new SecondExpandListAdapter(context, loaiSanPhamList.get(vitriGroup).getListCon());
//		secondExpanable.setAdapter(secondExpandListAdapter);

		secondExpanable.setGroupIndicator(null);
		notifyDataSetChanged();

		return secondExpanable;
	}

	@Override
	public boolean isChildSelectable(int vitriGroup, int vitriChild) {
		return false;
	}
}
