package steam;

import java.util.ArrayList;
import java.util.Set;

public class Jogo {

	private String nome;
	private double preco;
	private int maxScore;
	private int vezesJogado;
	private int vezesZerado;
	private Set<Jogabilidade> jogabilidades;
	
	public Jogo(String nome, double preco, Set<Jogabilidade> jogabilidade) throws Exception { 
		if ((nome == null) || (nome.trim().equals(""))){
			throw new Exception("Nome nao pode ser null ou vazio.");
		}
		this.nome = nome;
		
		if (preco <= 0){
			throw new Exception("Preco nao pode ser menor ou igual a 0");
		}
		this.preco = preco;
		
		this.maxScore = 0;
		this.vezesJogado = 0;
		this.vezesZerado = 0;
		this.jogabilidades = jogabilidade;
		
	}
	
	public int registraJogada(int score, boolean zerou){
		if (maxScore < score){
			maxScore = score;
		}
		return maxScore;
	}
	

	public void adicionaJogabilidade(Jogabilidade jogabilidade) throws Exception { 
		
		if (jogabilidades.contains(jogabilidade)){
			throw new Exception("Jogabilidade " + jogabilidade + " já existe");
		}
		jogabilidades.add(jogabilidade);
	}
}
