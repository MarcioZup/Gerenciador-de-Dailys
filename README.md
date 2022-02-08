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

### UML
<hr>
<mxGraphModel><root><mxCell id="0"/><mxCell id="1" parent="0"/><mxCell id="2" style="edgeStyle=orthogonalEdgeStyle;rounded=0;orthogonalLoop=1;jettySize=auto;html=1;exitX=0.5;exitY=0;exitDx=0;exitDy=0;entryX=0.591;entryY=1.01;entryDx=0;entryDy=0;entryPerimeter=0;strokeWidth=3;startArrow=diamond;startFill=0;startSize=9;" edge="1" source="17" target="8" parent="1"><mxGeometry relative="1" as="geometry"><mxPoint x="470" y="410" as="targetPoint"/><Array as="points"><mxPoint x="470" y="740"/><mxPoint x="470" y="730"/></Array></mxGeometry></mxCell><mxCell id="3" value="Usuário" style="swimlane;fontStyle=2;align=center;verticalAlign=top;childLayout=stackLayout;horizontal=1;startSize=26;horizontalStack=0;resizeParent=1;resizeLast=0;collapsible=1;marginBottom=0;rounded=0;shadow=0;strokeWidth=1;" vertex="1" parent="1"><mxGeometry x="210" y="30" width="440" height="370" as="geometry"><mxRectangle x="230" y="140" width="160" height="26" as="alternateBounds"/></mxGeometry></mxCell><mxCell id="4" value="- String email;&#10;" style="text;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;" vertex="1" parent="3"><mxGeometry y="26" width="440" height="26" as="geometry"/></mxCell><mxCell id="5" value="- String senha;" style="text;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;rounded=0;shadow=0;html=0;" vertex="1" parent="3"><mxGeometry y="52" width="440" height="26" as="geometry"/></mxCell><mxCell id="6" value="- String nome;&#10;&#10;- String nomeDaSquad; " style="text;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;rounded=0;shadow=0;html=0;" vertex="1" parent="3"><mxGeometry y="78" width="440" height="76" as="geometry"/></mxCell><mxCell id="7" value="" style="line;html=1;strokeWidth=1;align=left;verticalAlign=middle;spacingTop=-1;spacingLeft=3;spacingRight=3;rotatable=0;labelPosition=right;points=[];portConstraint=eastwest;" vertex="1" parent="3"><mxGeometry y="154" width="440" height="8" as="geometry"/></mxCell><mxCell id="8" value="+ salvarUsuario (Usuario usuario) : return Usuario; &#10;&#10;+ alterarDadosUsuario (String email, UsuarioDTO usuarioNovo): return Usuario;&#10;&#10;+ exibirUsuarioPorEmail (Strin email) : return Usuario;&#10;&#10;+ deletarUsuario (String email) : return void;&#10;&#10;+ exibirUsuarioPorSquad (String nomeDaSquad) : return Usuario;&#10;&#10;+ exibirUsuarioPorEmail (String email) : return Usuario;&#10;&#10;+ validarEmail (String email) : return void;" style="text;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;" vertex="1" parent="3"><mxGeometry y="162" width="440" height="208" as="geometry"/></mxCell><mxCell id="9" value="ProximaTask" style="swimlane;fontStyle=0;align=center;verticalAlign=top;childLayout=stackLayout;horizontal=1;startSize=26;horizontalStack=0;resizeParent=1;resizeLast=0;collapsible=1;marginBottom=0;rounded=0;shadow=0;strokeWidth=1;" vertex="1" parent="1"><mxGeometry x="10" y="460" width="440" height="260" as="geometry"><mxRectangle x="130" y="380" width="160" height="26" as="alternateBounds"/></mxGeometry></mxCell><mxCell id="10" value="- Long id;&#10;&#10;- String descricao;&#10;&#10;- LocalDate dataInicio;&#10;&#10;- String previsaoFIm;" style="text;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;" vertex="1" parent="9"><mxGeometry y="26" width="440" height="114" as="geometry"/></mxCell><mxCell id="11" value="" style="line;html=1;strokeWidth=1;align=left;verticalAlign=middle;spacingTop=-1;spacingLeft=3;spacingRight=3;rotatable=0;labelPosition=right;points=[];portConstraint=eastwest;" vertex="1" parent="9"><mxGeometry y="140" width="440" height="8" as="geometry"/></mxCell><mxCell id="12" value="+ salvarProximaTask (ProximaTaskEntradaDTO) : return ProximaTask;&#10;&#10;+ exibirProximasTasks () : return Iterable Proximastask;&#10;&#10;+ atualizarProximaTask ( Long id, ProximaTaskSaidaDTO) : return ProximaTask;&#10;&#10;+ deletarProximaTask (Long id) : return void;" style="text;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;fontStyle=0" vertex="1" parent="9"><mxGeometry y="148" width="440" height="112" as="geometry"/></mxCell><mxCell id="13" value="TaskAtual" style="swimlane;fontStyle=0;align=center;verticalAlign=top;childLayout=stackLayout;horizontal=1;startSize=26;horizontalStack=0;resizeParent=1;resizeLast=0;collapsible=1;marginBottom=0;rounded=0;shadow=0;strokeWidth=1;" vertex="1" parent="1"><mxGeometry x="490" y="460" width="440" height="260" as="geometry"><mxRectangle x="130" y="380" width="160" height="26" as="alternateBounds"/></mxGeometry></mxCell><mxCell id="14" value="- Long id;&#10;&#10;- String descricao;&#10;&#10;- LocalDate dataInicio;&#10;&#10;- String previsaoFIm;" style="text;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;" vertex="1" parent="13"><mxGeometry y="26" width="440" height="114" as="geometry"/></mxCell><mxCell id="15" value="" style="line;html=1;strokeWidth=1;align=left;verticalAlign=middle;spacingTop=-1;spacingLeft=3;spacingRight=3;rotatable=0;labelPosition=right;points=[];portConstraint=eastwest;" vertex="1" parent="13"><mxGeometry y="140" width="440" height="8" as="geometry"/></mxCell><mxCell id="16" value="+ salvarTaskAtual (String emailUsuario) : return TaskAtual;&#10;&#10;+ exibirTasksAtuais () : return Iterable TaskAtual;&#10;&#10;+ atualizarTaskAtual ( Long id, TaskAtualSaidaDTO) : return TaskAtual;&#10;&#10;+ deletarTaskAtual (Long id) : return void;" style="text;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;fontStyle=0" vertex="1" parent="13"><mxGeometry y="148" width="440" height="112" as="geometry"/></mxCell><mxCell id="17" value="Impedimento" style="swimlane;fontStyle=0;align=center;verticalAlign=top;childLayout=stackLayout;horizontal=1;startSize=26;horizontalStack=0;resizeParent=1;resizeLast=0;collapsible=1;marginBottom=0;rounded=0;shadow=0;strokeWidth=1;" vertex="1" parent="1"><mxGeometry x="230" y="740" width="480" height="260" as="geometry"><mxRectangle x="130" y="380" width="160" height="26" as="alternateBounds"/></mxGeometry></mxCell><mxCell id="18" value="- Long id;&#10;&#10;- String descricao;" style="text;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;" vertex="1" parent="17"><mxGeometry y="26" width="480" height="54" as="geometry"/></mxCell><mxCell id="19" value="" style="line;html=1;strokeWidth=1;align=left;verticalAlign=middle;spacingTop=-1;spacingLeft=3;spacingRight=3;rotatable=0;labelPosition=right;points=[];portConstraint=eastwest;" vertex="1" parent="17"><mxGeometry y="80" width="480" height="8" as="geometry"/></mxCell><mxCell id="20" value="+ salvarImpedimento (ImpedimentoEntradaDTO) : return Impedimento;&#10;&#10;+ exibirImpedimentos () : return Iterable Impedimento;&#10;&#10;+ alterarDescricaoImpedimento( Long id, ImpedimentoSaidaDTO) : return Impedimento;&#10;&#10;+ deletarImpedimento (Long id) : return void;" style="text;align=left;verticalAlign=top;spacingLeft=4;spacingRight=4;overflow=hidden;rotatable=0;points=[[0,0.5],[1,0.5]];portConstraint=eastwest;fontStyle=0" vertex="1" parent="17"><mxGeometry y="88" width="480" height="112" as="geometry"/></mxCell><mxCell id="21" style="edgeStyle=orthogonalEdgeStyle;rounded=0;orthogonalLoop=1;jettySize=auto;html=1;exitX=0.5;exitY=0;exitDx=0;exitDy=0;strokeWidth=3;startArrow=diamond;startFill=0;entryX=0;entryY=0.433;entryDx=0;entryDy=0;entryPerimeter=0;endSize=6;startSize=9;" edge="1" target="8" parent="1"><mxGeometry relative="1" as="geometry"><mxPoint x="140" y="250" as="targetPoint"/><mxPoint x="140" y="460" as="sourcePoint"/><Array as="points"><mxPoint x="140" y="282"/></Array></mxGeometry></mxCell><mxCell id="22" style="edgeStyle=orthogonalEdgeStyle;rounded=0;orthogonalLoop=1;jettySize=auto;html=1;exitX=0.5;exitY=0;exitDx=0;exitDy=0;strokeWidth=3;startArrow=diamond;startFill=0;entryX=1.007;entryY=0.428;entryDx=0;entryDy=0;entryPerimeter=0;startSize=9;" edge="1" target="8" parent="1"><mxGeometry relative="1" as="geometry"><mxPoint x="790" y="282.0640000000001" as="targetPoint"/><mxPoint x="720" y="460" as="sourcePoint"/><Array as="points"><mxPoint x="720" y="282"/></Array></mxGeometry></mxCell></root></mxGraphModel>
