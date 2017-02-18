package teste;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import steam.Loja;
import steam.Usuario;
import steam.Veterano;

public class TestLoja {

	Usuario filipe;
	Loja loja;
	
	@Before
	public void setUp() throws Exception{
		filipe = new Veterano("filipegl", "Filipe");
		loja = new Loja();
	}

	@Test
	public void testAdicionaUsuario() throws Exception {
		Assert.assertTrue(loja.adicionaUsuario("rebecagl", "Rebeca", "Noob"));
		Assert.assertFalse(loja.adicionaUsuario("rebecagl", "beca", "Veterano")); //Não pode adicionar o mesmo mais de 1 vez
		Assert.assertFalse(loja.adicionaUsuario("filipe", "Filipe", "Veteroob"));//tipo desconhecido
		Assert.assertTrue(loja.adicionaUsuario("filipegl", "Filipe", "Veterano"));
	}

	@Test
	public void testAdicionarDinheiro() throws Exception {
		Assert.assertTrue(loja.adicionaUsuario("rebecagl", "Rebeca", "Noob"));
		Assert.assertTrue(loja.adicionaUsuario("filipegl", "Filipe", "Veterano"));
		
		try {
			loja.adicionarDinheiro("filipe", 70);
			Assert.fail("Usuário não existe");

		} catch (Exception e) {
			Assert.assertEquals("Usuário não existe", e.getMessage());
		}
		try {
			loja.adicionarDinheiro("filipegl", -30);
			Assert.fail("Impossível subtrair dinheiro sem nenhuma compra");

		} catch (Exception e) {
			Assert.assertEquals("Impossível subtrair dinheiro sem nenhuma compra", e.getMessage());
		}
		
		loja.adicionarDinheiro("filipegl", 70);
		
	}

	@Test
	public void testUpgrade() {
		fail("Not yet implemented");
	}

	@Test
	public void testVendeJogoStringStringDoubleSetOfJogabilidadeString() {
		fail("Not yet implemented");
	}

	@Test
	public void testVendeJogoStringStringDoubleString() {
		fail("Not yet implemented");
	}

	@Test
	public void testImprimirInformacoes() {
		fail("Not yet implemented");
	}

}
