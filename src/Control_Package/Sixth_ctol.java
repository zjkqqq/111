package Control_Package;

/*editor xu;
 * 
 */

public class Sixth_ctol {

	/*
	 * ǰ6λchar��ת��Ϊint����6��������ư����࣬�鿴�Ƿ����������ư������������ģ�顣
4��7-22λchar��ת��ΪString���ͣ����ȱ���С�ڵ���16������k��cv���ͻ����������v����Կ�����������ģ�顣
5��23-25λchar��ת��Ϊint���ͣ��Ҵ��ڵ���0С��8����Ƿ�����v���ţ����������ģ�顣
6��26-45λchar��ת��ΪString���ͣ�����ϵͳʱ�䣬��ʽ����Ϊ������ʱ���룬���ַ������޿ո�������2018��4��28��8:35:42�����������ģ�顣
7��46-108λ��ת�����ο�Ticket��v����

	 */
	
	private int Id_Package=6;
	private String Kcv;
	private int IDv;
	private String TS;
	private Ticketv Ticketv_ctol;
	private String ticketv;
	
	public int get_Id_Package() {	
		return this.Id_Package;
	}
	
	public String get_Kcv() {
		return this.Kcv;
	}
	
	public void write_Kcv(String msg) {
		this.Kcv=msg;
	}
	
	public int get_IDv() {
		return this.IDv;
	}
	
	public void write_IDv(int msg) {
		this.IDv=msg;
	}
	
	public String get_TS() {
		return this.TS;
	}
	
	public void write_TS(String msg) {
		this.TS=msg;
	}
	
	public Ticketv get_Ticketv_ctol() {
		return this.Ticketv_ctol;
	}
	
	public String get_ticketv() {
		return this.ticketv;
	}
	
	public void write_ticketv(String msg) {
		this.ticketv=msg;
	}
	
}
