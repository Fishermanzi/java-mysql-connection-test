package br.com.eumesmo;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import br.com.eumesmo.strings.StaticStrings;

public class DB {

	private DataSource datasoure;

	public DB() {
		try {
			ComboPooledDataSource pool = new ComboPooledDataSource();
			pool.setDriverClass("com.mysql.jdbc.Driver");
			pool.setJdbcUrl(StaticStrings.bancoUrl);
			pool.setUser(StaticStrings.bancoUsuario);
			pool.setPassword(StaticStrings.bancoSenha);
			pool.setMinPoolSize(1);
			pool.setAcquireIncrement(5);
			pool.setMaxPoolSize(10);

			datasoure = pool;

		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getConnection() throws SQLException {
		return datasoure.getConnection();

	}
}
