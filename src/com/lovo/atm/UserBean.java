package com.lovo.atm;

/*
 * ʹ��UserBean����û���Ϣ
 * �ṩget,set���������������
 * ���û����ݽ����޸ĺͷ���
 * */
public class UserBean {
	private String userName;    //�û���
	
	private String passWord;    //����
	
	private float balance;      //�˻����
	
	//�޲εĹ��췽�������ڳ�ʼ�������
	public UserBean(){
		
	}
	
	//�вεĹ��췽���������������������ڳ�ʼ����������
	public UserBean(String userName,String passWord,float balance){
		this.userName = userName;
		this.passWord = passWord;
		this.balance = balance;
	}

	/*
	 * get,set�������ṩ�ⲿ���õ�;��
	 * get������ȡֵ��set�����޸�ֵ
	 * */
	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
