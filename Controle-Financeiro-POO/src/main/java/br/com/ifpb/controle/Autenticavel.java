package br.com.ifpb.controle;
/**
 * Interface autilizada para realizar a autenticação de usuários
 * utilizando seu email e senha.
 * @author Aguirre e Jonas
 */
public interface Autenticavel {
    /**
     * Método autentica um usuário.
     * @param email String - email do usuario.
     * @param senha String - senha do usuario
     * @return boolean
     */
    public boolean autentica(String email, String senha);
}
