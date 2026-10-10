$(function () {
    const usuarioLogadoId = parseInt($('#usuarioId').val());

    const RECEITA = {
        nome: 'receita',
        api: '/receita',
        paginaAtualizar: '/atualizarReceitaForm/',
        avatar: '📖',
        feed: '#feedReceitas'
    };

    const PROJETO = {
        nome: 'projeto',
        api: '/projeto',
        paginaAtualizar: '/atualizarProjetoForm/',
        avatar: '🧩',
        feed: '#feedProjetos'
    };

    function nomeDoAutor(item) {
        return item.usuario ? item.usuario.nome : 'desconhecido';
    }

    function montarPostagem(avatar, autor, titulo, descricao, nomeReceita) {
        const post = $('<div>').addClass('postagem');

        post.append($('<div>').addClass('topo-postagem')
                .append($('<span>').addClass('avatar').text(avatar))
                .append($('<span>').addClass('usuario').text(autor)));

        const descricaoEl = $('<div>').addClass('descricao').text(descricao);
        const tituloEl = $('<p>').addClass('nome-postagem').text(titulo + ' ▾');
        tituloEl.on('click', function () {
            descricaoEl.toggle();
        });
        post.append(tituloEl);

        if (nomeReceita) {
            post.append($('<p>').addClass('link-receita').text('receita: ' + nomeReceita));
        }

        post.append(descricaoEl);
        return post;
    }

    function montarAcoes(recurso, item, aoExcluir) {
        const acoes = $('<div>').addClass('acoes');

        acoes.append($('<a>')
                .attr('href', recurso.paginaAtualizar + item.id)
                .text('✏️ atualizar'));

        if (item.usuario && item.usuario.id === usuarioLogadoId) {
            const botaoExcluir = $('<button>').attr('type', 'button').text('🗑️ excluir');
            botaoExcluir.on('click', function () {
                if (!confirm('Tem certeza que deseja excluir este(a) ' + recurso.nome + '?')) {
                    return;
                }
                Api.remover(recurso.api + '/deletar/' + item.id)
                        .done(aoExcluir)
                        .fail(function () {
                            alert('Não foi possível excluir o(a) ' + recurso.nome + '.');
                        });
            });
            acoes.append(botaoExcluir);
        }

        return acoes;
    }

    function carregarReceitas() {
        Api.get(RECEITA.api + '/listar')
                .done(function (receitas) {
                    $(RECEITA.feed).empty();
                    receitas.forEach(function (r) {
                        const descricao = 'categoria: ' + r.categoria + ' | dificuldade: ' + r.dificuldade + ' — ' + r.conteudo;
                        const post = montarPostagem(RECEITA.avatar, nomeDoAutor(r), r.nome, descricao, null);
                        post.append(montarAcoes(RECEITA, r, carregarTudo));
                        $(RECEITA.feed).append(post);
                    });
                })
                .fail(function () {
                    $(RECEITA.feed).html('<p class="vazio">Não foi possível carregar as receitas.</p>');
                });
    }

    function carregarProjetos() {
        Api.get(PROJETO.api + '/listar')
                .done(function (projetos) {
                    $(PROJETO.feed).empty();
                    projetos.forEach(function (p) {
                        const descricao = 'categoria: ' + p.categoria + ' | status: ' + p.status + ' — ' + p.detalhes;
                        const nomeReceita = p.receita ? p.receita.nome : null;
                        const post = montarPostagem(PROJETO.avatar, nomeDoAutor(p), p.nome, descricao, nomeReceita);
                        post.append(montarAcoes(PROJETO, p, carregarTudo));
                        $(PROJETO.feed).append(post);
                    });
                })
                .fail(function () {
                    $(PROJETO.feed).html('<p class="vazio">Não foi possível carregar os projetos.</p>');
                });
    }

    function carregarHome() {
        $.when(Api.get(RECEITA.api + '/listar'), Api.get(PROJETO.api + '/listar'))
                .done(function (respostaReceitas, respostaProjetos) {
                    const receitas = respostaReceitas[0];
                    const projetos = respostaProjetos[0];

                    $('#feedHome').empty();
                    if (receitas.length === 0 && projetos.length === 0) {
                        $('#feedHome').html('<p class="vazio">que silêncio... 🦗🦗</p>');
                        return;
                    }
                    receitas.forEach(function (r) {
                        $('#feedHome').append(montarPostagem(RECEITA.avatar, nomeDoAutor(r), r.nome, r.conteudo, null));
                    });
                    projetos.forEach(function (p) {
                        const nomeReceita = p.receita ? p.receita.nome : null;
                        $('#feedHome').append(montarPostagem(PROJETO.avatar, nomeDoAutor(p), p.nome, p.detalhes, nomeReceita));
                    });
                })
                .fail(function () {
                    $('#feedHome').html('<p class="vazio">Não foi possível carregar o feed.</p>');
                });
    }

    function carregarTudo() {
        carregarHome();
        carregarReceitas();
        carregarProjetos();
    }

    $('.menu-lateral').on('click', '[data-aba]', function () {
        $('.aba').prop('hidden', true);
        $('#' + $(this).data('aba')).prop('hidden', false);
        $('.menu-lateral .btn-menu').removeClass('ativo');
        $(this).addClass('ativo');
    });

    carregarTudo();
});