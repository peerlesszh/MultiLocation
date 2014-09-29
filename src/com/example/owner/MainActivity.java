package com.example.owner;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView tvLocation,tvLocationMethod,tvLocationState;
	
	private int locationType;
	private double latitude = 0;
	private double longitude = 0;
	private String method = "Ĭ��";
	private String state = "������";
	
	private LocationServiceProvider lsp = null;
	private LagLng lagLng = null;
	
	private ProgressDialog pd = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tvLocation = (TextView)findViewById(R.id.location);
		tvLocationMethod = (TextView)findViewById(R.id.location_method);
		tvLocationState = (TextView)findViewById(R.id.location_state);
		pd = new ProgressDialog(this);
		pd.setMessage("������");
		pd.setCancelable(true);
		
		lsp = new LocationServiceProvider(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		if(checkNetWorkState()){
			pd.show();
			getLocation();
		}else{
			showSettingNetwork(this);
			getLocation();
		}
	}
	
	private void updateUI() {
		tvLocation.setText("��γ��("+latitude+","+longitude+")");
		tvLocationMethod.setText("��λ��ʽ("+method+")");
		tvLocationState.setText("��λ״̬("+state+")");
	}
	

	private void getLocation(){
		lagLng = lsp.getCurrentLocation();
		locationType = lagLng.getLocationServiceType();
		state = "������";
		latitude = lagLng.getLatitude();
		longitude = lagLng.getLongitude();
		switch (locationType) {
		case 1:
			method = "GPS";			
			break;
		case 2:
			method = "WIFI";
			break;
		case 3:
			method = "�ֻ�����";
			break;
		default:
			method = "Ĭ��";
			break;
		}
		updateUI();
		pd.dismiss();
					
	}
		
	private  boolean checkNetWorkState(){
		boolean flag = false;
		try {
			// ����ֻ��������ӹ�����󣨰�����wi-fi�����ӵĹ���
			ConnectivityManager connectivity = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectivity != null) {
				// ����������ӹ���Ķ���
				NetworkInfo info = connectivity.getActiveNetworkInfo();
				if (info != null && info.isConnected()) {
					// �жϵ�ǰ�����Ƿ�������
					if (info.getState() == NetworkInfo.State.CONNECTED)
					{
						flag = true;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	public void showSettingNetwork(final Context context){
		new AlertDialog.Builder(context)
		.setTitle("��ʾ")
		.setMessage("�ף��㻹û����������Ŷ�����綨λ�ٶȸ��죬Ҫ����ȥ������")
		.setPositiveButton("�ǵ�", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
			}
		})
		.setNegativeButton("���������", null)
		.create().show();
	}
	
}
