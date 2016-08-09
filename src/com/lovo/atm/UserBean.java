package com.lovo.atm;

/*
 * 使用UserBean存放用户信息
 * 提供get,set方法供其他类调用
 * 对用户数据进行修改和访问
 * */
public class UserBean {
	private String userName;    //用户名
	
	private String passWord;    //密码
	
	private float balance;      //账户余额
	
	//无参的构造方法，用于初始化这个类
	public UserBean(){
		
	}
	
	//有参的构造方法，传入三个参数，用于初始化三个属性
	public UserBean(String userName,String passWord,float balance){
		this.userName = userName;
		this.passWord = passWord;
		this.balance = balance;
	}

	/*
	 * get,set方法，提供外部调用的途径
	 * get方法获取值，set方法修改值
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
