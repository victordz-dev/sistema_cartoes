package br.com.fiap.view;

import br.com.fiap.controller.ClienteController;
import br.com.fiap.controller.CartaoController;
import br.com.fiap.controller.TransacaoController;
import br.com.fiap.model.Cartao;
import br.com.fiap.model.Cliente;
import br.com.fiap.model.Transacao;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        ClienteController clienteCtrl = new ClienteController();
        CartaoController cartaoCtrl = new CartaoController();
        TransacaoController transacaoCtrl = new TransacaoController();

        while (true) {
            System.out.println("\n=== SISTEMA DE CARTÕES ===");
            System.out.println("1. Cadastrar cliente");
            System.out.println("2. Solicitar cartão");
            System.out.println("3. Registrar compra");
            System.out.println("4. Consultar fatura (mês atual)");
            System.out.println("5. Pagar fatura");
            System.out.println("--- CONSULTAS GERAIS ---");
            System.out.println("6. Consultar Cliente por CPF");
            System.out.println("7. Consultar Cartão por Número");
            System.out.println("8. Listar Todas as Transações do Cartão");
            System.out.println("9. Sair");
            System.out.print("Escolha: ");
            int opcao = Integer.parseInt(sc.nextLine());

            try {
                switch (opcao) {
                    case 1 -> {
                        System.out.print("CPF: ");
                        String cpf = sc.nextLine();
                        System.out.print("Nome: ");
                        String nome = sc.nextLine();
                        System.out.print("E-mail: ");
                        String email = sc.nextLine();
                        System.out.print("Renda: ");
                        double renda = Double.parseDouble(sc.nextLine());

                        clienteCtrl.cadastrarCliente(cpf, nome, email, renda);
                    }
                    case 2 -> {
                        System.out.print("CPF do cliente: ");
                        String cpf = sc.nextLine();
                        Cliente cliente = clienteCtrl.buscarCliente(cpf);
                        if (cliente == null) {
                            System.out.println("Cliente não encontrado!");
                        } else {
                            System.out.print("Bandeira (VISA/MASTERCARD): ");
                            String bandeira = sc.nextLine();
                            cartaoCtrl.solicitarCartao(cliente, bandeira);
                        }
                    }
                    case 3 -> {
                        System.out.print("Número do cartão: ");
                        String numero = sc.nextLine();
                        System.out.print("Valor da compra: ");
                        double valor = Double.parseDouble(sc.nextLine());
                        System.out.print("Descrição: ");
                        String desc = sc.nextLine();
                        transacaoCtrl.registrarCompra(numero, valor, desc);
                    }
                    case 4 -> {
                        System.out.print("Número do cartão: ");
                        String numero = sc.nextLine();
                        transacaoCtrl.consultarFatura(numero);
                    }
                    case 5 -> {
                        System.out.print("Número do cartão: ");
                        String numero = sc.nextLine();
                        transacaoCtrl.pagarFatura(numero);
                    }
                    case 6 -> {
                        System.out.print("Digite o CPF do cliente: ");
                        String cpf = sc.nextLine();
                        Cliente cliente = clienteCtrl.buscarCliente(cpf);
                        if (cliente == null) {
                            System.out.println("Cliente não encontrado!");
                        } else {
                            System.out.println("\n--- DADOS DO CLIENTE ---");
                            System.out.println("Nome: " + cliente.getNome());
                            System.out.println("Email: " + cliente.getEmail());
                            System.out.println("Renda: R$" + cliente.getRenda());

                            List<Cartao> cartoes = cartaoCtrl.listarPorCliente(cliente.getId());
                            if (cartoes.isEmpty()) {
                                System.out.println("-> Cliente não possui cartões.");
                            } else {
                                System.out.println("--- CARTÕES VINCULADOS ---");
                                for (Cartao c : cartoes) {
                                    System.out.println("Número: " + c.getNumero() + " | Bandeira: " + c.getBandeira());
                                }
                            }
                        }
                    }
                    case 7 -> {
                        System.out.print("Digite o número do cartão: ");
                        String numero = sc.nextLine();
                        Cartao cartao = cartaoCtrl.buscarPorNumero(numero);
                        if (cartao == null) {
                            System.out.println("Cartão não encontrado!");
                        } else {
                            System.out.println("\n--- DADOS DO CARTÃO ---");
                            System.out.println("Número: " + cartao.getNumero());
                            System.out.println("Bandeira: " + cartao.getBandeira());
                            System.out.println("Limite Total: R$" + cartao.getLimiteTotal());
                            System.out.println("Limite Disponível: R$" + cartao.getLimiteDisponivel());
                        }
                    }
                    case 8 -> { // NOVA FUNCIONALIDADE: Listar Todas as Transações
                        System.out.print("Digite o número do cartão: ");
                        String numero = sc.nextLine();
                        Cartao cartao = cartaoCtrl.buscarPorNumero(numero);
                        if (cartao == null) {
                            System.out.println("Cartao não encontrado!");
                        } else {
                            List<Transacao> transacoes = transacaoCtrl.listarTodasPorCartao(cartao.getId());
                            if (transacoes.isEmpty()) {
                                System.out.println("Nenhuma transação encontrada para este cartão.");
                            } else {
                                System.out.println("\n--- EXTRATO COMPLETO DO CARTÃO " + cartao.getNumero() + " ---");
                                for (Transacao t : transacoes) {
                                    System.out.printf("Data: %s | Descrição: %s | Valor: R$%.2f | Status: %s\n",
                                            t.getData().format(dtf), t.getDescricao(), t.getValor(), t.getStatus());
                                }
                            }
                        }
                    }
                    case 9 -> { // Sair
                        System.out.println("Saindo...");
                        sc.close();
                        return;
                    }
                    default -> System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("ERRO INESPERADO: " + e.getMessage());
            }
        }
    }
}