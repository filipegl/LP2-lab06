package steam;

import java.util.ArrayList;
import java.util.List;

public class Loja {

	private List<Usuario> listaDeUsuarios = new ArrayList<Usuario>();
	private int index;
	//lista de jogos
	
	
	public void vendeJogos(){
		
	}
	public void adicionaUsuario(String login, String nome, String tipoDeJogador) throws Exception { 
		
		if ((pesquisarUsuario(login))){
			throw new Exception("Usuário" + login + " já existe");
		}
		Usuario usuario;
		
		if (tipoDeJogador.equalsIgnoreCase("veterano")){
			usuario = new Veterano(login, nome);
			
		}else if (tipoDeJogador.equalsIgnoreCase("noob")){
			usuario = new Noob(login, nome);
		
		}else{
			throw new Exception("Tipo de jogador inválido. Digite 'noob' ou 'veterano'");
		}
		
		listaDeUsuarios.add(usuario);
			
	}
	
	public void adicionarDinheiro(String login, double quantidadeDeDinheiro) throws Exception{
		if (pesquisarUsuario(login)){
			listaDeUsuarios.get(index).setDinheiro(quantidadeDeDinheiro);
			
		}else{
			throw new Exception("Usuário" + login + "não existe");
		}
		
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
	
	public void vendeJogos(String login){
		if (pesquisarUsuario(login)){
			
		}
	}
		
	
	public void imprimirInformacoes(){
		
	}
	
}
