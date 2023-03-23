package kr.co.company.maria;
import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegistRequest extends StringRequest{

    //서버설정
   final static private String URL = "http://192.168.24.148/Register.php";
   private Map<String, String> map;

    // 내가 원하는대로 수정이 필요요
   public RegistRequest(String userID, String userPassword, Response.Listener<String>listener){
       super(Method.POST, URL, listener, null);

       map = new HashMap<>();
       //키값, 실제 넣을 값 나는 두개만 쓰면 된다.
       map.put("userID", userID);
       map.put("userPassword",userPassword);
   }

    @Nullable
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
