package teste;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import steam.Jogabilidade;
import steam.Jogo;
import steam.Luta;
import steam.Plataforma;
import steam.RPG;

public class TestJogo {
	
	Set<Jogabilidade> jogabilidades;
	Jogo jRPG, jPlat, jLuta;
	
	@Before
	public void setUp() throws Exception{
		jogabilidades = new HashSet<Jogabilidade>();
		jogabilidades.add(Jogabilidade.COMPETITIVO);
		
		jRPG = new RPG("Ragnarok", 2.5, jogabilidades);
		jPlat = new Plataforma("Mário", 20.0);
		jLuta = new Luta("Street Fighter", 10.0, jogabilidades);
	}
	
	@Test
	public void testJogoStringDoubleSetOfJogabilidade() {
		//Como nesse caso os construtores das classes filhas são iguais, só irei testar apenas 1 classe filha,
		//assumindo o mesmo resultado para as demais.
		
		//Teste de nome do jogo vazio ou 'null'
		try {
			Jogo jogo = new RPG("  ", 10, jogabilidades);
			Assert.fail("Nome não pode ser null ou vazio.");

		} catch (Exception e) {
			Assert.assertEquals("Nome não pode ser null ou vazio.", e.getMessage());
		}
		
		try {
			Jogo jogo = new RPG(null, 10, jogabilidades);
			Assert.fail("Nome não pode ser null ou vazio.");

		} catch (Exception e) {
			Assert.assertEquals("Nome não pode ser null ou vazio.", e.getMessage());
		}
		//Teste de preço menor ou igual a 0
		try {
			Jogo jogo = new RPG("Mário", 0, jogabilidades);
			Assert.fail("Preco não pode ser menor ou igual a 0");

		} catch (Exception e) {
			Assert.assertEquals("Preco não pode ser menor ou igual a 0", e.getMessage());
		}
		
		try {
			Jogo jogo = new RPG("Mário", -4.5, jogabilidades);
			Assert.fail("Preco não pode ser menor ou igual a 0");

		} catch (Exception e) {
			Assert.assertEquals("Preco não pode ser menor ou igual a 0", e.getMessage());
		}
		
		//Teste de jogabilidade 'null'
		try{
			Jogo jogo = new RPG("Mário", 5.3, null);
			Assert.fail("Jogabilidade não pode ser null.");

		} catch (Exception e) {
			Assert.assertEquals("Jogabilidade não pode ser null.", e.getMessage());
		}
		
		
	}

	@Test
	public void testJogoStringDouble() {
		
		//Teste de nome do jogo vazio ou 'null'
		try {
			Jogo jogo = new RPG("  ", 10);
			Assert.fail("Nome não pode ser null ou vazio.");

		} catch (Exception e) {
			Assert.assertEquals("Nome não pode ser null ou vazio.", e.getMessage());
		}
		
		try {
			Jogo jogo = new RPG(null, 10);
			Assert.fail("Nome não pode ser null ou vazio.");

		} catch (Exception e) {
			Assert.assertEquals("Nome não pode ser null ou vazio.", e.getMessage());
		}
		
		//Teste de preço menor ou igual a 0
		try {
			Jogo jogo = new RPG("Mário", 0);
			Assert.fail("Preco não pode ser menor ou igual a 0");

		} catch (Exception e) {
			Assert.assertEquals("Preco não pode ser menor ou igual a 0", e.getMessage());
		}
		
		try {
			Jogo jogo = new RPG("Mário", -4.5);
			Assert.fail("Preco não pode ser menor ou igual a 0");

		} catch (Exception e) {
			Assert.assertEquals("Preco não pode ser menor ou igual a 0", e.getMessage());
		}
		
	}

	@Test
	public void testRegistro() throws Exception {
		
		//Método implementado na classe pai.
		
		jRPG.registraJogada(50, true);
		
		Assert.assertEquals(jRPG.getVezesZerado(), 1);
		Assert.assertEquals(jRPG.getMaxScore(), 50);
		
		jRPG.registraJogada(70, false);
		
		Assert.assertEquals(jRPG.getVezesZerado(), 1);
		Assert.assertEquals(jRPG.getMaxScore(), 70);
	}

	@Test
	public void testRegistraJogada() throws Exception {
		//para RPG:
		
		Assert.assertEquals(jRPG.registraJogada(50, true), 10);
		
		//para Luta:
		
		Assert.assertEquals(jLuta.registraJogada(40000, true), 40);
		Assert.assertEquals(jLuta.registraJogada(20000, true), 0);
		Assert.assertEquals(jLuta.registraJogada(70000, false), 70);
		
		//Se score de Luta for menor que 0 e maior que 100000
		try {
			jLuta.registraJogada(-43, true);
			Assert.fail("Score tem que estar entre 0 e 100000");

		} catch (Exception e) {
			Assert.assertEquals("Score tem que estar entre 0 e 100000", e.getMessage());
		}
		
		try {
			jLuta.registraJogada(100040, true);
			Assert.fail("Score tem que estar entre 0 e 100000");

		} catch (Exception e) {
			Assert.assertEquals("Score tem que estar entre 0 e 100000", e.getMessage());
		}
		
		//Para Plataforma: 
		
		Assert.assertEquals(jPlat.registraJogada(50, true), 20);
		Assert.assertEquals(jPlat.registraJogada(50, false), 0);
		Assert.assertEquals(jPlat.registraJogada(1000, true), 20);
	}

	
	@Test
	public void testAdicionaJogabilidade() throws Exception {
		
		//Não pode adicionar repetido.
		
		Assert.assertTrue(jPlat.adicionaJogabilidade(Jogabilidade.COMPETITIVO));
		Assert.assertTrue(jPlat.adicionaJogabilidade(Jogabilidade.MULTIPLAYER));
		
		Assert.assertFalse(jPlat.adicionaJogabilidade(Jogabilidade.COMPETITIVO));
	}


	@Test
	public void testEqualsObject() throws Exception {
		Jogo j1 = new RPG("Ragnarok", 2.5);
		Jogo j2 = new Luta("Ragnarok", 2.5);
		Jogo j3 = new Luta("Ragnarok", 2.5);
		
		Assert.assertTrue(j1.equals(j2));
		Assert.assertFalse(j1.equals(jRPG));
		Assert.assertTrue(j2.equals(j3));
		
		j3.setVezesJogado(3);
		
		Assert.assertFalse(j2.equals(j3));
	}

	@Test
	public void testToString() {

		Assert.assertEquals(jRPG.toString(), "RPG");
		Assert.assertEquals(jPlat.toString(), "Plataforma");
		Assert.assertEquals(jLuta.toString(), "Luta");
	}

}
