import folium
import openrouteservice as ors
import sys
import os
import webbrowser
import json

def generate_route_map(coords_file):
    """
    Générer une carte avec optimisation de route basée sur les coordonnées d'un fichier
    
    :param coords_file: Chemin vers le fichier contenant les coordonnées
    """
    try:
        # Lire les coordonnées depuis le fichier
        with open(coords_file, 'r') as f:
            coords = json.load(f)

        # Utiliser le premier point comme point de départ
        vehicle_start = [-6.864235174752737,33.98251650694769]

        # Configuration du client ORS
        client = ors.Client(key='YOUR_ORS_API_KEY')

        # Création de la carte avec Folium
        m = folium.Map(location=list(reversed(vehicle_start)), 
                       tiles="cartodbpositron", 
                       zoom_start=14)

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
                capacity=[10]
            ),
            #===================================================================
            # ors.optimization.Vehicle(
            #     id=1, 
            #     profile='driving-car', 
            #     start=vehicle_start, 
            #     end=vehicle_start, 
            #     capacity=[5]
            # )
            #===================================================================
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
        optimized = client.optimization(
            jobs=jobs, 
            vehicles=vehicles, 
            geometry=True
        )

        # Ajouter les lignes de parcours sur la carte
        line_colors = ['green', 'orange', 'blue', 'yellow']
        for route in optimized['routes']:
            coordinates = ors.convert.decode_polyline(route['geometry'])['coordinates']
            folium.PolyLine(
                locations=[list(reversed(coords)) for coords in coordinates],
                color=line_colors[route['vehicle']]
            ).add_to(m)

        # Sauvegarder la carte
        webapp_dir = os.path.dirname(os.path.abspath(__file__))
        html_path = os.path.join(webapp_dir, 'mapof.jsp')
        m.save(html_path)
        print(f"Carte générée avec succès dans : {html_path}")
        
        # Ouvrir automatiquement dans le navigateur
        # webbrowser.open('file://' + os.path.realpath(html_path))

    except Exception as e:
        print(f"Une erreur s'est produite : {str(e)}")
        sys.exit(1)

# Traitement des arguments de ligne de commande
if __name__ == "__main__":
    # Vérifier qu'un fichier est passé en argument
    if len(sys.argv) > 1:
        generate_route_map(sys.argv[1])
    else:
        print("Aucun fichier de coordonnées fourni")
        sys.exit(1)