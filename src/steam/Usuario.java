package steam;

import java.util.ArrayList;
import java.util.List;


public class Usuario {

	private String nome;
	private String login;
	private double dinheiro;
	private List<Jogo> listaJogos;
	private TipoDeUsuario tipoDeUsuario;
	private int ex2p;
	private double dispesaJogos; //Guarda o valor total de preços dos jogos comprados.
	
	public Usuario(String login, String nome, String tipo)throws Exception{
		if ((nome == null) || (nome.trim().equals(""))){
			throw new Exception("Nome nao pode ser null ou vazio.");
		}
		this.nome = nome;
		
		if ((login == null) || (login.trim().equals(""))){
			throw new Exception("Login nao pode ser null ou vazio.");
		}
		login = this.login;
		
		//se for veterano começa com 1000 de ex2p, se for noob começa com 0.
		if (tipo.equalsIgnoreCase("veterano")){
			this.tipoDeUsuario = new Veterano();
			this.ex2p = 1000;
			
		}else if (tipo.equalsIgnoreCase("noob")){
			this.tipoDeUsuario = new Noob();
			this.ex2p = 0;
			
		}else{
			throw new Exception("Tipo de jogador inválido. Digite 'noob' ou 'veterano'");
		}
		
		this.dinheiro = 0;
		listaJogos = new ArrayList<Jogo>();
		dispesaJogos = 0;
	}
	
	
	public void registraJogada(String nomeDoJogo, int score, boolean zerou) throws Exception{
		//procura o jogo pelo nome.
		for(int i = 0; i < listaJogos.size(); i++){
			
			if ( listaJogos.get(i).getNome().equals(nomeDoJogo) ){
				this.ex2p += listaJogos.get(i).registraJogada(score, zerou);
			}
		}
	}
	public boolean existeJogo(Jogo jogo){
		
		for (int i=0; i < listaJogos.size(); i++){
			if (jogo.equals(listaJogos.get(i))){
				return true;
			}
		}
		return false;
	}
	
	public void compraJogos(Jogo jogo) throws Exception{
		
		double precoDescontado = jogo.getPreco() - (jogo.getPreco()*this.tipoDeUsuario.getDesconto());
	
		if(existeJogo(jogo)){
			throw new Exception("Você ja possui esse jogo");
		}
		
		this.dinheiro -= precoDescontado;
		this.dispesaJogos += precoDescontado;
		
		this.listaJogos.add(jogo);
		if(getTipoDeUsuario().equalsIgnoreCase("Noob")){
			this.ex2p = (int) ((double)this.ex2p + precoDescontado*10);
		
		}else if(getTipoDeUsuario().equalsIgnoreCase("Veterano")){
			this.ex2p = (int) ((double)this.ex2p + precoDescontado*15);
			}
		
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
	public void setDinheiro(double dinheiro) throws Exception{
		this.dinheiro += dinheiro;
	
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
//getTipoDeUsuario retorna uma string "Noob/Veterano"
	public String getTipoDeUsuario() {
		return tipoDeUsuario.toString();
	}

	public void setTipoDeUsuario(TipoDeUsuario tipoDeUsuario) {
		this.tipoDeUsuario = tipoDeUsuario;
	}


	public double getDispesaJogos() {
		return dispesaJogos;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		return result;
	}

 // é igual se o login for igual
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
}
