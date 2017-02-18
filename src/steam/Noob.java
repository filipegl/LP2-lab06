package steam;
/**
 * 
 * @author filipe
 *
 */
public class Noob extends Usuario {
		
	/**
	 * Se um usuário é Noob, seu ex2p inicial é igual a 0 e o seu desconto é de 10%
	 * @param login Identificador único de um usuário
	 * @param nome Nome do usuário
	 * @throws Exception
	 */
	public Noob(String login, String nome) throws Exception {
		super(login, nome);
		
		this.desconto = 0.9;
		this.ex2p = 0;
	}
	
	/**
	 * O toString nesse caso foi feito para fins de comparação, onde o mesmo retorna "Noob".
	 */
	@Override
	public String toString() {
		return "Noob";
	}
}