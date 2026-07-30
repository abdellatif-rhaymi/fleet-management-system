<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Suivi du Chauffeur</title>
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css" />
    <script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"></script>
    <style>
        #map {
            height: 500px;
            width: 100%;
        }
    </style>
</head>
<body>
    <h1>Suivi en temps réel</h1>
    <div id="map"></div>
    <script>
        let map = L.map('map').setView([33.589886, -7.603869], 13);

        // Ajouter une couche de tuiles OpenStreetMap
        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            attribution: '&copy; OpenStreetMap contributors'
        }).addTo(map);

        let marker;

        // Récupérer la position actuelle du chauffeur depuis le serveur
        function fetchDriverLocation() {
            fetch('http://localhost:8082/s3_new_2/trajet.locate')
                .then(response => response.json())
                .then(data => {
                    const { latitude, longitude } = data;

                    if (!marker) {
                        marker = L.marker([latitude, longitude]).addTo(map);
                        map.setView([latitude, longitude], 15);
                    } else {
                        marker.setLatLng([latitude, longitude]);
                    }
                })
                .catch(error => console.error("Erreur de suivi :", error));
        }

        // Actualiser la position toutes les 5 secondes
        setInterval(fetchDriverLocation, 5000);
    </script>
</body>
</html>
