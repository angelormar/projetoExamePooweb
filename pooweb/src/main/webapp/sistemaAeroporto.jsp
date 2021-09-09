<!--
Created by IntelliJ IDEA.
User: angel
Date: 12/08/2021
Time: 18:52
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>-->
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Reserva de Passagens</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
          integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous" />
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <style>
        body{
            margin: 0;
            padding: 0;
            background-color: rgb(70, 101, 220);
            font-family: 'Open Sans', sans-serif;

        }
        html{
            margin: 0
        }

        nav {
            color: black;
            background-color:white;
            box-shadow: 0px 0px 10px black;
        }

        .container{
            margin: auto;
            padding: 2% 5% 1% 5%;
            background-color: white;
            height: 1000px;
        }


        .logo h1{
            text-align: center;
            margin-top: 1%;
        }
        bl {
            color: blue;
        }
        .active {
            font-weight: bold;
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

    <div class="jumbotron">
        <h1 class="display-4">Bem-vindo à <bl>Azul</bl>Airlines!</h1>
        <p class="lead">Com o novo sistema você pode gerenciar e acompanhar as viagens da Azul.</p>
        <hr class="my-4">
        <p>Para começar, escolha uma opção abaixo.</p>
        <div class="list-group">
            <a href="passageiros?opt=list" class="list-group-item list-group-item-action">Todos os Passageiros</a>
            <a href="viagens?opt=prox&prox=true" class="list-group-item list-group-item-action">Próximas Viagens</a>
            <a href="viagens?opt=prev" class="list-group-item list-group-item-action">Histórico de Viagens</a>
            <a href="aviao?opt=list" class="list-group-item list-group-item-action">Aviões cadastrados</a>
            <a href="oficina?opt=list" class="list-group-item list-group-item-action">Oficinas cadastradas</a>
            <a class="list-group-item list-group-item-action dropdown-toggle" href="#" role="button" data-toggle="dropdown">
                Nova...
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                <a class="dropdown-item" href="./viagens?opt=addViagem">Viagem</a>
                <a class="dropdown-item" href="./passageiros?opt=addPass">Passageiro</a>
                <a class="dropdown-item" href="./aviao?opt=addAviao">Avião</a>
                <a class="dropdown-item" href="./conserto?opt=addConserto">Solicitação de Conserto</a>
                <a class="dropdown-item" href="./oficina?opt=addOficina">Oficina</a>
            </div>
        </div>
    </div>




</div>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</body>

</html>
