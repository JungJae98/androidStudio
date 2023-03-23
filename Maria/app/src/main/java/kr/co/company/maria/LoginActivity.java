package kr.co.company.maria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private EditText et_id, et_pass;
    public Button btn_login, btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_id = findViewById(R.id.et_id);
        et_pass = findViewById(R.id.et_pass);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//회원가입 클릭시 회원가입으로 이동
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);

            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("실행","온클릭 View 작동 ");
                String userID = et_id.getText().toString();
                String userPass = et_pass.getText().toString();
                Log.v("실행",userID);
                Log.v("실행",userPass);


                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("실행","onResponse 실행이 되는가");
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Log.i("aa","dsa");
                            boolean success = jsonObject.getBoolean("success");
                            if (success) { //로그인에 성공한 경우
                                String userId = jsonObject.getString("userID");
                                String userpass = jsonObject.getString("userPassword");

                                Toast.makeText(getApplicationContext(), "로그인에 성공", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra("userID",userID);
                                intent.putExtra("userPass",userPass);
                                startActivity(intent);
                            } else { //로그인에 실패
                                Toast.makeText(getApplicationContext(), "로그인에 실패", Toast.LENGTH_SHORT).show();
                                return;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                };
                Log.v("로그인","리퀘스트쪽으로 데이터를 넘기는지");
                LoginRequest loginRequest = new LoginRequest(userID, userPass, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
                Log.v("로그인","리퀘스트쪽으로 데이터를 넘기는지");
            }
        });
    }
}