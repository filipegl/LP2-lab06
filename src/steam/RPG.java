package steam;

import java.util.Set;

public class RPG extends Jogo {

	public RPG(String nome, double preco, Set<Jogabilidade> jogabilidade) throws Exception {
		super(nome, preco, jogabilidade);
	}
	
	public RPG(String nome, double preco) throws Exception {
		super(nome, preco);
	}
	
	@Override
	public int registraJogada(int score, boolean zerou){
		
		super.registro(score, zerou);
	
		return 10;
	}

	@Override
	public String toString() {
		return "RPG";
	}

}
