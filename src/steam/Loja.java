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

	public boolean adicionaUsuario(String login, String nome, String tipo) throws Exception { 
		Usuario usuario;
		
		
		if ((pesquisarUsuario(login))){
			return false;
		}
		if (tipo.equals("Noob")){
			usuario = new Noob(login, nome);
		}
		else if (tipo.equals("Veterano")){
			usuario = new Veterano(login, nome);
		}
		else{
			return false;
		}
		
		listaDeUsuarios.add(usuario);
		
		return true;	
	}
	
	public void adicionarDinheiro(String login, double quantidadeDeDinheiro) throws Exception{
		if (quantidadeDeDinheiro < 0){
			throw new Exception("Impossível subtrair dinheiro sem nenhuma compra");
		}
		if (!(pesquisarUsuario(login))){
			throw new Exception("Usuário não existe");		
		}
		
		listaDeUsuarios.get(index).setDinheiro(quantidadeDeDinheiro);
		
	}
		
	private boolean pesquisarUsuario(String login) throws Exception{
		if ((login == null) || (login.trim().equals(""))){
			throw new Exception("Login nao pode ser null ou vazio.");
		}
		
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
			throw new Exception("Usuario não existe");
		}
		
		Usuario noob = listaDeUsuarios.get(index);
		
		if(noob.getEx2p() < 1000){
			throw new Exception("Pontuação deve ser igual ou maior que 1000");
		}
		if(noob.toString().equalsIgnoreCase("Veterano")){
			throw new Exception("O usuário já é veterano");
		}
		
		//processo de guardar os dados nas variáveis.
		String newNome = noob.getNome();
		String newLogin = noob.getLogin();
		double newDinheiro = noob.getDinheiro();
		double newDispesa = noob.getDispesaJogos();
		double newDesconto = noob.getDesconto();
		List<Jogo> newJogos = noob.getListaJogos();
		int newEx2p = noob.getEx2p();
		
		Usuario veterano = new Veterano(newLogin, newNome);
		
		//depois de criado o usuário veterano, é 'setado' os seus dados.
		veterano.setDinheiro(newDinheiro);
		veterano.setDispesaJogos(newDispesa);
		veterano.setDesconto(newDesconto);
		veterano.setListaJogos(newJogos);
		veterano.setEx2p(newEx2p);
		
		listaDeUsuarios.remove(noob);
		listaDeUsuarios.add(veterano);
		
	}
	//Neste metodo as excessões de entrada são tratadas na classe Jogo
	public boolean vendeJogo(String login, String nomeDoJogo, double preco, Set<Jogabilidade> jogabilidade, String tipo) throws Exception{
		Jogo jogo;
		
		if (pesquisarUsuario(login)){
			if (listaDeUsuarios.get(index).getDinheiro() >= preco){
				//Verifica o tipoh de jogo passado e cria um objeto deste tipo.
				
				if (tipo.equals("RPG")){
					jogo = new RPG(nomeDoJogo, preco, jogabilidade);
				}else if (tipo.equals("Plataforma")){	
					jogo = new Plataforma(nomeDoJogo, preco, jogabilidade);
				}else if (tipo.equals("Luta")){
					jogo = new Luta(nomeDoJogo, preco, jogabilidade);
				}else{
					throw new Exception("Tipo de jogo inválido");
				}
				
				listaDeUsuarios.get(index).compraJogos(jogo);
				return true;
			}
		}
		return false;
	}
	//Como a sobrecarga de métodos é possivel, aqui está uma opção caso não tenha as jogabilidades
	public boolean vendeJogo(String login, String nomeDoJogo, double preco, String tipo) throws Exception{
Jogo jogo;
		
		if (pesquisarUsuario(login)){
			if (listaDeUsuarios.get(index).getDinheiro() >= preco){
				
				if (tipo.equals("RPG")){
					jogo = new RPG(nomeDoJogo, preco);
				}else if (tipo.equals("Plataforma")){	
					jogo = new Plataforma(nomeDoJogo, preco);
				}else if (tipo.equals("Luta")){
					jogo = new Luta(nomeDoJogo, preco);
				}else{
					throw new Exception("Tipo de jogo inválido");
				}
				
				listaDeUsuarios.get(index).compraJogos(jogo);
				return true;
			}
		}
		return false;
	}
	
	public void imprimirInformacoes(){
		System.out.println("=== Central P2-CG ===\n");
		for (Usuario usuario : listaDeUsuarios){
			System.out.println(usuario.getLogin() + "\n" + usuario.getNome() + " - Jogador " + usuario.toString());
			System.out.println("Lista de Jogos: \n");
			for (Jogo jogos : usuario.getListaJogos()){
				System.out.println("+ " + jogos.getNome() + " - " + jogos.toString());
				System.out.println("==> Jogou " + jogos.getVezesJogado() + "vez(es)");
				System.out.println("==> zerou " + jogos.getVezesZerado() + "vez(es)");
				System.out.println("==> Maior score: " + jogos.getMaxScore());
			}
			System.out.printf("\nTotal de preço dos jogos: R$ %.2f", usuario.getDispesaJogos());
			System.out.println("--------------------------------------------\n");
			
		}
		
	}
	
}
