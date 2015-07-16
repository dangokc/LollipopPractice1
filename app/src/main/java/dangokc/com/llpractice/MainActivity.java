package dangokc.com.llpractice;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private Intent subActivity;

    private EditText etUsername, etPassword;
    private Button btnOK, btnClear;
    private MyBtnOnClickListener mBtnOnClickListener;

    private User user1, user2, user3;
    private ArrayList<User> userArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = (EditText)findViewById(R.id.etEmail);
        etPassword = (EditText)findViewById(R.id.etPassword);

        mBtnOnClickListener = new MyBtnOnClickListener();
        btnOK = (Button)findViewById(R.id.btnOk);
        btnOK.setOnClickListener(mBtnOnClickListener);
        btnClear = (Button)findViewById(R.id.btnClear);
        btnClear.setOnClickListener(mBtnOnClickListener);

        user1 = new User("huy","123");
        user2 = new User("hoang","123");
        user3 = new User("phuoc","123");
        userArrayList = new ArrayList<>();
        userArrayList.add(user1);
        userArrayList.add(user2);
        userArrayList.add(user3);

        subActivity = new Intent(this, SubActivity.class);
    }

    public class MyBtnOnClickListener implements View.OnClickListener {
        String username, pw;
        boolean found = false;
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.btnOk) {
                //Check whether the username et is empty or not
                if(etUsername.getText().toString().isEmpty()){
                    Toast.makeText(v.getContext(), "Missing Username", Toast.LENGTH_SHORT).show();
                }
                else {
                    for(int i = 0; i < userArrayList.size(); i++){
                        //Check whether the username is valid or not
                        if(userArrayList.get(i).getName().equals(etUsername.getText().toString())){
                            username = userArrayList.get(i).getName();
                            //Check whether the pw is valid or not
                            if(userArrayList.get(i).getPassword().equals(etPassword.getText().toString())){
                                pw = userArrayList.get(i).getPassword();
                                found = true;
                                break;
                            }
                        }
                    }
                    if(found == true){
                        startActivity(subActivity);
                    }
                    else
                        Toast.makeText(v.getContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
            if(v.getId() == R.id.btnClear) {
                etUsername.setText("");
                etPassword.setText("");
                username = "";
                pw = "";
                found = false;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
