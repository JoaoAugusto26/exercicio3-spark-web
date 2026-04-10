package aplicacao;

import static spark.Spark.*;
import java.util.List;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		// Define a porta onde o nosso servidor web vai rodar
		port(4567);
		
		DAO dao = new DAO();

		// Rota 1: Quando alguém acessar o site, mostramos o Formulário HTML
		get("/usuarios", (request, response) -> {
			String html = "<html><head><meta charset='UTF-8'><title>Cadastro Web</title></head>";
			html += "<body style='font-family: Arial, sans-serif; padding: 20px;'>";
			html += "<h2>Novo Usuário (Via Web!)</h2>";
			
			// Formulário HTML para inserir dados
			html += "<form action='/inserir' method='POST'>";
			html += "Nome: <input type='text' name='nome' required> <br><br>";
			html += "Idade: <input type='number' name='idade' required> <br><br>";
			html += "<button type='submit' style='padding: 5px 15px;'>Cadastrar no Banco</button>";
			html += "</form>";

			// Lista os usuários que já estão no banco de dados
			html += "<hr><h2>Usuários Cadastrados:</h2><ul>";
			List<Usuario> lista = dao.listar();
			for (Usuario u : lista) {
				html += "<li>" + u.getNome() + " (Idade: " + u.getIdade() + ")</li>";
			}
			html += "</ul></body></html>";

			return html;
		});

		// Rota 2: Onde o formulário envia os dados (método POST)
		post("/inserir", (request, response) -> {
			// Captura o que foi digitado nas caixinhas de texto da página web
			String nomeInput = request.queryParams("nome");
			String idadeInput = request.queryParams("idade");

			Scanner scannerNome = new Scanner(nomeInput);
			String nome = scannerNome.nextLine();
			
			Scanner scannerIdade = new Scanner(idadeInput);
			int idade = scannerIdade.nextInt();

			// Salva no banco de dados usando o DAO que você já tinha criado
			Usuario novoUsuario = new Usuario(0, nome, idade);
			dao.inserir(novoUsuario);

			// Redireciona a página de volta para a lista para vermos a atualização
			response.redirect("/usuarios");
			return "";
		});
	}
}