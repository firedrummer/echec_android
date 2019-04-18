package com.example.echec_android.echec;


import com.example.echec_android.echec.Piece.Couleur;
import com.example.echec_android.echec.Piece.Type;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Classe constituant un échiquier
 *
 * @author William Blackburn
 * @author Yanick Bellavance
 */
public class Echiquier {

    private static final String SAUT_LIGNE = System.lineSeparator();

    /**
     * L'échiquier représenté sous forme de hashmap
     */
    private LinkedHashMap<String, Piece> m_echiquier = new LinkedHashMap<>();

    /**
     * Constructeur sans paramètres
     */
    public Echiquier() {
    }

    /**
     * Méthode qui ajoute une piece blanche à une coordonnée indiquée
     *
     * @param p_coordonnee position de placement pour le piece à ajouter
     * @param piece        pièce à ajouter dans l'échiquier
     */
    public void ajouterPiece(String p_coordonnee, Piece piece) {
        m_echiquier.put(p_coordonnee, piece);
    }

    /**
     * Vide l'échiquier
     */
    public void viderEchiquier() {
        m_echiquier.clear();
    }

    /**
     * @return le nombre de pieces présents sur l'échiquier
     */
    public int getNombrePieces() {
        return m_echiquier.size();
    }

    /**
     * Obtient le piece à la position en paramètre
     *
     * @param p_position position d'un piece dans l'échiquier
     * @return le piece a la position déclarée en paramètre
     */
    public Piece getPiece(String p_position) {
        return m_echiquier.get(p_position);
    }

    /**
     * Obtient la pièce à la position indiquée
     * <p>
     * ps on n'était pas certain si getPiece(String p_position) était suffisant
     * alors on a ajouter cette méthode
     *
     * @param p_position position de la pièce
     * @return le style de pièce
     */
    public String getPieceEnTexte(String p_position) {
        Piece piece = m_echiquier.get(p_position);

        return piece.getType().toString() + " " + (piece.estNoir() ? "NOIRE" : "BLANC");
    }

    /**
     * Initialise l'échiquier
     */
    public void initialiser() {
        // Rangée 1
        ajouterPiece("a1", new Tour(Couleur.BLANC));
        ajouterPiece("b1", new Cavalier(Couleur.BLANC));
        ajouterPiece("c1", new Fou(Couleur.BLANC));
        ajouterPiece("d1", new Reine(Couleur.BLANC));
        ajouterPiece("e1", new Roi(Couleur.BLANC));
        ajouterPiece("f1", new Fou(Couleur.BLANC));
        ajouterPiece("g1", new Cavalier(Couleur.BLANC));
        ajouterPiece("h1", new Tour(Couleur.BLANC));

        for (char i = 'a'; i < 'i'; i++) {
            // Rangée 2
            ajouterPiece(i + "2", new Pion(Couleur.BLANC));

            // Rangée 7
            ajouterPiece(i + "7", new Pion(Couleur.NOIR));
        }

        // Rangée 8
        ajouterPiece("a8", new Tour(Couleur.NOIR));
        ajouterPiece("b8", new Cavalier(Couleur.NOIR));
        ajouterPiece("c8", new Fou(Couleur.NOIR));
        ajouterPiece("d8", new Reine(Couleur.NOIR));
        ajouterPiece("e8", new Roi(Couleur.NOIR));
        ajouterPiece("f8", new Fou(Couleur.NOIR));
        ajouterPiece("g8", new Cavalier(Couleur.NOIR));
        ajouterPiece("h8", new Tour(Couleur.NOIR));
    }

    /**
     * Represente les pièces actuelles dans l'echiquier sous forme d'une string
     *
     * @return la representation graphique de l'echiquier
     */
    public String obtenirRepresentation() {
        StringBuilder representation = new StringBuilder();

        for (int j = 1; j < 9; j++) {
            for (char i = 'a'; i < 'i'; i++) {
                if (m_echiquier.containsKey("" + i + j)) {
                    representation.append(getPiece("" + i + j).obtenirRepresentation());
                } else {
                    representation.append("X");
                }
            }

            if (j < 8)
                representation.append(SAUT_LIGNE);
        }
        return representation.toString();
    }

    /**
     * Compte du pointage des pièces de la couleur indiquer en paramètre
     *
     * @param p_couleur couleur des pièces à savoir le pointage
     */
    public double compterPointsEchiquier(Couleur p_couleur) {
        double nombrePoint = 0;
        Collection<Piece> pieces = m_echiquier.values();

        for (Piece piece : pieces) {
            if (piece.getCouleur() == p_couleur)
                nombrePoint += piece.obtenirPointagePiece();
        }
        return nombrePoint;
    }

