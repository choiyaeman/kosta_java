import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class JCF {//JavaCollectionFramework
	public static void test(Collection c) {
		c.add(new Object());
		c.add(new String("1"));
		c.add(new Integer(2));
		c.add(3.0); //컴파일시 c.add(new Double(3.0));
		c.add(3.0);
		
		System.out.println("자료수:" + c.size());
		System.out.println("자료들:" + c); //c.toString()자동호출됨
		Iterator iterator = c.iterator();
		while(iterator.hasNext()) {
			Object element = iterator.next();
			System.out.println("자료:" + element);
		}
	}
	
	public static void test(Map m) {
		m.put("one", new Object());
		m.put("two", new String("1"));
		m.put("three", new Integer(2));
		m.put("four", 3.0);//값중복
		m.put("five", 3.0);//값중복
		m.put("two", true);//키중복
		System.out.println("----Map----");
		System.out.println("자료수:" + m.size());
		System.out.println("자료들:" + m);
		
		Set keys = m.keySet();
		Iterator iterator = keys.iterator();
		while(iterator.hasNext()) {
			Object key = iterator.next();
			Object element = m.get(key);
			System.out.println("자료:키=" + key + ",값=" + element);
		}
		
		
	}
	public static void main(String[] args) {
		Collection c;
		//c = new Collection();		//c = new List();
		c = new ArrayList();
		test(c);
		
		c = new HashSet();
		test(c);

		Map m = new HashMap();
		test(m);
	}
}