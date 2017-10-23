package br.com.ifpb.controle;
/**
 * Interface autenticavel
 *
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