    /**
     * Obtient le nombre de pièces selon la couleur et le type spéicifié
     *
     * @param p_type    type de la pièce
     * @param p_couleur couleur de la pièce
     * @return le nombre de pièces selon le type / couleur spéficié
     */
    public int obtenirNombrePieceSelonCouleurEtType(Type p_type, Couleur p_couleur) {
        int nombrePieces = 0; // nombre de pieces du type et couleur spécifiés en paramètre
        Collection<Piece> pieces = m_echiquier.values();

        for (Piece piece : pieces) {
            if (piece.getCouleur() == p_couleur && piece.getType() == p_type)
                nombrePieces += 1;
        }
        return nombrePieces;
    }

    LinkedHashMap<String, Piece> getPiecesSelonCouleur(Couleur p_couleur) {
        LinkedHashMap<String, Piece> pieces = new LinkedHashMap<>();

        for (Map.Entry<String, Piece> position : m_echiquier.entrySet()) {
            if (position.getValue().getCouleur() == p_couleur)
                pieces.put(position.getKey(), position.getValue());
        }

        return pieces;
    }

    /**
     * Donne les possibilitées de tour possible à jouer pour une couleur
     *
     * @param p_couleur couleur des pieces à avoir les mouvements possibles
     * @return une LinkecHashMap des coordonnées de départ et de fin qui corresponde chacun à un mouvement possible
     */
    LinkedHashMap<String, String> getToursPossibleSelonCouleur(Couleur p_couleur) {
        LinkedHashMap<String, String> tourPossible = new LinkedHashMap<>();
        LinkedHashMap<String, Piece> pieces = getPiecesSelonCouleur(p_couleur);

        for (Map.Entry<String, Piece> position : pieces.entrySet()) {
            tourPossible.putAll(getToursPossibleSelonPiece(position.getValue(), position.getKey()));
        }

        return tourPossible;
    }

    /**
     * Donne les possibilitées de tour possible à jouer pour une pièce
     *
     * @param p_piece      pièce dont on veut savoir les mouvements possibles
     * @param p_coordonnee coordonnée de départ de la pièce
     * @return une LinkecHashMap des coordonnées de départ et de fin qui corresponde chacun à un mouvement possible
     */
    LinkedHashMap<String, String> getToursPossibleSelonPiece(Piece p_piece, String p_coordonnee) {
        LinkedHashMap<String, String> tourPossible = new LinkedHashMap<>();
        return tourPossible;
    }


    /**
     * Vérifie si un déplacement est valide en prenant en compte le reste de Échiquier
     *
     * @param p_piece          pièce à valider le déplacement
     * @param p_coordonneDebut de départ de la piece
     * @param p_coordonneFin   coordonnée de destination de la piece
     * @return si le déplacement est valide ou non
     */
    private boolean deplacementValide(Piece p_piece, String p_coordonneDebut, String p_coordonneFin) {
        Type type = p_piece.getType();

        switch (type) {
            case PION:
                return deplacementValidePion(p_piece);
            case ROI:
                return deplacementValideRoi(p_piece);
            case FOU:
                return deplacementValideFou(p_piece);
            case CAVALIER:
                return deplacementValideCavalier(p_piece);
            case DAME:
                return deplacementValideDame(p_piece);
            case TOUR:
                return deplacementValideTour(p_piece);
            default:
                throw new IllegalArgumentException("Le code ne devrait pas aller ici!!!");
        }
    }

    private boolean deplacementValideTour(Piece p_piece) {
        return true;
    }

    private boolean deplacementValideDame(Piece p_piece) {
        return true;
    }

    private boolean deplacementValideCavalier(Piece p_piece) {
        return true;
    }

    private boolean deplacementValideFou(Piece p_piece) {
        return true;
    }

    private boolean deplacementValideRoi(Piece p_piece) {
        return true;
    }

    private boolean deplacementValidePion(Piece p_piece) {
        return true;
    }

    public boolean deplacementPetitRoqueValide(Piece p_piece, String p_cDepart, String cFin) {
        Piece pieceCourante = getPiece(p_cDepart);

        return pieceCourante.getType() == Type.ROI && pieceCourante.getType() == Type.TOUR;
    }

    public boolean deplacementGrandRoqueValide() {
        return true;
    }

