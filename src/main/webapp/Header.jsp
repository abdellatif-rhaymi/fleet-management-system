<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="entities.Vehicule" %>
<%@ page import="VehiculeDao.VehiculeDaoImpl" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="Responsive Admin &amp; Dashboard Template based on Bootstrap 5">
	<meta name="author" content="AdminKit">
	<meta name="keywords" content="adminkit, bootstrap, bootstrap 5, admin, dashboard, template, responsive, css, sass, html, theme, front-end, ui kit, web">

	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link rel="shortcut icon" href="img/icons/icon-48x48.png" />

	<title>Forms | AdminKit Demo</title>

	<link href="css/app.css" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&display=swap" rel="stylesheet">
</head>

	<div class="wrapper">

<nav id="sidebar" class="sidebar js-sidebar">
			<div class="sidebar-content js-simplebar">
				<a class="sidebar-brand" href="index.html">
          <span class="align-middle">AdminKit</span>
        </a>

				<ul class="sidebar-nav">

					<li class="sidebar-item">
						<a class="sidebar-link" href="index.html">
              <i class="align-middle" data-feather="sliders"></i> <span class="align-middle">Dashboard</span>
            </a>
					</li>
<li class="sidebar-item">
						<a class="sidebar-link" href="vehicule.do">
              <i class="align-middle" data-feather="square"></i> <span class="align-middle">Vehicule</span>
            </a>
					</li>
					<li class="sidebar-item">
						<a class="sidebar-link" href="demande.demande">
              <i class="align-middle" data-feather="square"></i> <span class="align-middle">Oders</span>
            </a>
					</li>
					<li class="sidebar-item">
						<a class="sidebar-link" href="ajouterVoyage.jsp">
              <i class="align-middle" data-feather="square"></i> <span class="align-middle">Voyage</span>
            </a>
					</li>
				
					<li class="sidebar-item">
						<a class="sidebar-link" href="ajouterUser.jsp">
              <i class="align-middle" data-feather="square"></i> <span class="align-middle">User</span>
            </a>
					</li>
				</ul>
			</div>
		</nav>
	
