package steam;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

	private String nome;
	private String login;
	private double dinheiro;
	private List<Jogo> listaJogos = new ArrayList<Jogo>();
	private Jogo jogo;
	protected int ex2p;
	
	public Usuario(String login, String nome){
		login = this.login;
		nome = this.nome;
	}
	
	
	public void registraJogada(String nomeDoJogo, int score, boolean zerou){
		//procura o jogo pelo nome.
		for(int i = 0; i < listaJogos.size(); i++){
			
			if ( listaJogos.get(i).getNome().equals(nomeDoJogo) ){
				
				listaJogos.get(i).registraJogada(score, zerou);
			}
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
	public void setDinheiro(double dinheiro) {
		this.dinheiro = dinheiro;
	}
	public List<Jogo> getListaJogos() {
		return listaJogos;
	}
	public void setListaJogos(List<Jogo> listaJogos) {
		this.listaJogos = listaJogos;
	}
	public Jogo getJogo() {
		return jogo;
	}
	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}
	public int getEx2p() {
		return ex2p;
	}
	public void setEx2p(int ex2p) {
		this.ex2p = ex2p;
	}
}
