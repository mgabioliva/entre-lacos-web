/*
 * comum.js
 * Funções compartilhadas por todas as telas. Deve ser carregado
 * depois do jQuery e antes do script da própria tela.
 *
 * Padrão dos formulários:
 *   - <form id="formXxx"> com um <input id="campo"> por atributo da API
 *   - logo após cada campo: <span class="msg-erro" id="erroCampo"></span>
 *     (o id é "erro" + nome do campo com a primeira letra maiúscula)
 */

const Api = {
    requisicao: function (metodo, url, dados) {
        return $.ajax({
            url: url,
            method: metodo,
            contentType: 'application/json',
            data: dados === undefined ? undefined : JSON.stringify(dados)
        });
    },
    get: function (url) {
        return Api.requisicao('GET', url);
    },
    post: function (url, dados) {
        return Api.requisicao('POST', url, dados);
    },
    put: function (url, dados) {
        return Api.requisicao('PUT', url, dados);
    },
    remover: function (url) {
        return Api.requisicao('DELETE', url);
    }
};

function idDaUrl() {
    const partes = window.location.pathname.split('/');
    return partes[partes.length - 1];
}

function limparErros() {
    $('.msg-erro').text('');
}

function mostrarErros(erros) {
    limparErros();
    for (const campo in erros) {
        $('#erro' + campo.charAt(0).toUpperCase() + campo.slice(1)).text(erros[campo]);
    }
}

// 400 com JSON = erros de validação por campo; qualquer outro erro vira alerta.
function tratarErroFormulario(xhr, mensagemPadrao) {
    if (xhr.status === 400 && xhr.responseJSON) {
        mostrarErros(xhr.responseJSON);
    } else {
        alert(mensagemPadrao);
    }
}