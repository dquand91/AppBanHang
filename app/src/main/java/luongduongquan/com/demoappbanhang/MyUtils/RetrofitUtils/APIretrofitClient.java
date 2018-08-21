package luongduongquan.com.demoappbanhang.MyUtils.RetrofitUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIretrofitClient {
		private static Retrofit retrofit = null;
		
		// Phương thức để khởi tạo Retrofit với đối số là URL của API.
		public static Retrofit getClient(String base_url){
		
			// Tạo 1 OkHttpClient để kết nối với server API
			OkHttpClient okHttpClient = new OkHttpClient.Builder()
										.readTimeout(10000, TimeUnit.MILLISECONDS)
										.writeTimeout(1000, TimeUnit.MILLISECONDS)
										.connectTimeout(10000, TimeUnit.MILLISECONDS)
										.retryOnConnectionFailure(true)
										.protocols(Arrays.asList(Protocol.HTTP_1_1))
										.build();
										
			// Tạo 1 Gson để chứa data JSON lấy về từ API server.
			Gson gson = new GsonBuilder().setLenient().create();
			
			// Build retrofit
			retrofit = new Retrofit.Builder()
						.baseUrl(base_url)
						.client(okHttpClient)
						.addConverterFactory(GsonConverterFactory.create(gson)) // để nó tự động chuyển đổi JSON trả về thành 1 Object GSON.
						.build();
			return retrofit;
		}
	}