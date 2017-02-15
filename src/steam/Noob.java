package steam;
//Possui 10% de desconto em compra de jogos.

public class Noob extends TipoDeUsuario {

	public Noob() {
		this.desconto = 0.1;
	}

	@Override
	public double getDesconto() {
		return this.desconto;
	}

	@Override
	public String toString() {
		return "Noob";
	}

}