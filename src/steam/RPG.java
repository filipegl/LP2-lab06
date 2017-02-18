package steam;

import java.util.Set;
/**
 * 
 * @author filipe
 *
 */
public class RPG extends Jogo {

	public RPG(String nome, double preco, Set<Jogabilidade> jogabilidade) throws Exception {
		super(nome, preco, jogabilidade);
	}

	public RPG(String nome, double preco) throws Exception {
		super(nome, preco);
	}

	/**
	 * O método registraJogada da subclasse RPG irá registrar os scores normalmente e retornar 10 de ex2p
	 */
	@Override
	public int registraJogada(int score, boolean zerou){
		
		super.registro(score, zerou);
	
		return 10;
	}

	/**
	 * O toString de RPG serve para identificar-lo, retornando "RPG"
	 */
	@Override
	public String toString() {
		return "RPG";
	}
}