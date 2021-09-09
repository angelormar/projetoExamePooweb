<%@ page import="java.util.Date" %><%--suppress ALL --%>
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

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
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


        .logo h1 {
            text-align: center;
            margin-top: 1%;
        }

        bl {
            color: blue;
        }

        .form {
            padding-left: 3%;
            padding-right: 3%;
            width: 60%;
            margin: auto;
        }

        .formItem {
            margin-top: 30px;
        }

        .active {
            font-weight: bold;
        }

        .pt-3-half {
            padding-top: 1.4rem;
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

    <form action="">
        <div class="form">

            <c:if test="${param.opt == 'editBagagem'}">
            <form action="bagagems">
                <h1>Editar Bagagem</h1>
                <div class="formItem">
                    <div class="formItem">
                         <input type="hidden" name="ID" value="<c:out value='${bagagem.id}'/>">
                    </div>
                </div>
                <div class="formItem">
                    <div class="formItem">
                        <label for="bagagemPassageiro">Dono da Bagagem:</label>
                        <input required id="bagagemPassageiro" type="text" name="passageiro" class="form-control"
                               value="<c:out value='${bagagem.passageiro.CPF}'/>" disabled>
                        <input type="hidden" name="passageiro" value="<c:out value='${bagagem.passageiro.CPF}'/>">
                    </div>
                </div>
                <div class="formItem">
                    <div class="formItem">
                        <label for="bagagemAviao">Placa do Avião:</label>

                        <select required id="bagagemAviao"  name="placa"  class="form-select" aria-label="Default select example">
                            <option value="${bagagem.aviao.placa}"><c:out value="${bagagem.aviao.placa}"/></option>
                        <c:forEach var="a" items="${avioesSelect}">
                            <option value="<c:out value="${a.placa}"/>" ><c:out value="${a.placa}"/> - <c:out value="${a.fabricante}"/>/<c:out value="${a.modelo}"/></option>
                        </c:forEach>
                        </select>

                    </div>
                </div>
                <div class="formItem">
                    <div class="formItem">
                        <label for="bagagemPeso">Peso do Bagagem:</label>
                        <input required id="bagagemPeso" type="text" name="peso" class="form-control"
                               value="<c:out value='${bagagem.peso}'/>">
                    </div>
                </div>
                </c:if>

                <c:if test="${param.opt == 'addBagagem'}">
                <form action="bagagems">

                    <h1>Cadastrar Bagagem</h1>
                    <div class="formItem">
                        <div class="formItem">
                            <label for="bagagemPassageiro">Dono da Bagagem:</label>
                            <input required id="bagagemPassageiro" type="text" name="passageiro" class="form-control" value="<c:out value='${cpf}'/>"
                            <c:if test="${!cpf.isEmpty()}">disabled</c:if> >
                            <c:if test="${!cpf.isEmpty()}"><input type="hidden" name="passageiro" value="<c:out value='${cpf}'/>"></c:if>
                        </div>
                    </div>
                    <div class="formItem">
                        <div class="formItem">
                            <label for="bagagemAviao">Placa Avião:</label>
                            <select required id="bagagemAviao"  name="placa"  class="form-select" aria-label="Default select example">
                                <option selected disabled value="">Escolha a placa</option>
                                <c:forEach var="a" items="${avioesSelect}">
                                    <option value="<c:out value="${a.placa}"/>" ><c:out value="${a.placa}"/> - <c:out value="${a.fabricante}"/>/<c:out value="${a.modelo}"/></option>
                                </c:forEach>
                            </select>

                        </div>
                    </div>
                    <div class="formItem">
                        <div class="formItem">
                            <label for="bagagemPeso">Peso da Bagagem:</label>
                            <input required id="bagagemPeso" type="text" name="peso" class="form-control">
                        </div>
                    </div>
                    </c:if>


                    <c:if test="${param.opt == 'editOficina'}">
                    <form action="oficinas">
                        <h1>Editar Oficina</h1>
                        <div class="formItem">
                            <div class="formItem">
                                <input type="hidden" name="id" value="<c:out value='${oficina.id}'/>">
                            </div>
                        </div>
                        <div class="formItem">
                            <div class="formItem">
                                <label for="oficinaNome">Nome do Oficina:</label>
                                <input required id="oficinaNome" type="text" name="nome" class="form-control"
                                       value="<c:out value='${oficina.nome}'/>">
                            </div>
                        </div>
                        <div class="formItem">
                            <div class="formItem">
                                <label for="oficinaEndereco">Endereco do Oficina:</label>
                                <input required id="oficinaEndereco" type="text" name="endereco" class="form-control"
                                       value="<c:out value='${oficina.endereco}'/>">
                            </div>
                        </div>
                        </c:if>

                        <c:if test="${param.opt == 'addOficina'}">
                        <form action="oficinas">
                            <h1>Cadastrar Oficina</h1>
                            <div class="formItem">
                                <div class="formItem">
                                    <label for="oficinaNome">Nome do Oficina:</label>
                                    <input required id="oficinaNome" type="text" name="nome" class="form-control">
                                </div>
                            </div>
                            <div class="formItem">
                                <div class="formItem">
                                    <label for="oficinaEndereco">Endereco do Oficina:</label>
                                    <input required id="oficinaEndereco" type="text" name="endereco" class="form-control">
                                </div>
                            </div>
                            </c:if>

            <c:if test="${param.opt == 'editPassageiro'}">
            <form action="passageiros">
                <h1>Editar Passageiro</h1>
                <div class="formItem">
                    <div class="formItem">
                        <label for="passageiroCPF">CPF do Passageiro:</label>
                        <input id="passageiroCPF" type="text" name="CPF" class="form-control"
                               value="<c:out value='${passageiro.CPF}'/>" disabled>
                        <input type="hidden" name="CPF" value="<c:out value='${passageiro.CPF}'/>">
                    </div>
                </div>
                <div class="formItem">
                    <div class="formItem">
                        <label for="passageiroNome">Nome do Passageiro:</label>
                        <input required id="passageiroNome" type="text" name="nome" class="form-control"
                               value="<c:out value='${passageiro.nome}'/>">
                    </div>
                </div>
                <div class="formItem">
                    <div class="formItem">
                        <label for="passageiroEndereco">Endereco do Passageiro:</label>
                        <input required id="passageiroEndereco" type="text" name="endereco" class="form-control"
                               value="<c:out value='${passageiro.endereco}'/>">
                    </div>
                </div>
                </c:if>

                <c:if test="${param.opt == 'addPassageiro'}">
                <form action="passageiros">

                    <h1>Cadastrar Passageiro</h1>
                    <div class="formItem">
                        <div class="formItem">
                            <label for="passageiroCPF">CPF do Passageiro:</label>
                            <input required id="passageiroCPF" type="text" name="CPF" class="form-control">
                        </div>
                    </div>
                    <div class="formItem">
                        <div class="formItem">
                            <label for="passageiroNome">Nome do Passageiro:</label>
                            <input required id="passageiroNome" type="text" name="nome" class="form-control">
                        </div>
                    </div>
                    <div class="formItem">
                        <div class="formItem">
                            <label for="passageiroEndereco">Endereco do Passageiro:</label>
                            <input required id="passageiroEndereco" type="text" name="endereco" class="form-control">
                        </div>
                    </div>
                    </c:if>


                    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
                    <c:if test="${param.opt == 'editViagem'}">

                        <fmt:parseDate value="${viagem.data}" var="dataObj"
                                       pattern="yyyy-MM-dd HH:mm:ss"/>
                        <fmt:formatDate value="${dataObj}" pattern="yyyy-MM-dd" var="dataV"/>

                    <form action="viagem">
                        <input type="hidden" name="param" value="<c:out value='${viagem.codigo}'/>">
                        <h1>Editar Viagem</h1>
                        <div class="formItem">
                            <label for="viagemPlaca">Placa do avião:</label>
                            <select required id="viagemPlaca" name="placa" class="form-select" aria-label="Default select example">
                                <option value="${viagem.placa}"><c:out value="${viagem.placa}"/></option>
                                <c:forEach var="a" items="${avioesSelect}">
                                    <option value="<c:out value="${a.placa}"/>" ><c:out value="${a.placa}"/> - <c:out value="${a.fabricante}"/>/<c:out value="${a.modelo}"/></option>
                                </c:forEach>
                            </select>

                        </div>
                        <div class="formItem">
                            <label for="viagemOrigem">Origem:</label>
                            <input required id="viagemOrigem" type="text" name="origem" class="form-control"
                                   value="<c:out value='${viagem.origem}'/>">
                        </div>
                        <div class="formItem">
                            <label for="viagemDestino">Destino:</label>
                            <input required id="viagemDestino" type="text" name="destino" class="form-control"
                                   value="<c:out value='${viagem.destino}'/>">
                        </div>
                        <div class="formItem">
                            <label for="viagemData">Data da viagem:</label>
                            <input required id="viagemData" type="date" name="data" class="form-control"
                                   value="<c:out value='${dataV}'/>">
                        </div>
                        <div class="formItem">
                            <label for="viagemHora">Hora da viagem:</label>
                            <input required id="viagemHora" type="time" name="hora" class="form-control"
                                   value="<c:out value='${viagem.hora}'/>">
                        </div>
                        </c:if>

                        <c:if test="${param.opt == 'addViagem'}">
                        <form action="viagem">
                            <h1>Cadastrar Viagem</h1>
                            <div class="formItem">
                                <label for="viagemPlaca">Placa do avião:</label>

                                <select required id="viagemPlaca"  name="placa"  class="form-select" aria-label="Default select example">
                                    <option selected disabled value="">Escolha a placa</option>
                                    <c:forEach var="a" items="${avioesSelect}">
                                        <option value="<c:out value="${a.placa}"/>" ><c:out value="${a.placa}"/> - <c:out value="${a.fabricante}"/>/<c:out value="${a.modelo}"/></option>
                                    </c:forEach>
                                </select>

                            </div>
                            <div class="formItem">
                                <label for="viagemOrigem">Origem:</label>
                                <input required id="viagemOrigem" type="text" name="origem" class="form-control">
                            </div>
                            <div class="formItem">
                                <label for="viagemDestino">Destino:</label>
                                <input required id="viagemDestino" type="text" name="destino" class="form-control">
                            </div>
                            <div class="formItem">
                                <label for="viagemData">Data da viagem:</label>
                                <input required id="viagemData" type="date" name="data" class="form-control">
                            </div>
                            <div class="formItem">
                                <label for="viagemHora">Hora da viagem:</label>
                                <input required id="viagemHora" type="time" name="hora" class="form-control">
                            </div>
                            </c:if>


                            <c:if test="${param.opt == 'addConserto'}">
                            <form action="conserto">
                                <h1>Agendar Conserto</h1>
                                <div class="formItem">
                                    <label for="consertoPlaca">Placa do avião</label>

                                    <select required id="consertoPlaca"  name="placa"  class="form-select" aria-label="Default select example">
                                        <option selected disabled value="">Escolha a placa</option>
                                        <c:forEach var="a" items="${avioesSelect}">
                                            <option value="<c:out value="${a.placa}"/>" ><c:out value="${a.placa}"/> - <c:out value="${a.fabricante}"/>/<c:out value="${a.modelo}"/></option>
                                        </c:forEach>
                                    </select>

                                    <input type="hidden" name="idConserto"
                                           value="<c:out value='${conserto.idConserto}'/>">
                                </div>
                                <div class="formItem">
                                    <label for="consertoIfOficina">ID da oficina</label>
                                    <select required id="consertoIfOficina"  name="idOficina"  class="form-select" aria-label="Default select example">
                                    <option selected disabled value="">Escolha a oficina</option>
                                    <c:forEach var="of" items="${oficinaSelect}">
                                        <option value="<c:out value="${of.id}"/>" ><c:out value="${of.nome}"/></option>
                                    </c:forEach>
                                </select>
                                </div>

                                <div class="formItem">
                                    <label for="consertoData">Data</label>
                                    <input required id="consertoData" type="date" name="data" class="form-control">
                                </div>
                                </c:if>

                                <c:if test="${param.opt == 'editConserto'}">
                                <form action="conserto">
                                    <h1>Agendar Conserto</h1>
                                    <div class="formItem">
                                        <label for="consertoPlaca">Placa do avião</label>

                                        <select required id="consertoPlaca"  name="placa"  class="form-select" aria-label="Default select example">
                                            <option value="${conserto.placa}"><c:out value="${conserto.placa}"/></option>
                                            <c:forEach var="a" items="${avioesSelect}">
                                                <option value="<c:out value="${a.placa}"/>" ><c:out value="${a.placa}"/> - <c:out value="${a.fabricante}"/>/<c:out value="${a.modelo}"/></option>
                                            </c:forEach>
                                        </select>

                                        <input type="hidden" name="idConserto"
                                               value="<c:out value='${conserto.idConserto}'/>">
                                    </div>
                                    <div class="formItem">
                                        <label for="consertoIfOficina">ID da oficina</label>
                                        <select required id="consertoIfOficina"  name="idOficina"  class="form-select" aria-label="Default select example">
                                            <option value="${conserto.idOficina}"><c:out value="${conserto.idOficina}"/></option>
                                            <c:forEach var="of" items="${oficinaSelect}">
                                                <option value="<c:out value="${of.id}"/>" ><c:out value="${of.nome}"/></option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="formItem">
                                        <label for="consertoData">Data</label>
                                        <input required id="consertoData" type="date" name="data" class="form-control"
                                               value="<c:out value='${conserto.data}'/>">
                                    </div>
                                    </c:if>


                                    <c:if test="${param.opt == 'addAviao'}">
                                    <form action="aviao">
                                        <h1>Cadastrar avião</h1>
                                        <div class="formItem">
                                            <label for="aviaoPlaca">Placa do avião</label>
                                            <input required id="aviaoPlaca" type="text" name="placa"
                                                   class="form-control">
                                        </div>
                                        <div class="formItem">
                                            <label for="aviaoFabricante">Fabricante</label>
                                            <input required id="aviaoFabricante" type="text" name="fabricante"
                                                   class="form-control">
                                        </div>
                                        <div class="formItem">
                                            <label for="aviaoModelo">Modelo</label>
                                            <input required id="aviaoModelo" type="text" name="modelo"
                                                   class="form-control">
                                        </div>
                                        </c:if>


                                        <c:if test="${param.opt == 'editAviao'}">
                                        <form action="aviao">
                                            <h1>Editar avião</h1>
                                            <div class="formItem">
                                                <label for="aviaoPlaca">Placa do avião</label>
                                                <input required id="aviaoPlaca" type="text" name="placa"
                                                       class="form-control"
                                                       value="<c:out value='${aviao.placa}'/>">
                                            </div>
                                            <div class="formItem">
                                                <label for="aviaoFabricante">Fabricante</label>
                                                <input required id="aviaoFabricante" type="text" name="fabricante"
                                                       class="form-control"
                                                       value="<c:out value='${aviao.fabricante}'/>">
                                            </div>
                                            <div class="formItem">
                                                <label for="aviaoModelo">Modelo</label>
                                                <input required id="aviaoModelo" type="text" name="modelo"
                                                       class="form-control"
                                                       value="<c:out value='${aviao.modelo}'/>">
                                            </div>
                                            </c:if>

                                            <c:if test="${param.opt.startsWith('add')}">
                                            <input required type="submit" class="btn btn-success formItem" name="opt"
                                                   value="Cadastrar">
                                            </c:if>
                                            <c:if test="${param.opt.startsWith('edit')}">
                                            <input required type="submit" class="btn btn-warning formItem" name="opt"
                                                   value="Editar">
                                            </c:if>


        </div>
    </form>

</div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>

<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>

</html>