    /**
     * Tente de déplacer une piece si le déplacement n'est pas valide retourne false
     *
     * @param p_coordonneeDebut coordonnée de départ de la piece
     * @param p_coordonneFin    coordonnée de destination de la piece
     * @return true le déplacement réussit sinon false
     */
    public boolean deplacerPiece(String p_coordonneeDebut, String p_coordonneFin) {
        Piece piece = getPiece(p_coordonneeDebut);

        if (deplacementValide(piece, p_coordonneeDebut, p_coordonneFin)) {
            m_echiquier.remove(p_coordonneeDebut);
            m_echiquier.remove(p_coordonneFin);
            ajouterPiece(p_coordonneFin, piece);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Vérifie si tout les cases entre les deux déplacement, mais ne vérifie pas la derniere case
     *
     * @param p_coordonneeDebut coordonnée de départ de la piece
     * @param p_coordonneeFin   coordonnée de destination de la piece
     * @return true si les cases sont vides sinon false
     */
    public boolean casesVide(String p_coordonneeDebut, String p_coordonneeFin) {
        // Déplacement horizontal ou vertical
        if (p_coordonneeDebut.charAt(0) == p_coordonneeFin.charAt(0)) {
            if (p_coordonneeDebut.charAt(1) < p_coordonneeFin.charAt(1)) {
                for (char i = p_coordonneeDebut.charAt(1); i < p_coordonneeFin.charAt(1); i++) {
                    if (m_echiquier.containsKey("" + p_coordonneeDebut.charAt(0) + i))
                        return false;
                }
            } else {
                for (char i = p_coordonneeDebut.charAt(1); i > p_coordonneeFin.charAt(1); i--) {
                    if (m_echiquier.containsKey("" + p_coordonneeDebut.charAt(0) + i))
                        return false;
                }
            }
        } else if (p_coordonneeDebut.charAt(1) == p_coordonneeFin.charAt(1)) {
            if (p_coordonneeDebut.charAt(1) < p_coordonneeFin.charAt(1)) {
                for (char i = p_coordonneeDebut.charAt(0); i < p_coordonneeFin.charAt(0); i++) {
                    if (m_echiquier.containsKey("" + i + p_coordonneeDebut.charAt(1)))
                        return false;
                }
            } else {
                for (char i = p_coordonneeDebut.charAt(0); i > p_coordonneeFin.charAt(0); i--) {
                    if (m_echiquier.containsKey("" + i + p_coordonneeDebut.charAt(1)))
                        return false;
                }
            }
        }
        // Déplacement diagonal
        else {
            if (p_coordonneeDebut.charAt(0) < p_coordonneeFin.charAt(0) && p_coordonneeDebut.charAt(1) < p_coordonneeFin.charAt(1)) {
                for (char i = p_coordonneeDebut.charAt(0); i < p_coordonneeFin.charAt(0); i++) {
                    for (char j = p_coordonneeDebut.charAt(1); i < p_coordonneeFin.charAt(1); i++) {
                        if (m_echiquier.containsKey("" + i + j))
                            return false;
                    }
                }
            } else if (p_coordonneeDebut.charAt(0) > p_coordonneeFin.charAt(0) && p_coordonneeDebut.charAt(1) < p_coordonneeFin.charAt(1)) {
                for (char i = p_coordonneeDebut.charAt(0); i > p_coordonneeFin.charAt(0); i--) {
                    for (char j = p_coordonneeDebut.charAt(1); i < p_coordonneeFin.charAt(1); i++) {
                        if (m_echiquier.containsKey("" + i + j))
                            return false;
                    }
                }
            } else if (p_coordonneeDebut.charAt(0) < p_coordonneeFin.charAt(0) && p_coordonneeDebut.charAt(1) > p_coordonneeFin.charAt(1)) {
                for (char i = p_coordonneeDebut.charAt(0); i < p_coordonneeFin.charAt(0); i++) {
                    for (char j = p_coordonneeDebut.charAt(1); i > p_coordonneeFin.charAt(1); i--) {
                        if (m_echiquier.containsKey("" + i + j))
                            return false;
                    }
                }
            } else {
                for (char i = p_coordonneeDebut.charAt(0); i > p_coordonneeFin.charAt(0); i--) {
                    for (char j = p_coordonneeDebut.charAt(1); i > p_coordonneeFin.charAt(1); i--) {
                        if (m_echiquier.containsKey("" + i + j))
                            return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean estPromotionPossible(String p_coordonneeDebut, String p_coordonneeFin) {
        return (getPiece(p_coordonneeDebut).getCouleur() == Couleur.NOIR && p_coordonneeFin.charAt(1) == '1') ||
                (getPiece(p_coordonneeDebut).getCouleur() == Couleur.BLANC && p_coordonneeFin.charAt(1) == '8');
    }
}
