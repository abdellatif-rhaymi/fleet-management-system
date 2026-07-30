<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); %>
<% response.setHeader("Pragma", "no-cache"); %>
<% response.setDateHeader("Expires", 0); %>

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
	<!-- Leaflet CSS -->
<link rel="stylesheet" href="https://unpkg.com/leaflet@1.7.1/dist/leaflet.css">
    <style>
        #map {
            height: 400px;
            width: 100%;
        }
        .info-box {
            margin: 20px;
            padding: 10px;
            border: 1px solid #ddd;
            background: #f9f9f9;
        }
    </style>
<!-- Leaflet JavaScript -->
	
	<link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&display=swap" rel="stylesheet">
</head>

<body>
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

		<div class="main">
			<nav class="navbar navbar-expand navbar-light navbar-bg">
				<a class="sidebar-toggle js-sidebar-toggle">
          <i class="hamburger align-self-center"></i>
        </a>

				<div class="navbar-collapse collapse">
					<ul class="navbar-nav navbar-align">
						<li class="nav-item dropdown">
							<a class="nav-icon dropdown-toggle" href="#" id="alertsDropdown" data-bs-toggle="dropdown">
								<div class="position-relative">
									<i class="align-middle" data-feather="bell"></i>
									<span class="indicator">4</span>
								</div>
							</a>
							<div class="dropdown-menu dropdown-menu-lg dropdown-menu-end py-0" aria-labelledby="alertsDropdown">
								<div class="dropdown-menu-header">
									4 New Notifications
								</div>
								<div class="list-group">
									<a href="#" class="list-group-item">
										<div class="row g-0 align-items-center">
											<div class="col-2">
												<i class="text-danger" data-feather="alert-circle"></i>
											</div>
											<div class="col-10">
												<div class="text-dark">Update completed</div>
												<div class="text-muted small mt-1">Restart server 12 to complete the update.</div>
												<div class="text-muted small mt-1">30m ago</div>
											</div>
										</div>
									</a>
									<a href="#" class="list-group-item">
										<div class="row g-0 align-items-center">
											<div class="col-2">
												<i class="text-warning" data-feather="bell"></i>
											</div>
											<div class="col-10">
												<div class="text-dark">Lorem ipsum</div>
												<div class="text-muted small mt-1">Aliquam ex eros, imperdiet vulputate hendrerit et.</div>
												<div class="text-muted small mt-1">2h ago</div>
											</div>
										</div>
									</a>
									<a href="#" class="list-group-item">
										<div class="row g-0 align-items-center">
											<div class="col-2">
												<i class="text-primary" data-feather="home"></i>
											</div>
											<div class="col-10">
												<div class="text-dark">Login from 192.186.1.8</div>
												<div class="text-muted small mt-1">5h ago</div>
											</div>
										</div>
									</a>
									<a href="#" class="list-group-item">
										<div class="row g-0 align-items-center">
											<div class="col-2">
												<i class="text-success" data-feather="user-plus"></i>
											</div>
											<div class="col-10">
												<div class="text-dark">New connection</div>
												<div class="text-muted small mt-1">Christina accepted your request.</div>
												<div class="text-muted small mt-1">14h ago</div>
											</div>
										</div>
									</a>
								</div>
								<div class="dropdown-menu-footer">
									<a href="#" class="text-muted">Show all notifications</a>
								</div>
							</div>
						</li>
						<li class="nav-item dropdown">
							<a class="nav-icon dropdown-toggle" href="#" id="messagesDropdown" data-bs-toggle="dropdown">
								<div class="position-relative">
									<i class="align-middle" data-feather="message-square"></i>
								</div>
							</a>
							<div class="dropdown-menu dropdown-menu-lg dropdown-menu-end py-0" aria-labelledby="messagesDropdown">
								<div class="dropdown-menu-header">
									<div class="position-relative">
										4 New Messages
									</div>
								</div>
								<div class="list-group">
									<a href="#" class="list-group-item">
										<div class="row g-0 align-items-center">
											<div class="col-2">
												<img src="img/avatars/avatar-5.jpg" class="avatar img-fluid rounded-circle" alt="Vanessa Tucker">
											</div>
											<div class="col-10 ps-2">
												<div class="text-dark">Vanessa Tucker</div>
												<div class="text-muted small mt-1">Nam pretium turpis et arcu. Duis arcu tortor.</div>
												<div class="text-muted small mt-1">15m ago</div>
											</div>
										</div>
									</a>
									<a href="#" class="list-group-item">
										<div class="row g-0 align-items-center">
											<div class="col-2">
												<img src="img/avatars/avatar-2.jpg" class="avatar img-fluid rounded-circle" alt="William Harris">
											</div>
											<div class="col-10 ps-2">
												<div class="text-dark">William Harris</div>
												<div class="text-muted small mt-1">Curabitur ligula sapien euismod vitae.</div>
												<div class="text-muted small mt-1">2h ago</div>
											</div>
										</div>
									</a>
									<a href="#" class="list-group-item">
										<div class="row g-0 align-items-center">
											<div class="col-2">
												<img src="img/avatars/avatar-4.jpg" class="avatar img-fluid rounded-circle" alt="Christina Mason">
											</div>
											<div class="col-10 ps-2">
												<div class="text-dark">Christina Mason</div>
												<div class="text-muted small mt-1">Pellentesque auctor neque nec urna.</div>
												<div class="text-muted small mt-1">4h ago</div>
											</div>
										</div>
									</a>
									<a href="#" class="list-group-item">
										<div class="row g-0 align-items-center">
											<div class="col-2">
												<img src="img/avatars/avatar-3.jpg" class="avatar img-fluid rounded-circle" alt="Sharon Lessman">
											</div>
											<div class="col-10 ps-2">
												<div class="text-dark">Sharon Lessman</div>
												<div class="text-muted small mt-1">Aenean tellus metus, bibendum sed, posuere ac, mattis non.</div>
												<div class="text-muted small mt-1">5h ago</div>
											</div>
										</div>
									</a>
								</div>
								<div class="dropdown-menu-footer">
									<a href="#" class="text-muted">Show all messages</a>
								</div>
							</div>
						</li>
						<li class="nav-item dropdown">
							<a class="nav-icon dropdown-toggle d-inline-block d-sm-none" href="#" data-bs-toggle="dropdown">
                <i class="align-middle" data-feather="settings"></i>
              </a>

							<a class="nav-link dropdown-toggle d-none d-sm-inline-block" href="#" data-bs-toggle="dropdown">
                <img src="img/Rhaymi_Abdellatif.jpg" class="avatar img-fluid rounded me-1" alt="Charles Hall" /> <span class="text-dark">Rhaymi Abdellatif</span>
              </a>
							<div class="dropdown-menu dropdown-menu-end">
								<a class="dropdown-item" href="#"><i class="align-middle me-1" data-feather="user"></i> Profile</a>
								<a class="dropdown-item" href="#"><i class="align-middle me-1" data-feather="pie-chart"></i> Analytics</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="index.html"><i class="align-middle me-1" data-feather="settings"></i> Settings & Privacy</a>
								<a class="dropdown-item" href="#"><i class="align-middle me-1" data-feather="help-circle"></i> Help Center</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="#">Log out</a>
							</div>
						</li>
					</ul>
				</div>
			</nav>

			<main class="content">
				<div class="container-fluid p-0">

					<div class="card">
