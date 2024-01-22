package microservice.micronoticias.config.exception;

import lombok.Getter;

@Getter
public enum TipoErrorEnum {

  PROBLEMA_INTERNO_NO_SERVIDOR("Problema interno no servidor.", "/problema-interno-servidor"),
  REGRA_NEGOCIO_VIOLADA("Regra de Negócio Violada.", "/regra-de-negocio-violada"),
  RECURSO_NAO_ENCONTRADO("Recurso não encontrado!", "/recurso-nao-encontrado"),
  REQUISICAO_MAL_FORMULADA("Requisição mal formulada.", "/requisicao-mal-formulada"),
  DADOS_INVALIDOS("Dados inválidos.", "/dados-invalidos"),
  MIDIA_NAO_SUPORTADA("Tipo de mídia não suportada.", "/midia-nao-suportada"),
  VALOR_NULO_PROIBIDO("Valor nulo proibido.", "/valor-nulo-proibido"),
  USUARIO_NAO_AUTENTICADO("O Usuário não está autenticado. Precisa efetuar login.", "/usuario-nao-autenticado"),
  USUARIO_NAO_AUTORIZADO("O Usuário não está autorizado. Procure seu administrador para liberar o recurso.", "/usuario-nao-autorizado"),
  CONTROLE_DE_CONCORRENCIA_ACIONADO("O Usuário tentou atualizar um recurso desatualizado.", "/controle-concorrencia");

  private final String titulo;

  private final String caminho;

  TipoErrorEnum(String titulo, String caminho) {
    this.titulo = titulo;
    this.caminho = "http://localhost:8000" + caminho;
  }
}

