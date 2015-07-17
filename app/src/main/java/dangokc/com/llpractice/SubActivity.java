package dangokc.com.llpractice;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class SubActivity extends ActionBarActivity {

    private int radID = 0;

    private TextView tvUserName, tvDateTime;
    private Button btnOK, btnBack;
    private RadioButton radListView, radGridView;
    private RadioGroup radioGroup;

    private SubActBtnOnclickListener btnOnclickListener;
    private SubActRadCheckedChange radOnCheckedChaged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        tvUserName = (TextView)findViewById(R.id.tvHelloUser);
        tvDateTime = (TextView)findViewById(R.id.tvDateTime);

        btnOK = (Button)findViewById(R.id.btnOk);
        btnBack = (Button)findViewById(R.id.btnBack);
        btnOnclickListener = new SubActBtnOnclickListener();
        btnOK.setOnClickListener(btnOnclickListener);
        btnBack.setOnClickListener(btnOnclickListener);

        radioGroup = (RadioGroup)findViewById(R.id.radGroup);
        radListView = (RadioButton)findViewById(R.id.radListView);
        radGridView = (RadioButton)findViewById(R.id.radGridView);
        radOnCheckedChaged = new SubActRadCheckedChange();
        radioGroup.setOnCheckedChangeListener(radOnCheckedChaged);

        Bundle extras = getIntent().getExtras();
        tvUserName.setText("Welcome " + extras.getString("USER_NAME").toUpperCase());
        tvDateTime.setText(extras.getString("DATE_TIME"));

    }

    public class SubActBtnOnclickListener implements View.OnClickListener{
        String test;
        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.btnOk){
                if(radID == 0)
                    Toast.makeText(v.getContext(), "OK - List View", Toast.LENGTH_SHORT).show();
                else if(radID == 1)
                    Toast.makeText(v.getContext(), "OK - Grid View", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(v.getContext(), "BACK", Toast.LENGTH_SHORT).show();
        }
    }

    public class SubActRadCheckedChange implements RadioGroup.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if(checkedId == R.id.radListView)
                radID = 0;
            if(checkedId == R.id.radGridView)
                radID = 1;
        }

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sub, menu);
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
