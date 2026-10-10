$(function () {

    $('#formCriarReceita').on('submit', function (event) {
        event.preventDefault();
        limparErros();

        const receita = {
            nome: $('#nome').val(),
            categoria: $('#categoria').val(),
            dificuldade: $('#dificuldade').val(),
            material: $('#material').val(),
            conteudo: $('#conteudo').val(),
            usuario: { id: parseInt($('#usuarioId').val()) }
        };

        Api.post('/receita/adicionar', receita)
                .done(function () {
                    window.location.href = '/';
                })
                .fail(function (xhr) {
                    tratarErroFormulario(xhr, 'Não foi possível cadastrar a receita.');
                });
    });

});