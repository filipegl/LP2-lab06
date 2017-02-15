package steam;
//Possui 20% de desconto em compra de jogos

public class Veterano extends TipoDeUsuario {

	public Veterano() throws Exception{
		this.desconto = 0.2;
	}

	@Override
	public double getDesconto() {
		return this.desconto;
	}

	@Override
	public String toString() {
		return "Veterano";
	}

}