$(function () {

    $('#formCriarUsuario').on('submit', function (event) {
        event.preventDefault();
        limparErros();

        const usuario = {
            nome: $('#nome').val(),
            username: $('#username').val(),
            email: $('#email').val(),
            senha: $('#senha').val(),
            biografia: $('#biografia').val(),
            tipo: $('#tipo').val()
        };

        Api.post('/usuario/adicionar', usuario)
                .done(function () {
                    window.location.href = '/login';
                })
                .fail(function (xhr) {
                    tratarErroFormulario(xhr, 'Não foi possível cadastrar o usuário.');
                });
    });

});