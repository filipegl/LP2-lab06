package steam;

import java.util.ArrayList;
import java.util.List;


public class Usuario {

	private String nome;
	private String login;
	private double dinheiro;
	private List<Jogo> listaJogos;
	protected double desconto;
	protected int ex2p;
	private double dispesaJogos; //Guarda o valor total de preços dos jogos comprados.
	
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
		listaJogos = new ArrayList<Jogo>();
		dispesaJogos = 0;
	}
	
	
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
	
	public boolean existeJogo(Jogo jogo){
		
		for (int i=0; i < listaJogos.size(); i++){
			if (jogo.equals(listaJogos.get(i))){
				return true;
			}
		}
		return false;
	}
	
	public boolean compraJogos(Jogo jogo) throws Exception{
		
		double precoDescontado = jogo.getPreco() - (jogo.getPreco()*this.desconto);
	
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
