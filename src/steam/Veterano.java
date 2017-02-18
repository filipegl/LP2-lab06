package steam;
//Possui 20% de desconto em compra de jogos

public class Veterano extends Usuario {

	public Veterano(String login, String nome) throws Exception{
		super(login, nome);
		this.desconto = 0.2;
		this.ex2p = 1000;
	}

	@Override
	public String toString() {
		return "Veterano";
	}

}