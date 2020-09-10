package com.vvm.sh.ui.quadroPessoal.modelos;

public class ColaboradorRegisto {


    public int id, origem;
    public String nome, idMorada, morada, sexo, estado, nacionalidade;
    public long dataNascimento;
    //private String idMorada, morada, idColaborador, posto, dataNascimento, dataAdmissao, dataAdmissaoFuncao, genero, categoriaProfissional, idCategoriaProfissional, situacao, profissao, nacionalidade;
    //private int imagemSituacao, origem, corOrigem;

    public ColaboradorRegisto(int id, String nome, String idMorada, String morada, String sexo, long dataNascimento, String estado, String nacionalidade,
                              int origem,
                              String situacao, String posto,
                       String dataAdmissao, String dataAdmissaoFuncao, String categoriaProfissional, String idCategoriaProfissional,
                       String profissao){


        this.id = id;
        this.nome = nome;
        this.idMorada = idMorada;
        this.morada = morada;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.estado = estado;
        this.nacionalidade = nacionalidade;
        this.origem = origem;

//

//        gerarSituacao(situacao);
//        this.situacao = situacao;
//
//        this.origem = origem;
//        this.posto = posto;
//
//        if(origem == IdentificadoresIF.ORIGEM_DADOS_WS){
//            this.corOrigem = Color.TRANSPARENT;
//        }
//        else{
//            this.corOrigem = R.color.cor_app_novo_colaborador;
//        }
//
//        this.dataNascimento = MetodosDatas.converterData(dataNascimento, DataIF.DATA_FORMATO_DD_MM_YYYY);
//        this.dataAdmissao = MetodosDatas.converterData(dataAdmissao, DataIF.DATA_FORMATO_DD_MM_YYYY);
//        this.dataAdmissaoFuncao = MetodosDatas.converterData(dataAdmissaoFuncao, DataIF.DATA_FORMATO_DD_MM_YYYY);
//
//        this.categoriaProfissional = categoriaProfissional;
//        this.idCategoriaProfissional = idCategoriaProfissional;
//        this.profissao = profissao;
    }
//
//    /**
//     * Metodo que permite obter o identificador categoria profissional
//     * @return  o identificador categoria profissional
//     */
//    public String obterIdCategoriaProfissional() {
//        return idCategoriaProfissional;
//    }
//
//    /**
//     * Metodo que permite obter a categoria profissional
//     * @return  a categoria profissional
//     */
//    public String obterCategoriaProfissional() {
//        return categoriaProfissional;
//    }
//
//
//
//    /**
//     * Metodo que permite obter o genero
//     * @return o genero
//     */
//    public String obterGenero() {
//        return genero;
//    }
//
//
//    /**
//     * Metodo que permite obter a nacionalidade
//     * @return a nacionalidade
//     */
//    public String obterNacionalidade() {
//        return nacionalidade;
//    }
//
//
//    /**
//     * Metodo que permite obter a data de admissao
//     * @return a data (dd-MM-yyyy)
//     */
//    public String obterDataAdmissao() {
//        return dataAdmissao;
//    }
//
//    /**
//     * Metodo que permite obter a data de admissao de funcao
//     * @return a data (dd-MM-yyyy)
//     */
//    public String obterDataAdmissaoFuncao() {
//        return dataAdmissaoFuncao;
//    }
//
//
//    /**
//     * Metodo que permite obter a data de nascimento
//     * @return a data (dd-MM-yyyy)
//     */
//    public String obterDataNascimento() {
//        return dataNascimento;
//    }
//
//
//    /**
//     * Metodo que permite obter o posto
//     * @return o identificador o posto
//     */
//    public String obterPosto() {
//        return posto;
//    }
//
//
//    /**
//     * Metodo que permite obter o identificador do colaborador
//     * @return o identificador do colaborador
//     */
//    public String obterIdColaborador() {
//        return idColaborador;
//    }
//
//
//    /**
//     * Metodo que permite permite obter a origem
//     * @return a origem
//     */
//    public int obterOrigem(){
//        return origem;
//    }
//
//
//    /**
//     * Metodo que permite permite obter a cor da origem
//     * @return a cor da origem
//     */
//    public int obterCorOrigem(){
//        return corOrigem;
//    }
//
//
//    /**
//     * Metodo que devolve a morada
//     * @return a morada
//     */
//    public String obterMorada(){
//        return morada;
//    }
//
//
//    /**
//     * Metodo que devolve o identificador da morada
//     * @return identificador da morada
//     */
//    public String obterIdMorada(){
//        return idMorada;
//    }
//
//
//
//    /**
//     * Metodo que devolve a imagem da situacao atual do colaborador
//     * @return uma imagem
//     */
//    public int obterImagemSituacao(){
//        return imagemSituacao;
//    }
//
//
//    /**
//     * Metodo que devolve a imagem da situacao atual do colaborador
//     */
//    private void gerarSituacao(String situacao){
//
//        if(situacao.equals(SintaxeIF.ADMITIDO) == true || situacao.equals(SintaxeIF.ADMISSAO) == true){
//            this.imagemSituacao = R.drawable.user_add;
//        }
//        else if(situacao.equals(SintaxeIF.DEMITIDO) == true){
//            this.imagemSituacao = R.drawable.user_delete;
//        }
//        else if(situacao.equals(SintaxeIF.ESTADO_TRANSITADO) == true){
//            this.imagemSituacao = R.drawable.user_go;
//        }
//        else if(situacao.equals(SintaxeIF.READEMITIDO) == true){
//            this.imagemSituacao = R.drawable.user_edit;
//        }
//        else if(situacao.equals(SintaxeIF.QUADRO_PESSOAL_INICIAL) == true){
//            this.imagemSituacao = R.drawable.user;
//        }
//    }
//
//
//    /**
//     * Metodo que devolve a descricao da situacao do colaborador
//     * @return uma descricao
//     */
//    public String obterSituacao() {
//        return situacao;
//    }
//
//
//    /**
//     * Metodo que permite obter a profissao
//     * @return a profissao
//     */
//    public String obterProfissao() {
//        return profissao;
//    }
//
//
//    /**
//     * Metodo que permite obter a ocupacao
//     * @return a ocupacao
//     */
//    public Spanned obterOcupacao() {
//
//        String ocupacao = "<b><i>" + categoriaProfissional + "</i></b>";
//
//        if(profissao != null){
//            ocupacao = profissao + "<br>" + ocupacao;
//        }
//
//        return Html.fromHtml(ocupacao);
//    }


}
