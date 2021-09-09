<%--
  Created by IntelliJ IDEA.
  User: angel
  Date: 12/08/2021
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>
<c:set var="date" value="<%=new java.util.Date().getTime() %>"/>

<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <title>Reserva de Passagens</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
          integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300&display=swap" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.js"
            integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
    <script
            src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"
            data-auto-a11y="true"
    ></script>
    <style>
        body {
            margin: 0;
            padding: 0;
            background-color: rgb(70, 101, 220);
            font-family: 'Open Sans', sans-serif;

        }

        html {
            margin: 0
        }

        nav {
            color: black;
            background-color: white;
            box-shadow: 0px 0px 10px black;
        }

        .container {
            margin: auto;
            padding: 2% 5% 1% 5%;
            background-color: white;
            height: 1000px;
        }


        .logo h1 {
            text-align: center;
            margin-top: 1%;
        }

        bl {
            color: blue;
        }

        .fa-trash {
            color: red;
        }

        .fa-pen {
            color: orange;
            margin-left: 3px;
        }
        .active {
            font-weight: bold;
        }
        .cadastrar{
            float: right;

        }
    </style>
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="./">
        <bl><b>Azul</b></bl>
        Airlines</a>
    <button class="navbar-toggler dpd" type="button" data-toggle="collapse" data-target="#navbarNavDropdown"
            aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Alterna navegação">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="./passageiros?opt=list">Todos os Passageiros</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="./viagens?opt=prox&prox=true">Próximas Viagens</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="./viagens?opt=prev">Histórico de viagens</a>
            </li>
            <li class="nav-item">
                <a href="aviao?opt=list" class="nav-link">Aviões cadastrados</a>
            </li>
            <li class="nav-item">
                <a href="oficina?opt=list" class="nav-link">Oficinas cadastradas</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Nova
                </a>
                <div class="dropdown-menu dpd" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" href="./viagens?opt=addViagem">Viagem</a>
                    <a class="dropdown-item" href="./passageiros?opt=addPassageiro">Passageiro</a>
                    <a class="dropdown-item" href="./aviao?opt=addAviao">Avião</a>
                    <a class="dropdown-item" href="./conserto?opt=addConserto">Solicitação de Conserto</a>
                    <a class="dropdown-item" href="./oficina?opt=addOficina">Oficina</a>
                </div>
            </li>
        </ul>
    </div>
</nav>



