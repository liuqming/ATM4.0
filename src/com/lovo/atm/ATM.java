package com.lovo.atm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

public class ATM {
	/*
	 * 定义变量或者常量，接收数据
	 * */
	
	//定义一个UserBean类型的变量，用于引用对象
	private UserBean userBean;
	//ATM机的当前现金
	private float atmBal;
	//ATM能存放的最大金额,定义为常量，因为最大金额是不会更改的
	private final float atmMaxMoney = 100000.0f;
	//存放用户信息
	private Map<String, UserBean> userMap;
	//创建属性文件对象
	private Properties ps;
	
	/*
	 * 定义一个run方法，负责调用所有的方法
	 * */
	public void run(){
			this.loadData();
			this.welcome();
			this.login();
			this.choice();
	}
	

	/*
	 * 
	 * private UserBean theUser;
	 * 用集合存放数据
	 * 
	private void loadData(){
		//对当前现金进行初始化
		this.atmBal = "10000.0f";
		//创建一个集合，存放用户信息
		userMap = new HashMap();
		
		for(int i = 1;i <= 5;i ++){
			userBean = new UserBean();
			userBean.setUserName("liulu" + i);
			userBean.setPassWord("12345" + i);
			userBean.setBalance(i * 1000.0f);
			userMap.put("liulu" + i,userBean);
		}
	}
	*/
	/*
	 * 使用属性文件存放数据
	 * */
	private void loadData(){
		//对当前现金进行初始化
		this.atmBal = 10000.0f;
		//创建一个Properties对象
		ps = new Properties();
		try {
			//从输出流取出保存在属性文件里的数据，存入到properties集合中
			ps.load(new FileInputStream("ps.properties"));
		} catch (FileNotFoundException e1) {
			// TODO 自动生成 catch 块
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO 自动生成 catch 块
			e1.printStackTrace();
		}
		//判断属性文件中是否有内容，没有内容的话就重新加载，有内容的话，就不执行加载代码，从而保证数据持久化
		if(ps.get("liulu1") == null){
			//设置值，类似于map的put方法,但键值都只能为String类型，而map中可以放置任意类型的数据
			for(int i = 1;i <= 5;i ++){
				ps.setProperty("liulu" + i, "12345" + i + "&" + 1000.0*i);
				try {
					ps.store(new FileOutputStream("ps.properties"), null);
				} catch (IOException e) {
					// TODO 自动生成 catch 块
					e.printStackTrace();
				}
			}
		}
		
	}
	
