	package luongduongquan.com.demoappbanhang.MyUtils;

	import android.net.Uri;
import android.os.AsyncTask;
	import android.util.Log;

	import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DownloadJSON extends AsyncTask<String, Void, String> {

	String myURL;
	List<HashMap<String, String>> listParameters; // danh sach cac tham so truyen vao POST hoac GET
	StringBuilder data_stringBuilder; // biến để chứa kết quả lấy ra từ response trả về.

	boolean method = true; // true-GET   , false-POST

	public DownloadJSON (String url){
		// Constructor cho method GET
		this.myURL = url;
		method = true;
	}

	public DownloadJSON (String url, List<HashMap<String, String>> listParameters){
		// Constructor cho method POST với 1 list params
		this.myURL = url;
		this.listParameters = listParameters;
		method = false;
	}

	public DownloadJSON (String url, HashMap<String, String> param){
		// Constructor cho method POST với chỉ 1 param duy nhất
		this.myURL = url;
		this.listParameters = new ArrayList<>();
		this.listParameters.add(param);
		method = false;
	}

	@Override
	protected String doInBackground(String... strings) {

		try{
			URL url = new URL(myURL);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


			if(method){
				methodGet(httpURLConnection);
			} else {
				methodPost(httpURLConnection);
			}



		}catch (MalformedURLException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


		return data_stringBuilder.toString();
	}


	// Hàm xử lý phương thức GET
	private String methodGet(HttpURLConnection httpURLConnection){
		String dataGETresult = "";
		InputStream inputStream = null;

		try {
			httpURLConnection.connect();
			inputStream = httpURLConnection.getInputStream();
			InputStreamReader reader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(reader);

			data_stringBuilder = new StringBuilder();
			String line = "";
			while ((line = bufferedReader.readLine()) != null){
				data_stringBuilder.append(line);
			}

			dataGETresult = data_stringBuilder.toString();
			bufferedReader.close();
			reader.close();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataGETresult;
	}

		// Hàm xử lý phương thức POST
	private String methodPost(HttpURLConnection httpURLConnection){
		String dataPOSTresult = "";

		String key = "";
		String value = "";

		try {
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);

			Uri.Builder builderURI = new Uri.Builder();

			int count = listParameters.size(); // đem count ra ngoài để tối ưu, nếu list là 1000000 phần tử => mỗi lần nó phải lấy cái size lại nữa => tốn tài nguyên
			// Lấy danh sách các parameter mà user truyền vào để đưa lên POST
			for (int i = 0; i < count; i++) {
				for (Map.Entry<String, String> values123 : listParameters.get(i).entrySet()) {
					key = values123.getKey();
					value = values123.getValue();
				}

				builderURI.appendQueryParameter(key, value);
			}

			String query = builderURI.build().getEncodedQuery();

			OutputStream outputStream = httpURLConnection.getOutputStream();
			OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream);
			BufferedWriter writer = new BufferedWriter(streamWriter);

			writer.write(query);
			writer.flush();
			writer.close();
			streamWriter.close();
			outputStream.close();

			// thêm Body cho POST xong rồi, thì dùng phương thức có sẵn trong methodGET ở trên để truyền đi.
			dataPOSTresult = methodGet(httpURLConnection);
			Log.d("QUAN123", "methodPost: " + dataPOSTresult);

		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return dataPOSTresult;
	}
}
