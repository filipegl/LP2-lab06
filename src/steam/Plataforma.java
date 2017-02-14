package steam;

import java.util.Set;

public class Plataforma extends Jogo {
	
	protected int ex2pPlataforma = 0;

	public Plataforma(String nome, double preco, Set<Jogabilidade> jogabilidade) throws Exception {
		super(nome, preco, jogabilidade);
		
	}
	@Override
	public int registraJogada(int score, boolean zerou){
		if (maxScore < score){
			maxScore = score;
		}
		if (zerou){
			ex2pPlataforma += 20;	
		}
		return ex2pPlataforma;
	}

}
