package com.example.echec_android.echec;


import com.example.echec_android.echec.Piece.Couleur;
import com.example.echec_android.echec.Piece.Type;

import java.util.ArrayList;
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

        return (piece != null ? piece.getType().toString() : null) + " " +
                ((piece != null && piece.estNoir()) ? "NOIRE" : "BLANC");
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

    private LinkedHashMap<String, Piece> getPiecesSelonCouleur(Couleur p_couleur) {
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
    public LinkedHashMap<String, String> getToursPossibleSelonCouleur(Couleur p_couleur) {
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
    private LinkedHashMap<String, String> getToursPossibleSelonPiece(Piece p_piece, String p_coordonnee) {
        LinkedHashMap<String, String> tourPossible = new LinkedHashMap<>();
        Type type = p_piece.getType();

        ArrayList<String> mouvementsBasique = p_piece.deplacementPossibleSelonCoordoordee(p_coordonnee);

        if (type == Type.ROI) {
            for (String coordonne : mouvementsBasique) {
                if (casesVide(p_coordonnee, coordonne)) {
                    tourPossible.put(p_coordonnee, coordonne);
                }
            }

            if (p_coordonnee.equals("e1") || p_coordonnee.equals("e8")) {
                if (deplacementGrandRoqueValide(p_piece, p_coordonnee, "a" + p_coordonnee.charAt(1))) {
                    tourPossible.put(p_coordonnee, "a" + p_coordonnee.charAt(1));
                }

                if (deplacementPetitRoqueValide(p_piece, p_coordonnee, "h" + p_coordonnee.charAt(1))) {
                    tourPossible.put(p_coordonnee, "h" + p_coordonnee.charAt(1));
                }
            }

            return tourPossible;
        } else if (type == Type.TOUR) {
            for (String coordonne : mouvementsBasique) {
                if (casesVide(p_coordonnee, coordonne)) {
                    tourPossible.put(p_coordonnee, coordonne);
                }
            }

            if (p_coordonnee.equals("h1") || p_coordonnee.equals("h8")) {
                if (deplacementPetitRoqueValide(p_piece, p_coordonnee, "e" + p_coordonnee.charAt(1))) {
                    tourPossible.put(p_coordonnee, "e" + p_coordonnee.charAt(1));
                }
            } else if (p_coordonnee.equals("a1") || p_coordonnee.equals("a8")) {
                if (deplacementGrandRoqueValide(p_piece, p_coordonnee, "e" + p_coordonnee.charAt(1))) {
                    tourPossible.put(p_coordonnee, "e" + p_coordonnee.charAt(1));
                }
            }

            return tourPossible;
        } else if (type == Type.PION) {
            for (String coordonne : mouvementsBasique) {
                if (casesVide(p_coordonnee, coordonne)) {
                    tourPossible.put(p_coordonnee, coordonne);
                }
            }

            tourPossible.putAll(deplacementSpeciauxPion(p_piece, p_coordonnee));

            return tourPossible;
        } else if (type == Type.CAVALIER) {
            for (String coordonne : mouvementsBasique) {
                tourPossible.put(p_coordonnee, coordonne);
            }
            return tourPossible;
        } else {
            for (String coordonne : mouvementsBasique) {
                if (casesVide(p_coordonnee, coordonne)) {
                    tourPossible.put(p_coordonnee, coordonne);
                }
            }

            return tourPossible;
        }
    }

    private LinkedHashMap<String, String> deplacementSpeciauxPion(Piece p_piece, String p_coordonnee) {
        LinkedHashMap<String, String> tourPion = new LinkedHashMap<>();

        ArrayList<String> deplacementSpeciaux = new ArrayList<>();
        deplacementSpeciaux.add("" + (char) (p_coordonnee.charAt(0) + 1) + (char) (p_coordonnee.charAt(1) + 1));
        deplacementSpeciaux.add("" + (char) (p_coordonnee.charAt(0) + 1) + (char) (p_coordonnee.charAt(1) - 1));
        deplacementSpeciaux.add("" + (char) (p_coordonnee.charAt(0) - 1) + (char) (p_coordonnee.charAt(1) + 1));
        deplacementSpeciaux.add("" + (char) (p_coordonnee.charAt(0) - 1) + (char) (p_coordonnee.charAt(1) - 1));

        for (String coordonnee : deplacementSpeciaux) {
            if (deplacementValidePion(p_piece, p_coordonnee, coordonnee)) {
                tourPion.put(p_coordonnee, coordonnee);
            }
        }

        return tourPion;
    }

    /**
     * Fait la promotion d'un pion
     *
     * @param p_coordonnee pion à promouvoir
     * @param p_type       vers le type de pièce la promotion à lieu
     */
    public void promotion(String p_coordonnee, Type p_type) {
        Piece piece = getPiece(p_coordonnee);
        Piece nouvellePiece;

        switch (p_type) {
            case ROI:
                nouvellePiece = new Roi(piece.getCouleur());
                nouvellePiece.deplacer();
                break;
            case FOU:
                nouvellePiece = new Roi(piece.getCouleur());
                nouvellePiece.deplacer();
                break;
            case CAVALIER:
                nouvellePiece = new Roi(piece.getCouleur());
                nouvellePiece.deplacer();
                break;
            case DAME:
                nouvellePiece = new Roi(piece.getCouleur());
                nouvellePiece.deplacer();
                break;
            case TOUR:
                nouvellePiece = new Roi(piece.getCouleur());
                nouvellePiece.deplacer();
                break;
            default:
                throw new IllegalArgumentException("Le code ne devrait pas aller ici!!!");
        }

        m_echiquier.remove(p_coordonnee);
        m_echiquier.put(p_coordonnee, nouvellePiece);
    }

    /**
     * Vérifie si un déplacement est valide en prenant en compte le reste de Échiquier
     *
     * @param p_piece          pièce à valider le déplacement
     * @param p_coordonneDebut de départ de la piece
     * @param p_coordonneeFin  coordonnée de destination de la piece
     * @return si le déplacement est valide ou non
     */
    private byte deplacementValide(Piece p_piece, String p_coordonneDebut, String p_coordonneeFin) {
        Type type = p_piece.getType();

        switch (type) {
            case PION:
                return (byte) (deplacementValidePion(p_piece, p_coordonneDebut, p_coordonneeFin) ? 1 : 0);
            case ROI:
                return deplacementValideRoi(p_piece, p_coordonneDebut, p_coordonneeFin);
            case FOU:
                return (byte) (deplacementValideFou(p_piece, p_coordonneDebut, p_coordonneeFin) ? 1 : 0);
            case CAVALIER:
                return (byte) (deplacementValideCavalier(p_piece, p_coordonneDebut, p_coordonneeFin) ? 1 : 0);
            case DAME:
                return (byte) (deplacementValideDame(p_piece, p_coordonneDebut, p_coordonneeFin) ? 1 : 0);
            case TOUR:
                return deplacementValideTour(p_piece, p_coordonneDebut, p_coordonneeFin);
            default:
                throw new IllegalArgumentException("Le code ne devrait pas aller ici!!!");
        }
    }

    private byte deplacementValideTour(Piece p_piece, String p_coordonneDebut, String p_coordonneeFin) {
        if (casesVide(p_coordonneDebut, p_coordonneeFin)) {
            if (deplacementGrandRoqueValide(p_piece, p_coordonneDebut, p_coordonneeFin)
                    && deplacementPetitRoqueValide(p_piece, p_coordonneDebut, p_coordonneeFin)) {
                return 2;
            } else {
                return (byte) (p_piece.estDeplacementValide(p_coordonneDebut, p_coordonneeFin) ? 1 : 0);
            }
        } else {
            return 0;
        }
    }

    private boolean deplacementValideDame(Piece p_piece, String p_coordonneDebut, String p_coordonneeFin) {
        return casesVide(p_coordonneDebut, p_coordonneeFin) &&
                p_piece.estDeplacementValide(p_coordonneDebut, p_coordonneeFin) &&
                mangerValidation(p_coordonneDebut, p_coordonneeFin) != 'n';
    }

    private boolean deplacementValideCavalier(Piece p_piece, String p_coordonneDebut, String p_coordonneeFin) {
        return p_piece.estDeplacementValide(p_coordonneDebut, p_coordonneeFin);
    }

    private boolean deplacementValideFou(Piece p_piece, String p_coordonneDebut, String p_coordonneeFin) {
        return casesVide(p_coordonneDebut, p_coordonneeFin) &&
                p_piece.estDeplacementValide(p_coordonneDebut, p_coordonneeFin) &&
                mangerValidation(p_coordonneDebut, p_coordonneeFin) != 'n';
    }

    private byte deplacementValideRoi(Piece p_piece, String p_coordonneDebut, String p_coordonneeFin) {
        if (casesVide(p_coordonneDebut, p_coordonneeFin)) {
            if (deplacementGrandRoqueValide(p_piece, p_coordonneDebut, p_coordonneeFin)
                    && deplacementPetitRoqueValide(p_piece, p_coordonneDebut, p_coordonneeFin)) {
                return 2;
            } else {
                return (byte) (p_piece.estDeplacementValide(p_coordonneDebut, p_coordonneeFin) ? 1 : 0);
            }
        } else {
            return 0;
        }
    }

    /**
     * Valide si une case est vide ou si la piece peut à cette position peu l'être
     *
     * @param p_coordonneeDebut coordonnée de départ
     * @param p_coordonneeFin   coordonnée de fin
     * @return v si elle est vide, m si la pièce est mangeable et n dans le cas contraire
     */
    private char mangerValidation(String p_coordonneeDebut, String p_coordonneeFin) {
        Piece pieceFin = getPiece(p_coordonneeFin);
        Piece pieceDebut = getPiece(p_coordonneeDebut);

        if (pieceFin == null) {
            return 'v';
        } else if (pieceDebut.getCouleur() != pieceFin.getCouleur()) {
            return 'n';
        } else {
            return 'm';
        }
    }

    private boolean deplacementValidePion(Piece p_piece, String p_coordonneDebut, String p_coordonneeFin) {
        char manger = mangerValidation(p_coordonneDebut, p_coordonneeFin);

        if (manger == 'v') {
            if (true) {
                // TODO prise en passant
            } else {
                return p_piece.estDeplacementValide(p_coordonneDebut, p_coordonneeFin);
            }
            return false; // à enlever quand le todo sera fait
        } else if (manger == 'n') {
            return false;
        } else {
            return p_piece.estDeplacementValide(p_coordonneDebut, p_coordonneeFin);
        }
    }

    private boolean deplacementPetitRoqueValide(Piece p_piece, String p_coordonneePiece1, String p_coordonneePiece2) {
        Piece piece1 = getPiece(p_coordonneePiece1);
        Piece piece2 = getPiece(p_coordonneePiece2);

        if (piece1.estDeplacer() || piece2.estDeplacer()) {
            return false;
        }

        if (Math.abs(p_coordonneePiece1.charAt(0) - p_coordonneePiece2.charAt(0)) == 2 &&
                casesVide(p_coordonneePiece1, p_coordonneePiece2)) {
            if (piece1.getCouleur() == piece2.getCouleur()) {
                return (piece1.getType() == Type.ROI && piece2.getType() == Type.TOUR) ||
                        (piece2.getType() == Type.ROI && piece1.getType() == Type.TOUR);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean deplacementGrandRoqueValide(Piece p_piece, String p_coordonneePiece1, String p_coordonneePiece2) {
        Piece piece1 = getPiece(p_coordonneePiece1);
        Piece piece2 = getPiece(p_coordonneePiece2);

        if (piece1.estDeplacer() || piece2.estDeplacer()) {
            return false;
        }

        if (Math.abs(p_coordonneePiece1.charAt(0) - p_coordonneePiece2.charAt(0)) == 3 &&
                casesVide(p_coordonneePiece1, p_coordonneePiece2)) {
            if (piece1.getCouleur() == piece2.getCouleur()) {
                return (piece1.getType() == Type.ROI && piece2.getType() == Type.TOUR) ||
                        (piece2.getType() == Type.ROI && piece1.getType() == Type.TOUR);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    // TODO ajouter la notion d'échec
    // TODO ajou

    /**
     * Tente de déplacer une piece si le déplacement n'est pas valide retourne false
     *
     * @param p_coordonneeDebut coordonnée de départ de la piece
     * @param p_coordonneeFin   coordonnée de destination de la piece
     * @return true le déplacement réussit sinon false
     */
    public boolean deplacerPiece(String p_coordonneeDebut, String p_coordonneeFin) {
        Piece piece = getPiece(p_coordonneeDebut);

        byte valide = deplacementValide(piece, p_coordonneeDebut, p_coordonneeFin);

        if (valide == 1) {
            m_echiquier.remove(p_coordonneeDebut);

            if (getPiece(p_coordonneeFin) != null) {
                m_echiquier.remove(p_coordonneeFin);
            }

            piece.deplacer();

            ajouterPiece(p_coordonneeFin, piece);

            return true;
        } else if (valide == 2) {
            Piece pieceRoi = getPiece(p_coordonneeDebut);
            Piece pieceTour = getPiece(p_coordonneeFin);

            pieceRoi.deplacer();
            pieceTour.deplacer();

            m_echiquier.remove(p_coordonneeDebut);
            m_echiquier.remove(p_coordonneeFin);

            if (pieceRoi.getType() != Type.ROI) {
                Piece pieceTempon = pieceRoi;
                pieceTour = pieceRoi;
                pieceRoi = pieceTempon;
            }

            if (Math.abs(p_coordonneeDebut.charAt(0) - p_coordonneeFin.charAt(0)) == 2) {
                if (pieceRoi.getCouleur() == Couleur.NOIR) {
                    ajouterPiece("g8", pieceRoi);
                    ajouterPiece("f8", pieceTour);
                } else {
                    ajouterPiece("g1", pieceRoi);
                    ajouterPiece("f1", pieceTour);
                }
            } else {
                if (pieceRoi.getCouleur() == Couleur.NOIR) {
                    ajouterPiece("c8", pieceRoi);
                    ajouterPiece("d8", pieceTour);
                } else {
                    ajouterPiece("c1", pieceRoi);
                    ajouterPiece("d1", pieceTour);
                }
            }

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
    private boolean casesVide(String p_coordonneeDebut, String p_coordonneeFin) {
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

    /**
     * Vérifie si un pion est à la bonne position pour avoir une promotion
     *
     * @param p_coordonneeDebut coordonnee de debut
     * @param p_coordonneeFin   coordonne de fin
     * @return true si la promotion est possible sinon false
     */
    public boolean estPromotionPossible(String p_coordonneeDebut, String p_coordonneeFin) {
        return (getPiece(p_coordonneeDebut).getCouleur() == Couleur.NOIR && p_coordonneeFin.charAt(1) == '1') ||
                (getPiece(p_coordonneeDebut).getCouleur() == Couleur.BLANC && p_coordonneeFin.charAt(1) == '8');
    }
}
