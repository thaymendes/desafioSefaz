package br.com.thaymendes.desafiosefaz.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.thaymendes.desafiosefaz.entidade.Lancamento;

public class LancamentoDao {
	private String urlBanco = "jdbc:mysql://localhost:3306/desafio?useSSL=false";
	private String usuarioBanco = "root";
	private String senhaBanco = "";
	private static Connection connection = null;

	public Connection conectar() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(urlBanco, usuarioBanco, senhaBanco);
			System.out.println("Deu certo!!!!!!");
		} catch (SQLException e) {
			System.out.println("Deu errado!!!!!!");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Deu errado!!!!!!");
			e.printStackTrace();
		}
		return connection;
	}

	public static boolean fecharConexao() {
		try {
			connection.close();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public void insereLancamento(Lancamento lancamento) throws SQLException {

		try (Connection connection = conectar();
				PreparedStatement ps = connection.prepareStatement(
						"INSERT INTO lancamento (descricao, tipo, vencimento, status, valor) VALUES (?, ?, ?, ?, ?)")) {
			ps.setString(1, lancamento.getDescricao());
			ps.setString(2, lancamento.getTipo());
			ps.setDate(3, new Date(lancamento.getVencimento().getTime()));
			ps.setBoolean(4, lancamento.isStatus());
			ps.setDouble(5, lancamento.getValor());

			ps.executeUpdate();
			fecharConexao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Lancamento> buscarTodosLancamentos() {

		List<Lancamento> busca = new ArrayList<>();

		try (Connection connection = conectar();
				PreparedStatement preparedStatement = connection.prepareStatement("select * from Lancamento");) {

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Lancamento l1 = new Lancamento();
				l1.setDescricao(rs.getString(2));
				l1.setTipo(rs.getString(3));
				l1.setVencimento(rs.getDate(4));
				l1.setStatus(rs.getBoolean(5));
				l1.setValor(rs.getDouble(6));
				busca.add(l1);

			}
			rs.close();
			fecharConexao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return busca;

	}

	public Lancamento buscarLancamentoPorId(int id) {
		Lancamento lancamento = new Lancamento();
		try (Connection connection = conectar();
				PreparedStatement preparedStatement = connection
						.prepareStatement("select * from lancamento where id = ?");) {
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				lancamento.setId(rs.getInt(1));
				lancamento.setDescricao(rs.getString(2));
				lancamento.setTipo(rs.getString(3));
				lancamento.setVencimento(rs.getDate(4));
				lancamento.setStatus(rs.getBoolean(5));
				lancamento.setValor(rs.getDouble(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lancamento;
	}

	public boolean atualizaLanamento(Lancamento lancamento) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = conectar();
				PreparedStatement ps = connection.prepareStatement(
						"update lancamento set descricao = ?, tipo =?, vencimento = ?, status  = ?, valor = ? where id = ?");) {
			ps.setString(1, lancamento.getDescricao());
			ps.setString(2, lancamento.getTipo());
			ps.setDate(3, new Date(lancamento.getVencimento().getTime()));
			ps.setBoolean(4, lancamento.isStatus());
			ps.setDouble(5, lancamento.getValor());
			ps.setInt(6, lancamento.getId());

			rowUpdated = ps.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	public boolean apagarLancamento(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = conectar();
				PreparedStatement ps = connection.prepareStatement("delete from lancamento where id = ?");) {
			ps.setInt(1, id);
			rowDeleted = ps.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public static void main(String[] args) throws SQLException {
//		LancamentoDao dao = new LancamentoDao();
//		Lancamento l1 = new Lancamento();
//		l1.setDescricao("Teste descri??o");
//		l1.setTipo("Receita");
//		l1.setVencimento(new java.util.Date());
//		l1.setStatus(true);
//		l1.setValor(3000.00);
//
//		dao.insereLancamento(l1);
//		System.out.println(dao.buscarTodosLancamentos());
//		System.out.println(dao.buscarLancamentoPorId(1));
//		dao.atualizaLanamento(l1);
//		dao.apagarLancamento(1);
//		
	}
}
