package br.com.eumesmo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.eumesmo.model.Produto;

public class ProdutoDAO {

	private Connection connection;
	private String sqlInsert = "insert into produto (nome,descricao) values(?,?)";

	public ProdutoDAO(Connection connection) {
		this.connection = connection;
	}

	public List<Produto> getList() {
		String sql = "select * from produto";
		List<Produto> produtos = new ArrayList<>();

		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.execute();

			try (ResultSet resultSet = statement.getResultSet()) {
				while (resultSet.next()) {
					produtos.add(new Produto(resultSet.getInt("id"), resultSet.getString("nome"),
							resultSet.getString("descricao")));
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return produtos;
	}

	@SuppressWarnings("finally")
	public int addToDb(Produto produto) {
		int num = 0;
		try (PreparedStatement statement = connection.prepareStatement(sqlInsert,Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, produto.getNome());
			statement.setString(2, produto.getDescricao());

			statement.execute();

			connection.commit();
			try (ResultSet resultSet = statement.getGeneratedKeys()) {
				while (resultSet.next())
		            num = resultSet.getInt(1);
			}
			
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			} finally {
				e.printStackTrace();
			}
		}
		finally {
			return num;
		}
	}

}
