package interthreadcomm;

public class InterThread {

	public static void main(String[] args) {
		Q q = new Q();
		new Producer(q);
		new Consumer(q);

	}

}

class Q
{
 int num;
 boolean verify = false;
 
 public synchronized void put(int i) {
	 while(verify)
	 {
		try {
		 wait();
		}catch(Exception e) {}
	 }
	 num=i;
	 verify = true;
	 System.out.println("Put "+ num);
	 notify();
 }
 public synchronized void get()
 {
	 while(!verify)
	 {
			try {
				 wait();
				}catch(Exception e) {}
	 }
	 verify =false;
	 System.out.println("Get "+num);
	 notify();
 }
	
}

class Producer implements Runnable
{
	Q q;

	public Producer(Q q) {
		
		this.q = q;
Thread t=	 new Thread(this,"Producer");
t.start();
	}

	@Override
	public void run() {
	 int i=0;
	 while(true)
	 {
		 q.put(i++);
		 try {Thread.sleep(1000);}catch(Exception e) {}
	 }
		
	}
}
class Consumer implements Runnable
{
	Q q;

	public Consumer(Q q) {
		
		this.q = q;
		Thread t=	 new Thread(this,"Consumer");
		t.start();
	}

	@Override
	public void run() {
	
	 while(true)
	 {
		 q.get();
		 try {Thread.sleep(1000);}catch(Exception e) {}
	 }
		
	}
}
