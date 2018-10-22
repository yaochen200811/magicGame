package magicGame;

public class test {
	public static void main(String[] args) {
		final int v1;
		A a = new B();
		System.out.println((a).a[0]);
	}
}

class A {
	public int a[] = { 1 };

	A() {
	}

	public void aMe() {
		System.out.println(1);
	}

}

class B extends A {
	public int[] a = { 3 };

	B() {

	}

	public void aMe() {
		System.out.println(2);
	}
}