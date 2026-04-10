package aplicacao;

public class Usuario {
	private int id;
	private String nome;
	private int idade;

	// Construtor vazio
	public Usuario() {
	}

	// Construtor com dados
	public Usuario(int id, String nome, int idade) {
		this.id = id;
		this.nome = nome;
		this.idade = idade;
	}

	// Getters e Setters (para pegar e mudar os dados)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	// Metodo para imprimir o usuario bonitinho na tela
	@Override
	public String toString() {
		return "ID: " + id + " | Nome: " + nome + " | Idade: " + idade;
	}
}