package steam;
/**
 * 
 * @author filipe
 *
 */
public class Veterano extends Usuario {

	/**
	 * Se um usuário é Veterano, seu ex2p inicial é igual a 1000 e o seu desconto é de 20%
	 * @param login Identificador único de um usuário
	 * @param nome Nome do usuário
	 * @throws Exception
	 */
	public Veterano(String login, String nome) throws Exception{
		super(login, nome);
		
		this.desconto = 0.8;
		this.ex2p = 1000;
	}

	/**
	 * O toString nesse caso foi feito para fins de comparação, onde o mesmo retorna "Veterano".
	 */
	@Override
	public String toString() {
		return "Veterano";
	}
}