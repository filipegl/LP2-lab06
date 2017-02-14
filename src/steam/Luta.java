package steam;

import java.util.Set;

public class Luta extends Jogo {
	protected int ex2pLuta = 0;

	public Luta(String nome, double preco, Set<Jogabilidade> jogabilidade) throws Exception {
		super(nome, preco, jogabilidade);
	}

	@Override
	public int registraJogada(int score, boolean zerou){ //score de luta tem que ser < 100.000
		if (maxScore < score){
			maxScore = score;
			ex2pLuta += (double)score/1000.0;
		}
		return ex2pLuta;
	}

}
