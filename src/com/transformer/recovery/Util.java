package com.transformer.recovery;
import java.io.DataOutputStream;
import android.util.Log;

public class Util {
	public static boolean runRootCommand(String command) {
	      Process process = null;
	      DataOutputStream os = null;
	      try {
	                process = Runtime.getRuntime().exec("su");
	                os = new DataOutputStream(process.getOutputStream());
	                os.writeBytes(command+"\n");
	                os.writeBytes("exit\n");
	                os.flush();
	                process.waitFor();
	                } catch (Exception e) {
	                        Log.d("TF-RR", "Unexpected error - Here is what I know: "+e.getMessage());
	                        return false;
	                }
	                finally {
	                        try {
	                             if (os != null) os.close();
	                             process.destroy();
	                        } catch (Exception e) {}
	                }
	                return true;
	}
	
	public static boolean isRoot(){
		return runRootCommand("echo 'I am root!'");
	}
}
