package com.transformer.recovery;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Context;
import android.content.DialogInterface;
import android.app.AlertDialog;

public class RecoveryActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final Context context = this;
        final Activity myact = this;
        Button reboot = (Button) findViewById(R.id.recovery);
        reboot.setOnClickListener(new OnClickListener(){
        	public void onClick(View v) {
				if (Util.isRoot()){
					AlertDialog.Builder dialog = new AlertDialog.Builder(context);
					dialog.setMessage("Are you sure you want to reboot to recovery?")
						.setCancelable(false)
						.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
							public void onClick (DialogInterface mydialog, int id){
								Util.runRootCommand("echo boot-recovery|dd of=/dev/block/mmcblk0p3 bs=1 seek=0; reboot");
							}
						})
						.setNegativeButton("No", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface mydialog, int which) {
								myact.finish();
							}
						})
						.create().show();
				}
			}
        });
        
        
    }
}