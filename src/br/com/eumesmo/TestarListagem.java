package br.com.eumesmo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.eumesmo.DAO.ProdutoDAO;
import br.com.eumesmo.model.Produto;

public class TestarListagem {
	public static void main(String[] args) {
		
		try (Connection connection = new DB().getConnection()) {
			ProdutoDAO produtoDAO = new ProdutoDAO(connection);
			
			List<Produto> produtos = produtoDAO.getList();
			
			produtos.forEach(System.out::println);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
}
