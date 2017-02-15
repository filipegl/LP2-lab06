package steam;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Loja {

	private List<Usuario> listaDeUsuarios;
	private int index;
	
	public Loja(){
		listaDeUsuarios = new ArrayList<Usuario>();
	}
	
	
	public void vendeJogos(){
		
	}
	public void adicionaUsuario(String login, String nome, String tipoDeJogador) throws Exception { 
		
		if ((pesquisarUsuario(login))){
			throw new Exception("Usuário" + login + " já existe");
		}
		
		Usuario usuario = new Usuario(login, nome, tipoDeJogador);
			
		listaDeUsuarios.add(usuario);
			
	}
	
	public void adicionarDinheiro(String login, double quantidadeDeDinheiro) throws Exception{
		if (quantidadeDeDinheiro < 0){
			throw new Exception("Impossível subtrair dinheiro sem nenhuma compra");
		}
		if (!(pesquisarUsuario(login))){
			throw new Exception("Usuário" + login + " não existe");		
		}
		
		listaDeUsuarios.get(index).setDinheiro(quantidadeDeDinheiro);
		
	}
		
	private boolean pesquisarUsuario(String login){
		
		for (int i=0; i < listaDeUsuarios.size(); i++){
			if (listaDeUsuarios.get(i).getLogin().equals(login)){
				int index = i;
				return true;
			}
		}
		return false;	
	}
	
	public void upgrade(String login)throws Exception{
		if (!(pesquisarUsuario(login))){
			throw new Exception("Usuario" + login + " não existe");
		}
		Usuario usuario = listaDeUsuarios.get(index);
		if(usuario.getEx2p() < 1000){
			throw new Exception("Pontuação deve ser igual ou maior que 1000");
		}
		if(usuario.getTipoDeUsuario().equalsIgnoreCase("Veterano")){
			throw new Exception("O usuário já é veterano");
		}
		TipoDeUsuario veterano = new Veterano();
		usuario.setTipoDeUsuario(veterano);
	}
	//Neste metodo as excessões de entrada são tratadas na classe Jogo
	public boolean vendeJogo(String login, String nomeDoJogo, double preco, Set<Jogabilidade> jogabilidade, TipoDeJogo tipo) throws Exception{
		if (pesquisarUsuario(login)){
			if (listaDeUsuarios.get(index).getDinheiro() >= preco){
				Jogo jogo = new Jogo(nomeDoJogo, preco, jogabilidade, tipo);
				listaDeUsuarios.get(index).compraJogos(jogo);
				return true;
			}
		}
		return false;
	}
	//Como a sobrecarga de métodos é possivel, aqui está uma opção caso não tenha as jogabilidades
	public boolean vendeJogo(String login, String nomeDoJogo, double preco, TipoDeJogo tipo) throws Exception{
		if (pesquisarUsuario(login)){
			if (listaDeUsuarios.get(index).getDinheiro() >= preco){
				Jogo jogo = new Jogo(nomeDoJogo, preco, tipo);
				listaDeUsuarios.get(index).compraJogos(jogo);
				return true;
			}
		}
		return false;
	}
	
	public void imprimirInformacoes(){
		System.out.println("=== Central P2-CG ===\n");
		for (Usuario usuario : listaDeUsuarios){
			System.out.println(usuario.getLogin() + "\n" + usuario.getNome() + " - Jogador " + usuario.getTipoDeUsuario());
			System.out.println("Lista de Jogos: \n");
			for (Jogo jogos : usuario.getListaJogos()){
				System.out.println("+ " + jogos.getNome() + " - " + jogos.getTipo());
				System.out.println("==> Jogou " + jogos.getVezesJogado() + "vez(es)");
				System.out.println("==> zerou " + jogos.getVezesZerado() + "vez(es)");
				System.out.println("==> Maior score: " + jogos.getMaxScore());
			}
			System.out.printf("\nTotal de preço dos jogos: R$ %.2f", usuario.getDispesaJogos());
			System.out.println("--------------------------------------------\n");
			
		}
		
	}
	
}
