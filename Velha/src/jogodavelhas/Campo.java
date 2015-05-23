/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogodavelhas;

/**
 *
 * @author AFONSO
 */
public final class Campo {

    private final char[][] campo = new char[3][3];
    private final String posição_invalida = "Posição invalida";

    public Campo() {
        limpaCampo();
    }

    public char[][] getCampo() {
        return campo;
    }

    public char getPosicao(int i) throws Exception {
        switch (i) {
            case 1:
                return campo[0][0];
            case 2:
                return campo[0][1];
            case 3:
                return campo[0][2];
            case 4:
                return campo[1][0];
            case 5:
                return campo[1][1];
            case 6:
                return campo[1][2];
            case 7:
                return campo[2][0];
            case 8:
                return campo[2][1];
            case 9:
                return campo[2][2];
            default:
                throw new Exception(posição_invalida);
        }
    }

    private void setPosicao(int i, Opcoes opcao) throws Exception {
        switch (i) {
            case 1:
                campo[0][0] = opcao.getValor();
                break;
            case 2:
                campo[0][1] = opcao.getValor();
                break;
            case 3:
                campo[0][2] = opcao.getValor();
                break;
            case 4:
                campo[1][0] = opcao.getValor();
                break;
            case 5:
                campo[1][1] = opcao.getValor();
                break;
            case 6:
                campo[1][2] = opcao.getValor();
                break;
            case 7:
                campo[2][0] = opcao.getValor();
                break;
            case 8:
                campo[2][1] = opcao.getValor();
                break;
            case 9:
                campo[2][2] = opcao.getValor();
                break;
            default:
                throw new Exception(posição_invalida+" " + i);
        }
    }

    public void realzarJogada(int i, Opcoes opcao) throws Exception {
        setPosicao(i, opcao);
    }

    public String getCampoFormatado() {
        StringBuilder Sb = new StringBuilder("#");
        Sb.append(getLinha(1));
        Sb.append("#");
        Sb.append(getLinha(2));
        Sb.append("#");
        Sb.append(getLinha(3));
        Sb.append("#");
        return Sb.toString();
    }

    private String getLinha(int i) {
        StringBuilder Sb = new StringBuilder();
        Sb.append(campo[i - 1][0]);
        Sb.append(campo[i - 1][1]);
        Sb.append(campo[i - 1][2]);
        return Sb.toString();
    }

    public void setCampo(String campo) {
        final String[] split = campo.split("#");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.campo[i][j] = split[i + 1].charAt(j);
            }
        }

    }

    @Override
    public String toString() {
        return "Campo{\n"
                + "\t\t|" + campo[0][0] + "|" + campo[0][1] + "|" + campo[0][2] + "|\n"
                + "\t\t|" + campo[1][0] + "|" + campo[1][1] + "|" + campo[1][2] + "|\n"
                + "\t\t|" + campo[2][0] + "|" + campo[2][1] + "|" + campo[2][2] + "|\n"
                + "\n}";
    }

    public void limpaCampo() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                campo[i][j] = ' ';
            }
        }
    }    
}
