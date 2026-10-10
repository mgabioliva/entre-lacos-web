$(function () {

    function carregarReceitas() {
        Api.get('/receita/listar')
                .done(function (receitas) {
                    receitas.forEach(function (receita) {
                        $('#receitaId').append($('<option>').val(receita.id).text(receita.nome));
                    });
                });
    }

    $('#formCriarProjeto').on('submit', function (event) {
        event.preventDefault();
        limparErros();

        const receitaId = $('#receitaId').val();
        const projeto = {
            nome: $('#nome').val(),
            categoria: $('#categoria').val(),
            duracao: $('#duracao').val(),
            material: $('#material').val(),
            status: $('#status').val(),
            detalhes: $('#detalhes').val(),
            usuario: { id: parseInt($('#usuarioId').val()) },
            receita: receitaId ? { id: parseInt(receitaId) } : null
        };

        Api.post('/projeto/adicionar', projeto)
                .done(function () {
                    window.location.href = '/';
                })
                .fail(function (xhr) {
                    tratarErroFormulario(xhr, 'Não foi possível cadastrar o projeto.');
                });
    });

    carregarReceitas();
});