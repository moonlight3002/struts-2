<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error</title>
<link rel="icon" type="image/png" href="/ID0420FF19OWidya/assets/icon.png">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
        <link rel = "stylesheet" href="css/navStyle.css">
    <style>
    	:root{
		    --font-color-default:#155263;
		    --primary-color: #29cdb5;
		}
        body{
            width: 100%;
            height: 100vh;
            overflow: hidden;
        }
        .container{
            height: 100vh;
            background-image:url(/ID0420FF19OWidya/assets/icon_grayscale.png);
            background-repeat: no-repeat;
            background-position: center;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .Success-icon{
            display: flex;
            align-items: center;
            justify-content: center;
            font-size:3rem;
            color: red;
        }
        h2{
            text-align: center;
            color: var(--font-color-default);
        }
        .redirectlink{
            display: flex;
            align-items: center;
            justify-content: center;
            font-weight: 600;
        }
    </style>
</head>
<body>
    <div class="row">
        <div class="container">
            <div class="row d-flex justify-content-center">
                <div class="col-12">
                    <div class="row d-flex justify-content-center">
                        <div class="Success-icon col-12"><i class="fas fa-robot"></i></div>
                        <div class="Success-text col-12"><h2>Opps.. something went wrong</h2></div>
                        <a class="col-12 redirectlink" href="http://localhost:8080/ID0420FF19OWidya/home">Back to Home</a>
                    </div>

                </div>

            </div>

        </div>
    </div>
</body>
</html>