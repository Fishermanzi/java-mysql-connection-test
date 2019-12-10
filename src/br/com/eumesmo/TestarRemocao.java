package br.com.eumesmo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TestarRemocao {
	public static void main(String[] args) {
		try (Connection connection = new DB().getConnection()) {
			
			try (Statement statement = connection.createStatement()) {
				statement.execute("delete from Produto where id > 12");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
