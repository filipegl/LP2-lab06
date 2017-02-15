/*package steam;

import java.util.Set;

public class RPG extends Jogo {
	
	protected int ex2pRPG = 0;


	public RPG(String nome, double preco, Set<Jogabilidade> jogabilidade) throws Exception {
		super(nome, preco, jogabilidade);
	}
	@Override
	public int registraJogada(int score, boolean zerou){
		if (maxScore < score){
			maxScore = score;
		}
		ex2pRPG += 10;
		return ex2pRPG;
	}

}/*
