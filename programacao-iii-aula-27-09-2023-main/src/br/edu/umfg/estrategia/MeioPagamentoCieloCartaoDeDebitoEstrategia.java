package br.edu.umfg.estrategia;
import java.util.Calendar;

public class MeioPagamentoCieloCartaoDeDebitoEstrategia extends 
        MeioPagamentoCieloEstrategia{
            Calendar dataCalendario = Calendar.getInstance();
            

            public MeioPagamentoCieloCartaoDeDebitoEstrategia(
                    String numeroCartao, String cpf, String cvv, String dataValidade) throws Exception {
                        super(numeroCartao, cpf, cvv, dataValidade);
                            setNumeroCartao(numeroCartao); 
                            setCvv(cvv); 
                            setCpf(cpf); 
                            setDataValidade(dataValidade); 
            }
            
            
            public void pagar(Double valor) {
                System.out.printf("Pagamento via Cielo Debito no valor: " +
                        valor + ", realizado com sucesso \n");
            }

            public void setNumeroCartao(String numeroCartao) throws Exception{
                if (numeroCartao.matches("[0-9]*") && numeroCartao.length() == 16){   
                    this.numeroCartao = numeroCartao;   
                } else {
                    throw new Exception("O cartao deve conter 16 numeros");
                }
            }
        
            public void setCvv(String cvv) throws Exception{
                if(cvv.matches("[0-9]*") && cvv.length() == 3){
                    this.cvv = cvv;
                } else {
                    throw new Exception("O cvv deve conter 3 digitos");
                }
            }
        
            public void setDataValidade(String dataValidade) throws Exception {
                // Obtém o ano e mês atuais
                int anoAtual = dataCalendario.get(Calendar.YEAR);
                int mesAtual = dataCalendario.get(Calendar.MONTH) + 1;
            
                // Obtém o ano e mês da data de validade
                int ano = Integer.parseInt(dataValidade.substring(3, 5)) + 2000;
                int mes = Integer.parseInt(dataValidade.substring(0, 2));
            
                // Verifica se a data de validade é válida
                if (ano > anoAtual || (ano == anoAtual && mes >= mesAtual)) {
                    this.dataValidade = dataValidade;
                } else {
                    throw new Exception("Validade inferior à atual");
                }
            }
            
        
            public void setCpf(String cpf) throws Exception{
                if (cpf.matches("[0-9]*") && cpf.length() == 11){
                    if (validaCpf(cpf)){
                        this.cpf = cpf;
                    } else{
                        throw new Exception("CPF INVALIDO");
                    }
                } else {
                    throw new Exception("O cpf deve conter 11 numeros");
                }
            }

            private boolean validaCpf(String cpf) {
                // Verifica o comprimento do CPF
                if (cpf.length() != 11) {
                    return false;
                }
            
                // Inicializa as somas para os dígitos verificadores
                int soma1 = 0;
                int soma2 = 0;
            
                // Calcula as somas ponderadas dos primeiros 9 dígitos do CPF
                for (int i = 0; i < 9; i++) {
                    int digito = Character.getNumericValue(cpf.charAt(i));
                    soma1 += digito * (10 - i);
                    soma2 += digito * (11 - i);
                }
            
                // Calcula os restos e ajusta se for 10
                int resto1 = (soma1 * 10) % 11;
                int resto2 = (soma2 * 10) % 11;
            
                if (resto1 == 10) {
                    resto1 = 0;
                }
            
                if (resto2 == 10) {
                    resto2 = 0;
                }
            
                // Compara os dígitos verificadores calculados com os do CPF
                return (resto1 == Character.getNumericValue(cpf.charAt(9)))
                        && (resto2 == Character.getNumericValue(cpf.charAt(10)));
            }
            
        
}