<div class="card">
    <div class="card-header">
        <h5 class="card-title mb-0">Nouvelle Demande</h5>
    </div>
    <div class="card-body">
      <form action="save.demande" method="post">
    <div class="row g-3">
        <div class="col-md-6">
            <label for="numero_demande" class="form-label">Numro de Demande</label>
            <input type="text" class="form-control" name="numero_demande" id="numero_demande" required>
        </div>
        <div class="col-md-6">
            <label for="date_demande" class="form-label">Date de Demande</label>
            <input type="date" class="form-control" name="date_demande" id="date_demande" required>
        </div>
        <div class="col-12">
            <label for="adresse_livraison" class="form-label">Adresse de Livraison</label>
            <input type="text" class="form-control" name="adresse_livraison" id="adresse_livraison" required>
        </div>
        <div class="col-md-4">
            <label for="ville" class="form-label">Ville</label>
            <input type="text" class="form-control" name="ville" id="ville">
        </div>
        <div class="col-md-4">
            <label for="code_postal" class="form-label">Code Postal</label>
            <input type="text" class="form-control" name="code_postal" id="code_postal">
        </div>
        <div class="col-md-4">
            <label for="pays" class="form-label">Pays</label>
            <input type="text" class="form-control" name="pays" id="pays">
        </div>
        <div class="col-md-6">
            <label for="date_livraison" class="form-label">Date de Livraison</label>
            <input type="date" class="form-control" name="date_livraison" id="date_livraison">
        </div>
        <div class="col-md-6">
            <label for="statut" class="form-label">Statut</label>
            <select name="statut" id="statut" class="form-select" required>
                <option value="en attente">En attente</option>
                <option value="valide">Valide</option>
                <option value="annule">Annule</option>
                <option value="complt">Complt</option>
            </select>
        </div>
        <div class="col-12">
            <label for="commentaire" class="form-label">Commentaire</label>
            <textarea name="commentaire" id="commentaire" class="form-control" rows="3"></textarea>
        </div>
        <div class="col-md-6">
    <label for="latitude" class="form-label">Latitude</label>
    <input type="text" class="form-control" name="latitude" id="latitude" readonly>
