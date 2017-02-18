package steam;

import java.util.Set;

public class Luta extends Jogo {

	public Luta(String nome, double preco, Set<Jogabilidade> jogabilidade) throws Exception {
		super(nome, preco, jogabilidade);
	}
	
	public Luta(String nome, double preco) throws Exception {
		super(nome, preco);
	}

	@Override
	public int registraJogada(int score, boolean zerou) throws Exception{ //score de luta tem que ser entre 0 e 100.000
		if ((score < 0) || (score > 100000)){
			throw new Exception("Score tem que estar entre 0 e 100000");
		}
		
		boolean ehmaximo = super.registro(score, zerou);
		if (ehmaximo){
			return (int)(score/1000);
		}
		
		return 0;
		
		
	}

	@Override
	public String toString() {
		return "Luta";
	}

}