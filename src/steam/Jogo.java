package steam;

import java.util.ArrayList;
import java.util.Set;


public class Jogo {

	
	private String nome;
	private double preco;
	protected int maxScore;
	private int vezesJogado;
	private int vezesZerado;
	private TipoDeJogo tipo;
	private Set<Jogabilidade> jogabilidades;
	
	
	public Jogo(String nome, double preco, Set<Jogabilidade> jogabilidade, TipoDeJogo tipo) throws Exception { 
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
		this.tipo = tipo;
	}
	
	//Como tenho o método "adicionaJogabilidade", o construtor também pode iniciar sem as jogabilidades.
	public Jogo(String nome, double preco, TipoDeJogo tipo) throws Exception { 
		if ((nome == null) || (nome.trim().equals(""))){
			throw new Exception("Nome nao pode ser null ou vazio.");
		}
		this.nome = nome;
		
		if (preco <= 0){
			throw new Exception("Preco nao pode ser menor ou igual a 0");
		}
		this.preco = preco;
		

		this.tipo = tipo;
		this.maxScore = 0;
		this.vezesJogado = 0;
		this.vezesZerado = 0;
	}
	
	public int registraJogada(int score, boolean zerou)throws Exception{
		int ex2p = 0;

		if(zerou){
			this.vezesZerado ++;
		}
		if(score > maxScore){
			if(tipo.equals(TipoDeJogo.LUTA) && score > 100000){
				throw new Exception("Score de Luta deve ser menor ou igual a 100000");
			}
			this.maxScore = score;	
		} 
		
		if(this.tipo.equals(TipoDeJogo.RPG)){
			ex2p+=10;
		}
		else if(this.tipo.equals(TipoDeJogo.LUTA)){
			ex2p = (int) ((double)(ex2p) + (double)(maxScore/1000));
		}
		else if(this.tipo.equals(TipoDeJogo.PLATAFORMA) && zerou){
			ex2p += 20;
		}
		
		return ex2p;
	}
	

	public void adicionaJogabilidade(Jogabilidade jogabilidade) throws Exception { 
		
		if (jogabilidades.contains(jogabilidade)){
			throw new Exception("Jogabilidade " + jogabilidade + " já existe");
		}
		jogabilidades.add(jogabilidade);
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}

	public int getVezesJogado() {
		return vezesJogado;
	}

	public void setVezesJogado(int vezesJogado) {
		this.vezesJogado = vezesJogado;
	}

	public int getVezesZerado() {
		return vezesZerado;
	}

	public void setVezesZerado(int vezesZerado) {
		this.vezesZerado = vezesZerado;
	}

	public TipoDeJogo getTipo() {
		return tipo;
	}

	public void setTipo(TipoDeJogo tipo) {
		this.tipo = tipo;
	}

	public Set<Jogabilidade> getJogabilidades() {
		return jogabilidades;
	}

	public void setJogabilidades(Set<Jogabilidade> jogabilidades) {
		this.jogabilidades = jogabilidades;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((jogabilidades == null) ? 0 : jogabilidades.hashCode());
		result = prime * result + maxScore;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		long temp;
		temp = Double.doubleToLongBits(preco);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + vezesJogado;
		result = prime * result + vezesZerado;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Jogo))
			return false;
		Jogo other = (Jogo) obj;
		if (jogabilidades == null) {
			if (other.jogabilidades != null)
				return false;
		} else if (!jogabilidades.equals(other.jogabilidades))
			return false;
		if (maxScore != other.maxScore)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (Double.doubleToLongBits(preco) != Double.doubleToLongBits(other.preco))
			return false;
		if (tipo != other.tipo)
			return false;
		if (vezesJogado != other.vezesJogado)
			return false;
		if (vezesZerado != other.vezesZerado)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Nome do jogo: " + nome + ".\npreco: " + preco + ".\nMáximo score: " + maxScore + ".\nQuantidade de vezes jogado: " + vezesJogado
				+ ".\nQuantidade de vezes zerado: " + vezesZerado + ".\nTipo de jogo: " + tipo + ".";
	}


	
}
