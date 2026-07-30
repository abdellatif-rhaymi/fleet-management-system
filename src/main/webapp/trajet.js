/*// Fonction pour récupérer les coordonnées des demandes de voyage via l'API
function getTrajet(voyageId) {
    fetch(`/s3_new_2/trajetVoyage.do?voyageId=${voyageId}`, {
        method: 'POST'
    })
 .then(response => response.text()) // Utilise text() pour obtenir la réponse brute
.then(data => {
    console.log("Réponse brute reçue : ", data);  // Affiche la réponse brute dans la console
    try {
        var jsonData = JSON.parse(data);  // Tente de parser la réponse JSON
        // Continuer avec le traitement de la réponse
        console.log("Données JSON : ", jsonData);
    } catch (error) {
        console.error("Erreur de parsing JSON :", error);
    }
})
.catch(error => console.error('Error fetching trajet:', error));
}*/