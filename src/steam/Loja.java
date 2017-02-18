package steam;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
/**
 * Loja de jogos
 * @author filipe
 *
 */
public class Loja {

	private List<Usuario> listaDeUsuarios;
	private int index;
	
	/**
	 * Construtor inicializa um ArrayList vazio de usuários
	 */
	public Loja(){
		listaDeUsuarios = new ArrayList<Usuario>();
	}
	
	
	/**
	 * Adiciona um novo usuário ao ArrayList da loja. Caso o usuário já existe, retorna falso.
	 * Se a variável 'tipo' for diferente de "Noob" ou "Usuario" retorna falso.
	 * 
	 * @param login Identificador único de um usuário
	 * @param nome Nome do usuário
	 * @param tipo Uma String passada para saber em qual subclasse (Noob ou Veterano) de "Usuario" o usuário irá pertencer.
	 * @return
	 * @throws Exception
	 */
	public boolean adicionaUsuario(String login, String nome, String tipo) throws Exception { 
		Usuario usuario;
		
		if ((pesquisarUsuario(login))){
			return false;
		}
		if (tipo.equals("Noob")){
			usuario = new Noob(login, nome);
		
		}else if (tipo.equals("Veterano")){
			usuario = new Veterano(login, nome);
		
		}else{
			return false;
		}
		
		listaDeUsuarios.add(usuario);
		
		return true;	
	}
	
	/**
	 * Adiciona determinada quantia de dinheiro para um determinado usuário
	 * @param login Identificador único de um usuário
	 * @param quantidadeDeDinheiro Quantidade de dinheiro a ser adicionado
	 * @throws Exception
	 */
	public void adicionarDinheiro(String login, double quantidadeDeDinheiro) throws Exception{
		if (quantidadeDeDinheiro < 0){
			throw new Exception("Impossível subtrair dinheiro sem nenhuma compra");
		}
		if (!(pesquisarUsuario(login))){
			throw new Exception("Usuário não existe");		
		}
		
		listaDeUsuarios.get(index).setDinheiro(quantidadeDeDinheiro);
		
	}
	
	/**
	 * Método privado que pesquisa um usuário pelo seu login cadastrado na loja.
	 * Se o usuário não estiver cadastrado retorna falso, caso contrário retorna true. 
	 * @param login Identificador único de um usuário
	 * @return
	 * @throws Exception
	 */
	private boolean pesquisarUsuario(String login) throws Exception{
		if ((login == null) || (login.trim().equals(""))){
			throw new Exception("Login nao pode ser null ou vazio.");
		}
		
		for (int i=0; i < listaDeUsuarios.size(); i++){
			if (listaDeUsuarios.get(i).getLogin().equals(login)){
				index = i;
				return true;
			}
		}
		return false;	
	}
	
	/**
	 * O método 'upgrade' transforma um usuário noob em veterano. 
	 * Primeiro os dados do usuário são guardados em variáveis, em seguida, é criado um novo usuário do tipo
	 * veterano, depois disso os dados do usuário noob são transferidos para o novo usuário criado, e por fim,
	 * é excluido da lista de usuários da loja o antigo usuário noob e adicionado o novo usuário veterano. 
	 * 
	 * @param login Identificador único de um usuário
	 * @throws Exception
	 */
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
		
		// guarda os dados nas variáveis.
		String newNome = noob.getNome();
		String newLogin = noob.getLogin();
		double newDinheiro = noob.getDinheiro();
		double newDispesa = noob.getDispesaJogos();
		List<Jogo> newJogos = noob.getListaJogos();
		int newEx2p = noob.getEx2p();
		
		Usuario veterano = new Veterano(newLogin, newNome);
		
		// depois de criado o usuário veterano, é 'setado' os seus dados.
		veterano.setDinheiro(newDinheiro);
		veterano.setDispesaJogos(newDispesa);
		veterano.setDesconto(0.2); //o descondo de Veterano é 20%
		veterano.setListaJogos(newJogos);
		veterano.setEx2p(newEx2p);
		
		listaDeUsuarios.remove(noob);
		listaDeUsuarios.add(veterano);
		
	}
	/**
	 * Neste método é criado um novo jogo dependendo do tipo passado. O metodo vai chamar um outro método da classe
	 * 'Usuario', "compraJogos" que também retorna boolean. Se o método "compraJogos" retorna verdadeiro, quer
	 * dizer que a compra ocorreu conforme o esperado e este método também retornará verdadeiro, caso contrário
	 * também retornará falso.
	 * Se o preço do jogo for maior que o dinheiro do usuário ou se o usuário não existir, este método retornará falso.
	 * @param login Identificador único de um usuário
	 * @param nomeDoJogo Nome do jogo a ser vendido
	 * @param preco Preço do jogo a ser vendido
	 * @param jogabilidade HashSet de jogabilidades do jogo
	 * @param tipo Tipo do jogo, que pode assumir os valores "RPG", "Plataforma" e "Luta"
	 * @return
	 * @throws Exception
	 */
	// Neste metodo as excessões de entrada são tratadas na classe Jogo
	public boolean vendeJogo(String login, String nomeDoJogo, double preco, Set<Jogabilidade> jogabilidade, String tipo) throws Exception{
		Jogo jogo;
		
		if (pesquisarUsuario(login)){
			if (listaDeUsuarios.get(index).getDinheiro() >= preco){
				// Verifica o tipo de jogo passado e cria um objeto deste tipo.
				
				if (tipo.equals("RPG")){
					jogo = new RPG(nomeDoJogo, preco, jogabilidade);
				}else if (tipo.equals("Plataforma")){	
					jogo = new Plataforma(nomeDoJogo, preco, jogabilidade);
				}else if (tipo.equals("Luta")){
					jogo = new Luta(nomeDoJogo, preco, jogabilidade);
				}else{
					throw new Exception("Tipo de jogo inválido");
				}
				
				if(listaDeUsuarios.get(index).compraJogos(jogo)){
					return true;
				}
				// Se o usuário ja tiver o jogo, retorna falso.
				return false;
			}
		}// Se o login não existir ou o jogo for muito caro retorna falso
		return false;
	}
	
	/**
	 * Como a sobrecarga de métodos é possivel, aqui está uma opção caso não tenha as jogabilidades.
	 * O método funciona exatamente igual ao outro método "vendeJogo(login, nomeDoJogo, preco, jogabilidade, tipo)"
	 * @param login Identificador único de um usuário
	 * @param nomeDoJogo Nome do jogo a ser vendido
	 * @param preco Preço do jogo a ser vendido
	 * @param tipo Tipo do jogo, que pode assumir os valores "RPG", "Plataforma" e "Luta"
	 * @return
	 * @throws Exception
	 */
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
				
				if(listaDeUsuarios.get(index).compraJogos(jogo)){
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * Impressão dos dados de cada usuário da lista da loja 
	 */
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

	public List<Usuario> getListaDeUsuarios() {
		return listaDeUsuarios;
	}

	public void setListaDeUsuarios(List<Usuario> listaDeUsuarios) {
		this.listaDeUsuarios = listaDeUsuarios;
	}	
}