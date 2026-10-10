$(function () {
    const receitaId = idDaUrl();

    function carregarReceita() {
        Api.get('/receita/pesquisar/' + receitaId)
                .done(function (receita) {
                    $('#nome').val(receita.nome);
                    $('#categoria').val(receita.categoria);
                    $('#dificuldade').val(receita.dificuldade);
                    $('#material').val(receita.material);
                    $('#conteudo').val(receita.conteudo);
                })
                .fail(function () {
                    alert('Não foi possível carregar os dados da receita.');
                });
    }

    $('#formAtualizarReceita').on('submit', function (event) {
        event.preventDefault();
        limparErros();

        const receita = {
            nome: $('#nome').val(),
            categoria: $('#categoria').val(),
            dificuldade: $('#dificuldade').val(),
            material: $('#material').val(),
            conteudo: $('#conteudo').val()
        };

        Api.put('/receita/atualizar/' + receitaId, receita)
                .done(function () {
                    window.location.href = '/';
                })
                .fail(function (xhr) {
                    tratarErroFormulario(xhr, 'Não foi possível atualizar a receita.');
                });
    });

    carregarReceita();
});