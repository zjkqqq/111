package Control_Package;
/*editor xu;
 * 
 */

public class Fifth_ctol {

	/*
	 * ǰ6λchar��ת��Ϊint����5��������ư����࣬���������ģ�顣
4��7-9λchar��ת��Ϊint���ͣ��Ҵ��ڵ���0С��8����Ƿ�����v���ţ����������ģ�顣
5��10-71λchar��ת������ʽ����Ticket��tgs����ʽ������Ticket��tgs�������������ģ�顣
6��72-109λchar��ת��Ϊint���ͣ���ʽ����Authenticator(c)�����������ģ�顣

	 */
	private int Id_Package=5;
	private int IDv;
	private Ticket_tgs Ticket_tgs_ctol;
	private Authenticator Authenticator_c;
	
	
	public int get_Id_Package() {	
		return this.Id_Package;
	}
	
	public int get_IDv() {
		return this.IDv;
	}
	
	public void write_IDv(int msg) {
		this.IDv=msg;
	}
	
	public Ticket_tgs get_Ticket_tgs() {
		return this.Ticket_tgs_ctol;
	}
	
	public Authenticator get_Authenticator_c() {
		return this.Authenticator_c;
	}
}
