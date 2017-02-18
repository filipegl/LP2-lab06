package steam;

import java.util.Set;
/**
 * 
 * @author filipe
 *
 */
public class Luta extends Jogo {

	public Luta(String nome, double preco, Set<Jogabilidade> jogabilidade) throws Exception {
		super(nome, preco, jogabilidade);
	}
	
	public Luta(String nome, double preco) throws Exception {
		super(nome, preco);
	}

	/**
	 * O score passado no método registraJogada da subclasse 'Luta' deve ser entre 0 e 100000
	 * Ele só irá retornar ex2p se o score for máximo, caso contrario retorna 0.
	 * O ex2p retornado é calculado como sendo o 'score' divido por 1000
	 */
	@Override
	public int registraJogada(int score, boolean zerou) throws Exception{
		if ((score < 0) || (score > 100000)){
			throw new Exception("Score tem que estar entre 0 e 100000");
		}
		
		boolean ehmaximo = super.registro(score, zerou);
		
		if (ehmaximo){
			return (int)(score/1000);
		}
		
		return 0;
	}

	/**
	 * O toString de Luta serve para identificar-lo, retornando "Luta"
	 */
	@Override
	public String toString() {
		return "Luta";
	}
}