<div class="container">

    <table class="table">
        <c:if test="${oficinas != null}">
        <thead>
        <tr>
            <th>Id</th>
            <th>Nome</th>
            <th>Endereco</th>
            <th>Ação</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="of" items="${oficinas}">
        <tr>
            <td>${of.id}</td>
            <td>${of.nome}</td>
            <td>${of.endereco}</td>
            <td>
                <a href="./oficina?opt=editOficina&param=<c:out value='${of.id}'/>"><i class="fas fa-pen"></i></a>
                <a href="./oficina?opt=deleteOficina&param=<c:out value='${of.id}'/>"><i class="fas fa-trash"></i></a>
            </td>
        </tr>
        </c:forEach>
        </c:if>


        <c:if test="${avioes != null}">
        <thead>
        <tr>
            <th>Placa</th>
            <th>Fabricante</th>
            <th>Modelo</th>
            <th>Ação</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="a" items="${avioes}">
        <tr>
            <td>${a.placa}</td>
            <td>${a.fabricante}</td>
            <td>${a.modelo}</td>
            <td>
                <a href="./aviao?opt=editAviao&param=<c:out value='${a.placa}'/>"><i class="fas fa-pen"></i></a>
                <a href="./aviao?opt=deleteAviao&param=<c:out value='${a.placa}'/>"><i
                        class="fas fa-trash"></i></a>
                <a href="./aviao?opt=showConserto&param=<c:out value='${a.placa}'/>"><i class="fas fa-tools"></i></a>
            </td>
        </tr>
        </c:forEach>
        </c:if>

        <c:if test="${consertos != null}">
        <thead>
        <tr>
            <th>ID Conserto</th>
            <th>Placa</th>
            <th>ID Oficina</th>
            <th>Data</th>
            <th>Ação</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="c" items="${consertos}">
        <tr>
            <td>${c.idConserto}</td>
            <td>${c.placa}</td>
            <td>${c.idOficina}</td>
            <td>${c.data}</td>
            <td>
                <a href="./conserto?opt=editConserto&param=<c:out value='${c.idConserto}'/>"><i class="fas fa-pen"></i></a>
                <a href="./conserto?opt=deleteConserto&param=<c:out value='${c.idConserto}'/>"><i
                        class="fas fa-trash"></i></a>
            </td>
        </tr>
        </c:forEach>
        </c:if>

        <c:if test="${passageiros != null || todospassageiros != null}">
        <thead>
        <tr>
            <th>Nome</th>
            <th>CPF</th>
            <th>Endereco</th>
            <th>Ação</th>
            <c:if test="${(param.opt.equals('showViagem')  || todospassageiros != null) && param.prox == 'true'}">
                <th>Cancelar Embarque</th>
            </c:if>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="p" items="${passageiros}">
        <tr>
            <td>${p.nome}</td>
            <td>${p.CPF}</td>
            <td>${p.endereco}</td>
            <td>
                <a href="./passageiros?opt=editPassageiro&param=<c:out value='${p.CPF}'/>"><i class="fas fa-pen"></i></a>
                <a href="./passageiros?opt=deletePassageiro&param=<c:out value='${p.CPF}'/>"><i class="fas fa-trash"></i></a>
                <a href="./bagagem?opt=passBagagem&param=<c:out value='${p.CPF}'/>"><i class="fas fa-suitcase"></i></a>
            </td>
            <td>
                <c:if test="${(param.opt.equals('showViagem')  || todospassageiros != null) && param.prox == 'true'}">
                <a class="btn btn-danger" href="./embarque?opt=del&param=<c:out value='${param.param}'/>&CPF=<c:out value='${p.CPF}'/>"><i class="fas fa-minus"></i></a>
                </c:if>
            </td>
        </tr>
        </c:forEach>
        <c:if test="${(param.opt.equals('showViagem')  || todospassageiros != null) && param.prox == 'true'}">
        <div class="cadastrar">
            <a class="dropdown-toggle btn btn-success btn-sm" href="#" role="button" data-toggle="dropdown">
                <i class="fas fa-plus"></i>
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                <c:forEach var="tp" items="${todospassageiros}">
                <a class="dropdown-item" href="./embarque?opt=add&CPF=<c:out value='${tp.CPF}'/>&param=<c:out value='${param.param}'/>">${tp.nome}</a>
                </c:forEach>
            </div>
        </div>
        </c:if>
        </c:if>


        <c:if test='${viagens != null}'>
        <thead>
        <tr>
            <th>Placa</th>
            <th>Data e Hora</th>
            <th>Origem</th>
            <th>Destino</th>
            <th>Ação</th>
        </tr>
        </thead>
        <tbody>

        <c:if test="${param.opt == 'prev'}">
        <c:forEach var="v" items="${viagens}">
        <c:if test="${v.data.getTime() < date }">
        <tr>
            <td>${v.placa}</td>
            <td>${v.data}</td>
            <td>${v.origem}</td>
            <td>${v.destino}</td>
            <td>
                <a href="./viagens?opt=editViagem&param=<c:out value='${v.codigo}'/>"><i class="fas fa-pen"></i></a>
                <a href="./viagens?opt=deleteViagem&param=<c:out value='${v.codigo}'/>"><i class="fas fa-trash"></i></a>
                <a href="./viagens?opt=showViagem&param=<c:out value='${v.codigo}'/>"><i class="fas fa-eye"></i></a>
            </td>
        </tr>
        </c:if>
        </c:forEach>
        </c:if>

        <c:if test="${param.opt == 'prox' || todospassageiros != null}">
        <c:forEach var="v" items="${viagens}">
        <c:if test="${v.data.getTime() > date }">
        <tr>
            <td>${v.placa}</td>
            <td>${v.data}</td>
            <td>${v.origem}</td>
            <td>${v.destino}</td>
            <td>
                <a href="./viagens?opt=editViagem&param=<c:out value='${v.codigo}'/>"><i class="fas fa-pen"></i></a>
                <a href="./viagens?opt=deleteViagem&param=<c:out value='${v.codigo}'/>"><i class="fas fa-trash"></i></a>
                <a href="./viagens?opt=showViagem&param=<c:out value='${v.codigo}'/><c:if test="${param.prox.equals('true')}">&prox=true</c:if>"><i class="fas fa-eye"></i></a>
            </td>
        </tr>
        </c:if>
        </c:forEach>

        </c:if>

        <c:if test="${param.opt != 'prox'}">
        <c:if test="${param.opt != 'prev'}">

        <c:forEach var="v" items="${viagens}">
        <tr>
            <td>${v.placa}</td>
            <td>${v.data}</td>
            <td>${v.origem}</td>
            <td>${v.destino}</td>
            <td>
                <a href="./viagens?opt=editViagem&param=<c:out value='${v.codigo}'/>"><i class="fas fa-pen"></i></a>
                <a href="./viagens?opt=deleteViagem&param=<c:out value='${v.codigo}'/>"><i class="fas fa-trash"></i></a>
                <a href="./viagens?opt=showViagem&param=<c:out value='${v.codigo}'/>"><i class="fas fa-eye"></i></a>
            </td>
        </tr>
        </c:forEach>
        </c:if>
        </c:if>
        </c:if>

        <c:if test="${bagagens != null}">
            <thead>
            <tr>
                <th>ID</th>
                <th>Peso</th>
                <th>CPF Passageiro</th>
                <th>Placa Avião</th>
                <th>Ação</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="b" items="${bagagens}">
            <tr>
                <td>${b.id}</td>
                <td>${b.peso}</td>
                <td>${b.passageiro.CPF}</td>
                <td>${b.aviao.placa}</td>
                <td>
                    <a href="./bagagem?opt=editBagagem&param=<c:out value='${b.id}'/>"><i class="fas fa-pen"></i></a>
                    <a href="./bagagem?opt=deleteBagagem&param=<c:out value='${b.id}'/>"><i class="fas fa-trash"></i></a>
                </td>
            </tr>
            </c:forEach>
            <div class="cadastrar">
                <a class="btn btn-success" href="./bagagem?opt=addBagagem&CPF=<c:out value='${param.param}'/>"><i class="fas fa-plus"></i></a>
            </div>
            </c:if>

        <tbody>
    </table>
    <status value="<c:out value="${status}"></c:out>"/>
</div>

<script>
    status = $('status').attr('value')
    if (status.length > 0) {
        if (status == 'ok') {
            $('.container').prepend('<div class="alert alert-success" role="alert">Operação bem sucedida.<div>')
        }else if(status.startsWith('ERROR: null value in column "fk_passageiro_cpf"')){
            $('.container').prepend(`<div class="alert alert-danger" role="alert">Não foi possiivel excluir o passageiro, o mesmo possui viagens já cadastradas.<div>`)
        }
        else {
            $('.container').prepend(`<div class="alert alert-danger" role="alert">${status}<div>`)
        }

    }
</script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</body>

</html>
