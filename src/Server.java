import java.net.*;
import java.io.*;

public class Server extends Thread{
      static ServerSocket ss=null;
      Socket clientSocket=null;
      
     
	private Server(Socket s){
	    this.clientSocket=s;
		start();
	}
	
	//���̴߳���ͻ�������
	public void run(){
		BufferedReader b=null;
		PrintWriter p=null;
		String str;
		try {b=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			p=new PrintWriter(clientSocket.getOutputStream(),true);
				
			while((str=b.readLine())!=null){
				System.out.println("�����������ܵ��ַ���:"+str);
				String newStr=Reverse(str);//���������������ַ���
				p.println(newStr);
				if(str==""){
					break;
				}
			}
			b.close();
			p.close();
			clientSocket.close();
			
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	 public String Reverse(String str){//�ַ�������
 		String newStr=null;
 		StringBuffer sb=new StringBuffer(str);
 		newStr=sb.reverse().toString();
 		return newStr;
 	}
	 
	 public static void main(String[] args){
	 
		 try {
			ss=new ServerSocket(3333);//��������3333�����߳�
			while(true){
				new Server(ss.accept());//�����̴߳���ͻ�������
			}
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		 
	 }
}
