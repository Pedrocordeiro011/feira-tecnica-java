import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
    static Scanner entrada = new Scanner(System.in);
    static ArrayList<Tarefa> tarefas = new ArrayList<>();

    public static void main(String[] args) {
        int opcao;

        do {
            System.out.println("\n===== ORGANIZADOR DE TAREFAS =====");
            System.out.println("1 - Cadastrar tarefa");
            System.out.println("2 - Listar todas as tarefas");
            System.out.println("3 - Buscar tarefa pelo código");
            System.out.println("4 - Concluir tarefa");
            System.out.println("5 - Listar tarefas pendentes");
            System.out.println("6 - Mostrar quantidade de tarefas");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            while (!entrada.hasNextInt()) {
                System.out.println("Digite um número válido.");
                entrada.nextLine();
                System.out.print("Escolha uma opção: ");
            }
            opcao = entrada.nextInt();
            entrada.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarTarefa();
                    break;
                case 2:
                    listarTodas();
                    break;
                case 3:
                    buscarTarefa();
                    break;
                case 4:
                    concluirTarefa();
                    break;
                case 5:
                    listarPendentes();
                    break;
                case 6:
                    mostrarQuantidade();
                    break;
                case 0:
                    System.out.println("Programa encerrado.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);

        entrada.close();
    }

    public static void cadastrarTarefa() {
        System.out.print("Código: ");
        while (!entrada.hasNextInt()) {
            System.out.println("O código deve ser um número.");
            entrada.nextLine();
            System.out.print("Código: ");
        }
        int codigo = entrada.nextInt();
        entrada.nextLine();

        if (buscarPorCodigo(codigo) != null) {
            System.out.println("Já existe uma tarefa com esse código.");
            return;
        }

        System.out.print("Descrição: ");
        String descricao = entrada.nextLine().trim();
        if (descricao.isEmpty()) {
            System.out.println("A descrição não pode ficar vazia.");
            return;
        }

        System.out.print("Nome do responsável: ");
        String responsavel = entrada.nextLine().trim();
        if (responsavel.isEmpty()) {
            System.out.println("O responsável não pode ficar vazio.");
            return;
        }

        System.out.print("Prioridade (baixa, média ou alta): ");
        String prioridade = entrada.nextLine().trim().toLowerCase();
        if (!prioridade.equals("baixa") && !prioridade.equals("média") && !prioridade.equals("media") && !prioridade.equals("alta")) {
            System.out.println("Prioridade inválida.");
            return;
        }
        if (prioridade.equals("media")) {
            prioridade = "média";
        }

        Tarefa tarefa = new Tarefa(codigo, descricao, responsavel, prioridade);
        tarefas.add(tarefa);
        System.out.println("Tarefa cadastrada com sucesso!");
    }

    public static void listarTodas() {
        if (tarefas.isEmpty()) {
            System.out.println("Não há tarefas cadastradas.");
            return;
        }

        for (Tarefa tarefa : tarefas) {
            tarefa.mostrarDados();
        }
    }

    public static void buscarTarefa() {
        System.out.print("Digite o código da tarefa: ");
        if (!entrada.hasNextInt()) {
            System.out.println("Código inválido.");
            entrada.nextLine();
            return;
        }
        int codigo = entrada.nextInt();
        entrada.nextLine();

        Tarefa tarefa = buscarPorCodigo(codigo);
        if (tarefa == null) {
            System.out.println("Tarefa não encontrada.");
        } else {
            tarefa.mostrarDados();
        }
    }

    public static void concluirTarefa() {
        System.out.print("Digite o código da tarefa que deseja concluir: ");
        if (!entrada.hasNextInt()) {
            System.out.println("Código inválido.");
            entrada.nextLine();
            return;
        }
        int codigo = entrada.nextInt();
        entrada.nextLine();

        Tarefa tarefa = buscarPorCodigo(codigo);
        if (tarefa == null) {
            System.out.println("Tarefa não encontrada.");
        } else if (tarefa.isConcluida()) {
            System.out.println("Essa tarefa já está concluída.");
        } else {
            tarefa.concluir();
            System.out.println("Tarefa concluída com sucesso!");
        }
    }

    public static void listarPendentes() {
        boolean encontrou = false;

        for (Tarefa tarefa : tarefas) {
            if (!tarefa.isConcluida()) {
                tarefa.mostrarDados();
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Não há tarefas pendentes.");
        }
    }

    public static void mostrarQuantidade() {
        int pendentes = 0;
        int concluidas = 0;

        for (Tarefa tarefa : tarefas) {
            if (tarefa.isConcluida()) {
                concluidas++;
            } else {
                pendentes++;
            }
        }

        System.out.println("Tarefas pendentes: " + pendentes);
        System.out.println("Tarefas concluídas: " + concluidas);
    }

    public static Tarefa buscarPorCodigo(int codigo) {
        for (Tarefa tarefa : tarefas) {
            if (tarefa.getCodigo() == codigo) {
                return tarefa;
            }
        }
        return null;
    }
}
