package steam;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

	private String nome;
	private String nomeLogin;
	private double dinheiro;
	private List<Jogo> listaJogos = new ArrayList();
	private Jogo jogo;
	
	public void registraJogada(String nomeDoJogo, int score, boolean zerou){
		//procura o jogo pelo nome.
		for(int i = 0; i < listaJogos.size(); i++){
			
			if ( listaJogos.get(i).getNome().equals(nomeDoJogo) ){
				
				listaJogos.get(i).registraJogada(score, zerou);
			}
		}
			
	}
	//sets e gets
}
