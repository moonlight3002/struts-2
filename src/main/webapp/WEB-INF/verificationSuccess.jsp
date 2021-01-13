<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Verification Success</title>
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
            color: var(--primary-color);
        }
        h5,h2{
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
                        <div class="Success-icon col-12"><i class="fas fa-laugh"></i></div>
                        <div class="Success-text col-12"><h2>Thank you</h2></div>
                        <div class="Success-text col-12"><h5>Redirect to Login in 5 seconds..</h5></div>
                        <a class="col-12 redirectlink" href="http://localhost:8080/ID0420FF19OWidya/home/login">or Login now</a>
                    </div>

                </div>

            </div>

        </div>
    </div>

<script>
	setTimeout(function(){
	   window.location = 'http://localhost:8080/ID0420FF19OWidya/home/login';
	}, 5000);
</script>
</body>
</html>