package Control_Package;

/*edito xu;
 * 
 */

public class Fourth_ctol {
	/*����ע���AS����Client��ע��ɹ������Ϣ
	 * ǰ6λ char ���ư���Ϣ
	 * ǰ6λchar��ת��Ϊint����4��������ư����࣬�鿴�Ƿ����������ư������������ģ�顣
4��7-9λchar��ת��Ϊint���ͣ��Ҵ���0С�ڵ���8��������ĸ��ͻ��ˣ�������Ǳ��������������ģ�顣
5��10-25λchar��ת��ΪString���ͣ��ұ���16λ������K��c��tgs����Կ��
6��26-27λchar��ת��Ϊint���ͣ��Ҵ���0С�ڵ���4�����������ģ�顣
7��28-47λchar��ת��ΪStirng���ͣ�����ϵͳʱ�䡣
8��48-53λchar��ת��ΪString���ͣ�������ʱ�䡣
9��54-112λchar��ת����ΪTicket��tgs�����ο����ʽת����

	 */
	
	private int Id_Package=4;

	private String K_ctgs;
	private String IDtgs;
	private String TS2;
	private String Lifetime1;
	private Ticket_tgs Ticket_tgs_ctol;
	private String ticket_tgs;
	public String get_K_ctgs() {
		return this.K_ctgs;
	}
	
	public void write_tickettgs(String msg) {
		this.ticket_tgs=msg;
	}
	
	public String get_tickettgs() {
		return this.ticket_tgs;
	}
	
	public void write_K_ctgs(String msg) {
		this.K_ctgs=msg;
	}
	
	
	public String get_TS2() {
		return this.TS2;
	}
	
	public void write_TS2(String msg) {
		this.TS2=msg;
	}
	
	public String get_Lifetime1() {
		return this.Lifetime1;
	}
	
	public void write_Lifetime1(String msg) {
		this.Lifetime1=msg;
	}
	
	public int get_Id_Package() {	
		return this.Id_Package;
	}
	
	public String get_IDtgs() {	
		return this.IDtgs;
	}
	
	public void write_IDtgs(String msg) {
		
		this.IDtgs=msg;
	}
	
	public Ticket_tgs Ticket_tgs_ctol() {	
		return this.Ticket_tgs_ctol;
	}
}