</div>
<div class="col-md-6">
    <label for="longitude" class="form-label">Longitude</label>
    <input type="text" class="form-control" name="longitude" id="longitude" readonly>
</div>

        <div class="col-md-6">
            <label for="weight" class="form-label">Poids (kg)</label>
            <input type="number" class="form-control" name="weight" id="weight">
        </div>
        
        <div class="card-body px-4">
									<div id="world_map" style="height:350px;"></div>
								</div>
            <div id="map"></div>
        
    </div>
    
    <div class="d-flex justify-content-end mt-4">
        <button type="reset" class="btn btn-secondary me-2">Annuler</button>
        <button type="submit" class="btn btn-primary">Ajouter</button>
    </div>
</form>

    </div>
</div>

</div>
					
			<footer class="footer">
				<div class="container-fluid">
					<div class="row text-muted">
						<div class="col-6 text-start">
							<p class="mb-0">
								<a class="text-muted" href="https://adminkit.io/" target="_blank"><strong>AdminKit</strong></a> - <a class="text-muted" href="https://adminkit.io/" target="_blank"><strong>Bootstrap Admin Template</strong></a>								&copy;
							</p>
						</div>
						<div class="col-6 text-end">
							<ul class="list-inline">
								<li class="list-inline-item">
									<a class="text-muted" href="https://adminkit.io/" target="_blank">Support</a>
								</li>
								<li class="list-inline-item">
									<a class="text-muted" href="https://adminkit.io/" target="_blank">Help Center</a>
								</li>
								<li class="list-inline-item">
									<a class="text-muted" href="https://adminkit.io/" target="_blank">Privacy</a>
								</li>
								<li class="list-inline-item">
									<a class="text-muted" href="https://adminkit.io/" target="_blank">Terms</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</footer>
		</div>
	</div>

	<script src="js/app.js"></script>
	<script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js" integrity="sha256-QVnS/bDXRi3KZX9QxMdK9/vkTMfnUwiMJuZQ/s8WFeM=" crossorigin=""></script>
	
	<script>
    // Initialisation de la carte
    var map = L.map('map').setView([34.020882, -6.841650], 13);

    // Chargement des tuiles OpenStreetMap
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        maxZoom: 19,
        attribution: ' OpenStreetMap contributors'
    }).addTo(map);

    // Ajouter un marqueur initialement vide
    var marker;

    // Fonction pour mettre  jour les coordonnes sur clic
    function onMapClick(e) {
        var lat = e.latlng.lat;
        var lng = e.latlng.lng;

        // Mettre  jour les champs latitude et longitude
        document.getElementById('latitude').value = lat;
        document.getElementById('longitude').value = lng;

        // Ajouter ou dplacer un marqueur
        if (marker) {
            marker.setLatLng(e.latlng);
        } else {
            marker = L.marker(e.latlng).addTo(map);
        }
    }

    // vnement de clic sur la carte
    map.on('click', onMapClick);
