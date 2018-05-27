package Socket_Run;

import java.io.IOException;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import RSA.Rsa;
import X_509.X_509;


public class Socket_Kerberos_AS {
	

	 public static void main(String[] args) {
	        // TODO Auto-generated method stub
		 
		   
			
			
		 
	        ServerSocket s = null;
	        
	        try{
	        	 s= new ServerSocket(5437);
	            System.out.println("start AS service:");
	        }
	        catch(IOException e)
	        {
	            System.out.println(e);
	            System.exit(1);
	        }
	        int i = 1;
	        while(true)
	        {
	            
	            try{
	                Socket cs = s.accept();
	                new AS_ServerThread(cs).start();
	                System.out.println("接收了 第"+i+"个请求");
	                i++;
	            }
	            catch(IOException e)
	            {
	                System.out.println(e);
	            }
	        }
	    }
	
}
