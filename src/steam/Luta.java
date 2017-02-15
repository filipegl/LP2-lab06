/*package steam;

import java.util.Set;

public class Luta extends Jogo {

	public Luta(String nome, double preco, Set<Jogabilidade> jogabilidade) throws Exception {
		super(nome, preco, jogabilidade);
	}

	@Override
	public int registraJogada(int score, boolean zerou){ //score de luta tem que ser < 100.000
		int ex2pLuta = 0;
		
		if (maxScore < score){
			maxScore = score;
			ex2pLuta += score/1000;
		}
		return ex2pLuta;
	}

}\*