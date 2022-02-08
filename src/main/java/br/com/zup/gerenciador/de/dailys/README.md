# DailyPoint

##Rotas

Cadastro do Usuário:

* /usuario
* POST
* json

```
{
"email":"xablau@zup.com.br",
"senha":"Aviao11",
"nome":"Vinicius",
"nomeDaSquad":"Itau"
}
```

* Resposta 201

<hr>

Atualizar Cadastro do Usuário:

* /usuario/email
* PUT
* Precisa de Token de Autenticação
* json
```
{
"email":"xablau@zup.com.br",
"senha":"Aviao11"
}
```

Deletar Cadastro de Usuário

* /usuario/email
* DELETE
* *Precisa de Token de Autenticação
* Resposta 204

<hr>
Login

* /login
* POST
* json
```
{
"email":"xablau@zup.com.br",
"senha":"Aviao11"
}
```

* Resposta 200
<hr>

```
{
"Authorization":"Token 1278637gdasdas"
}
```

<hr>
Cadastro TaskAtual

* /taskAtual
* POST
* json
```
{
"descricao":"Implementar método",
"previsaoFim":"14/02/2022",
"emailUsuario":"xablau@zup.com.br"
}
```
Visualizar Task Atual

* /taskAtual
* GET
* Resposta 200

Atualizar TaskAtual

* /task/id
* PUT
* Precisa de Token de Autenticação
* json
```
{
"descricao":"Refatorar classe Usuario"
}
```

* Resposta 200
```
{
"descricao":"Refatorar classe Usuario",
"dataInicio":"11/02/2022",
"previsaoFim":"14/02/2022",
"emailUsuario": "xablau@zup.com.br"
}
```

Deletar TaskAtual

* /taskAtual/id
* DELETE
* Precisa de Token de Autenticação
* Resposta 204


<hr>
Cadastro Próxima Task

* /proximaTask
* POST
* json
```
{
"descricao":"Implementar método",
"previsaoFim":"14/02/2022",
"emailUsuario":"xablau@zup.com.br"
}
```
Visualizar Próxima Task

* /proximaTask
* GET
* Resposta 200

Atualizar Próxima Task

* /proximaTask/id
* PUT
* Precisa de Token de Autenticação
* json
```
{
"descricao":"Refatorar classe Usuario"
}
```

* Resposta 200
```
{
"descricao":"Refatorar classe Usuario",
"dataInicio":"11/02/2022",
"previsaoFim":"14/02/2022",
"emailUsuario": "xablau@zup.com.br"
}
```

Deletar Próxima Task

* /proximaTask/id
* DELETE
* Precisa de Token de Autenticação
* Resposta 204

<hr>
Cadastro Impedimento

* /impedimento
* POST
* json
```
{
"descricao":"Implementar método",
"emailUsuario":"xablau@zup.com.br"
}
```
Visualizar Impedimento

* /impedimento
* GET
* Resposta 200

Atualizar impedimento

* /impedimento/id
* PUT
* Precisa de Token de Autenticação
* json
```
{
"descricao":"Refatorar classe Usuario"
}
```

* Resposta 200
```
{
"id": 1,
"descricao":"Refatorar classe Usuario",
"usuario": {
    "email":"xablau@zup.com.br",
    "nome": "Vinicius"
    }
}
```

Deletar Impedimento

* /impedimento/id
* DELETE
* Precisa de Token de Autenticação
* Resposta 204