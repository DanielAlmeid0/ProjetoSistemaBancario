package app;

import exception.InvalidValueException;
import model.Cliente;
import model.Conta;
import model.Endereco;
import service.Banco;
import util.Validacoes;

import javax.swing.*;
import java.awt.*;
import java.util.List;

    public class BancoGUI extends JFrame {

        // Referência ao seu Service (O "Cérebro" do sistema)
        private Banco banco;

        // Componentes da Interface (Campos que substituem o Scanner)
        private JTabbedPane abas;
        private JTextArea areaLog; // Onde vamos "imprimir" os dados
        private JComboBox<String> comboClientes; // Para escolher clientes na hora de abrir conta

        // Campos de Cadastro de Cliente
        private JTextField txtNome, txtDoc, txtDataNasc, txtNomeEmpresa;
        private JTextField txtRua, txtCep, txtNum, txtBairro, txtCidade, txtComplemento;
        private JComboBox<String> comboTipoCliente;

        public BancoGUI() {
            // 1. Inicializa o Banco (Carrega arquivos, faz a persistência funcionar)
            banco = new Banco("PACHECO's Bank");

            // 2. Configuração básica da Janela
            setTitle("Sistema Bancário - Pacheco's Bank");
            setSize(800, 600);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            // 3. Criando as Abas
            abas = new JTabbedPane();
            abas.addTab("Cadastro de Clientes", criarPainelCadastro());
            abas.addTab("Abrir Conta", criarPainelContas());
            abas.addTab("Visão Geral", criarPainelListagem());

            add(abas);

            // Atualiza as listas iniciais
            atualizarListaClientesCombo();
            atualizarAreaLog();
        }

        // --- ABA 1: CADASTRO (Substitui o metodo adicaoDeCliente) ---
        private JPanel criarPainelCadastro() {
            JPanel painel = new JPanel(new GridLayout(12, 2, 10, 10));
            painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            painel.add(new JLabel("")); // Espaço vazio
            painel.add(new JLabel("DADOS PESSOAIS"));
            painel.add(new JLabel("")); // Espaço vazio

            // Campos do Formulário
            painel.add(new JLabel("Tipo de Cliente:"));
            comboTipoCliente = new JComboBox<>(new String[]{"Pessoa Física", "Pessoa Jurídica"});
            painel.add(comboTipoCliente);

            String tipoDoClienteCombo = (String) comboTipoCliente.getSelectedItem();
            String comboCliente;

            if (tipoDoClienteCombo.equals("Pessoa Física")){
                comboCliente = "PF";
            } else if  (tipoDoClienteCombo.equals("Pessoa Jurídica")){
                comboCliente = "PJ";
            } else {
                comboCliente = "";
            }

            painel.add(new JLabel("Nome do Cliente:"));
            txtNome = new JTextField();
            painel.add(txtNome);

            //quero alterar essa parte para aparecer só cpf quando o cliente for PF
            // e aparecer só cnpj e tambem nomeDaEmpresa para quando o cliente for PJ
            painel.add(new JLabel("CPF / CNPJ:"));
            txtDoc = new JTextField();
            painel.add(txtDoc);

            painel.add(new JLabel("Data de Nascimento (dd/MM/yyyy):"));
            txtDataNasc = new JTextField();
            painel.add(txtDataNasc);

            //painel.add(new JLabel("Nome da Empresa:")); txtNomeEmpresa = new JTextField(); painel.add(txtNomeEmpresa);
            //rever essa lógica de nomeDaEmpresa, ela deve aparecer só se o cliente for PJ

            // Endereço (Simplificado para o exemplo)
            painel.add(new JLabel("ENDEREÇO"));
            painel.add(new JLabel("")); // Espaço vazio

            painel.add(new JLabel("Rua:")); txtRua = new JTextField(); painel.add(txtRua);
            painel.add(new JLabel("CEP:")); txtCep = new JTextField(); painel.add(txtCep);
            painel.add(new JLabel("Número:")); txtNum = new JTextField(); painel.add(txtNum);
            painel.add(new JLabel("Bairro:")); txtBairro = new JTextField(); painel.add(txtBairro);
            painel.add(new JLabel("Cidade:")); txtCidade = new JTextField(); painel.add(txtCidade);
            //revisar aqui essa parrte de "complemento"
            painel.add(new JLabel("Complemento:")); txtComplemento = new JTextField(); painel.add(txtComplemento);

            JButton btnSalvar = new JButton("Salvar Cliente");
            painel.add(btnSalvar);

            // --- A LÓGICA DO BOTÃO (Onde a mágica acontece) ---
            btnSalvar.addActionListener(e -> {
                try {
                    // 1. Coletar dados dos campos (Não usa Scanner!)
                    String nome = txtNome.getText();
                    String doc = txtDoc.getText();
                    String data = txtDataNasc.getText();
                    String tipo = comboCliente;
                    //String nomeDaEmpresa = txtNomeEmpresa.getText();
                    String complemento = txtComplemento.getText();
                    Validacoes.validacaoDasStrings(nome);
                    Validacoes.validacaoDasStrings(doc, 11);// para o cpf
                    Validacoes.validacaoDasDatas(data);

                    if (complemento.trim().isEmpty() || complemento.trim() == null) {
                        complemento = "Nenhum";
                    }

//                    if (nomeDaEmpresa.trim().isEmpty() || nomeDaEmpresa.trim() == null) {
//                        nomeDaEmpresa = "Empresa Default";
//                    }

                    Endereco novoEndereco = new Endereco(
                            txtRua.getText(), txtCep.getText(),
                            Integer.parseInt(txtNum.getText()), complemento,
                            txtBairro.getText(), txtCidade.getText()
                    );

                    // 2. Chama o seu Banco
                    boolean sucesso = false;

                    if (tipo.equals("PF")) {
                        sucesso = banco.adicionarCliente(nome, doc, novoEndereco, data);
                    } else {
                        sucesso = banco.adicionarCliente(nome, doc, novoEndereco, data, "Empresa Default");
                    }

                    if (sucesso) {
                        JOptionPane.showMessageDialog(this, "Cliente Cadastrado!");
                        limparCamposCadastro();
                        atualizarListaClientesCombo(); // Atualiza a outra aba
                        atualizarAreaLog(); // Atualiza a listagem
                    } else {
                        JOptionPane.showMessageDialog(this, "Erro ao cadastrar.");
                    }

                }catch (InvalidValueException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                } catch (NumberFormatException ex) {
                    // Tratamento específico se digitar letras no número da casa
                    JOptionPane.showMessageDialog(this, "Erro: O campo 'Número' aceita apenas dígitos!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro nos dados: " + ex.getMessage());
                }
            });

            return painel;
        }

        // --- ABA 2: ABRIR CONTA (Substitui o metodo abrirConta) ---
        private JPanel criarPainelContas() {
            JPanel painel = new JPanel(new FlowLayout());

            painel.add(new JLabel("Selecione o Cliente:"));
            comboClientes = new JComboBox<>();
            painel.add(comboClientes);

            painel.add(new JLabel("Tipo de Conta:"));
            JComboBox<String> comboTipoConta = new JComboBox<>(new String[]{"Conta Corrente", "Conta Poupança"});
            painel.add(comboTipoConta);

            String tipoDaContaCombo = (String) comboTipoConta.getSelectedItem();

            if (tipoDaContaCombo.equals("Conta Corrente")) {
                tipoDaContaCombo = "CC";
            } else if (tipoDaContaCombo.equals("Conta Poupança")) {
                tipoDaContaCombo = "CP";
            }

            JButton btnAbrir = new JButton("Abrir Conta");
            painel.add(btnAbrir);

            String finalTipoDaContaCombo = tipoDaContaCombo;
            btnAbrir.addActionListener(e -> {
                try {
                    String nomeSelecionado = (String) comboClientes.getSelectedItem();
                    String tipoConta = finalTipoDaContaCombo;

                    // Busca o objeto Cliente na lista do banco
                    Cliente clienteAlvo = banco.getClientesDoBanco().stream()
                            .filter(c -> c.getNome().equals(nomeSelecionado))
                            .findFirst()
                            .orElse(null);

                    //verificação de existência de conta
                    if (tipoConta.equalsIgnoreCase("CP") || tipoConta.equalsIgnoreCase("CC")) {

                        for (Conta contaExistente : clienteAlvo.consultarContasVinculadas()) {
                            if (contaExistente.getTipo().equalsIgnoreCase(tipoConta)) {
                                throw new IllegalArgumentException("ERRO: o cliente já possui uma conta do tipo \"" + tipoConta.toUpperCase() + "\" vinculada!");
                            }
                        }

                    } else {
                        throw new IllegalArgumentException("ERRO: tipo de conta \"" + tipoConta.trim() + "\" não válido!");
                    }

                    // abre a conta
                    if (clienteAlvo != null) {
                        boolean abriu = banco.abrirConta(clienteAlvo, tipoConta);

                        if (abriu) {
                            JOptionPane.showMessageDialog(this, "Conta " + tipoConta.trim() + " aberta com sucesso!");
                            atualizarAreaLog();
                        } else {
                            JOptionPane.showMessageDialog(this, "Erro ao abrir conta (Verifique se já existe).");
                        }
                    }

                }catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
                }
            });

            return painel;
        }

        // --- ABA 3: LISTAGEM (Substitui mostrarClientesComContasVinculadas) ---
        private JPanel criarPainelListagem() {
            JPanel painel = new JPanel(new BorderLayout());
            areaLog = new JTextArea();
            areaLog.setEditable(false);
            areaLog.setFont(new Font("Monospaced", Font.PLAIN, 12));

            painel.add(new JScrollPane(areaLog), BorderLayout.CENTER);

            JButton btnAtualizar = new JButton("Atualizar Listagem");
            btnAtualizar.addActionListener(e -> atualizarAreaLog());
            painel.add(btnAtualizar, BorderLayout.SOUTH);

            return painel;
        }

        // --- Métodos Auxiliares ---

        // Atualiza o ComboBox da aba de Contas para aparecer novos clientes
        private void atualizarListaClientesCombo() {
            comboClientes.removeAllItems();
            List<Cliente> clientes = banco.getClientesDoBanco();
            for (Cliente c : clientes) {
                comboClientes.addItem(c.getNome());
            }
        }

        // Simula o System.out.println jogando texto na tela
        private void atualizarAreaLog() {
            areaLog.setText(""); // Limpa
            areaLog.append("--- CLIENTES E CONTAS ---\n\n");

            //talvez tenha que verificar se a lista de clientes do banco está atualizada com a do arquivo.txt
            List<Cliente> clientes = banco.getClientesDoBanco();
            for (Cliente c : clientes) {
                areaLog.append("Cliente: " + c.getNome() + "\n");

                // Aqui usei sua lógica de pegar contas
                var contas = c.consultarContasVinculadas();
                if (contas.isEmpty()) {
                    areaLog.append("   (Sem contas)\n");
                } else {
                    for (var conta : contas) {
                        areaLog.append("   -> Conta " + conta.getTipo() + " | Saldo: " + conta.getSaldo() + "\n");
                    }
                }
                areaLog.append("---------------------------\n");
            }
        }

        private void limparCamposCadastro() {
            txtNome.setText("");
            txtDoc.setText("");
            txtDataNasc.setText("");
            txtRua.setText("");
            // ... limpar os outros
        }

        // Metodo Main para rodar a tela
        public static void main(String[] args) {
            // Estilo visual nativo do Windows/Mac/Linux
            try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ignored) {}

            // Inicia a tela
            SwingUtilities.invokeLater(() -> new BancoGUI().setVisible(true));
        }
}