	/*
	 * 欢迎界面
	 * */
	private void welcome(){
		System.out.println("******************************************************");
        System.out.println("*                                                    *");
        System.out.println("*============== 欢迎使用666666银行ATM机 ==============*");
        System.out.println("*                                                    *");
        System.out.println("******************************************************");
	}
	
	
	/*
	 * 验证用户登陆
	 * */
	private void login(){
		for(int i = 1;i <= 3;i ++){
			//接收用户输入的值，存在内存中
			Scanner scan = new Scanner(System.in);
			//提示用户输入用户名
			System.out.println("请输入用户名：");
			//定义一个字符串类型的变量，存放用户输入的用户名
			String un = scan.next();
			//提示用户输入密码
			System.out.println("请输入密码：");
			//定义一个字符串类型的变量，存放用户输入的密码
			String pwd = scan.next();
			
			//进行逻辑判断，将用户输入的信息同UserBean中存放的信息进行比较
			//字符串之间的比较要使用equals()方法进行比较
			//userBean.getUserName()是取出存放在userBean中的用户名
//用集合的方式实现
//			if(userMap.get(un) != null){
//				userBean = userMap.get(un);
//				System.out.println("欢迎您，" + userBean.getUserName());
//				break;
//			}else{
//				//用户累计输错3次，退出程序
//				if(i == 3){
//					System.out.println("您累计输入密码错误三次，冻结此账户，程序退出！！！");
//					//该语句直接终止虚拟机运行，强制退出程序
//					System.exit(0);
//				}else{
//					System.out.println("对不起！用户名或密码错误，请重新输入...");   
//				}
//			}
			/*
			 * 使用属性文件的方式实现
			 * */
			//取出属性文件中存放的键和值
			try {
				ps.load(new FileInputStream("ps.properties"));
				if(ps.getProperty(un) != null){
					String pwdAndBal = ps.getProperty(un);
					String [] login  = pwdAndBal.split("&");
					if(pwd.equals(login[0])){    //判断密码匹配后，将当前对象的三个属性赋给当前userBean对象
						userBean = new UserBean();
						userBean.setUserName(un);
						userBean.setPassWord(login[0]);
						userBean.setBalance(Float.parseFloat(login[1]));
						System.out.println("欢迎您，" + userBean.getUserName());
						break;
					}else{
						System.out.println("对不起！用户名或密码错误，请重新输入...");   
					}
				}else{
					//用户累计输错3次，退出程序
					if(i == 3){
						System.out.println("您累计输入密码错误三次，冻结此账户，程序退出！！！");
						//该语句直接终止虚拟机运行，强制退出程序
						System.exit(0);
					}else{
						System.out.println("对不起！用户名或密码错误，请重新输入...");   
					}
				}
			} catch (FileNotFoundException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
			
		}
	}
	
	
	/*
	 * 业务选择
	 * */
	private int choice() {
		
		while(true) {      
	        System.out.println("*************************************************");
	        System.out.println("*                                                                                                      *");
	        System.out.println("*     1.存款    2.取款    3.查询    4.转帐    5.修改密码     6.退出        *");
	        System.out.println("*                                                                                                      *");
	        System.out.println("*************************************************");
	        while(true){
	        	//捕获异常
	        	try{
	        		System.out.print("请选择您需要的业务：");
	        		Scanner scan = new Scanner(System.in);
	        		//定义一个String型变量，接收用户输入的金额
	        		String chooseNums = scan.next();
	        		//正则表达式进行匹配
	        		String zhengze = "[123456]";
	        		if(chooseNums.matches(zhengze)){
	        			//将String类型的数据转换成int型
	        			int chooseNum = Integer.parseInt(chooseNums);
	        			if(chooseNum == 1) {
		        			//调用存钱方法，同时将UserBean中的钱balance进行修改
		        			userBean.setBalance(saveMoney(userBean.getBalance())); 
		        			break;
		        		} else if (chooseNum == 2) {
		        			//调用取钱方法，同时将UserBean中的钱balance进行修改
		        			userBean.setBalance(getMoney(userBean.getBalance()));
		        			break;
		        		} else if (chooseNum == 3) {
		        			//调用本类的查询方法
		        			this.queryMoney();   
		        			break;
		        		} else if (chooseNum == 4) {
		        			//调用本类的转账方法
		        			this.virement();
		        			break;
		        		} else if (chooseNum == 5) {
		        			//调用本类的修改密码方法
		        			this.changePWD();
		        			break;
		        		} else if (chooseNum == 6) {
		        			//调用本类的退出方法
		        			this.exitSystem();  
		        			break;
		        		} 
	        		}else {
	        			System.out.println("对不起！您选择的业务不存在...");    
	        		}
	        	}catch(InputMismatchException ie){           //类型不匹配异常
	        		System.out.println("小朋友，别乱来哟~~~");
	        	} 
	        }
	    }     
	} 
	
	
	/*
	 * 存款
	 * */
	private float saveMoney(float balance){
		while(1 == 1){
		Scanner scan = new Scanner(System.in);
		System.out.println("请输入您的存款金额：");
		//定义一个String型变量，接收用户输入的金额
		String storeMoney = scan.next();
		//进行正则判断
		String zhengze = "[1-9][0-9]{0,}[0][0]";
		//进行逻辑判断，存入金额是100的整数倍，且存入的金额加上ATM中的原始金额不超过最大额度
		if(storeMoney.matches(zhengze)){
			//将String类型的数据转换成int型
			float storeMoneys = Float.parseFloat(storeMoney);
			if(storeMoneys + this.atmBal <= this.atmMaxMoney) {
				 balance += storeMoneys; 
			    	this.atmBal += storeMoneys;
			    	//修改属性文件中的金额
			    	try {
						ps.load(new FileInputStream("ps.properties"));
						String usn = this.userBean.getUserName();
						System.out.println(usn);
						String pAb = this.userBean.getPassWord() + "&" + (this.userBean.getBalance() + storeMoneys);
						System.out.println(pAb);
						ps.setProperty(usn,pAb);
						ps.store(new FileOutputStream("ps.properties"), null);
					} catch (FileNotFoundException e) {
						// TODO 自动生成 catch 块
						e.printStackTrace();
					} catch (IOException e) {
						// TODO 自动生成 catch 块
						e.printStackTrace();
					}
			    	System.out.println(this.atmBal);
			    	//打印取款的日期
			        Date daytime = new Date();
			        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			        String time = sim.format(daytime);
			        System.out.println("您在" + time + "存款" + storeMoneys + "元！操作已成功！" );   
			        break;
			    } else if(storeMoneys + this.atmBal > this.atmMaxMoney) {
			    	System.out.println("对不起！存款金额过大，请重新输入！");
				}
			}else {
		    	System.out.println("对不起！您的输入不正确，请重新输入！");    
		    }
		}
		return balance;
	}
	
	/*
	 * 取款
	 * */
	private float getMoney(float balance) {
		Scanner scan = new Scanner(System.in);
		//定义一个变量，用来累计用户总共取走的金额数
		float sum = 0;
	    System.out.print("请输入您的取款金额：");
	    //定义一个String类型的变量，接收用户的输入
	    String getMon = scan.next();
	    
	    //判断用户输入的金额是否是100的整数倍，是否小于单次取款最高额度，是否小于账户余额，累计取款是否小于2000，是否小于ATM的余额
	    //使用正则表达式验证输入的金额是否合法
	    String zhengze1 = "[1-9][0-9]{0,1}[0][0]";
	    if(getMon.matches(zhengze1)){
	    	//将String类型的数据转换成int型
		    float getMoney = Float.parseFloat(getMon);
	    	if(getMoney <= balance && sum + getMoney <= 20000.0 && getMoney <= this.atmBal) {
		    	balance -= getMoney;    
		        sum += getMoney;
		        this.atmBal -= getMoney;
//		      修改属性文件中的金额
		    	try {
					ps.load(new FileInputStream("ps.properties"));
					String usn = this.userBean.getUserName();
					System.out.println(usn);
					String pAb = this.userBean.getPassWord() + "&" + (this.userBean.getBalance() - getMoney);
					System.out.println(pAb);
					ps.setProperty(usn,pAb);
					ps.store(new FileOutputStream("ps.properties"), null);
				} catch (FileNotFoundException e) {
					// TODO 自动生成 catch 块
					e.printStackTrace();
				} catch (IOException e) {
					// TODO 自动生成 catch 块
					e.printStackTrace();
				}
		        //打印取款的日期
		        Date daytime = new Date();
		        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        String time = sim.format(daytime);
		        System.out.println("您在" + time + "取款" + getMoney + "元！操作已成功！" ); 
		    } else if(getMoney > balance) { 
		    	System.out.println("对不起！您的余额不足,取款失败！");     
		    } else if(getMoney > 5000) { 
		    	System.out.println("对不起！您输入的金额超出单次取款最高限额，取款失败！");     
		    } else if(sum + getMoney > 20000) {
		    	System.out.println("对不起！您的取款总额超出当日最高限额，取款失败！");
		    } else if(getMoney > this.atmBal) {
		    	System.out.println("对不起！ATM机现金不足，取款失败");
		    } 
	    }else {
	    	System.out.println("对不起！您的输入不正确，取款失败！");    
	    }
	    return balance;    
	}
	
	/*
	 * 查询余额
	 * */
	private void queryMoney() {
		System.out.println("您的余额为：" + this.userBean.getBalance());   //直接调用UserBean中的余额
	}
	
	/*
	 * 转账
	 * */
	private void virement() {
		System.out.println("此业务尚未开放，敬请期待...");
	}
	
	/*
	 * 修改密码
	 * */
	private void changePWD() {
		Scanner scan = new Scanner(System.in);
		System.out.print("请输入新密码：");
		String thePWD = scan.next();
		System.out.print("请再次输入新密码：");
		String thePWD1 = scan.next();
		if(thePWD.equals(thePWD1)) {
			//使用set方法，将新密码放入UserBean中
			userBean.setPassWord(thePWD);
			//修改属性文件中的密码
			try {
				ps.load(new FileInputStream("ps.properties"));
				String usn = this.userBean.getUserName();
				System.out.println(usn);
				String pAb = thePWD1 + "&" + (this.userBean.getBalance());
				System.out.println(pAb);
				ps.setProperty(usn,pAb);
				ps.store(new FileOutputStream("ps.properties"), null);
			} catch (FileNotFoundException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
			System.out.println("密码修改成功！");
		} else {
			System.out.println("对不起！两次输入的新密码不相同，修改密码失败！");
		}
	}
	
	/*
	 * 退出
	 * */
	private void exitSystem() {
		System.out.println("退出成功！");
		System.exit(0);
	}
}
