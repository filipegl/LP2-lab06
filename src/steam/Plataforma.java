package steam;

import java.util.Set;
/**
 * 
 * @author filipe
 *
 */
public class Plataforma extends Jogo {

	public Plataforma(String nome, double preco, Set<Jogabilidade> jogabilidade) throws Exception {
		super(nome, preco, jogabilidade);
	}

	public Plataforma(String nome, double preco) throws Exception {
		super(nome, preco);
	}

	/**
	 * O método registraJogada da subclasse Plataforma irá registrar os scores normalmente, porém só irá
	 * retornar 20 de ex2p se "zerou == true", caso contrario retornará 0.
	 */
	@Override
	public int registraJogada(int score, boolean zerou){

		super.registro(score, zerou);

		if (zerou){
			return 20;	
		}

		return 0;
	}

	/**
	 * O toString de Plataforma serve para identificar-lo, retornando "Plataforma"
	 */
	@Override
	public String toString(){
		return "Plataforma";

	}
}