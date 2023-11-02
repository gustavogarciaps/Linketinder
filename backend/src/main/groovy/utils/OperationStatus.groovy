package utils

enum OperationStatus {

    SUCCESS(">>>> Executado com sucesso."),
    FAILURE(">>>> Operação não executada."),
    NOT_FOUND(">>>> Registro não encontrado."),
    PERMISSION_DENIED(">>>> Permissão negada."),
    IN_PROGRESS(">>>> Operação em andamento."),
    CANCELED(">>>> Operação cancelada."),
    NOT_NUMBER(">>>> Operação não executada. Digite apenas números inteiros.")

    String message;

    OperationStatus(String message) {
        this.message = message
    }

}