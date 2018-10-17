package magicGame;

public class test {
	public static void main(String[] args) {
		A a = new A();
		a.a = 1;
		A a2 = new A();
		a2.a += 1;
		A.a += 1;
		B b = new B();
		b.a += 1;
		B.a += 1;
		System.out.println(A.a);
	}
}

class A{
	public static int a;
	
	A(){
	}
}

class B extends A{
	B(){
		
	}
}