$(function () {
    const projetoId = idDaUrl();

    function carregarProjeto() {
        Api.get('/projeto/pesquisar/' + projetoId)
                .done(function (projeto) {
                    $('#nome').val(projeto.nome);
                    $('#categoria').val(projeto.categoria);
                    $('#duracao').val(projeto.duracao);
                    $('#material').val(projeto.material);
                    $('#status').val(projeto.status);
                    $('#detalhes').val(projeto.detalhes);
                })
                .fail(function () {
                    alert('Não foi possível carregar os dados do projeto.');
                });
    }

    $('#formAtualizarProjeto').on('submit', function (event) {
        event.preventDefault();
        limparErros();

        const projeto = {
            nome: $('#nome').val(),
            categoria: $('#categoria').val(),
            duracao: $('#duracao').val(),
            material: $('#material').val(),
            status: $('#status').val(),
            detalhes: $('#detalhes').val()
        };

        Api.put('/projeto/atualizar/' + projetoId, projeto)
                .done(function () {
                    window.location.href = '/';
                })
                .fail(function (xhr) {
                    tratarErroFormulario(xhr, 'Não foi possível atualizar o projeto.');
                });
    });

    carregarProjeto();
});