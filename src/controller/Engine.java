package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import model.Client;

/**
 * @author mra2
 * 
 */

public class Engine {

	static final String driver = "oracle.jdbc.driver.OracleDriver";
	
	//example = jdbc:oracle:driver_type:[username/password]@database_specifier
	static final String URL = "jdbc:oracle:thin:hr/hr@localhost:1521/XE";

	static final String username = "system";
	static final String password = ""; //local password

	public static Connection connectToDatabase(String username, String password)
			throws ClassNotFoundException {

		Connection con = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(URL, username, password);
		} catch (ClassNotFoundException exc1) {
			exc1.printStackTrace();
		} catch (SQLException exc2) {
			exc2.printStackTrace();
		}

		return con;
	}

	public void Insert(Client c) throws SQLException, ClassNotFoundException {

		/*
		 * INSERT INTO tb_cliente VALUES(tp_cliente('785.895.165-47', 'Jair
		 * Bolsonaro', '12/12/1970', EMPTY_BLOB(),
		 * tp_contato('jairbolsonaro@email.com',
		 * tp_va_telefones(tp_telefone('81', '68011620'))),(SELECT REF(e) FROM
		 * tb_endereco e WHERE e.id_endereco = 3), (SELECT REF(f) FROM
		 * tb_funcionario f WHERE f.cpf ='510.362.857-78')));
		 * 
		 * 
		 * INSERT INTO tb_cliente VALUES(tp_cliente('" +
		 * c.getCpf() + "', '" + // c.getName() + "', '" + c.getDate() +
		 * "', EMPTY_BLOB(), tp_contato('" // + c.getEmail() + "',
		 * tp_va_telefones(tp_telefone('81', // '68011620'))),(SELECT REF(e)
		 * FROM tb_endereco e WHERE e.id_endereco = // 3), (SELECT REF(f) FROM
		 * tb_funcionario f WHERE f.cpf // ='510.362.857-78')));
		 */

		int count_id = 30;
		Connection con = connectToDatabase(username, password);
		Statement stmt = con.createStatement();

		int ID = count_id++;
		String cpf_Func = "510.362.857-78";

		String i = "INSERT INTO tb_cliente VALUES(tp_cliente('"
				+ c.getCpf()
				+ "', '"
				+ c.getName()
				+ "', '"
				+ c.getDate()
				+ "', EMPTY_BLOB(), tp_contato('"
				+ c.getEmail()
				+ "',  tp_va_telefones(tp_telefone('81', '"
				+ c.getPhone()
				+ "'))),(SELECT REF(e) FROM tb_endereco e WHERE e.id_endereco = "
				+ ID + "), (SELECT REF(f) FROM tb_funcionario f WHERE f.cpf ='"
				+ cpf_Func + " ')));";

		stmt.executeQuery(i);
		stmt.close();
		con.close();
	}
}
