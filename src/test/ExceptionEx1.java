package test;

import java.sql.SQLException;
import java.sql.SQLTransientException;

public class ExceptionEx1 {

	public void main() {
		A a = new B();
		try {
			a.method();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class B implements A {
	public void method() throws SQLTransientException {
	}
}
interface A {
	void method() throws SQLException ;
}





