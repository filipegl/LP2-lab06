package steam;
//Possui 10% de desconto em compra de jogos.

public class Noob extends Usuario {
		
	public Noob(String login, String nome) throws Exception {
		super(login, nome);
		this.desconto = 0.1;
		this.ex2p = 0;
	}
	
	@Override
	public String toString() {
		return "Noob";
	}

}