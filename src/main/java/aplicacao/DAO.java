package aplicacao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAO {
	private Connection conexao;

	// Coloque a sua senha do PostgreSQL AQUI dentro das aspas!
	private String senhaDoBanco = "#Dinossaur0"; 

	public DAO() {
		try {
			// Conectando ao banco_exercicio no seu computador
			conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/banco_exercicio", "postgres", senhaDoBanco);
		} catch (Exception e) {
			System.out.println("Erro ao conectar no banco: " + e.getMessage());
		}
	}

	// C - Inserir
	public void inserir(Usuario usuario) {
		try {
			String sql = "INSERT INTO usuario (nome, idade) VALUES (?, ?)";
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, usuario.getNome());
			stmt.setInt(2, usuario.getIdade());
			stmt.executeUpdate();
			stmt.close();
			System.out.println("Sucesso: Usuário inserido no banco!");
		} catch (Exception e) {
			System.out.println("Erro ao inserir: " + e.getMessage());
		}
	}

	// R - Listar
	public List<Usuario> listar() {
		List<Usuario> lista = new ArrayList<>();
		try {
			String sql = "SELECT * FROM usuario";
			PreparedStatement stmt = conexao.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Usuario u = new Usuario();
				u.setId(rs.getInt("id"));
				u.setNome(rs.getString("nome"));
				u.setIdade(rs.getInt("idade"));
				lista.add(u);
			}
			stmt.close();
		} catch (Exception e) {
			System.out.println("Erro ao listar: " + e.getMessage());
		}
		return lista;
	}

	// U - Atualizar
	public void atualizar(Usuario usuario) {
		try {
			String sql = "UPDATE usuario SET nome = ?, idade = ? WHERE id = ?";
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, usuario.getNome());
			stmt.setInt(2, usuario.getIdade());
			stmt.setInt(3, usuario.getId());
			stmt.executeUpdate();
			stmt.close();
			System.out.println("Sucesso: Usuário atualizado no banco!");
		} catch (Exception e) {
			System.out.println("Erro ao atualizar: " + e.getMessage());
		}
	}

	// D - Excluir
	public void excluir(int id) {
		try {
			String sql = "DELETE FROM usuario WHERE id = ?";
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			stmt.close();
			System.out.println("Sucesso: Usuário excluído do banco!");
		} catch (Exception e) {
			System.out.println("Erro ao excluir: " + e.getMessage());
		}
	}
}