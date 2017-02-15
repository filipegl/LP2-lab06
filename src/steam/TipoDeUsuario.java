package steam;

public abstract class TipoDeUsuario {
	protected double desconto;
	
	protected abstract double getDesconto();
	//Dependendo das classes filhas "Noob" ou "Veterano", o desconto será aplicado da forma pedida.
	
	@Override
	public abstract String toString();
}
