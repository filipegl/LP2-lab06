package teste;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import steam.Loja;
import steam.Usuario;
import steam.Veterano;

public class TestLoja {

	Usuario filipe;
	Loja loja;
	List<Usuario> lista;
	
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
		loja.adicionaUsuario("rebecagl", "Rebeca", "Noob");
		loja.adicionaUsuario("filipegl", "Filipe", "Veterano");
		
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
		lista = loja.getListaDeUsuarios();
		Assert.assertEquals(lista.get(1).getDinheiro(), 70, 0.0);
		
	}
	
	@Test
	public void testUpgrade() throws Exception {
		try {
			loja.upgrade("filipegl");
			Assert.fail("Usuario não existe");

		} catch (Exception e) {
			Assert.assertEquals("Usuario não existe", e.getMessage());
		}
		
		loja.adicionaUsuario("rebecagl", "Rebeca", "Noob");
		loja.adicionaUsuario("filipegl", "Filipe", "Veterano");
		
		try {
			loja.upgrade("filipegl");
			Assert.fail("O usuário já é veterano");

		} catch (Exception e) {
			Assert.assertEquals("O usuário já é veterano", e.getMessage());
		}
		
		try {
			loja.upgrade("rebecagl");
			Assert.fail("Pontuação deve ser igual ou maior que 1000");

		} catch (Exception e) {
			Assert.assertEquals("Pontuação deve ser igual ou maior que 1000", e.getMessage());
		}
		
		lista = loja.getListaDeUsuarios();
		lista.get(0).setEx2p(1010);
		
		loja.upgrade("rebecagl");
		
		Assert.assertTrue(lista.get(1).toString().equals("Veterano")); 
		//como ela foi excluida da lista e adicionada novamente, a sua nova posição deixa de ser 0 e passa a ser 1.
		
		
		
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
