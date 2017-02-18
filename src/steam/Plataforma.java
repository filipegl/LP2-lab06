package steam;

import java.util.Set;

public class Plataforma extends Jogo {

	public Plataforma(String nome, double preco, Set<Jogabilidade> jogabilidade) throws Exception {
		super(nome, preco, jogabilidade);
	}
	
	public Plataforma(String nome, double preco) throws Exception {
		super(nome, preco);
	}
	
	@Override
	public int registraJogada(int score, boolean zerou){
	
		super.registro(score, zerou);
		
		if (zerou){
			return 20;	
		}
		
		return 0;
	}
	
	@Override
	public String toString(){
		return "Plataforma";
		
	}
}
