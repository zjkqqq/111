package C_Run;

import java.math.BigInteger;

public class C_33nums_Proc {
	
	public BigInteger AS_e;
	public BigInteger AS_n;
	public String rec_sign;
	
	public String process(String msg) {
		String result="";
		
		String rec[]=msg.split("&");
		String sign_info[];
		int len=rec.length;
		
		
		sign_info=new String[len-4];
		for(int i=1;i<len;i++) {
			if(i==1) {
				String pube=rec[1];
				AS_e=new BigInteger(pube);
				continue;
			}
			if(i==2) {
				String pubn=rec[2];
				AS_n=new BigInteger(pubn);
				continue;
			}
			if(i==3) {
				String name=rec[3];
				continue;
			}
			
			for(int j=0;j<len-4;j++) {
				sign_info[j]=rec[i++];
			}
			
			
		}
		
		
		return result;
		
		
	}
	
	
	
	

}
