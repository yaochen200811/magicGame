package magicGame;

public class test {
	public static void main(String[] args) {
		A a = new A();
		a.ac +=1;
		B b = new B();
		b.ac += 1;
		Integer[] arr = new Integer[5];
		arr[0] = null;
		for (Integer i: arr) {
			System.out.println(i);
		}
		String pu = "^utorid: ([a-z]+\\d*)$";
		String pa = "^assignments:(1?[0-9]|20)/20 ([1-2]?[0-9]|30)/30$";
		String pl = "^lab\\d:([0-2])/2$";
		String pe = "^end$";
	}
}

class A {
	public int a[] = { 1 };
	static int ac = 1;

	public A() {
		System.out.println(1);
	}

	public void aMe() {
		System.out.println(1);
	}

}

class B extends A {
	public int[] a = { 3 };


	public void aMe() {
		System.out.println(2);
	}
}