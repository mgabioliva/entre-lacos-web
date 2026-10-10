$(function () {
    const usuarioId = idDaUrl();

    // A API exige "senha" na validação, mas a atualização ignora o valor.
    // Reaproveitamos o que veio no carregamento só para passar na validação.
    let senhaAtual = '';

    function carregarUsuario() {
        Api.get('/usuario/pesquisar/' + usuarioId)
                .done(function (usuario) {
                    senhaAtual = usuario.senha;
                    $('#nome').val(usuario.nome);
                    $('#username').val(usuario.username);
                    $('#email').val(usuario.email);
                    $('#biografia').val(usuario.biografia);
                    $('#tipo').val(usuario.tipo);
                })
                .fail(function () {
                    alert('Não foi possível carregar os dados do usuário.');
                });
    }

    $('#formAtualizarUsuario').on('submit', function (event) {
        event.preventDefault();
        limparErros();

        const usuario = {
            nome: $('#nome').val(),
            username: $('#username').val(),
            email: $('#email').val(),
            senha: senhaAtual,
            biografia: $('#biografia').val(),
            tipo: $('#tipo').val()
        };

        Api.put('/usuario/atualizar/' + usuarioId, usuario)
                .done(function () {
                    window.location.href = '/';
                })
                .fail(function (xhr) {
                    tratarErroFormulario(xhr, 'Não foi possível atualizar o usuário.');
                });
    });

    carregarUsuario();
});