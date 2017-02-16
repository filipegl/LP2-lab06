package steam;

import java.util.Set;

public class RPG extends Jogo {
	
	protected int ex2pRPG = 0;


	public RPG(String nome, double preco, Set<Jogabilidade> jogabilidade) throws Exception {
		super(nome, preco, jogabilidade);
	}
	public RPG(String nome, double preco) throws Exception {
		super(nome, preco);
	}
	
	
	@Override
	public int registraJogada(int score, boolean zerou){
		
		super.registro(score, zerou);
		
		ex2pRPG += 10;
		return ex2pRPG;
	}

}
