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
	 * ����������߳�������������
	 * */
	
	//����һ��UserBean���͵ı������������ö���
	private UserBean userBean;
	//ATM���ĵ�ǰ�ֽ�
	private float atmBal;
	//ATM�ܴ�ŵ������,����Ϊ��������Ϊ������ǲ�����ĵ�
	private final float atmMaxMoney = 100000.0f;
	//����û���Ϣ
	private Map<String, UserBean> userMap;
	//���������ļ�����
	private Properties ps;
	
	/*
	 * ����һ��run����������������еķ���
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
	 * �ü��ϴ������
	 * 
	private void loadData(){
		//�Ե�ǰ�ֽ���г�ʼ��
		this.atmBal = "10000.0f";
		//����һ�����ϣ�����û���Ϣ
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
	 * ʹ�������ļ��������
	 * */
	private void loadData(){
		//�Ե�ǰ�ֽ���г�ʼ��
		this.atmBal = 10000.0f;
		//����һ��Properties����
		ps = new Properties();
		try {
			//�������ȡ�������������ļ�������ݣ����뵽properties������
			ps.load(new FileInputStream("ps.properties"));
		} catch (FileNotFoundException e1) {
			// TODO �Զ����� catch ��
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO �Զ����� catch ��
			e1.printStackTrace();
		}
		//�ж������ļ����Ƿ������ݣ�û�����ݵĻ������¼��أ������ݵĻ����Ͳ�ִ�м��ش��룬�Ӷ���֤���ݳ־û�
		if(ps.get("liulu1") == null){
			//����ֵ��������map��put����,����ֵ��ֻ��ΪString���ͣ���map�п��Է����������͵�����
			for(int i = 1;i <= 5;i ++){
				ps.setProperty("liulu" + i, "12345" + i + "&" + 1000.0*i);
				try {
					ps.store(new FileOutputStream("ps.properties"), null);
				} catch (IOException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
			}
		}
		
	}
	
	/*
	 * ��ӭ����
	 * */
	private void welcome(){
		System.out.println("******************************************************");
        System.out.println("*                                                    *");
        System.out.println("*============== ��ӭʹ��666666����ATM�� ==============*");
        System.out.println("*                                                    *");
        System.out.println("******************************************************");
	}
	
	
	/*
	 * ��֤�û���½
	 * */
	private void login(){
		for(int i = 1;i <= 3;i ++){
			//�����û������ֵ�������ڴ���
			Scanner scan = new Scanner(System.in);
			//��ʾ�û������û���
			System.out.println("�������û�����");
			//����һ���ַ������͵ı���������û�������û���
			String un = scan.next();
			//��ʾ�û���������
			System.out.println("���������룺");
			//����һ���ַ������͵ı���������û����������
			String pwd = scan.next();
			
			//�����߼��жϣ����û��������ϢͬUserBean�д�ŵ���Ϣ���бȽ�
			//�ַ���֮��ıȽ�Ҫʹ��equals()�������бȽ�
			//userBean.getUserName()��ȡ�������userBean�е��û���
//�ü��ϵķ�ʽʵ��
//			if(userMap.get(un) != null){
//				userBean = userMap.get(un);
//				System.out.println("��ӭ����" + userBean.getUserName());
//				break;
//			}else{
//				//�û��ۼ����3�Σ��˳�����
//				if(i == 3){
//					System.out.println("���ۼ���������������Σ�������˻��������˳�������");
//					//�����ֱ����ֹ��������У�ǿ���˳�����
//					System.exit(0);
//				}else{
//					System.out.println("�Բ����û����������������������...");   
//				}
//			}
			/*
			 * ʹ�������ļ��ķ�ʽʵ��
			 * */
			//ȡ�������ļ��д�ŵļ���ֵ
			try {
				ps.load(new FileInputStream("ps.properties"));
				if(ps.getProperty(un) != null){
					String pwdAndBal = ps.getProperty(un);
					String [] login  = pwdAndBal.split("&");
					if(pwd.equals(login[0])){    //�ж�����ƥ��󣬽���ǰ������������Ը�����ǰuserBean����
						userBean = new UserBean();
						userBean.setUserName(un);
						userBean.setPassWord(login[0]);
						userBean.setBalance(Float.parseFloat(login[1]));
						System.out.println("��ӭ����" + userBean.getUserName());
						break;
					}else{
						System.out.println("�Բ����û����������������������...");   
					}
				}else{
					//�û��ۼ����3�Σ��˳�����
					if(i == 3){
						System.out.println("���ۼ���������������Σ�������˻��������˳�������");
						//�����ֱ����ֹ��������У�ǿ���˳�����
						System.exit(0);
					}else{
						System.out.println("�Բ����û����������������������...");   
					}
				}
			} catch (FileNotFoundException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			} catch (IOException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
			
		}
	}
	
	
	/*
	 * ҵ��ѡ��
	 * */
	private int choice() {
		
		while(true) {      
	        System.out.println("*************************************************");
	        System.out.println("*                                                                                                      *");
	        System.out.println("*     1.���    2.ȡ��    3.��ѯ    4.ת��    5.�޸�����     6.�˳�        *");
	        System.out.println("*                                                                                                      *");
	        System.out.println("*************************************************");
	        while(true){
	        	//�����쳣
	        	try{
	        		System.out.print("��ѡ������Ҫ��ҵ��");
	        		Scanner scan = new Scanner(System.in);
	        		//����һ��String�ͱ����������û�����Ľ��
	        		String chooseNums = scan.next();
	        		//������ʽ����ƥ��
	        		String zhengze = "[123456]";
	        		if(chooseNums.matches(zhengze)){
	        			//��String���͵�����ת����int��
	        			int chooseNum = Integer.parseInt(chooseNums);
	        			if(chooseNum == 1) {
		        			//���ô�Ǯ������ͬʱ��UserBean�е�Ǯbalance�����޸�
		        			userBean.setBalance(saveMoney(userBean.getBalance())); 
		        			break;
		        		} else if (chooseNum == 2) {
		        			//����ȡǮ������ͬʱ��UserBean�е�Ǯbalance�����޸�
		        			userBean.setBalance(getMoney(userBean.getBalance()));
		        			break;
		        		} else if (chooseNum == 3) {
		        			//���ñ���Ĳ�ѯ����
		        			this.queryMoney();   
		        			break;
		        		} else if (chooseNum == 4) {
		        			//���ñ����ת�˷���
		        			this.virement();
		        			break;
		        		} else if (chooseNum == 5) {
		        			//���ñ�����޸����뷽��
		        			this.changePWD();
		        			break;
		        		} else if (chooseNum == 6) {
		        			//���ñ�����˳�����
		        			this.exitSystem();  
		        			break;
		        		} 
	        		}else {
	        			System.out.println("�Բ�����ѡ���ҵ�񲻴���...");    
	        		}
	        	}catch(InputMismatchException ie){           //���Ͳ�ƥ���쳣
	        		System.out.println("С���ѣ�������Ӵ~~~");
	        	} 
	        }
	    }     
	} 
	
	
	/*
	 * ���
	 * */
	private float saveMoney(float balance){
		while(1 == 1){
		Scanner scan = new Scanner(System.in);
		System.out.println("���������Ĵ���");
		//����һ��String�ͱ����������û�����Ľ��
		String storeMoney = scan.next();
		//���������ж�
		String zhengze = "[1-9][0-9]{0,}[0][0]";
		//�����߼��жϣ���������100�����������Ҵ���Ľ�����ATM�е�ԭʼ�����������
		if(storeMoney.matches(zhengze)){
			//��String���͵�����ת����int��
			float storeMoneys = Float.parseFloat(storeMoney);
			if(storeMoneys + this.atmBal <= this.atmMaxMoney) {
				 balance += storeMoneys; 
			    	this.atmBal += storeMoneys;
			    	//�޸������ļ��еĽ��
			    	try {
						ps.load(new FileInputStream("ps.properties"));
						String usn = this.userBean.getUserName();
						System.out.println(usn);
						String pAb = this.userBean.getPassWord() + "&" + (this.userBean.getBalance() + storeMoneys);
						System.out.println(pAb);
						ps.setProperty(usn,pAb);
						ps.store(new FileOutputStream("ps.properties"), null);
					} catch (FileNotFoundException e) {
						// TODO �Զ����� catch ��
						e.printStackTrace();
					} catch (IOException e) {
						// TODO �Զ����� catch ��
						e.printStackTrace();
					}
			    	System.out.println(this.atmBal);
			    	//��ӡȡ�������
			        Date daytime = new Date();
			        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			        String time = sim.format(daytime);
			        System.out.println("����" + time + "���" + storeMoneys + "Ԫ�������ѳɹ���" );   
			        break;
			    } else if(storeMoneys + this.atmBal > this.atmMaxMoney) {
			    	System.out.println("�Բ��𣡴����������������룡");
				}
			}else {
		    	System.out.println("�Բ����������벻��ȷ�����������룡");    
		    }
		}
		return balance;
	}
	
	/*
	 * ȡ��
	 * */
	private float getMoney(float balance) {
		Scanner scan = new Scanner(System.in);
		//����һ�������������ۼ��û��ܹ�ȡ�ߵĽ����
		float sum = 0;
	    System.out.print("����������ȡ���");
	    //����һ��String���͵ı����������û�������
	    String getMon = scan.next();
	    
	    //�ж��û�����Ľ���Ƿ���100�����������Ƿ�С�ڵ���ȡ����߶�ȣ��Ƿ�С���˻����ۼ�ȡ���Ƿ�С��2000���Ƿ�С��ATM�����
	    //ʹ��������ʽ��֤����Ľ���Ƿ�Ϸ�
	    String zhengze1 = "[1-9][0-9]{0,1}[0][0]";
	    if(getMon.matches(zhengze1)){
	    	//��String���͵�����ת����int��
		    float getMoney = Float.parseFloat(getMon);
	    	if(getMoney <= balance && sum + getMoney <= 20000.0 && getMoney <= this.atmBal) {
		    	balance -= getMoney;    
		        sum += getMoney;
		        this.atmBal -= getMoney;
//		      �޸������ļ��еĽ��
		    	try {
					ps.load(new FileInputStream("ps.properties"));
					String usn = this.userBean.getUserName();
					System.out.println(usn);
					String pAb = this.userBean.getPassWord() + "&" + (this.userBean.getBalance() - getMoney);
					System.out.println(pAb);
					ps.setProperty(usn,pAb);
					ps.store(new FileOutputStream("ps.properties"), null);
				} catch (FileNotFoundException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				} catch (IOException e) {
					// TODO �Զ����� catch ��
					e.printStackTrace();
				}
		        //��ӡȡ�������
		        Date daytime = new Date();
		        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        String time = sim.format(daytime);
		        System.out.println("����" + time + "ȡ��" + getMoney + "Ԫ�������ѳɹ���" ); 
		    } else if(getMoney > balance) { 
		    	System.out.println("�Բ�����������,ȡ��ʧ�ܣ�");     
		    } else if(getMoney > 5000) { 
		    	System.out.println("�Բ���������Ľ�������ȡ������޶ȡ��ʧ�ܣ�");     
		    } else if(sum + getMoney > 20000) {
		    	System.out.println("�Բ�������ȡ���ܶ����������޶ȡ��ʧ�ܣ�");
		    } else if(getMoney > this.atmBal) {
		    	System.out.println("�Բ���ATM���ֽ��㣬ȡ��ʧ��");
		    } 
	    }else {
	    	System.out.println("�Բ����������벻��ȷ��ȡ��ʧ�ܣ�");    
	    }
	    return balance;    
	}
	
	/*
	 * ��ѯ���
	 * */
	private void queryMoney() {
		System.out.println("�������Ϊ��" + this.userBean.getBalance());   //ֱ�ӵ���UserBean�е����
	}
	
	/*
	 * ת��
	 * */
	private void virement() {
		System.out.println("��ҵ����δ���ţ������ڴ�...");
	}
	
	/*
	 * �޸�����
	 * */
	private void changePWD() {
		Scanner scan = new Scanner(System.in);
		System.out.print("�����������룺");
		String thePWD = scan.next();
		System.out.print("���ٴ����������룺");
		String thePWD1 = scan.next();
		if(thePWD.equals(thePWD1)) {
			//ʹ��set�����������������UserBean��
			userBean.setPassWord(thePWD);
			//�޸������ļ��е�����
			try {
				ps.load(new FileInputStream("ps.properties"));
				String usn = this.userBean.getUserName();
				System.out.println(usn);
				String pAb = thePWD1 + "&" + (this.userBean.getBalance());
				System.out.println(pAb);
				ps.setProperty(usn,pAb);
				ps.store(new FileOutputStream("ps.properties"), null);
			} catch (FileNotFoundException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			} catch (IOException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
			System.out.println("�����޸ĳɹ���");
		} else {
			System.out.println("�Բ�����������������벻��ͬ���޸�����ʧ�ܣ�");
		}
	}
	
	/*
	 * �˳�
	 * */
	private void exitSystem() {
		System.out.println("�˳��ɹ���");
		System.exit(0);
	}
}
