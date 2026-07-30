import folium
import openrouteservice as ors
import sys
import os
import webbrowser

try:
    # Obtenir le fichier contenant les coordonnées
    coord_file = sys.argv[1]
    coords = []

    # Lire les coordonnées depuis le fichier
    with open(coord_file, 'r') as file:
        for line in file:
            lat, lon = map(float, line.strip().split(','))
            coords.append([lat, lon])

    if not coords:
        raise ValueError("Aucune coordonnée trouvée dans le fichier.")

    # Configuration du client ORS
    client = ors.Client(key='YOUR_ORS_API_KEY')

    # Définition du point de départ (première coordonnée)
    vehicle_start = coords[0]

    # Création de la carte avec Folium
    m = folium.Map(location=list(reversed(vehicle_start)), tiles="cartodbpositron", zoom_start=14)

    # Ajout des marqueurs sur la carte
    for coord in coords:
        folium.Marker(location=list(reversed(coord))).add_to(m)

    # Marqueur pour la position du véhicule
    folium.Marker(
        location=list(reversed(vehicle_start)), 
        icon=folium.Icon(color="red")
    ).add_to(m)

    # Définition des véhicules
    vehicles = [
        ors.optimization.Vehicle(
            id=0, 
            profile='driving-car', 
            start=vehicle_start, 
            end=vehicle_start, 
            capacity=[5]
        )
    ]

    # Définition des jobs
    jobs = [
        ors.optimization.Job(
            id=index, 
            location=coord, 
            amount=[1]
        ) for index, coord in enumerate(coords)
    ]

    # Optimisation de la route avec ORS
    optimized = client.optimization(jobs=jobs, vehicles=vehicles, geometry=True)

    # Ajouter les lignes de parcours sur la carte
    line_colors = ['green', 'orange', 'blue', 'yellow']
    for route in optimized['routes']:
        coordinates = ors.convert.decode_polyline(route['geometry'])['coordinates']
        folium.PolyLine(
            locations=[list(reversed(coords)) for coords in coordinates],
            color=line_colors[route['vehicle']]
        ).add_to(m)

    # Sauvegarder et ouvrir la carte
    html_path = os.path.join(os.path.dirname(os.path.abspath(__file__)), 'mapnew.html')
    m.save(html_path)
    webbrowser.open('file://' + os.path.realpath(html_path))

except Exception as e:
    print(f"Une erreur s'est produite : {str(e)}")
    sys.exit(1)
