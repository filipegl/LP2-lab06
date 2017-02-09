package steam;

public class Jogo {

	private String nome;
	private int preco;
	private int maxScore = 0;
	private int vezesJogado = 0;
	private int vezesZerado = 0;
	private String tipo;
	
	public int registraJogada(int score, boolean zerou){
		if (maxScore < score){
			maxScore = score;
		}
		return maxScore;
		
	}
}
