package teste;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import steam.Jogabilidade;
import steam.Jogo;
import steam.Loja;
import steam.RPG;
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
		Assert.assertFalse(loja.adicionaUsuario("rebecagl", "beca", "Veterano")); //Não pode adicionar o mesmo mais de 1 vez.
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
	public void testVendeJogoStringStringDoubleSetOfJogabilidadeString() throws Exception {
		
		Set<Jogabilidade> jogabilidade = new HashSet<Jogabilidade>();
		jogabilidade.add(Jogabilidade.COMPETITIVO);
		Jogo jogo1 = new RPG("Ragnarok", 10, jogabilidade);
		List<Jogo> lista = new ArrayList<Jogo>();
		lista.add(jogo1);
		
		loja.adicionaUsuario("filipegl", "Filipe", "Veterano");
		loja.getListaDeUsuarios().get(0).setListaJogos(lista);
		loja.adicionarDinheiro("filipegl", 10);
		
		try {
			loja.vendeJogo("filipegl", "PES 2008", 10, jogabilidade, "");
			Assert.fail("Tipo de jogo inválido");

		} catch (Exception e) {
			Assert.assertEquals("Tipo de jogo inválido", e.getMessage());
		}
		Assert.assertFalse(loja.vendeJogo("rebecagl", "PES 2008", 10,  jogabilidade, "Plataforma")); // Login inexistente
		Assert.assertFalse(loja.vendeJogo("filipegl", "PES 2008", 15,  jogabilidade, "Plataforma")); // Muito caro
		Assert.assertFalse(loja.vendeJogo("filipegl", "Ragnarok", 10,  jogabilidade, "RPG")); // Usuário já tem o jogo
		
		Assert.assertTrue(loja.vendeJogo("filipegl", "Mário", 10, jogabilidade, "Plataforma"));
		
		Assert.assertEquals(loja.getListaDeUsuarios().get(0).getDinheiro(), 2, 0.0); 
		// Subtraiu 8 do dinheiro, por causa do desconto de 20%
	}

	@Test
	public void testVendeJogoStringStringDoubleString() throws Exception {
		
		Jogo jogo1 = new RPG("Ragnarok", 10);
		List<Jogo> lista = new ArrayList<Jogo>();
		lista.add(jogo1);
		
		loja.adicionaUsuario("filipegl", "Filipe", "Noob");
		loja.getListaDeUsuarios().get(0).setListaJogos(lista);
		loja.adicionarDinheiro("filipegl", 10);
		
		try {
			loja.vendeJogo("filipegl", "PES 2008", 10, "");
			Assert.fail("Tipo de jogo inválido");

		} catch (Exception e) {
			Assert.assertEquals("Tipo de jogo inválido", e.getMessage());
		}
		Assert.assertFalse(loja.vendeJogo("rebecagl", "PES 2008", 10, "Plataforma")); // Login inexistente
		Assert.assertFalse(loja.vendeJogo("filipegl", "PES 2008", 15, "Plataforma")); // Muito caro
		Assert.assertFalse(loja.vendeJogo("filipegl", "Ragnarok", 10, "RPG")); // Usuário já tem o jogo
		
		Assert.assertTrue(loja.vendeJogo("filipegl", "Mário", 10, "Plataforma"));
		
		Assert.assertEquals(loja.getListaDeUsuarios().get(0).getDinheiro(), 1, 0.0); 
		// Subtraiu 9 do dinheiro, por causa do desconto de 10%
	}
}