package br.com.eumesmo;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.eumesmo.DAO.ProdutoDAO;
import br.com.eumesmo.model.Produto;

public class TestarInsercao {
	public static void main(String[] args) {
		Produto produto = new Produto("Carro", "Vrum Vrum");
		try (Connection connection = new DB().getConnection()) {
			connection.setAutoCommit(false);
			
			ProdutoDAO produtoDAO = new ProdutoDAO(connection);
			
			System.out.println(produtoDAO.addToDb(produto));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
