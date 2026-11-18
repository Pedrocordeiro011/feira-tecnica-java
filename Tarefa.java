public class Tarefa {
    private int codigo;
    private String descricao;
    private String responsavel;
    private String prioridade;
    private boolean concluida;

    public Tarefa(int codigo, String descricao, String responsavel, String prioridade) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.responsavel = responsavel;
        this.prioridade = prioridade;
        this.concluida = false;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void concluir() {
        concluida = true;
    }

    public void mostrarDados() {
        System.out.println("Código: " + codigo);
        System.out.println("Descrição: " + descricao);
        System.out.println("Responsável: " + responsavel);
        System.out.println("Prioridade: " + prioridade);

        if (concluida) {
            System.out.println("Situação: Concluída");
        } else {
            System.out.println("Situação: Pendente");
        }
        System.out.println("-------------------------");
    }
}
