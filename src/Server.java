import java.net.*;
import java.io.*;

public class Server extends Thread{
      static ServerSocket ss=null;
      Socket clientSocket=null;
      
     
	private Server(Socket s){
	    this.clientSocket=s;
		start();
	}
	
	//多线程处理客户端请求
	public void run(){
		BufferedReader b=null;
		PrintWriter p=null;
		String str;
		try {b=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			p=new PrintWriter(clientSocket.getOutputStream(),true);
				
			while((str=b.readLine())!=null){
				System.out.println("服务器所接受的字符串:"+str);
				String newStr=Reverse(str);//服务器返回逆序字符串
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
	
	 public String Reverse(String str){//字符串逆序
 		String newStr=null;
 		StringBuffer sb=new StringBuffer(str);
 		newStr=sb.reverse().toString();
 		return newStr;
 	}
	 
	 public static void main(String[] args){
	 
		 try {
			ss=new ServerSocket(3333);//创建监听3333的主线程
			while(true){
				new Server(ss.accept());//启用线程处理客户端请求
			}
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		 
	 }
}
