package luongduongquan.com.demoappbanhang.MyUtils.RetrofitUtils;

import java.util.List;

import luongduongquan.com.demoappbanhang.Model.ObjectClass.LoaiSanPham;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface DataClientListener {
		// @Multipart: khi gửi data dạng âm thanh, hình ảnh. Gửi Text thì dùng cái khác.
		// @POST dùng dạng POST. và "uploadhinh.php" là link API. Ví dụ API có tên là: get_userlist => thì truyền vào @POST("get_userlist")
		// Call<String> : vì ở đây server trả về response dạng chuỗi. Nên dùng String.
		// Đây là phương thức có tên là "UploadPhoto123" thực hiện lắng nghe việc gửi nhận photo giữa client và server.
		// đối số @Part MultipartBody.Part photo123 vì ta muốn truyền vào 1 cái MultipartBody.Part để đẩy lên server.
		@Multipart
		@POST("uploadhinh.php")
		Call<String> UploadPhoto123(@Part MultipartBody.Part photo123);


		// Call<List<SinhVien>> server sẽ response data dạng object (SinhVien) về cho client.
		// Ở đây là 1 list Object luôn. Nếu muốn 1 Object thôi thì để Call<SinhVien>
		// @Field("taikhoan123") => là tham số mà API server yêu cầu mình truyền vào. Ở đây tham số tên là taikhoan123
		@FormUrlEncoded
		@POST("loaisanpham.php")
		Call<List<LoaiSanPham>> getLoaiSanPhamTheoCha(@Field("maloaicha") String maloaicha);
	}