</script>
<script src="https://unpkg.com/leaflet@1.7.1/dist/leaflet.js"></script>
    <script>
        const map = L.map('map').setView([34.020882, -6.841650], 13); // Rabat, Maroc

        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            maxZoom: 19,
            attribution: ' OpenStreetMap contributors'
        }).addTo(map);

        const apiKey = "YOUR_ORS_API_KEY"; // Remplacez par votre cl API OpenRouteService

        let startMarker, endMarker, routeLayer;
        let startCoords = null, endCoords = null;

        map.on('click', async function (e) {
            const lat = e.latlng.lat.toFixed(6);
            const lng = e.latlng.lng.toFixed(6);

            if (!startCoords) {
                startCoords = [lat, lng];
                startMarker = L.marker(e.latlng, { title: "Point de dpart" }).addTo(map);
                document.getElementById('start-coords').textContent = `${lat}, ${lng}`;
                document.getElementById('start-address').textContent = "Recherche...";
                const address = await getAddressFromCoords(lat, lng);
                document.getElementById('start-address').textContent = address || "Adresse non trouve";
            } else if (!endCoords) {
                endCoords = [lat, lng];
                endMarker = L.marker(e.latlng, { title: "Point de destination" }).addTo(map);
                document.getElementById('end-coords').textContent = `${lat}, ${lng}`;
                document.getElementById('end-address').textContent = "Recherche...";
                const address = await getAddressFromCoords(lat, lng);
                document.getElementById('end-address').textContent = address || "Adresse non trouve";

                // Dessiner le trajet
                if (startCoords && endCoords) {
                    drawRoute(startCoords, endCoords);
                }
            }
        });

        async function getAddressFromCoords(lat, lng) {
            const url = `https://api.openrouteservice.org/geocode/reverse?api_key=${apiKey}&point.lat=${lat}&point.lon=${lng}&size=1`;
            try {
                const response = await fetch(url);
                const data = await response.json();
                if (data.features && data.features.length > 0) {
                    return data.features[0].properties.label;
                }
                return null;
            } catch (error) {
                console.error("Erreur lors de la rcupration de l'adresse :", error);
                return null;
            }
        }

        async function drawRoute(start, end) {
            const url = `https://api.openrouteservice.org/v2/directions/driving-car?api_key=${apiKey}&start=${start[1]},${start[0]}&end=${end[1]},${end[0]}`;
            try {
                const response = await fetch(url);
                const data = await response.json();

                // Ajouter le trajet sur la carte
                const coordinates = data.features[0].geometry.coordinates;
                const latlngs = coordinates.map(coord => [coord[1], coord[0]]);
                if (routeLayer) {
                    map.removeLayer(routeLayer);
                }
                routeLayer = L.polyline(latlngs, { color: 'blue' }).addTo(map);
                map.fitBounds(routeLayer.getBounds());

                // Extraire et afficher les informations sur le trajet
                const distance = (data.features[0].properties.segments[0].distance / 1000).toFixed(2); // En km
                const duration = (data.features[0].properties.segments[0].duration / 60).toFixed(0); // En minutes

                document.getElementById('distance').textContent = distance;
                document.getElementById('duration').textContent = `${duration} minutes`;
                document.getElementById('route-info').style.display = 'block';

                // **Stocker les informations (exemple, vous pouvez adapter pour envoyer au backend)**
                console.log("Trajet enregistr :", {
                    start,
                    end,
                    distance,
                    duration
                });

            } catch (error) {
                console.error("Erreur lors de la rcupration du trajet :", error);
            }
        }
    </script>
    <script>
  // Initialisation de la carte
  var map = L.map('world_map').setView([31.7917, -7.0926], 6); // Coordonnes du Maroc et zoom

  // Ajouter les tuiles OpenStreetMap
  L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
  }).addTo(map);

  // Ajouter un gestionnaire d'vnements de clic
  map.on('click', function(e) {
    var lat = e.latlng.lat;  // Rcupre la latitude
    var lng = e.latlng.lng;  // Rcupre la longitude

    // Placer un marqueur  l'endroit o l'utilisateur a cliqu
    L.marker([lat, lng]).addTo(map)
      .bindPopup('<b>Adresse slectionne</b><br>Latitude: ' + lat + '<br>Longitude: ' + lng)
      .openPopup();

    // Mettre  jour les champs latitude et longitude dans le formulaire
    document.getElementById("latitude").value = lat;
    document.getElementById("longitude").value = lng;
  });
</script>

	

	

</body>

</html>