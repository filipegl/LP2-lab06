package teste;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import steam.Jogo;
import steam.Luta;
import steam.Noob;
import steam.Plataforma;
import steam.RPG;
import steam.Usuario;
import steam.Veterano;

public class TestUsuario {

	Usuario noob, veter;
	
	@Before
	public void setUp() throws Exception{
		noob = new Noob("filipegl", "Filipe");
		veter = new Veterano("rebecagl","Rebeca");
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testUsuario() {
		//Como nesse caso os construtores das classes filhas são iguais, só irei testar apenas 1 classe filha,
		//assumindo o mesmo resultado para as demais.
				
			//Teste de nome do usuário vazio ou 'null'
		try {
			Usuario user = new Veterano("filipegl", "    ");
			Assert.fail("Nome não pode ser null ou vazio.");

		} catch (Exception e) {
			Assert.assertEquals("Nome não pode ser null ou vazio.", e.getMessage());
		}
		try {
			Usuario user = new Veterano("filipegl", null);
			Assert.fail("Nome não pode ser null ou vazio.");

		} catch (Exception e) {
			Assert.assertEquals("Nome não pode ser null ou vazio.", e.getMessage());
		}
		
		//Teste de login do usuário vazio ou 'null'
		try {
			Usuario user = new Veterano("   ", "Filipe");
			Assert.fail("Loguin não pode ser null ou vazio.");

		} catch (Exception e) {
			Assert.assertEquals("Login não pode ser null ou vazio.", e.getMessage());
		}
		try {
			Usuario user = new Veterano(null, "Filipe");
			Assert.fail("Login não pode ser null ou vazio.");

		} catch (Exception e) {
			Assert.assertEquals("Login não pode ser null ou vazio.", e.getMessage());
		}
		
		//descontos inicializados no construtor para Noob e Veterano
		Assert.assertEquals(0.1,(noob.getDesconto()), 0.0);
		Assert.assertEquals(0.2,(veter.getDesconto()), 0.0);
		
		
	}
	

	@Test
	public void testRegistraJogada() throws Exception {
		Jogo jogo1 = new Plataforma("Mario", 5.0);
		Jogo jogo2 = new Plataforma("Donkey Kong", 5.0);
		Jogo jogo3 = new Luta("Street Fighter", 5.0);
		
		List<Jogo> lista = new ArrayList<Jogo>();
		lista.add(jogo1);
		lista.add(jogo2);
		lista.add(jogo3);
		noob.setListaJogos(lista);
		
		Assert.assertTrue(noob.registraJogada("Mario", 80, false));//jogo existente
		Assert.assertFalse(noob.registraJogada("Fifa", 80, false));//jogo inexistente
		
		noob.registraJogada("Mario", 80, true);
		Assert.assertEquals(noob.getEx2p(), 20);
		
		noob.registraJogada("Mario",20, true);
		Assert.assertEquals(noob.getEx2p(), 40); //20 + 20 = 40
		
	}

	@Test
	public void testExisteJogo() throws Exception {
		Jogo jogo1 = new Plataforma("Mario", 5.0);
		Jogo jogo2 = new Plataforma("Donkey Kong", 5.0);
		Jogo jogo3 = new Luta("Street Fighter", 5.0);
		
		List<Jogo> lista = new ArrayList<Jogo>();
		lista.add(jogo1);
		lista.add(jogo2);
		noob.setListaJogos(lista);
		
		Assert.assertTrue(noob.existeJogo(jogo2));
		Assert.assertFalse(noob.existeJogo(jogo3));
	}

	@Test
	public void testCompraJogos() throws Exception {
		Jogo jogo1 = new Plataforma("Mario", 5.0);
		Jogo jogo2 = new Plataforma("Donkey Kong", 5.0);
		Usuario jogador1 = new Noob("rebecagl", "Rebeca");
		Usuario jogador2 = new Veterano("filipegl", "Filipe");
		
		List<Jogo> lista = new ArrayList<Jogo>();
		lista.add(jogo1);
		jogador1.setListaJogos(lista);
	
		
		Assert.assertFalse(jogador1.compraJogos(jogo1));
		Assert.assertTrue(jogador1.compraJogos(jogo2));
		Assert.assertTrue(jogador2.compraJogos(jogo1));
		
		Assert.assertEquals(jogador1.getDispesaJogos(), 4.5, 0.0); // 5 - (5*0.1) = 4.5
		Assert.assertEquals(jogador1.getEx2p(), 45); // 4.5 * 10 = 45
		
		Assert.assertEquals(jogador2.getDispesaJogos(), 4.0, 0.0); //5 - (5*0.2) = 4.0
		Assert.assertEquals(jogador2.getEx2p(), 1060); // 4.0 * 15 = 60. Como o veterano começa com 1000
		//60 + 1000 = 1060
		
	}

	@Test
	public void testEqualsObject() throws Exception {
		Usuario j1 = new Veterano("filipegl", "Filipe");
		Usuario j2 = new Noob("rebecagl", "Rebeca");
		
		//Só é igual se o login for o mesmo
		
		Assert.assertTrue(j1.equals(noob));
		Assert.assertFalse(j1.equals(j2));
		Assert.assertTrue(j2.equals(veter));
		
	}

	@Test
	public void testToString() {
		Assert.assertEquals(noob.toString(), "Noob");
		Assert.assertEquals(veter.toString(), "Veterano");
		
	}

}
