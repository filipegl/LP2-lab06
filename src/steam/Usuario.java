package steam;

import java.util.ArrayList;
import java.util.List;
/**
 *   
 * @author filipe
 *
 */
public class Usuario {
	
	private String nome;
	private String login;
	private double dinheiro;
	private List<Jogo> listaJogos;
	protected double desconto;
	protected int ex2p;
	private double dispesaJogos; //Guarda o valor total de preços dos jogos comprados.
	
	/**
	 * O construtor desta classe basicamente inicia uma nova lista de jogos do usuário e atribui valores iniciais as variáveis.
	 * @param login Identificador único de um usuário
	 * @param nome Nome do usuário
	 * @throws Exception
	 */
	public Usuario(String login, String nome)throws Exception{
		if ((nome == null) || (nome.trim().equals(""))){
			throw new Exception("Nome não pode ser null ou vazio.");
		}
		this.nome = nome;
		
		if ((login == null) || (login.trim().equals(""))){
			throw new Exception("Login não pode ser null ou vazio.");
		}
		this.login = login;
		
		this.dinheiro = 0;
		this.dispesaJogos = 0;
		listaJogos = new ArrayList<Jogo>();
	}
	
	/**
	 * O método retorna verdadeiro se o jogo passado como argumento existir, assim ele faz a atribuição de ex2p na
	 * entidade 'Jogo'. O ex2p fica armazenado na variável "ex2p"
	 * Caso o jogo não exista, retorna falso. 
	 * 
	 * @param nomeDoJogo Nome do jogo
	 * @param score Pontuação feita na jogada
	 * @param zerou Se o jogador zerou, "zerou = true" se não "zerou = false"
	 * @return
	 * @throws Exception
	 */
	public boolean registraJogada(String nomeDoJogo, int score, boolean zerou) throws Exception{
		//procura o jogo pelo nome.
		for(int i = 0; i < listaJogos.size(); i++){
			
			if ( listaJogos.get(i).getNome().equals(nomeDoJogo) ){
				this.ex2p += listaJogos.get(i).registraJogada(score, zerou);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Se o jogo existe na lista de jogos, retorna verdadeiro, caso contrário, retorna falso.
	 * @param jogo
	 * @return
	 */
	public boolean existeJogo(Jogo jogo){
		
		for (int i=0; i < listaJogos.size(); i++){
			if (jogo.equals(listaJogos.get(i))){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Se o jogo existe na lista de jogos retorna falso.
	 * Dependendo do tipo de usuário, é feito um desconto do preço do jogo
	 * Esse preço descontado é subtraido do dinheiro do usuário e somado na variável "dispesaJogos"
	 * Quando a compra é realizada, o usuário ganha ex2p dependendo do seu tipo. Para diferenciar os tipos, faz-se o uso do toString
	 * 
	 * 
	 * @param jogo Jogo a ser comprado
	 * @return
	 * @throws Exception
	 */
	public boolean compraJogos(Jogo jogo) throws Exception{
		
		double precoDescontado = jogo.getPreco()*this.desconto;
	
		if(existeJogo(jogo)){
			return false;
		}
		
		this.dinheiro -= precoDescontado;
		this.dispesaJogos += precoDescontado;
		
		this.listaJogos.add(jogo);
		
		if(this.toString().equals("Noob")){
			this.ex2p = (int) ((double)this.ex2p + precoDescontado*10);
		
		}else if(this.toString().equals("Veterano")){
			this.ex2p = (int) ((double)this.ex2p + precoDescontado*15);
			}
		
		return true;
	}

	//sets e gets
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public double getDinheiro() {
		return dinheiro;
	}
	public void setDinheiro(double dinheiro) {
		this.dinheiro = dinheiro;
	}
	public List<Jogo> getListaJogos() {
		return listaJogos;
	}
	public void setListaJogos(List<Jogo> listaJogos) {
		this.listaJogos = listaJogos;
	}
	public int getEx2p() {
		return ex2p;
	}
	public void setEx2p(int ex2p) {
		this.ex2p = ex2p;
	}

	public double getDispesaJogos() {
		return dispesaJogos;
	}

	public void setDispesaJogos(double dispesaJogos) {
		this.dispesaJogos = dispesaJogos;
	}
	
	public double getDesconto() {
		return desconto;
	}
	
	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}

	/**
	 * No método 'equals', um usuário é igual ao outro se as variáveis "login" forem as mesmas
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Usuario))
			return false;
		Usuario other = (Usuario) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		return result;
	}
}