package luongduongquan.com.demoappbanhang.MyUtils.RetrofitUtils;

public class APIUtils {

		// BASE_URL này phải là 1 địa chỉ kết thuc bằng dấu /
		// Ví dụ: BASE_URL = "http://jsonplaceholder.typicode.com/"
		public static final String BASE_URL = "http://172.18.128.58/appbanhang/";

		public static DataClientListener getDataRetrofit(){
			// Sẽ xử lý việc nhận và gửi dữ liệu rồi sau đó sẽ gọi các callback để trả về.
			return APIretrofitClient.getClient(BASE_URL).create(DataClientListener.class);
		}

	}	