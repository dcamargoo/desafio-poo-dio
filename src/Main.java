import br.com.dio.desafio.dominio.*;

import java.time.LocalDate;
import java.util.*;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final Map<Integer, Dev> devs = new HashMap<>();
    private static final Map<Integer, Curso> cursos = new HashMap<>();
    private static final Map<Integer, Mentoria> mentorias = new HashMap<>();
    private static final Map<Integer, Bootcamp> bootcamps = new HashMap<>();

    private static int devId = 1;
    private static int cursoId = 1;
    private static int mentoriaId = 1;
    private static int bootcampId = 1;

    public static void main(String[] args) {

        int opcao;

        do {
            mostrarMenu();
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1 -> registrarDev();
                case 2 -> registrarCurso();
                case 3 -> registrarMentoria();
                case 4 -> registrarBootcamp();
                case 5 -> inserirConteudosBootcamp();
                case 6 -> inscreverDevBootcamp();
                case 7 -> progredirDev();
                case 8 -> listarDados();
                case 9 -> verConteudosDeUmDev();
                case 0 -> System.out.println("Encerrando sistema...");
                default -> System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    private static void mostrarMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1 - Registrar Dev");
        System.out.println("2 - Registrar Curso");
        System.out.println("3 - Registrar Mentoria");
        System.out.println("4 - Registrar Bootcamp");
        System.out.println("5 - Inserir conteúdos no Bootcamp");
        System.out.println("6 - Inscrever Dev em Bootcamp");
        System.out.println("7 - Progredir Dev");
        System.out.println("8 - Listar Dados");
        System.out.println("9 - Ver Conteudos de um Dev");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");
    }

    private static void registrarDev() {
        System.out.print("Nome do Dev: ");
        String nome = scanner.nextLine();

        Dev dev = new Dev();
        dev.setNome(nome);

        devs.put(devId, dev);
        System.out.println("Dev registrado com ID: " + devId);
        devId++;
    }

    private static TipoConteudo escolherTipoConteudo() {

        System.out.println("Escolha o tipo do conteúdo:");

        TipoConteudo[] tipos = TipoConteudo.values();

        for (int i = 0; i < tipos.length; i++) {
            System.out.println(i + " - " + tipos[i]);
        }

        int escolha = Integer.parseInt(scanner.nextLine());

        if (escolha < 0 || escolha >= tipos.length) {
            System.out.println("Tipo inválido. Usando PROGRAMACAO como padrão.");
            return TipoConteudo.PROGRAMACAO;
        }

        return tipos[escolha];
    }

    private static void registrarCurso() {
        Curso curso = new Curso();

        System.out.print("Título: ");
        curso.setTitulo(scanner.nextLine());

        System.out.print("Descrição: ");
        curso.setDescricao(scanner.nextLine());

        System.out.print("Carga horária: ");
        curso.setCargaHoraria(Integer.parseInt(scanner.nextLine()));

        TipoConteudo tipo = escolherTipoConteudo();
        curso.setTipo(tipo);

        cursos.put(cursoId, curso);
        System.out.println("Curso registrado com ID: " + cursoId);
        cursoId++;
    }

    private static void registrarMentoria() {
        Mentoria mentoria = new Mentoria();

        System.out.print("Título: ");
        mentoria.setTitulo(scanner.nextLine());

        System.out.print("Descrição: ");
        mentoria.setDescricao(scanner.nextLine());

        mentoria.setData(LocalDate.now());

        TipoConteudo tipo = escolherTipoConteudo();
        mentoria.setTipo(tipo);

        mentorias.put(mentoriaId, mentoria);
        System.out.println("Mentoria registrada com ID: " + mentoriaId);
        mentoriaId++;
    }

    private static void registrarBootcamp() {
        Bootcamp bootcamp = new Bootcamp();

        System.out.print("Nome do Bootcamp: ");
        bootcamp.setNome(scanner.nextLine());

        bootcamp.getConteudos().addAll(cursos.values());
        bootcamp.getConteudos().addAll(mentorias.values());

        bootcamps.put(bootcampId, bootcamp);
        System.out.println("Bootcamp registrado com ID: " + bootcampId);
        bootcampId++;
    }

    private static void inserirConteudosBootcamp() {

        if (bootcamps.isEmpty()) {
            System.out.println("Nenhum bootcamp cadastrado.");
            return;
        }

        System.out.println("Bootcamps disponíveis:");
        bootcamps.forEach((id, b) ->
                System.out.println("ID: " + id + " | " + b.getNome())
        );

        System.out.print("Digite o ID do Bootcamp: ");
        int idBootcamp = Integer.parseInt(scanner.nextLine());

        Bootcamp bootcamp = bootcamps.get(idBootcamp);

        if (bootcamp == null) {
            System.out.println("Bootcamp não encontrado.");
            return;
        }

        System.out.println("1 - Adicionar Curso");
        System.out.println("2 - Adicionar Mentoria");
        System.out.print("Escolha: ");
        int tipo = Integer.parseInt(scanner.nextLine());

        if (tipo == 1) {

            if (cursos.isEmpty()) {
                System.out.println("Nenhum curso cadastrado.");
                return;
            }

            System.out.println("Cursos disponíveis:");
            cursos.forEach((id, c) ->
                    System.out.println("ID: " + id + " | " + c.getTitulo())
            );

            System.out.print("Digite o ID do Curso: ");
            int idCurso = Integer.parseInt(scanner.nextLine());

            Curso curso = cursos.get(idCurso);

            if (curso == null) {
                System.out.println("Curso não encontrado.");
                return;
            }

            bootcamp.getConteudos().add(curso);
            System.out.println("Curso adicionado ao Bootcamp!");

        } else if (tipo == 2) {

            if (mentorias.isEmpty()) {
                System.out.println("Nenhuma mentoria cadastrada.");
                return;
            }

            System.out.println("Mentorias disponíveis:");
            mentorias.forEach((id, m) ->
                    System.out.println("ID: " + id + " | " + m.getTitulo())
            );

            System.out.print("Digite o ID da Mentoria: ");
            int idMentoria = Integer.parseInt(scanner.nextLine());

            Mentoria mentoria = mentorias.get(idMentoria);

            if (mentoria == null) {
                System.out.println("Mentoria não encontrada.");
                return;
            }

            bootcamp.getConteudos().add(mentoria);
            System.out.println("Mentoria adicionada ao Bootcamp!");

        } else {
            System.out.println("Opção inválida.");
        }
    }

    private static void inscreverDevBootcamp() {

        System.out.print("ID do Dev: ");
        int idDev = Integer.parseInt(scanner.nextLine());

        System.out.print("ID do Bootcamp: ");
        int idBootcamp = Integer.parseInt(scanner.nextLine());

        Dev dev = devs.get(idDev);
        Bootcamp bootcamp = bootcamps.get(idBootcamp);

        if (dev == null || bootcamp == null) {
            System.out.println("Dev ou Bootcamp não encontrado.");
            return;
        }

        dev.inscreverBootcamp(bootcamp);
        System.out.println("Dev inscrito com sucesso!");
    }

    private static void progredirDev() {

        System.out.print("ID do Dev: ");
        int idDev = Integer.parseInt(scanner.nextLine());

        Dev dev = devs.get(idDev);

        if (dev == null) {
            System.out.println("Dev não encontrado.");
            return;
        }

        dev.progredir();
        System.out.println("Dev progrediu!");

        System.out.println("Conteúdos concluídos: " + dev.getConteudosConcluidos());
        System.out.println("XP Total: " + dev.calcularTotalXP());
    }

    private static void listarDados() {

        System.out.println("\n--- DEVS ---");
        devs.forEach((id, dev) ->
                System.out.println("ID: " + id + " | Nome: " + dev.getNome())
        );

        System.out.println("\n--- CURSOS ---");
        cursos.forEach((id, curso) ->
                System.out.println("ID: " + id + " | " + curso.getTitulo())
        );

        System.out.println("\n--- MENTORIAS ---");
        mentorias.forEach((id, mentoria) ->
                System.out.println("ID: " + id + " | " + mentoria.getTitulo())
        );

        System.out.println("\n--- BOOTCAMPS ---");
        bootcamps.forEach((id, bootcamp) ->
                System.out.println("ID: " + id + " | " + bootcamp.getNome())
        );
    }

    private static void verConteudosDeUmDev(){

        if(devs.isEmpty()) {
            System.out.println("Lista de Devs vazia.");
            return;
        }

        System.out.print("Qual o ID do Dev que se deseja ver os dados:");
        int idDev = Integer.parseInt(scanner.nextLine());
        
        Dev dev = devs.get(idDev);

        if(dev == null){
            System.out.println("Dev não encontrado.");
            return;
        }
        
        System.out.println("\n--- CONTEUDOS DO DEV " + dev.getNome() + " ---");
        System.out.println("\nInscritos: " + dev.getConteudosInscritos());
        System.out.println("\nConcluídos: " + dev.getConteudosConcluidos());
    }
}